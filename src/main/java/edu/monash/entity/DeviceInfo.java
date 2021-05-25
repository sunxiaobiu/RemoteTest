package edu.monash.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class DeviceInfo {

    private int id;
    private int deviceId;
    private Date createTime;
    private String sdkVersion;
    private String releaseVersion;
    private String deviceModel;
    private String brand;
    private String host;
    private String deviceName;
    private String hardwareName;
    private String language;
    private String screenSize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getHardwareName() {
        return hardwareName;
    }

    public void setHardwareName(String hardwareName) {
        this.hardwareName = hardwareName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public static DeviceInfo convert2DeviceInfo(JSONObject jsonObject){
        DeviceInfo deviceInfo = new DeviceInfo();

        deviceInfo.setSdkVersion(jsonObject.get("id").toString());
        deviceInfo.setSdkVersion(jsonObject.get("deviceId").toString());
        deviceInfo.setSdkVersion(jsonObject.get("sdkVersion").toString());
        deviceInfo.setSdkVersion(jsonObject.get("releaseVersion").toString());
        deviceInfo.setSdkVersion(jsonObject.get("deviceModel").toString());
        deviceInfo.setSdkVersion(jsonObject.get("brand").toString());
        deviceInfo.setSdkVersion(jsonObject.get("host").toString());
        deviceInfo.setSdkVersion(jsonObject.get("deviceName").toString());
        deviceInfo.setSdkVersion(jsonObject.get("hardwareName").toString());
        deviceInfo.setSdkVersion(jsonObject.get("language").toString());
        deviceInfo.setSdkVersion(jsonObject.get("screenSize").toString());

        return deviceInfo;
    }
}
