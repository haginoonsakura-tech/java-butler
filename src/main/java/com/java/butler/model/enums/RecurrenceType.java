package com.butler.rule;

public enum RecurrenceType {
    DAILY("每天"),
    WEEKLY("每週"),
    MONTHLY("每月"),
    YEARLY("每年"),
    ONCE("一次性");

    private final String chineseDescription;

    RecurrenceType(String chineseDescription) {
        this.chineseDescription = chineseDescription;
    }

    public String getStatusMessage() {
        return chineseDescription;
    }
}
