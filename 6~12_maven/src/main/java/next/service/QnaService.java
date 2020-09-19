package next.service;

import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.exception.CannotDeleteException;
import next.exception.CannotUpdateException;
import next.model.Answer;
import next.model.Question;
import next.model.User;

import java.util.List;

/**
 * Created by jyami on 2020/09/19
 */
public class QnaService {

    private static QnaService qnaService;

    private QnaService() {
    }

    public static QnaService getInstance() {
        if (qnaService == null) {
            qnaService = new QnaService();
        }
        return qnaService;
    }

    public void deleteQuestion(long questionId, User user) throws CannotDeleteException {

        Question question = QuestionDao.getInstance().findByQuestionId(questionId);
        List<Answer> answers = AnswerDao.getInstance().findAllByQuestionId(questionId);

        if (question == null) {
            throw new CannotDeleteException("존재하지 않는 질문입니다.");
        }

        if (!question.isSameUser(user)) {
            throw new CannotDeleteException("다른 사용자가 쓴 글을 삭제할 수 없습니다.");
        }

        if (!answers.isEmpty()) {
            throw new CannotDeleteException("해당 글에 댓글이 존재해 삭제할 수 없습니다.");
        }

        QuestionDao.getInstance().delete(question);
    }
}
