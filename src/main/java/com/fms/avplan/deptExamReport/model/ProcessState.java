package com.fms.avplan.deptExamReport.model;

public class ProcessState {

    public static final String KEY = "_partStatus";
    public static final String SUCCESS = "success";
    public static final String INFO = "info";
    public static final String DANGER = "danger";
    public static final String DEFAULT = "default";

    public ProcessState(String type){
        this.type = type;
    }

    public ProcessState(String type,String title){
        this.type = type;
        this.title = title;
    }

    public ProcessState(){
        type = "default";

    }

    private String type;

    private String title;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void set(String type,String title){
        this.type = type;
        this.title = title;
    }
}
