package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.controller.changePost.ChangePost;
import com.bbms.boardmanagement.cli.board.ui.AppUI;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class ReadMore {

    private static void readMore(){
        allDocumentIndexSearchMoreScreen();
        while (true){
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 수정
                    new ChangePost();
                    break;
                case 2: // 삭제
                    //글 삭제 로직
                    break;
                case 3: // 추천
                    // 글 추천 로직
                    break;
                case 4: // 댓글 쓰기
                    //댓글 쓰기 로직
                    break;
                case 5: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력");
            }
        }// while 종료
    }
}
