package DTO;

import java.sql.Timestamp;

public class UserDTO {
    private String userId;
    private String status;
    private Timestamp attendanceTime;
    private Timestamp leaveTime;

    public String getUserId() {
        return this.userId;
    }

    public String getStatus() {
        return this.status;
    }

    public Timestamp getAttendanceTime(){
        return this.attendanceTime;
    }

    public Timestamp getLeaveTime(){
        return this.leaveTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAttendanceTime(Timestamp attendanceTime){
        this.attendanceTime = attendanceTime;
    }

    public void setLeaveTime(Timestamp leaveTime){
        this.leaveTime = leaveTime;
    }
}


