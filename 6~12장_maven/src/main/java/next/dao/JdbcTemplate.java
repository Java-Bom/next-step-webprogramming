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
public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter pss) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pss.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {

        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pss.setValues(pstmt);
            rs = pstmt.executeQuery();
            List<T> users = new ArrayList<>();

            while (rs.next()) {
                users.add(rowMapper.mapRow(rs));
            }
            return users;

        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException(e);
                }
            }
        }
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        List<T> result = query(sql, pss, rowMapper);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

}
