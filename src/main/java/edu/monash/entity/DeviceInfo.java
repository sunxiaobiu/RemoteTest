package edu.monash.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class DeviceInfo {

    private int id;
    private String deviceId;
    private Date createTime;
    private Date updateTime;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

        deviceInfo.setDeviceId(jsonObject.get("deviceId").toString());
        deviceInfo.setSdkVersion(jsonObject.get("sdkVersion").toString());
        deviceInfo.setReleaseVersion(jsonObject.get("releaseVersion").toString());
        deviceInfo.setDeviceModel(jsonObject.get("deviceModel").toString());
        deviceInfo.setBrand(jsonObject.get("brand").toString());
        deviceInfo.setHost(jsonObject.get("host").toString());
        deviceInfo.setDeviceName(jsonObject.get("deviceName").toString());
        deviceInfo.setHardwareName(jsonObject.get("hardware").toString());
        deviceInfo.setLanguage(jsonObject.get("language").toString());
        deviceInfo.setScreenSize(jsonObject.get("screenSize").toString());

        return deviceInfo;
    }

    public boolean equals(Object object){
        if(!(object instanceof DeviceInfo)){
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo)object;
        if(!this.sdkVersion.equals(deviceInfo.getSdkVersion())){
            return false;
        }
        if(!this.releaseVersion.equals(deviceInfo.getReleaseVersion())){
            return false;
        }
        if(!this.deviceModel.equals(deviceInfo.getDeviceModel())){
            return false;
        }
        if(!this.brand.equals(deviceInfo.getBrand())){
            return false;
        }
        if(!this.host.equals(deviceInfo.getHost())){
            return false;
        }
        if(!this.deviceName.equals(deviceInfo.getDeviceName())){
            return false;
        }
        if(!this.hardwareName.equals(deviceInfo.getHardwareName())){
            return false;
        }
        if(!this.screenSize.equals(deviceInfo.getScreenSize())){
            return false;
        }
        return true;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id:").append(this.id).append(";")
                .append("deviceId:").append(this.deviceId).append(";")
                .append("createTime:").append(this.createTime).append(";")
                .append("updateTime:").append(this.updateTime).append(";")
                .append("sdkVersion:").append(this.sdkVersion).append(";")
                .append("releaseVersion:").append(this.releaseVersion).append(";")
                .append("deviceModel:").append(this.deviceModel).append(";")
                .append("brand:").append(this.brand).append(";")
                .append("host:").append(this.host).append(";")
                .append("deviceName:").append(this.deviceName).append(";")
                .append("hardwareName:").append(this.hardwareName).append(";")
                .append("language:").append(this.language).append(";")
                .append("screenSize:").append(this.screenSize).append(";");
        return stringBuilder.toString();
    }

}
