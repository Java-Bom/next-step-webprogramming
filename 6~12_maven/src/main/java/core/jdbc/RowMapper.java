package core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jyami on 2020/08/29
 */
@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
