package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.board.domain.controller.MyComment;
import com.bbms.boardmanagement.cli.board.domain.controller.MyPost;
import com.bbms.boardmanagement.cli.board.ui.AppUI;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class UserMyInfo { // 내 정보 보여주기

    private static void userMyInfo() {
        myInformationScreen(); // 내 정보 메뉴
        while (true) {
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1:// 내 글
                    new MyPost();
                    break;
                case 2:// 내 댓글
                    new MyComment();
                    break;
                case 3://내 정보 변경
                    new ChangeMyData();
                    break;
                case 4://회원탈퇴
                    //new delMyAccount();
                    break;
                case 5://돌아가기
                    return;
                default:
                    System.out.println("잘 못 선택");
            }
        }//while 종료
    }
}
