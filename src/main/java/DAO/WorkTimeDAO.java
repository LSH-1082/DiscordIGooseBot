package DAO;

import DTO.WorkTimeDTO;
import util.SECRET;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class WorkTimeDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void insertAttendanceTime(String userId){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        try {
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
                try {
                    String sql = "INSERT INTO work_time(employee_user_id, attendance_time, leave_time) VALUES(?, ?, ?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, userId);
                    preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                    preparedStatement.setTimestamp(3, null);

                    preparedStatement.executeUpdate();
                }
                catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                }
                finally {
                    if (preparedStatement != null) preparedStatement.close();
                    if (connection != null) connection.close();
                }
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void updateLeaveTime(String userId){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        try {
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
            try {
                String sql = "UPDATE work_time SET leave_time = ? WHERE employee_user_id=? AND leave_time IS NULL";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setString(2, userId);

                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
            finally {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            }
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public WorkTimeDTO getWorkTime(String userId){
        WorkTimeDTO workTimeDTO = new WorkTimeDTO();
        try {
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
            try {
                String sql = "SELECT * FROM work_time WHERE employee_user_id=? ORDER BY attendance_time DESC LIMIT 1";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userId);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    workTimeDTO.setAttendanceTime(resultSet.getTimestamp("attendance_time"));
                    workTimeDTO.setLeaveTime(resultSet.getTimestamp("leave_time"));
                }
                else return null;

                return workTimeDTO;
            }
            catch (SQLException e) {
                return null;
            }
            finally {
                if (preparedStatement != null) preparedStatement.close();
                if (resultSet != null) resultSet.close();
                if (connection != null) connection.close();
            }
        }
        catch (SQLException e){
            return null;
        }
    }
}
