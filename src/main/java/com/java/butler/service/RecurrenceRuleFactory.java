package com.butler.rule;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class RecurrenceRuleFactory {
    // 1. 建立「指定星期」的循環規則 (例如：每週二、四)
    public static RecurrenceRule createWeeklyRule(int interval,
                                                  Set<DayOfWeek> days,
                                                  String desc) {
        RecurringRule rule = new RecurringRule(RecurrenceType.WEEKLY,
                                               interval,
                                               ChronoUnit.WEEKS,
                                               LocalDateTime.now(),
                                               desc);
        rule.addFilter(new DayOfWeekFilter(days)); // 掛載星期過濾器
        return rule;
    }

    // 2. 建立「指定日期」的循環規則 (例如：每月 5、20 號)
    public static RecurrenceRule createMonthlyRule(int interval,
                                                   Set<Integer> days,
                                                   String desc) {
        RecurringRule rule = new RecurringRule(RecurrenceType.MONTHLY,
                                               interval,
                                               ChronoUnit.MONTHS,
                                               LocalDateTime.now(),
                                               desc);
        rule.addFilter(new DayOfMonthFilter(days)); // 掛載日期過濾器
        return rule;
    }

    // 3. 建立「一次性」規則
    public static RecurrenceRule createOnceRule(LocalDateTime date,
                                                String desc) {
        return new OneTimeRule(date, desc);
    }
}