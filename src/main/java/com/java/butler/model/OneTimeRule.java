package com.butler.rule;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 單次（一次性）事件規則
 * 例如：臨時運動、臨時推拿、只做一次的事
 */
public class OneTimeRule extends AbstractRecurrenceRule {
    private boolean isCompleted = false;
    private final LocalDateTime targetDate;

    // 建構子：指定日期時間
    public OneTimeRule(LocalDateTime targetDate, String description) {
        super(RecurrenceType.ONCE, description);
        this.targetDate = targetDate;
    }

    @Override
    public LocalDateTime nextOccurrenceAfter(LocalDateTime lastTime) {
        return targetDate;
    }

    @Override
    public String getStatusMessage() {
        if (this.isCompleted) {
            return "任務狀態：[已完成]";
        }
        return super.getStatusMessage(); // 回傳原本的類型訊息
    }

    @Override
    public boolean shouldOccur(LocalDateTime date) {
        // 這裡只能回傳 true 或 false！

        // 1. 如果已經完成了，就絕對不會再發生 (回傳 false)
        if (this.isCompleted) {
            return false;
        }

        // 2. 判斷傳進來的日期是不是跟我們設定的目標日期同一天
        // targetDate 是你在建構子存起來的那個時間
        return targetDate.toLocalDate().equals(date.toLocalDate());
    }
}