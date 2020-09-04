package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
    PreparedStatement set(Connection connection) throws SQLException;
}
