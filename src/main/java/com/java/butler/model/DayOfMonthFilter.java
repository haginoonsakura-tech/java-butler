package com.butler.rule;


import java.time.LocalDateTime;
import java.util.Set;

//日期篩選
public class DayOfMonthFilter implements ScheduleFilter {
    private final Set<Integer>   days; // 存放 1~31 的數字

    public DayOfMonthFilter(Set<Integer>   days) {
        this.days = days;
    }

    @Override
    public boolean isSatisfied(LocalDateTime date) {
        // 如果集合是空的，代表不限制日期，直接回傳 true
        if (days == null || days.isEmpty()) {
            return true;
        }// 檢查幾號
        return days.contains(date.getDayOfMonth());
    }

    @Override
    public String getFilterDescription() {
        // 把 Set 轉成文字，例如：[15號]
        return "指定日期: " + days.toString() + " 號";
    }
}
