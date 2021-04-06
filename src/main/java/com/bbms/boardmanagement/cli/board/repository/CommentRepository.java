package com.bbms.boardmanagement.cli.board.repository;

public interface CommentRepository {

    //댓글 추가
    void addComment();

    //댓글 삭제
    void delComment();

    //댓글 수정
    void changeComment();

    //댓글 추천
    void recommendComment();

    //대댓글 추가
    void nestedComment();

}
