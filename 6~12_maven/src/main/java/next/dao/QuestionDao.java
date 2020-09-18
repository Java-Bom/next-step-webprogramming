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

    private static QuestionDao questionDao;

    private QuestionDao() {
    }

    public static QuestionDao getInstance(){
        if(questionDao == null){
            questionDao = new QuestionDao();
        }
        return questionDao;
    }

    public void insert(Question question) {
        JdbcTemplate.getInstance().update("INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) " +
                        "VALUES (?, ?, ?, ?, ?)",
                question.getWriter(), question.getTitle(), question.getContents(),
                question.getCreatedDate(), question.getCountOfComment());
    }

    public void update(Question question) {
        JdbcTemplate.getInstance().update("UPDATE QUESTIONS SET title = ?, contents = ? WHERE writer = ?",
                question.getTitle(), question.getContents(), question.getWriter());
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

        return JdbcTemplate.getInstance().query(sql, pss, rowMapper);

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

        return JdbcTemplate.getInstance().queryForObject(sql, pss, rowMapper);
    }

    public int addCountOfComment(long questionId) {
        Question question = findByQuestionId(questionId);
        int countOfComment = question.getCountOfComment();

        JdbcTemplate.getInstance().update("UPDATE QUESTIONS SET countOfAnswer = " + (++countOfComment) + "WHERE questionId = " + question.getQuestionId());
        return countOfComment;
    }

    public int deleteCountOfComment(long questionId) {
        Question question = findByQuestionId(questionId);
        int countOfComment = question.getCountOfComment();

        JdbcTemplate.getInstance().update("UPDATE QUESTIONS SET countOfAnswer = " + (--countOfComment) + "WHERE questionId = " + question.getQuestionId());
        return countOfComment;
    }


}
