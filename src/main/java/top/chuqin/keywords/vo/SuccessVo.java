package top.chuqin.keywords.vo;

import java.time.LocalDateTime;

public class SuccessVo {
    private boolean success;
    private String timestamp;

    public SuccessVo() {
        success = true;
        timestamp = LocalDateTime.now().toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
