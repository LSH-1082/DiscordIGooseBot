package DAO;

import DTO.UserDTO;
import util.SECRET;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class AttendanceDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public String getStatus(String userId){
        try{
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
            String sql = "select status from users where user_id = ?";
            try{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userId);
                resultSet = preparedStatement.executeQuery();

                String stat = null;
                while(resultSet.next()){
                    stat = resultSet.getString("status");
                }
                return stat;
            }
            catch (SQLException e) {
                return "false";
            }
            finally {
                if(preparedStatement != null) preparedStatement.close();
                if(resultSet != null) resultSet.close();
                if(connection != null) connection.close();
            }
        }
        catch (SQLException e){
            return "database connection failed";
        }
    }

    public void changeStatus(String userId, String status){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        try {
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
                try {
                    if(status.equals("출근")){
                        String sql = "update users set status = ?, attendance_time = ? where user_id = ?";
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, status);
                        preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                        preparedStatement.setString(3, userId);

                        preparedStatement.executeUpdate();
                    }
                    if(status.equals("퇴근")){
                        String sql = "update users set status = ?, leave_time = ? where user_id = ?";
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, "퇴근");
                        preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                        preparedStatement.setString(3, userId);

                        preparedStatement.executeUpdate();
                    }

                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserId(resultSet.getString("user_id"));
                    userDTO.setStatus(resultSet.getString("status"));
                    userDTO.setAttendanceTime(resultSet.getTimestamp("attendance_time"));
                    userDTO.setLeaveTime(resultSet.getTimestamp("leave_time"));

                } catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                } finally {
                    if (preparedStatement != null) preparedStatement.close();
                    if (resultSet != null) resultSet.close();
                    if (connection != null) connection.close();
                }
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
