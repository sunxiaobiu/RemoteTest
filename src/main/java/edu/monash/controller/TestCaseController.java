package edu.monash.controller;

import com.google.gson.Gson;
import edu.monash.GlobalRef;
import edu.monash.entity.DeviceInfo;
import edu.monash.entity.DispatchStrategy;
import edu.monash.entity.TestCase;
import edu.monash.entity.TestRunner;
import edu.monash.util.FileUtil;
import edu.monash.util.SocketClient;
import edu.monash.webservice.DeviceWebService;
import edu.monash.webservice.DispatchStrategyWebService;
import edu.monash.webservice.TestCaseWebService;
import edu.monash.webservice.TestRunnerWebService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/testCase")
public class TestCaseController {
    private static final Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    private static String aospFrameworkPrefix = "AOSP_F_";
    private static String junitTestGenPrefix = "TestCase_";

    @Autowired
    private DeviceWebService deviceWebService;

    @Autowired
    private TestRunnerWebService testRunnerWebService;

    @Autowired
    private TestCaseWebService testCaseWebService;

    @Autowired
    private DispatchStrategyWebService dispatchStrategyWebService;

    @RequestMapping("/generatePatchAPK")
    public void generateAPK(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        // URL: http://localhost:8081/RemoteTest/testCase/generateAPK
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        logger.info("[deviceInfo] deviceInfo========");

        //step1. insert deviceInfo and dispatchStrategy
        DeviceInfo deviceInfo = deviceWebService.addDevice(request.getParameter("deviceInfo"));
        DispatchStrategy dispatchStrategy = dispatchStrategyWebService.addDispatchStrategy(request.getParameter("dispatchStrategy"));

        logger.info("[deviceInfo] deviceInfo:" + deviceInfo.toString());

        //step2. collect test cases
        //List<TestCase> testCaseList = testCaseWebService.getNotYetExecutedTestCases(deviceInfo.getDeviceId(), dispatchStrategy);
        List<TestCase> testCaseList = testCaseWebService.getTestsFromStartId2EndId(dispatchStrategy.getStartId(), dispatchStrategy.getEndId());

        if (CollectionUtils.isNotEmpty(testCaseList)) {
            for (TestCase testCase : testCaseList) {
                logger.info("[testCaseList] testCaseList:" + testCase.getName());
            }
        }

        //step4. copy testcases from original path to destination
        copyTestCases2TargetPath(testCaseList);

        //step5. generate apk patch in /Users/xsun0035/workspace/monash/BasicUnitAndroidTest
        SocketClient.chmod777(GlobalRef.apkBuildPath);
        SocketClient.generateAPKPatch();

        //step6. output instrumented apk
        OutputStream out = response.getOutputStream();
        File patchAPK = new File(GlobalRef.patchAPKPath);
        response.addHeader("Content-Disposition", "attachment;filename=" + patchAPK.getName());
        response.setContentType("application/octet-stream");
        byte[] bytes = new byte[(int) patchAPK.length()];
        FileInputStream is = new FileInputStream(patchAPK);
        is.read(bytes);
        is.close();
        out.write(bytes);

    }

    @RequestMapping("/collectExecutedTests")
    public void collectExecutedTests(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/testCase/collectExecutedTests
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        DeviceInfo deviceInfo = deviceWebService.getDeviceById(request.getParameter("deviceId"));

        //collect Executed Tests
        List<String> testCaseIds = testRunnerWebService.getExecutedTestsByDeviceIdAndDispatchStrategy(request.getParameter("deviceId"), deviceInfo.getDispatchStrategy());

        PrintWriter out = response.getWriter();
        String resJson = new Gson().toJson(testCaseIds);
        response.setContentType("application/json");
        out.print(resJson);
        out.flush();
    }

    @RequestMapping("/collectBatchTests")
    public void collectBatchTests(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/testCase/collectExecutedTests
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        DeviceInfo deviceInfo = deviceWebService.getDeviceById(request.getParameter("deviceId"));
        DispatchStrategy dispatchStrategy = dispatchStrategyWebService.selectLatestBatch(deviceInfo.getDeviceId(), deviceInfo.getDispatchStrategy());

        List<String> testCaseIds = testCaseWebService.getBatchTests(deviceInfo.getDeviceId(), dispatchStrategy);

        PrintWriter out = response.getWriter();
        String resJson = new Gson().toJson(testCaseIds);
        response.setContentType("application/json");
        out.print(resJson);
        out.flush();
    }

