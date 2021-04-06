package com.bbms.boardmanagement.cli.main;


import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.user.controller.AddUser;
import com.bbms.boardmanagement.cli.user.controller.Login;
import com.bbms.boardmanagement.cli.user.domain.CheckCondition;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.inputInteger;
import static com.bbms.boardmanagement.cli.board.ui.AppUI.inputString;

public class FrontController {

    UserRepository userRepository = new MemoryUserRepository();
    private static AppController appController;

    public static void firstPage(int selection) {
        while (true) {
            switch (selection) {
                case 1:// 로그인
                    //로그인기능

                    appController = new Login();
                    break;

                case 2:// 회원가입
                    //회원가입 기능

                    appController = new AddUser();
                    break;

                case 3:// 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);

                default: //번호를 잘못 입력한 경우
                    System.out.println("잘못 선택 하셨습니다.");
                    return;

            }
            appController.start();
            if (MemoryUserRepository.getCurrentSession().getUserNow() == null) return;
        }//while 종료
    }



}
