package next.dto;

import next.model.Answer;

public class AnswerResponse {
    private Long answerId;
    private String writer;
    private String contents;
    private String createdDate;

    public AnswerResponse(final Answer answer) {
        this.answerId = answer.getAnswerId();
        this.writer = answer.getWriter();
        this.contents = answer.getContents();
        this.createdDate = answer.getCreatedDate().toString();
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

    public String getCreatedDate() {
        return createdDate;
    }
}
