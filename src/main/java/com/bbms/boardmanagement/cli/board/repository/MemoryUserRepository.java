package com.bbms.boardmanagement.cli.board.repository;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository{

    private final static Map<Integer, User> userMemoryDB = new HashMap<>();

    static {
        insertTestData();
    }

    private static void insertTestData() {
        User user1 = new User("isec", "isec123", "아이섹", 1996, 10, 10, "남자", "안녕하세요 정보보호교육학원 아이섹입니다.");

        userMemoryDB.put(user1.getPostedPostCount(), user1);

    }


    @Override
    public void join(User user) {

    }

    @Override
    public void login(String id, String pw) {

    }

    @Override
    public void changePw(User user, String newPw) {

    }

    @Override
    public void changeNickName(User user, String newNickName) {

    }

    @Override
    public void myInfo(User user) {

    }

    @Override
    public void myPost(User user) {

    }

    @Override
    public void myComment(User user) {

    }

    @Override
    public void changeIntro(User user, String newIntro) {

    }

    @Override
    public void searchUser() {

    }

    @Override
    public void congratulate() {

    }

    @Override
    public void exit() {

    }
}
