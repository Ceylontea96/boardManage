package com.bbms.boardmanagement.cli.comment.repository;

import com.bbms.boardmanagement.cli.comment.Comment;
import com.bbms.boardmanagement.cli.user.domain.User;

public interface CommentRepository {

    //댓글 추가
    void addComment(Comment comment, User user);

    //댓글 삭제
    void deleteComment(int commentNumber);

    //댓글 삭제2
    void deleteComment2(int commentNumber);

    //댓글 보기
    void showComments();

    //댓글 수정
    void changeComment(int commentNumber, String newText);

    //댓글 특정하기
    int findComment(int commentNumber);

    Comment findCommentByCommentNum(int commentNumber);

}
