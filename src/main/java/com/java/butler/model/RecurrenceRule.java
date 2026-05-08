package com.butler.rule;

import java.time.LocalDateTime;

//介面: 重複規則
public interface RecurrenceRule {
    // 核心功能：判斷給定的日期是否符合此規則
    boolean shouldOccur(LocalDateTime date);
    boolean shouldDisplayInNext7Days(LocalDateTime today);

    LocalDateTime nextOccurrenceAfter(LocalDateTime lastTime); // 固定合約：一定要能算出「下一次發生時間」
    String getDescription();                                   // 取得規則的文字描述（例如：中藥回診）
    String getStatusMessage();                                 // 取得規則的狀態訊息（例如：?）
}

