package com.bbms.boardmanagement.cli.comment.repository;

import com.bbms.boardmanagement.cli.comment.Comment;

public interface CommentRepository {

    //댓글 추가
    void addComment(Comment comment);

    //댓글 삭제
    void deleteComment(int commentNumber);

    //댓글 보기
    void showComments();

    //댓글 수정
    void changeComment(int commentNumber, String newText);

}