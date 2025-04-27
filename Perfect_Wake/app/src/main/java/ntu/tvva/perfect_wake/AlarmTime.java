package ntu.tvva.perfect_wake;

public class AlarmTime {
    private String time;     // Giờ thức dậy (ví dụ: 04:45)
    private String detail;   // Thông tin chi tiết (ví dụ: 3 cycles - 4 hours 45 minutes)
    private boolean active;  // Trạng thái bật/tắt báo thức

    public AlarmTime(String time, String detail) {
        this.time = time;
        this.detail = detail;
        this.active = false;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}