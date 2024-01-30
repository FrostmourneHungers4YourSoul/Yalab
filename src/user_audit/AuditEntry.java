package user_audit;

import java.time.LocalDateTime;

public class AuditEntry {
    private final LocalDateTime timestamp;
    private final String username;
    private final String action;

    public AuditEntry(String username, String action) {
        this.timestamp = LocalDateTime.now();
        this.username = username;
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }
}
