package next.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Answer {
    private Long answerId;

    private String writer;

    private String contents;

    private LocalDateTime createdDate;

    private Long questionId;

    public Answer(String writer, String contents, long questionId) {
        this(0L, writer, contents, LocalDateTime.now(), questionId);
    }

    public Answer(Long answerId, String writer, String contents, LocalDateTime createdDate, Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public long getTimeFromCreateDate() {
        return ZonedDateTime.of(this.createdDate, ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    public long getQuestionId() {
        return questionId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getAnswerId());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Answer answer = (Answer) o;
        return Objects.equals(getAnswerId(), answer.getAnswerId());
    }

    @Override
    public String toString() {
        return "Answer [answerId=" + answerId + ", writer=" + writer + ", contents=" + contents + ", createdDate="
                + createdDate + ", questionId=" + questionId + "]";
    }
}
