package com.butler.rule;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.*;

/**
 * EventRepository 類別：負責管理（儲存、查詢）所有的事件。
 * 這裡應用了教材中的 Map 集合練習。
 */
public class EventRepository {
    // 使用 Map：Key 是字串 ID，Value 是 Event 物件
    private final Map<String, Event> events = new LinkedHashMap<>();

    public EventRepository() {
        loadInitialEvents();     // 初始化時自動載入預設資料
    }

    /**
     * 初始化資料：調用工廠 (Factory) 來建立規則，避免參數錯誤。
     */
    private void loadInitialEvents() {
        System.out.println("DEBUG: 正在載入初始資料..."); // 加入這行測試

        // 1. 每月美甲 (每月 1 號)
        addEvent("nail", "美甲清單",
                RecurrenceRuleFactory.createMonthlyRule(1, Set.of(1), "預約美甲"));

        // 2. 4/11中藥回診 (每 2 週一次，週六)
        addEvent("medicine", "中藥回診",
                RecurrenceRuleFactory.createWeeklyRule(2, Set.of(DayOfWeek.SATURDAY), "中藥回診"));

        // 3. JAVA 練習 (每週一、三、五)
        // 使用 Set.of 快速建立集合
        addEvent("java", "JAVA 練習",
                RecurrenceRuleFactory.createWeeklyRule(1, Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY), "集合框架複習"));

        // 4. 定期定額 (每月 9 號、12 號)
        addEvent("payment", "定期定額扣款",
                RecurrenceRuleFactory.createMonthlyRule(1, Set.of(9, 12), "銀行自動扣款"));

        // 測試用：每天執行的任務，保證明天一定會出現
        addEvent("test", "喝水測試",
                RecurrenceRuleFactory.createOnceRule( LocalDateTime.now().plusDays(1),"明天的重要任務"));
    }

    /**
     * 輔助方法：將事件放入 Map 中
     */
    private void addEvent(String id, String title, RecurrenceRule rule) {
        events.put(id, new Event(id, title, rule));
    }

    /**
     * 取得所有事件：將 Map 的 values 轉為 Collection (對應 Main 的需求)
     */
    public Collection<Event> getAllEvents() {
        return events.values();
    }

    /**
     * 透過 ID 快速查詢單一事件 (Map 的優勢)
     */
    public Event getEventById(String id) {
        return events.get(id);
    }
}