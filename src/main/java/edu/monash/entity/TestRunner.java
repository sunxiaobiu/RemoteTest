package edu.monash.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class TestRunner {

    private int id;
    private String testCaseId;
    private String deviceId;
    private Date createTime;
    private Date updateTime;
    private boolean isSuccess;
    private String result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
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

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static TestRunner convert2TestRunner(JSONObject jsonObject){
        TestRunner testRunner = new TestRunner();

        testRunner.setTestCaseId(jsonObject.get("testCaseName").toString());
        testRunner.setSuccess(Boolean.valueOf(jsonObject.get("isSuccess").toString()));
        testRunner.setResult(jsonObject.get("result").toString());

        return testRunner;
    }

}
