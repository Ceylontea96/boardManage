package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

import com.bbms.boardmanagement.cli.Session;

public class Login {
    private final UserRepository userRepository;

    public Login() {
        userRepository = new MemoryUserRepository();
    }

    private void idCheck() {
        System.out.println("아이디를 입력해 주세요");
        String id = inputString(">>> ");
        User user = userRepository.idCheck(id);
        String pw = inputString(">>> ");
        boolean loginCheck = userRepository.pwCheck(pw, user);

        if (loginCheck) {

            MemoryUserRepository.getCurrentSession().setUserNow(user);
            User userNow =  MemoryUserRepository.getCurrentSession().getUserNow();
            System.out.println("로그인 되셨습니다");
        } else {
            System.out.println("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
        }


    }
}
