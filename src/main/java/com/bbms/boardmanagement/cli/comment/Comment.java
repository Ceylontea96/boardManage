package com.bbms.boardmanagement.cli.comment;

import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {


    LocalDateTime dateTimeNow = LocalDateTime.now();

    private int commentNumber;          //댓글 고유 번호
    private LocalDateTime commentTime;  //댓글 작성시간
    private String commentText;         //댓글 내용
    private LocalDateTime changeTime;   //수정된 시간
    private boolean isChange;           //수정 여부
    private String cAuthor;             //댓글 작성자
    private String cAuthorCode;         //댓글 작성자 유저코드
    private int cMainPostNum;           //댓글의 본문번호

    private static int cSquence;

    public Comment(String commentText, User user) {
        this.commentTime = dateTimeNow;
        this.commentNumber = ++cSquence;
        this.commentText = commentText;
        this.cAuthor = user.getNickName();
        this.cAuthorCode = user.getUserCode();
        this.cMainPostNum = MemoryPostRepository.getCurrentSession().getPostNow().getPostNumber();
    }

    @Override
    public String toString() {
        return String.format("%s    %s      %s", cAuthor, commentText, commentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }




    //getter 및 setter
    public LocalDateTime getDateTimeNow() {
        return dateTimeNow;
    }

    public void setDateTimeNow(LocalDateTime dateTimeNow) {
        this.dateTimeNow = dateTimeNow;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }

    public String getcAuthor() {
        return cAuthor;
    }

    public void setcAuthor(String cAuthor) {
        this.cAuthor = cAuthor;
    }

    public static int getcSquence() {
        return cSquence;
    }

    public static void setcSquence(int cSquence) {
        Comment.cSquence = cSquence;
    }

    public String getcAuthorCode() {
        return cAuthorCode;
    }

    public void setcAuthorCode(String cAuthorCode) {
        this.cAuthorCode = cAuthorCode;
    }
    public int getcMainPostNum() {
        return cMainPostNum;
    }

    public void setcMainPostNum(int cMainPost) {
        this.cMainPostNum = cMainPost;
    }
}
