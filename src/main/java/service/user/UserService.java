package service.user;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "250399");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver not found.");
        } catch (SQLException e) {
            System.out.println("Can't connect.");
        }
        return connection;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from mydatabase.user;");
            ResultSet resultSet = preparedStatement.executeQuery();
            returnUserList(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean add(User user) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into mydatabase.user (id, name, country, email) value (?,?,?,?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setString(4, user.getEmail());
            boolean isAdded = preparedStatement.executeUpdate() > 0;
            if (isAdded) return true;
        } catch (SQLException e) {
            System.out.println("");
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from mydatabase.user where id=?");
            preparedStatement.setInt(1, id);
            boolean isRemoved = preparedStatement.executeUpdate() > 0;
            if (isRemoved) return true;
        } catch (SQLException e) {
            System.out.println("");
        }
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public boolean update(int id, User user) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update mydatabase.user set name=?,country=?,email=? where id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getCountry());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, id);
            boolean isUpdated = preparedStatement.executeUpdate() > 0;
            if (isUpdated) return true;
        } catch (SQLException e) {
            System.out.println("");
        }
        return false;
    }

    @Override
    public List<User> findByName(String nameToFind) {
        List<User> users = new ArrayList<>();
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from mydatabase.user where name=?");
            preparedStatement.setString(1, nameToFind);
            ResultSet resultSet = preparedStatement.executeQuery();
            returnUserList(users, resultSet);
        } catch (SQLException e) {
            System.out.println("");
        }

        return users;
    }

    private void returnUserList(List<User> users, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String country = resultSet.getString("country");
            String email = resultSet.getString("email");
            users.add(new User(id, name, country, email));
        }
    }

    @Override
    public List<User> findByCountry(String country) {
        List<User> users = new ArrayList<>();
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from mydatabase.user where country=?");
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            returnUserList(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
