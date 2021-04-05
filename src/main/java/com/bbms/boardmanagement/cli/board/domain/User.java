package com.bbms.boardmanagement.cli.board.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.time.*;

public class User {

    LocalDateTime dateTimeNow = LocalDateTime.now();

    private int userNumber;                                 //회원 번호
    private String id;                                      //회원 아이디
    private String password;                                //회원 비밀번호
    private String nickName;                                //회원 닉네임
    private LocalDateTime joinDate;                         //가입일
    private LocalDate birth;                            //생일
    private String gender;                                  //성별
    private String introduce;                               //자기소개
    private String userCode;                                //유저코드
    private int postedPostCount;                            //작성한 게시글 수
    private int postedCommentCount;                         //작성한 댓글 수
    private List<Post> myPost;

    private static int userCount;                           //전체 유저 수

    private static int uSequence;                            //유저 번호 정하는 상수

    public User(String id, String password, String nickName, int birthYear, int birthMonth, int birthDay, String gender, String introduce) {
        this.userNumber = ++uSequence;
        this.id = id.trim();
        this.password = password.trim();
        this.nickName = nickName;
        this.joinDate = dateTimeNow;
        this.birth = LocalDate.of(birthYear, birthMonth, birthDay);
        this.gender = gender.trim();
        this.introduce = introduce;
        this.userCode = randomUserCode();
        this.postedPostCount = 0;
        this.postedCommentCount = 0;
    }

    private String randomUserCode() {
        return "";
    }



    //setter 및 getter
    public LocalDateTime getDateTimeNow() {
        return dateTimeNow;
    }

    public void setDateTimeNow(LocalDateTime dateTimeNow) {
        this.dateTimeNow = dateTimeNow;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public int getPostedPostCount() {
        return postedPostCount;
    }

    public void setPostedPostCount(int postedPostCount) {
        this.postedPostCount = postedPostCount;
    }

    public int getPostedCommentCount() {
        return postedCommentCount;
    }

    public void setPostedCommentCount(int postedCommentCount) {
        this.postedCommentCount = postedCommentCount;
    }

    public static int getUserCount() {
        return userCount;
    }

    public static void setUserCount(int userCount) {
        User.userCount = userCount;
    }

    public static int getuSequence() {
        return uSequence;
    }

    public static void setuSequence(int uSequence) {
        User.uSequence = uSequence;
    }
}
