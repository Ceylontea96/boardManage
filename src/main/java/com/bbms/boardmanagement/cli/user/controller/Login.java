package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.board.domain.controller.PostList;
import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.main.AppMain;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

import com.bbms.boardmanagement.cli.Session;

public class Login implements AppController {
    private final UserRepository userRepository = new MemoryUserRepository();
    private static AppController appController;

    @Override
    public void start() {

        login();

        while (true) {
            if (MemoryUserRepository.getCurrentSession().getUserNow() == null) return;
            boardMainScreen();
            int selection = inputInteger(">>> ");
            switch (selection) { // 로그인이 된 다음 보여주는 스위치
                case 1: // 내 정보
                    appController = new UserMyInfo();
                    break;
                case 2: // 글 목록
                    appController = new PostList();
                    break;
                case 3: // 로그아웃
                    MemoryUserRepository.getCurrentSession().setUserNow(null);
                    return;
                default:
                    System.out.println("잘못된 번호를 입력하셨습니다.");
                    continue;
            }
            appController.start();
        }
    }

    //기능
    //로그인
    private void login() {
        while (true) {
            System.out.println("아이디를 입력해주세요.");
            String id = inputString(">>> ");
            User user = userRepository.idCheck(id);
            System.out.println("비밀번호를 입력해주세요.");
            String pw = inputString(">>> ");
            if (user == null) {
                System.out.println("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
                continue;
            }
            boolean loginCheck = userRepository.pwCheck(pw, user);

            if (loginCheck) {

                MemoryUserRepository.getCurrentSession().setUserNow(user);
                User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
                System.out.println("로그인 되셨습니다");
                userRepository.congratulate(userNow);
                break;
            } else {
                System.out.println("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
                continue;
            }
        }//while 종료
    }
}
