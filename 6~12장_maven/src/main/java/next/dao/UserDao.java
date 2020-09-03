package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.User;

import java.util.List;

public class UserDao {
    public void insert(User user) {
        String query = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update(query, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        String query = "UPDATE USERS SET userId=?, password=?, email=?, name=? WHERE userId=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update(query, user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return jdbcTemplate.query("SELECT * FROM USERS", resultSet -> new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                resultSet.getString("email"))
        );
    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        return jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
                resultSet -> new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                        resultSet.getString("email")), userId);
    }
}
