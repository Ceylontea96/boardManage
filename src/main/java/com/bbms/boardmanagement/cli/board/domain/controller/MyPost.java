package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.ui.AppUI;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class MyPost { //내 글 보기

    private static void myPost() {
        myDocumentAndCommentScreen();
        while (true) {
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 수정
                    //글 수정 기능
                    break;
                case 2: // 삭제
                    //글 삭제 기능
                    break;
                case 3: // 자세히 보기
                    //자세히 보기 기능
                    break;
                case 4: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력");

            }
        }
    }
}
