package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.board.domain.controller.MyComment;
import com.bbms.boardmanagement.cli.board.domain.controller.MyPost;
import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;
import static com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository.getCurrentSession;

public class UserMyInfo implements AppController { // 내 정보 보여주기

    UserRepository userRepository = new MemoryUserRepository();

    private static AppController appController;

    @Override
    public void start() {
        userRepository.myInfo(getCurrentSession().getUserNow());//내정보 출력
        while (true) {
        myInformationScreen(); // 내 정보 메뉴
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1:// 내 글
                    appController = new MyPost();
                    break;
                case 2:// 내 댓글
                    appController = new MyComment();
                    break;
                case 3://내 정보 변경
                    appController = new ChangeMyData();
                    break;
                case 4://회원탈퇴
                    //new delMyAccount();
                    break;
                case 5://돌아가기
                    return;
                default:
                    System.out.println("잘 못 선택하셨습니다.");
                    return;
            }
            appController.start();
        }//while 종료
    }
}
