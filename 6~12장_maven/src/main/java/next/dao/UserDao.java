package next.dao;

import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }


    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("UPDATE USERS SET password = ?, name = ?, email = ? WHERE userid=?",
                user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter pss = pstmt -> {
        };

        RowMapper<User> rowMapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        String sql = "SELECT userId, password, name, email FROM USERS";
        return jdbcTemplate.query(sql, pss, rowMapper);
    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter pss = pstmt -> pstmt.setString(1, userId);

        RowMapper<User> rowMapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return jdbcTemplate.queryForObject(sql, pss, rowMapper);
    }
}
