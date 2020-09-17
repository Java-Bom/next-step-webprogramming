package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.Question;

import java.util.List;

/**
 * Created by jyami on 2020/09/05
 */
public class QuestionDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void insert(Question question) {
        jdbcTemplate.update("INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) " +
                        "VALUES (?, ?, ?, ?, ?)",
                question.getWriter(), question.getTitle(), question.getContents(),
                question.getCreatedDate(), question.getCountOfComment());
    }


    public List<Question> findAll() {

        PreparedStatementSetter pss = pstmt -> {
        };
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));

        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";

        return jdbcTemplate.query(sql, pss, rowMapper);

    }


    public Question findByQuestionId(long questionId) {

        PreparedStatementSetter pss = pstmt -> pstmt.setLong(1, questionId);

        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));

        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer " +
                "FROM QUESTIONS WHERE questionId = ?";

        return jdbcTemplate.queryForObject(sql, pss, rowMapper);
    }

    public int addCountOfComment(long questionId) {
        Question question = findByQuestionId(questionId);
        int countOfComment = question.getCountOfComment();

        jdbcTemplate.update("UPDATE QUESTIONS SET coundOfAnswer = " + (++countOfComment));
        return countOfComment;
    }

}
