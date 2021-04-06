package com.bbms.boardmanagement.cli.user.domain;

/*
회원 등급 정책
게시글 1개, 댓글 1개 작성시 일반회원
게시글 3개, 댓글 5개 작성시
 */
public enum RankPolicy {
    일반회원, 우수회원,
}
