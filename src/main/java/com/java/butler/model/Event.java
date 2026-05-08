package com.butler.rule;

import java.time.LocalDateTime;

public class Event {
    private final String id;
    private final String title;
    private final RecurrenceRule rule;

    public Event(String id, String title, RecurrenceRule rule) {
        this.id = id;
        this.title = title;
        this.rule = rule;
    }

    // Getter 方法：讓外部可以讀取資料
    public String getId() { return id; }
    public String getTitle() { return title; }
    public RecurrenceRule getRule() { return rule; }

    @Override
    public String toString() {
        return "事件: " + title + "(ID: " + id + ")";
    }
}