package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import next.model.Answer;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class AnswerDao {
    public Answer insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pss = con -> {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setTimestamp(3, new Timestamp(answer.getTimeFromCreateDate()));
            pstmt.setLong(4, answer.getQuestionId());
            return pstmt;
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(pss, keyHolder);
        return findById(keyHolder.getId());
    }

    public Answer findById(long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";

        return jdbcTemplate.queryForObject(sql, rs -> new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(), rs.getLong("questionId")), answerId);
    }

    public List<Answer> findAllByQuestionId(long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
                + "order by answerId desc";

        return jdbcTemplate.query(sql, rs -> new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(), questionId), questionId);
    }

    public void delete(final Long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "DELETE FROM ANSWERS WHERE answerId = ?";
        jdbcTemplate.update(sql, answerId);
    }
}
