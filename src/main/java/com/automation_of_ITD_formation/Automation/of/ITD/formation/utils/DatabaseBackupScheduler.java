package com.automation_of_ITD_formation.Automation.of.ITD.formation.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatabaseBackupScheduler {
    @Scheduled(cron = "0 0 0 */3 * ?")
    public void backupDatabase() {
        String dbName = "ITD_db";
        String dbUser = "root";
        String dbPassword = "root";
        String backupPath = "C:\\backups\\";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String backupFileName = "backup_" + sdf.format(new Date()) + ".sql";
        String mysqldumpPath = "C:\\Program Files\\MySQL\\MySQL Server 9.1\\bin\\mysqldump";

        String command = String.format("%s --column-statistics=0 -u%s -p%s --databases %s -r %s",
                mysqldumpPath, dbUser, dbPassword, dbName, backupPath + backupFileName);

        try {
            File backupDir = new File(backupPath);
            if (!backupDir.exists()) {
                backupDir.mkdirs();
            }

            Process process = Runtime.getRuntime().exec(command);
            int processComplete = process.waitFor();

            if (processComplete == 0) {
                System.out.println("Резервная копия успешно создана: " + backupPath + backupFileName);
            } else {
                System.err.println("Ошибка при создании резервной копии.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}