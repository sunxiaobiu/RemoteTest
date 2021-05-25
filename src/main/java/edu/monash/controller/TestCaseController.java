package edu.monash.controller;

import com.alibaba.fastjson.JSONObject;
import edu.monash.GlobalRef;
import edu.monash.entity.DeviceInfo;
import edu.monash.entity.TestCase;
import edu.monash.service.DeviceInfoService;
import edu.monash.service.TestCaseService;
import edu.monash.util.ExceptionUtil;
import edu.monash.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/testCase")
public class TestCaseController {
    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private TestCaseService testCaseService;

    @RequestMapping("/generateAPK")
    public void patchRun(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        // URL: http://localhost:8081/RemoteTest/testCase/generateAPK
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //insert deviceInfo
        JSONObject deviceInfoJson = (JSONObject)JSONObject.parse(request.getParameter("deviceInfo"));
        DeviceInfo deviceInfo = DeviceInfo.convert2DeviceInfo(deviceInfoJson);
        DeviceInfo existDevice = deviceInfoService.findDeviceInfoById(deviceInfo.getDeviceId());
        if(existDevice == null){
            deviceInfoService.insertDeviceInfo(deviceInfo);
        }

        //collect test cases
        Integer testCaseNumber = Integer.parseInt(request.getParameter("testCaseNumber"));
        ExceptionUtil.runtimeExpWithNullCheck(testCaseNumber, "Test Case Number should be greater than 0");
        List<TestCase> testCaseList = testCaseService.selectList(testCaseNumber);

        //copy testcases from original path to destination
        for(TestCase testCase : testCaseList){
            FileUtil.copyFile(GlobalRef.testCaseOriginPath, GlobalRef.testCaseDestinationPath, testCase.getName()+".java");
        }

        //generate instrumented apk
        String command = "./gradlew assembleAndroidTest -DtestBuildType=debug";
        Process process = Runtime.getRuntime().exec(command, null, new File(GlobalRef.generateInstrumentAPKPath));
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            ExceptionUtil.runtimeExp("Generate instrumented apk fail!");
        }

        //output instrumented apk
        OutputStream out = response.getOutputStream();
        File instrumentAPK = new File(GlobalRef.instrumentAPKPath);
        response.addHeader("Content-Disposition", "attachment;filename=" + instrumentAPK.getName());
        response.setContentType("application/octet-stream");
        byte[] bytes = new byte[(int)instrumentAPK.length()];
        FileInputStream is = new FileInputStream(instrumentAPK);
        is.read(bytes);
        is.close();
        out.write(bytes);

    }
}
