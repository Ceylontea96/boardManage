package com.bbms.boardmanagement.cli.board.repository;

import com.bbms.boardmanagement.cli.board.domain.User;

import java.util.*;
public interface UserRepository {

    //회원가입
    void join(User user);

    //로그인
    void login(String id, String pw);

    //비밀번호 변경
    void changePw(User user, String newPw);

    //닉네임 변경
    void changeNickName(User user, String newNickName);

    //내정보 보기
    void myInfo(User user);

    //내가 쓴 게시글 보기
    void myPost(User user);

    //내가 쓴 댓글 보기
    void myComment(User user);

    //내 소개 변경
    void changeIntro(User user, String newIntro);

    //유저검색
    void searchUser();

    //생일축하 기능
    void congratulate();

    //회원탈퇴
    void exit();



}
