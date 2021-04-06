package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.main.AppController;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class ChangeMyData implements AppController { // 내 정보 변경

    @Override
    public void start() {
        myInformationChangeScreen(); // 내 정보 변경 메뉴
        while (true) {
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 닉네임 변경
                    new ChangeNickName();
                    break;
                case 2: // 비밀번호 변경
                    new ChangePw();
                    break;
                case 3: // 개인정보 변경
                    //개인정보 변경 로직
                    break;
                case 4: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력");
            }
        }//while 종료
    }
}
