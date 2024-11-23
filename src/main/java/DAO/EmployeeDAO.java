package DAO;

import DTO.EmployeeDTO;
import util.SECRET;

import java.sql.*;

public class EmployeeDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public void insertEmployee(EmployeeDTO employeeDTO){
        try{
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
            String sql = "INSERT INTO employee(user_id, name, state, img_url) VALUES(?, ?, ?, ?)";
            try{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, employeeDTO.getUserId());
                preparedStatement.setString(2, employeeDTO.getName());
                preparedStatement.setString(3, employeeDTO.getState());
                preparedStatement.setString(4, employeeDTO.getImgUrl());

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

    public void updateEmployee(EmployeeDTO employeeDTO){
        try{
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
            String sql = "UPDATE employee SET name = ?, state = ?, img_url = ? WHERE user_id = ?";
            try{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, employeeDTO.getName());
                preparedStatement.setString(2, employeeDTO.getState());
                preparedStatement.setString(3, employeeDTO.getImgUrl());
                preparedStatement.setString(4, employeeDTO.getUserId());

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


    public EmployeeDTO getEmployeeInfo(String userId){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
            connection = DriverManager.getConnection(SECRET.DB_URL, SECRET.DB_USER, SECRET.DB_PASSWORD);
            try {
                String sql = "SELECT * FROM employee WHERE user_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    employeeDTO.setUserId(resultSet.getString("user_id"));
                    employeeDTO.setState(resultSet.getString("state"));
                }
                else employeeDTO.setState("false");

                return employeeDTO;
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

