package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class ChangePw {
    private final UserRepository userRepository;

    public ChangePw() {
        userRepository = new MemoryUserRepository();
    }

    private void modifyPw(User user) {
        System.out.println("비밀번호 변경");
        String newPw = inputString(">>> ");
//        userRepository.changePw(nowUserCode,newPw);


    }

}
