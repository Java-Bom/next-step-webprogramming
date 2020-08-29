package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @SuppressWarnings("rawtypes")
    public List query(String sql) throws SQLException {

        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) { // TODO:// ResultSet try-resource 가능한지 확인

            setValues(pstmt);
            rs = pstmt.executeQuery();
            List<Object> users = new ArrayList<>();

            while (rs.next()) {
                users.add(mapRow(rs));
            }

            return users;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public Object queryForObject(String sql) throws SQLException {
        List result = query(sql);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    abstract void setValues(PreparedStatement pstmt) throws SQLException;

    abstract Object mapRow(ResultSet rs) throws SQLException;
}
