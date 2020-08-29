package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2020/08/29
 */
public abstract class SelectJdbcTemplate {


    public List query(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setValues(pstmt);

            List<Object> users = new ArrayList<>();
            while (rs.next()) {
                users.add(mapRow(rs));
            }
            return users;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public Object QueryForObject(String sql) throws SQLException {
        List result = query(sql);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    abstract void setValues(PreparedStatement pstmt) throws SQLException;

    abstract Object mapRow(ResultSet rs) throws SQLException;

}
