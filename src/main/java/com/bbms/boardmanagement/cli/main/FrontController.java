package com.bbms.boardmanagement.cli.main;


import com.bbms.boardmanagement.cli.user.controller.AddUser;
import com.bbms.boardmanagement.cli.user.controller.Login;

public class FrontController {

    public static void firstPage(int selection) {
        while (true) {
            switch (selection) {
                case 1:// 로그인
                    //로그인기능
                    new Login();
                    break;

                case 2:// 회원가입
                    //회원가입 기능
                    new AddUser();
                    break;

                case 3:// 프로그램 종료
                    return;

                default: //번호를 잘못 입력한 경우
                    System.out.println("잘못 선택 하셨습니다.");

            }
        }//while 종료
    }
}
