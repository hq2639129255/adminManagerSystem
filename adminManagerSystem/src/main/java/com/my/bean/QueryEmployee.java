package com.my.bean;

public class QueryEmployee {
    private int eid;
    private int jobid;
    private String ename;

    public QueryEmployee(int eid, int jobid, String ename) {
        this.eid = eid;
        this.jobid = jobid;
        this.ename = ename;
    }

    public QueryEmployee() {
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
