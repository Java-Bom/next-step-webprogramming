package next.dao;

import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {

    private static UserDao userDao;

    private UserDao() {}

    public static UserDao getInstance(){
        if(userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }

    public void insert(User user) {
        JdbcTemplate.getInstance().update("INSERT INTO USERS VALUES (?, ?, ?, ?)",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }


    public void update(User user) {
        JdbcTemplate.getInstance().update("UPDATE USERS SET password = ?, name = ?, email = ? WHERE userid=?",
                user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        PreparedStatementSetter pss = pstmt -> {
        };

        RowMapper<User> rowMapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        String sql = "SELECT userId, password, name, email FROM USERS";
        return JdbcTemplate.getInstance().query(sql, pss, rowMapper);
    }

    public User findByUserId(String userId) {
        PreparedStatementSetter pss = pstmt -> pstmt.setString(1, userId);

        RowMapper<User> rowMapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return JdbcTemplate.getInstance().queryForObject(sql, pss, rowMapper);
    }
}
