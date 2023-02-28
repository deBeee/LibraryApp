package org.example.database;

import org.example.model.User;

import java.sql.*;

public class UserDAO {
    private final Connection connection;
    private static final UserDAO instance = new UserDAO();

    private UserDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library",
                    "root",
                    "");
        } catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    public User findByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";

            PreparedStatement ps = this.connection.prepareStatement(sql);

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(
                        rs.getString("login"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static UserDAO getInstance() {
        return instance;
    }
}
