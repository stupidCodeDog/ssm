package com.bigshow.ssm.entity;

public class Department {
    private Integer depId;

    private String deptName;

    public Department() {
    }

    public Department(Integer depId, String deptName) {
        this.depId = depId;
        this.deptName = deptName;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + depId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}