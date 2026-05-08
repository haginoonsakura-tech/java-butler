package com.butler.rule;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

//星期篩選
public class DayOfWeekFilter  implements ScheduleFilter {
    private final Set<DayOfWeek> days; // 這裡我們依然用 Set，因為星期不能重複

    public DayOfWeekFilter (Set<DayOfWeek> days) {
        this.days = days;
    }

    @Override
    public boolean isSatisfied(LocalDateTime date) {
        // 如果集合是空的，代表不限制日期，直接回傳 true
        if (days == null || days.isEmpty()) {
            return true;
        }// 檢查週幾
        return days.contains(date.getDayOfWeek());
    }

    @Override
    public String getFilterDescription() {
        // 把 Set 轉成文字，例如：[MONDAY, FRIDAY]
        return "指定星期: " + days.toString();
    }
}
