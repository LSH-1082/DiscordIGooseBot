package DTO;

import java.sql.Timestamp;

public class WorkTimeDTO {
    private String userId;
    private Timestamp attendanceTime;
    private Timestamp leaveTime;

    public String getUserId() {
        return this.userId;
    }

    public Timestamp getAttendanceTime() {
        return this.attendanceTime;
    }

    public Timestamp getLeaveTime() {
        return this.leaveTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAttendanceTime(Timestamp attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }
}
