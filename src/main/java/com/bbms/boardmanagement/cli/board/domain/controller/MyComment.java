package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class MyComment implements AppController { // 내 댓글

    UserRepository userRepository = new MemoryUserRepository();


    @Override
    public void start() {
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
        if (userNow.getMyComment().size() == 0) {
            System.out.println("내가 작성한 댓글이 없습니다.");
            return;
        }
        while (true) {
        myDocumentAndCommentScreen(); // 내 댓글 메뉴
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 수정
                    //댓글 수정 기능
                    break;
                case 2: // 삭제
                    //댓글 삭제 기능
                    break;
                case 3: // 자세히 보기
                    //자세히 보기 기능
                    break;
                case 4: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    return;
            }
        }//while 종료
    }
}

