package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {
    public void update(String sql) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setValues(pstmt);
            pstmt.executeUpdate();
        }
    }

    public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;
}
