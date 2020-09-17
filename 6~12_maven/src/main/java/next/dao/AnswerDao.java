package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.Answer;
import next.model.Question;

import java.util.List;

/**
 * Created by jyami on 2020/09/05
 */
public class AnswerDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void insert(Answer answer){
        jdbcTemplate.update("INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)",
                answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
    }

    public void delete(long answerId){
        jdbcTemplate.update("DELETE FROM ANSWERS WHERE answerId=?", answerId);
    }

    public List<Answer> findAllByQuestionId(long questionId) {

        PreparedStatementSetter pss = pstmt -> pstmt.setLong(1, questionId);

        RowMapper<Answer> rowMapper = rs -> new Answer(
                rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("questionId"));

        String sql = "SELECT answerId, writer, contents, createdDate, questionId " +
                "FROM ANSWERS WHERE questionId = ?";

        return jdbcTemplate.query(sql, pss, rowMapper);

    }
}
