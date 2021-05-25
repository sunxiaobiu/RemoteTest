package edu.monash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.monash.entity.DeviceInfo;
import edu.monash.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/device")
public class DeviceInfoController {
    @Autowired
    private DeviceInfoService deviceInfoService;

    @RequestMapping("/selectDevice")
    public void selectPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // URL: http://localhost:8081/RemoteTest/device/selectDevice
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int deviceId = Integer.parseInt(request.getParameter("id"));
        System.out.println("req id = " + deviceId);
        System.out.println("deviceService " + deviceInfoService);
        DeviceInfo deviceInfo = deviceInfoService.findDeviceInfoById(deviceId);
        System.out.println("query deviceInfo = " + deviceInfo);

        ObjectMapper mapper = new ObjectMapper();

        response.getWriter().write(mapper.writeValueAsString(deviceInfo));
        response.getWriter().close();
    }

}
