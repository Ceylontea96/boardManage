package com.bbms.boardmanagement.cli.board.domain.controller;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class MyComment { // 내 댓글


    private static void myComment() {
        myDocumentAndCommentScreen(); // 내 댓글 메뉴
        while (true) {
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
                    System.out.println("잘 못 입력");
            }
        }//while 종료

    }
}

