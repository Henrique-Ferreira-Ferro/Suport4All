package com.io.Suport4All.dto;

import com.io.Suport4All.enums.NetworkCommandType;

public class NetworkCommandRequest {
    private NetworkCommandType command;
    private String host;
    private Integer count;

    public NetworkCommandType getCommand() {
        return command;
    }

    public void setCommand(NetworkCommandType command) {
        this.command = command;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}


