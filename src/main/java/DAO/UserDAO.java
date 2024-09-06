package DAO;

import DTO.UserDTO;
import util.SECRET;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class UserDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void saveUser(String userId){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        try{
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
            String sql = "INSERT INTO users(user_id, status, attendance_time, leave_time) VALUES(?, ?, ?, ?)";
            try{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, "출근");
                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setNull(4, Types.TIMESTAMP);

                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
            finally {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public UserDTO getUserInfo(String userId){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        try {
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
            try {
                String sql = "SELECT * FROM users WHERE user_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userId);

                resultSet = preparedStatement.executeQuery();
                UserDTO userDTO = new UserDTO();
                while(resultSet.next()){
                    userDTO.setUserId(resultSet.getString("user_id"));
                    userDTO.setStatus(resultSet.getString("status"));
                    userDTO.setAttendanceTime(resultSet.getTimestamp("attendance_time"));
                    userDTO.setLeaveTime(resultSet.getTimestamp("leave_time"));
                }

                return userDTO;

            } catch (SQLException e) {
                return null;
            } finally {
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

