package next.dao;

import core.jdbc.ConnectionManager;
import next.dao.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class JdbcTemplate {
    public void update(String sql, Consumer<PreparedStatement> setValues) throws DataAccessException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setValues.accept(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> query(String sql, Consumer<PreparedStatement> setValues, Function<ResultSet, T> rowMapper) throws DataAccessException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setValues.accept(preparedStatement);
            ResultSet resultSet = preparedStatement.getResultSet();
            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(rowMapper.apply(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> T queryForObject(String sql, Consumer<PreparedStatement> setValues, Function<ResultSet, T> rowMapper) throws DataAccessException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setValues.accept(preparedStatement);
            ResultSet resultSet = preparedStatement.getResultSet();
            return rowMapper.apply(resultSet);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

}
