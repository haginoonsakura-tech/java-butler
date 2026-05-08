package com.butler.rule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 循環規則類別：處理「每隔 X 天/週/月/年」的邏輯。
 * 不再直接持有 Set<DayOfWeek>，避免泛型衝突。
 */
public class RecurringRule extends AbstractRecurrenceRule {

    private final int interval;            // 間隔 (例如："2"日)
    private final ChronoUnit unit;         // 單位 (例如：2"週" =WEEKS)
    private final LocalDateTime startDate; // 起始計算點

    // 【簡化建構子】只收基礎循環參數，不再收 Set 集合
    public RecurringRule(RecurrenceType type, int interval, ChronoUnit unit, LocalDateTime startDate, String description) {
        super(type, description);
        this.interval = interval;
        this.unit = unit;
        this.startDate = startDate;
    }

    /**
     * 計算「下一次發生時間」的核心邏輯
     * 這是繼承 AbstractRecurrenceRule 必須實作的方法。
     */
    @Override
    public LocalDateTime nextOccurrenceAfter(LocalDateTime lastTime) {
        // 1. 起始點設為上次時間 (lastTime)
        LocalDateTime next = lastTime;

        // 2.【關鍵修正】先檢查「今天以後」的第一個符合 Filter 的日子，而不是先加一個月
        while (true) {
            // 往後找 1 天
            next = next.plus(1, ChronoUnit.DAYS);

            // 檢查是否符合 Filter (9號或12號)
            // 檢查是否符合基礎週期 (每 X 個月)
            if (shouldOccur(next) && next.isAfter(lastTime)) {
                return next;
            }

            // 安全機制：避免找太遠（例如找超過 10 年就停下）
            if (next.isAfter(lastTime.plusYears(10))) break;
        }
        return null;
    }

    /**
     * 實作介面的核心判斷方法
     */
    @Override
    public boolean shouldOccur(LocalDateTime date) {
        // 1. 基礎檢查：是否正好落在循環週期點上？
        long amountBetween = startDate.until(date, unit);
        if (amountBetween < 0 || amountBetween % interval != 0) {
            return false;
        }

        // 2. 附加檢查：是否通過所有「加選項目」(Filters)？
        return checkFilters(date);
    }
}