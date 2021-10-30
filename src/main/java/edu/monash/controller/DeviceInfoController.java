package edu.monash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.monash.GlobalRef;
import edu.monash.entity.DeviceInfo;
import edu.monash.service.DeviceInfoService;
import edu.monash.webservice.DeviceWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/device")
public class DeviceInfoController {

    @Autowired
    private DeviceWebService deviceWebService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @RequestMapping("/selectDevice")
    public void selectPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/device/selectDevice
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String deviceId = request.getParameter("id");
        System.out.println("req id = " + deviceId);
        System.out.println("deviceServicDeviceInfoController.java:38e " + deviceInfoService);
        DeviceInfo deviceInfo = deviceInfoService.findDeviceInfoById(deviceId);
        System.out.println("query deviceInfo = " + deviceInfo);

        ObjectMapper mapper = new ObjectMapper();

        response.getWriter().write(mapper.writeValueAsString(deviceInfo));
        response.getWriter().close();
    }

    @RequestMapping("/downloadOriginAPK")
    public void registerDevice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/device/downloadOriginAPK
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        OutputStream out = response.getOutputStream();
        File applicationAPK = new File(GlobalRef.orginAPKPath);
        response.addHeader("Content-Disposition", "attachment;filename=" + "application_"+applicationAPK.getName());
        response.setContentType("application/octet-stream");
        byte[] bytes = new byte[(int)applicationAPK.length()];
        FileInputStream is = new FileInputStream(applicationAPK);
        is.read(bytes);
        is.close();
        out.write(bytes);
    }

    @RequestMapping("/downloadConfirmVulnerabilityAPK")
    public void downloadConfirmVulnerabilityAPK(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/device/downloadOriginAPK
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        OutputStream out = response.getOutputStream();
        File applicationAPK = new File(GlobalRef.confirmVulnerabilityAPKPath);
        response.addHeader("Content-Disposition", "attachment;filename=" + "application_"+applicationAPK.getName());
        response.setContentType("application/octet-stream");
        byte[] bytes = new byte[(int)applicationAPK.length()];
        FileInputStream is = new FileInputStream(applicationAPK);
        is.read(bytes);
        is.close();
        out.write(bytes);
    }

    @RequestMapping("/downloadMonitorCrashAPK")
    public void downloadMonitorCrashAPK(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8080/RemoteTest/device/downloadMonitorCrashAPK
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        OutputStream out = response.getOutputStream();
        File applicationAPK = new File(GlobalRef.monitorCrashAPKPath);
        response.addHeader("Content-Disposition", "attachment;filename=" + "application_"+applicationAPK.getName());
        response.setContentType("application/octet-stream");
        byte[] bytes = new byte[(int)applicationAPK.length()];
        FileInputStream is = new FileInputStream(applicationAPK);
        is.read(bytes);
        is.close();
        out.write(bytes);
    }

}
