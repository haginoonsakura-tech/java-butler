package com.butler.rule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRecurrenceRule implements RecurrenceRule {
    protected final String description;
    protected final RecurrenceType type;

    // 【新增】用來存放「加選項目」的清單 (List 練習)
    protected List<ScheduleFilter> filters = new ArrayList<>();

    // 建構子（同時接收 type 和 description）
    public AbstractRecurrenceRule(RecurrenceType type, String description) {
        this.type = type;
        this.description = description;
    }

    // 【新增】提供一個方法，讓外部可以「加裝」過濾條件
    public void addFilter(ScheduleFilter filter) {
        this.filters.add(filter);
    }

    @Override
    public String getDescription()   { return description; }

    @Override
    public String getStatusMessage() { return type.getStatusMessage();
    }

    // 【重要】判斷未來 7 天內是否發生的邏輯
    public boolean shouldDisplayInNext7Days(LocalDateTime today) {
        LocalDateTime nextTime = nextOccurrenceAfter(today);
        if (nextTime == null) return false;

        // 計算今天到下次發生日期之間差幾天
        long daysUntilNext = ChronoUnit.DAYS.between(today.toLocalDate(), nextTime.toLocalDate());

        // 核心邏輯：在 0~7 天內，且必須通過所有「加選過濾器」的檢查
        return daysUntilNext >= 0 && daysUntilNext <= 7 && checkFilters(nextTime);
    }

    /**
     * 【核心改寫】
     * 這裡實作一個通用的過濾邏輯：
     * 遍歷所有的 filters，只要有一個不符合 (isSatisfied 為 false)，
     * 這個日期就不會觸發提醒。
     */
    protected boolean checkFilters(LocalDateTime date) {
        for (ScheduleFilter filter : filters) {
            if (!filter.isSatisfied(date)) return false;
        }
        return true; // 如果沒有過濾器，或是全部都通過，就回傳 true
    }

    // 強迫子類別（Recurring, OneTime）一定要寫出如何計算「下一次發生時間」
    @Override
    public abstract LocalDateTime nextOccurrenceAfter(LocalDateTime lastTime);
}