package edu.monash.entity;

import java.util.Objects;

public class DeviceGroup {

    private String sdkVersion;
    private String releaseVersion;
    private String deviceModel;
    private String brand;

    @Override
    public int hashCode() {
        return Objects.hash(sdkVersion, releaseVersion, deviceModel, brand);
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof DeviceGroup)){
            return false;
        }
        DeviceGroup deviceGroup = (DeviceGroup)object;
        if(!deviceGroup.sdkVersion.equals(this.sdkVersion)){
            return false;
        }
        if(!deviceGroup.releaseVersion.equals(this.releaseVersion)){
            return false;
        }
        if(!deviceGroup.deviceModel.equals(this.deviceModel)){
            return false;
        }
        if(!deviceGroup.brand.equals(this.brand)){
            return false;
        }
        return true;
    }

    public DeviceGroup(String sdkVersion, String releaseVersion, String deviceModel, String brand){
        this.sdkVersion = sdkVersion;
        this.releaseVersion = releaseVersion;
        this.deviceModel = deviceModel;
        this.brand = brand;
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
}
