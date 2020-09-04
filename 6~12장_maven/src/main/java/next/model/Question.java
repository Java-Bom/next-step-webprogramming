package next.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Question {
    private Long questionId;

    private String writer;

    private String title;

    private String contents;

    private LocalDateTime createdDate;

    private int countOfComment;

    public Question(String writer, String title, String contents) {
        this(0L, writer, title, contents, LocalDateTime.now(), 0);
    }

    public Question(Long questionId, String writer, String title, String contents, LocalDateTime createdDate,
                    int countOfComment) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfComment = countOfComment;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
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

    public int getCountOfComment() {
        return countOfComment;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Question question = (Question) o;
        return Objects.equals(getQuestionId(), question.getQuestionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestionId());
    }

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", writer=" + writer + ", title=" + title + ", contents="
                + contents + ", createdDate=" + createdDate + ", countOfComment=" + countOfComment + "]";
    }

}
