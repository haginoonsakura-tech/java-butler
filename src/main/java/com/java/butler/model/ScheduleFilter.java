package com.butler.rule;

import java.time.LocalDateTime;

/**
 * 這是「加選項目」的介面。
 * 無論是指定星期、指定日期，只要實作這個介面，就能掛載到循環規則上。
 */
public interface ScheduleFilter {
    // 傳入一個日期，回傳是否符合該過濾條件
    boolean isSatisfied(LocalDateTime date);

    // 取得過濾器的描述（例如：「每週六」、「每月1號」）
    String getFilterDescription();
}
