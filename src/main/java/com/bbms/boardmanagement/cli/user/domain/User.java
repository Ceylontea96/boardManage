package com.bbms.boardmanagement.cli.user.domain;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.comment.Comment;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.*;
import java.util.List;
import java.util.Map;

public class User {

    LocalDateTime dateTimeNow = LocalDateTime.now();
    UserRepository userRepository = new MemoryUserRepository();

    private int userNumber;                                 //회원 번호
    private String id;                                      //회원 아이디
    private String password;                                //회원 비밀번호
    private String nickName;                                //회원 닉네임
    private LocalDateTime joinDate;                         //가입일
    private LocalDate birth;                                //생일
    private String gender;                                  //성별
    private String introduce;                               //자기소개
    private String userCode;                                //유저코드
    private int postedPostCount;                            //작성한 게시글 수
    private int postedCommentCount;                         //작성한 댓글 수
    private Rank userRank;                                  //회원등급

    private Map<Integer, Post> myPost = new HashMap<>();        //내가 작성한 게시글 모음
    private Map<Integer, Comment> myComment = new HashMap<>();  //내가 작성한 댓글 모음음

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
        this.userRank = Rank.BRANCH;
        this.postedPostCount = 0;
        this.postedCommentCount = 0;
        //회원등급 초기화하기

    }
    //List<Post>로 뽑아내기
    public List<Post> myPostList() {
        List<Post> postList = new ArrayList<>();
        for (int key : myPost.keySet()) {
            Post post = myPost.get(key);

            postList.add(post);
        }
        return postList;
    }

    //내 게시글 목록에 추가 기능..
    public void addMyPost(Post post) {
        myPost.put(post.getPostNumber(), post);
        this.postedPostCount++;
    }

    //내 게시글 목록에서 삭제 기능
    public Post delMyPost(int postNumber) {
        this.postedPostCount--;
        return myPost.remove(postNumber);
    }

    //내 댓글 목록에 추가 기능
    public void addMyComment(Comment comment) {
        myComment.put(comment.getCommentNumber(), comment);
        this.postedCommentCount++;
    }

    public List<Integer> findCommentNumber() {
        List<Integer> commentList = new ArrayList<>();
        for (int key : myComment.keySet()) {
            Comment comment = myComment.get(key);

            commentList.add(comment.getCommentNumber());
        }
        return commentList;
    }

    //내 댓글 목록에서 삭제 기능
    public Comment delMyComment(int commentNumber) {
        this.postedCommentCount--;
        return myComment.remove(commentNumber);
    }

    private String randomUserCode() {

            char[] tmp = new char[5];
            for(int i=0; i<tmp.length; i++) {
                int div = (int) Math.floor( Math.random() * 2 );

                if(div == 0) { // 0이면 숫자로
                    tmp[i] = (char) (Math.random() * 10 + '0') ;
                }else { //1이면 알파벳
                    tmp[i] = (char) (Math.random() * 26 + 'A') ;
                }
            }
            //중복여부 추가해야함
            return new String(tmp);
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

    public Rank getUserRank() {
        return userRank;
    }

    public void setUserRank(Rank userRank) {
        this.userRank = userRank;
    }

    public Map<Integer, Post> getMyPost() {
        return myPost;
    }

    public void setMyPost(Map<Integer, Post> myPost) {
        this.myPost = myPost;
    }

    public Map<Integer, Comment> getMyComment() {
        return myComment;
    }

    public void setMyComment(Map<Integer, Comment> myComment) {
        this.myComment = myComment;
    }
}
