package com.butler.rule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class FileService {
    // 把目前的任務清單存成一個文字檔
    public static void saveTasksToFile(Collection<Event> events, String fileName) {
        // 使用 try-with-resources (教材重點：會自動關閉檔案，不用寫 finally)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Event event : events) {
                // 將任務資訊拼成一行：ID,標題,描述
                String line = String.format("%s,%s,%s",
                        event.getId(), event.getTitle(), event.getRule().getDescription());
                writer.write(line);
                writer.newLine(); // 換行
            }
            System.out.println("✅ 任務已成功備份至 " + fileName);
        } catch (IOException e) {
            // 例外處理練習：萬一硬碟沒空間或權限不足
            System.err.println("❌ 儲存檔案時發生錯誤: " + e.getMessage());
        }
    }
}
