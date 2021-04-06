package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.ui.AppUI;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class PostSearch {

    private static void postSearch() { //글 검색
        allDocumentIndexSearchScreen();
        while (true) {
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 자세히 보기
                    new ReadMore();
                    break;
                case 2: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력");
            }
        }//while 종료

    }
}
