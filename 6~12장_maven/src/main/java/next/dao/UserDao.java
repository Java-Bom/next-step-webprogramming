package next.dao;

import next.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) {
        String query = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
        };
        jdbcTemplate.update(query, preparedStatement -> {
            try {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, user.getEmail());
            } catch (SQLException throwables) {
                throw new IllegalArgumentException(throwables.getMessage());
            }
        });
    }

    public void update(User user) {
        String query = "UPDATE USERS SET userId=?, password=?, email=?, name=? WHERE userId=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
        };
        jdbcTemplate.update(query, preparedStatement -> {
            try {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getName());
                preparedStatement.setString(5, user.getUserId());
            } catch (SQLException throwables) {
                throw new IllegalArgumentException(throwables.getMessage());
            }

        });
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
        };
        return jdbcTemplate.query("SELECT * FROM USERS", preparedStatement -> {
        }, resultSet -> {
            try {
                return new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                        resultSet.getString("email"));
            } catch (SQLException throwables) {
                throw new IllegalArgumentException(throwables.getMessage());
            }
        });
    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
        };

        return jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
                preparedStatement -> {
                    try {
                        preparedStatement.setString(1, userId);
                    } catch (SQLException throwables) {
                        throw new IllegalArgumentException(throwables.getMessage());
                    }
                },
                resultSet -> {
                    try {
                        return new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                                resultSet.getString("email"));
                    } catch (SQLException throwables) {
                        throw new IllegalArgumentException(throwables.getMessage());
                    }
                });
    }
}
