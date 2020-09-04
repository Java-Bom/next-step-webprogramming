package core.jdbc;

import core.jdbc.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, Object... params) throws DataAccessException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setValues(pstmt, params);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void update(PreparedStatementSetter preparedStatementSetter, KeyHolder keyHolder) throws DataAccessException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = preparedStatementSetter.set(connection)) {
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                keyHolder.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private void setValues(final PreparedStatement pstmt, final Object[] params) throws SQLException {
        for (int i = 1; i <= params.length; i++) {
            pstmt.setObject(i, params[i - 1]);
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params) throws DataAccessException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setValues(preparedStatement, params);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(rowMapper.rowMap(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... params) throws DataAccessException {
        return query(sql, rowMapper, params).get(0);
    }

}
