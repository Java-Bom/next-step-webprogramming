package next.dao;

import core.jdbc.ConnectionManager;
import next.dao.exception.DataAccessException;

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
            for (int i = 1; i <= params.length; i++) {
                pstmt.setObject(i, params[i - 1]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params) throws DataAccessException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
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
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
            ResultSet resultSet = preparedStatement.getResultSet();
            return rowMapper.rowMap(resultSet);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

}