    @RequestMapping("/checkIfCrash")
    public void checkIfCrash(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/testCase/collectExecutedTests
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        DeviceInfo deviceInfo = deviceWebService.getDeviceById(request.getParameter("deviceId"));
        int latestId = testRunnerWebService.getLatestExecutedTestCaseId(deviceInfo.getDeviceId(), deviceInfo.getDispatchStrategy());

        int remainder = latestId % deviceInfo.getDispatchStrategy();

        PrintWriter out = response.getWriter();
        String resJson = new Gson().toJson(remainder);
        response.setContentType("application/json");
        out.print(resJson);
        out.flush();
    }

    @RequestMapping("/checkDispatchStrategy")
    public void checkDispatchStrategy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/testCase/collectRes
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

//        int dispatchStrategy = Integer.valueOf(request.getParameter("dispatchStrategy"));
//
//        return testRunnerWebService.existTestRunnerForStrategy(request.getParameter("deviceId"), dispatchStrategy);
        DeviceInfo deviceInfo = deviceWebService.getDeviceById(request.getParameter("deviceId"));

        PrintWriter out = response.getWriter();
        String resJson = new Gson().toJson(deviceInfo.getDispatchStrategy());
        response.setContentType("application/json");
        out.print(resJson);
        out.flush();
    }

    @RequestMapping("/updateDevice")
    public void updateDevice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/testCase/collectRes
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        deviceWebService.updateDispatchStrategy(request.getParameter("deviceInfo"));
    }

    @RequestMapping("/collectRes")
    public void collectRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/testCase/collectRes
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //step1. add testRunnerRecord
        testRunnerWebService.addRecord(request.getParameter("testCaseRecord"), request.getParameter("deviceId"));

    }

    private void copyTestCases2TargetPath(List<TestCase> dispatchTestCases) throws IOException {
        SocketClient.chmod777(GlobalRef.jUnitTestGenTestCaseDestinationPath);
        SocketClient.chmod777(GlobalRef.aospFrameworkTestCaseDestinationPath);

        cleanExistTestCase();

        for (TestCase testCase : dispatchTestCases) {
            System.out.println("start copy:"+testCase);
            try {
                //Dispatch tests of different origin
//                if (testCase.getName().startsWith(aospFrameworkPrefix)) {
//                    testCase.setName(testCase.getName().replace(aospFrameworkPrefix, ""));
//                    File testFile = new File(GlobalRef.aospFrameworkTestCaseOriginPath+testCase.getName()+".java");
//                    String line = "";
//                    BufferedReader br = new BufferedReader(new FileReader(testFile));
//                    String packagePath = "";
//
//                    while ((line = br.readLine()) != null) {
//                        if (line.startsWith("package")) {
//                            packagePath = line.replaceAll("package ", "").replace(";", "").replace(".", "/");
//                            break;
//                        }
//                    }
//                    File theDir = new File(GlobalRef.aospFrameworkTestCaseDestinationPath + packagePath);
//                    if (!theDir.exists()) {
//                        System.out.println("mkdir:"+theDir);
//                        theDir.mkdirs();
//                    }
//
//                    FileUtils.copyFileToDirectory(new File(GlobalRef.aospFrameworkTestCaseOriginPath + testCase.getName() + ".java"), theDir);
//                    System.out.println("copyFile aospFramework success:"+GlobalRef.aospFrameworkTestCaseDestinationPath);
//
//                } else if (testCase.getName().startsWith(junitTestGenPrefix)) {
                    FileUtil.copyFile(GlobalRef.jUnitTestGenTestCaseOriginPath, GlobalRef.jUnitTestGenTestCaseDestinationPath, testCase.getName() + ".java");
                    System.out.println("copyFile junitTestGen success:"+testCase.getName());
//                }
            } catch (FileNotFoundException e) {
                logger.info("[Test Case File not found] Skip test case:" + testCase.getName());
                continue;
            }
        }
    }

    private void cleanExistTestCase() throws IOException {
        FileUtils.cleanDirectory(new File(GlobalRef.jUnitTestGenTestCaseDestinationPath));

        List<String> aospTests = new ArrayList<>();
        FileUtil.getFileNameList(GlobalRef.aospFrameworkTestCaseOriginPath, aospTests);
        List<String> tinkerExistTests = new ArrayList<>();
        FileUtil.getTestFileList(GlobalRef.aospFrameworkTestCaseDestinationPath, tinkerExistTests);
        for (String fileName : tinkerExistTests) {
            //check if it is real Test Case in /home/ubuntu/AOSP_framework_tests/
            File existTest = new File(fileName);
            if(aospTests.contains(existTest.getName())){
                existTest.delete();
                System.out.println("success:"+fileName);
            }
        }
    }
}
