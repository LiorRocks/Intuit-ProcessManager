package com.intuit.processmanager.model;

public class Process {
    private String pid;
    private PriorityEnum priority;
    private boolean isRunning;

    public Process() {
    }

    public Process(String pid, PriorityEnum priority) {
        this.pid = pid;
        this.priority = priority;
        this.isRunning = true;
    }

    public Process(String pid, PriorityEnum priority, boolean isRunning) {
        this.pid = pid;
        this.priority = priority;
        this.isRunning = isRunning;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

}
