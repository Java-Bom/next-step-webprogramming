package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jyami on 2020/08/29
 */
public interface PreparedStatementSetter {
    void setValues(PreparedStatement pstmt) throws SQLException;
}
