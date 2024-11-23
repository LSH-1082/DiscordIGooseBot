package DTO;

import java.sql.Timestamp;

public class WorkTimeDTO {
    private String employeeUserId;
    private Timestamp attendanceTime;
    private Timestamp leaveTime;

    public String getEmployeeUserId() {
        return this.employeeUserId;
    }

    public Timestamp getAttendanceTime() {
        return this.attendanceTime;
    }

    public Timestamp getLeaveTime() {
        return this.leaveTime;
    }

    public void setEmployeeUserId(String employeeUserId) {
        this.employeeUserId = employeeUserId;
    }

    public void setAttendanceTime(Timestamp attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }
}
