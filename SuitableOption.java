package com.example.mudepartmentchoice;

import java.io.Serializable;

public class SuitableOption implements Serializable {
    private String name;
    private String reason;

    public SuitableOption(String name, String reason) {
        this.name = name;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }
}