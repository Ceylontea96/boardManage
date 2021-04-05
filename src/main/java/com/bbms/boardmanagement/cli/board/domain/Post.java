package com.bbms.boardmanagement.cli.board.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//역할: 이 클래스는 하나의 게시글 정보를 저장할 수 있어야 한다.
public class Post {

    LocalDateTime dateTimeNow = LocalDateTime.now();

    private int postNumber;                                 //게시글 번호
    private String title;                                   //게시글 제목
    private String author;                                  //게시글 작성자 이름
    private String userRank;                                //게시글 작성회원 등급(Member에서 가져오기)
    private LocalDateTime reportingDate;                    //게시글 작성시간
    private String mainText;                                //게시글 본문
    private int view;                                       //게시글 조회수
    private int recommend;                                  //게시글 추천수
    private String comment;                                 //게시글 댓글 배열로 담기
    private static int postCount;                           //전체 게시글 수
    private int commentCount;                               //댓글 수



    private static int sequence;                           //게시글 번호 정하는 상수


    //생성자


    public Post(String title, String author, String mainText) {
        this.postNumber = ++sequence;
        this.title = title;
        this.author = author;
        this.userRank = "일반회원";
        this.reportingDate = dateTimeNow;
        this.mainText = mainText;

    }









    //toString

    public String toString() {

        /*return String.format
                ("제목: %s\n글쓴이: %s\n작성시간: %s\n%s\n추천수: %d\n",
                        title, author,
                        reportingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        mainText, recommend);*/
        return String.format("%d    %s              %s      %s      %d      %d\n", postNumber, title, author, reportingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), view, recommend);
    }

    //getter 및 setter

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public LocalDateTime getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(LocalDateTime reportingDate) {
        this.reportingDate = reportingDate;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static int getPostCount() {
        return postCount;
    }

    public static void setPostCount(int postCount) {
        Post.postCount = postCount;
    }

    public static int getSequence() {
        return sequence;
    }

    public static void setSequence(int sequence) {
        Post.sequence = sequence;
    }
}
