package com.bbms.boardmanagement.cli.board.domain;

//게시글 검색시 조건
public enum SearchCondition {

    //제목, 작성자, 내용, 제목 + 내용, 전체
    TITLE, AUTHOR, TEXT, TITLE_TEXT, ALL
}
