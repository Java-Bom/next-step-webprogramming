package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jyami on 2020/08/29
 */
public abstract class JdbcTemplate {

    public void update(String sql) throws SQLException {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            setValues(pstmt);
            pstmt.executeUpdate();
        }
    }


    abstract void setValues(PreparedStatement pstmt) throws SQLException;

}
