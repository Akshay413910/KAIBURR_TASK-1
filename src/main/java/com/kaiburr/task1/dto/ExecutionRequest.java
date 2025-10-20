package com.kaiburr.task1.dto;

public class ExecutionRequest {
    private String command;
    public ExecutionRequest() {}
    public ExecutionRequest(String command) { this.command = command; }
    public String getCommand() { return command; }
    public void setCommand(String command) { this.command = command; }
}
