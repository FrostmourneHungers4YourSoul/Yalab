package user_audit;

import java.util.ArrayList;
import java.util.List;

public class AuditLogService {
    private static final List<AuditEntry> auditLog = new ArrayList<>();

    public static void logEntry(String username, String action) {
        AuditEntry entry = new AuditEntry(username, action);
        auditLog.add(entry);
    }

    public static void displayAuditLog() {
        System.out.println("Audit Log:");
        for (AuditEntry entry : auditLog) {
            System.out.println(entry.getTimestamp() + " - " + entry.getUsername() + ": " + entry.getAction());
        }
    }
}
