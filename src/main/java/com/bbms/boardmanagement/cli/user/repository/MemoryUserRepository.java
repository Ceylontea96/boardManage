package com.bbms.boardmanagement.cli.user.repository;

import com.bbms.boardmanagement.cli.Session;
import com.bbms.boardmanagement.cli.user.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {

    private final static Map<String, User> userMemoryDB = new HashMap<>();
    private static Session currentSession = new Session();

    static {
        insertTestData();
    }

    private static void insertTestData() {
        User user1 = new User("isec", "isec123", "아이섹", 1996, 10, 10, "남자", "안녕하세요 정보보호교육학원 아이섹입니다.");

        userMemoryDB.put(user1.getUserCode(), user1);

    }

    public User getLoginUser() {
        return currentSession.getUserNow();
    }

    public static Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public void join(User user) {
        userMemoryDB.put(user.getUserCode(), user);
    }

    @Override
    public void login(String id, String pw) {
        for (String key : userMemoryDB.keySet()) {
            User user = userMemoryDB.get(key);

            if (id.equals(user.getId())) {

            }
        }
    }

    //아이디 체크
    @Override
    public User idCheck(String insertId) {
        User user1 = null;
        for (String key : userMemoryDB.keySet()) {
            User user = userMemoryDB.get(key);

            if (user.getId().equals(insertId)) {
                user1 = user;
            }
        }

        return user1;
    }


    //비밀번호 체크
    public boolean pwCheck(String insertPw, User user) {
        boolean login = false;
        if (user.getPassword().equals(insertPw)) {
            login = true;
        }
        return login;
    }


    @Override
    public void changePw(User user, String newPw) {
        user.setPassword(newPw);
    }

    @Override
    public void changeNickName(User user, String newNickName) {
        user.setNickName(newNickName);
    }

    @Override
    public void myInfo(User user) {
        System.out.println("===========================");
        System.out.println("회원 번호: " + user.getUserNumber());
        System.out.println("회원 ID: " + user.getId());
        System.out.println("회원 닉네임: " + user.getNickName());
        System.out.println("가입 날짜: " + user.getJoinDate());
        System.out.println("생일: " + user.getBirth());
        System.out.println("성별: " + user.getGender());
        System.out.println("자기소개: " + user.getIntroduce());
        System.out.println("내가 작성한 게시글 수: " + user.getPostedPostCount());
        System.out.println("내가 작성한 댓글 수: " + user.getPostedCommentCount());
        System.out.println("=================================");
    }

    @Override
    public void myPost(User user) {

    }

    @Override
    public void myComment(User user) {

    }

    @Override
    public void changeIntro(User user, String newIntro) {
        user.setIntroduce(newIntro);
    }

    @Override
    public List<User> searchUserByNickName(String targetNickName) {
        List<User> userList = new ArrayList<>();
        for (String key : userMemoryDB.keySet()) {
            User user = userMemoryDB.get(key);

            if (user.getNickName().equals(targetNickName)) {
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public void congratulate(User user) {
        if (LocalDate.now().equals(user.getBirth())) {
            System.out.printf("\n[%s]님! 생일을 축하드립니다!!! 즐거운 하루 되세요~\n", user.getNickName());
        }
    }

    @Override
    public void exit() {

    }
}
