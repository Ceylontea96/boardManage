package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.controller.changePost.ChangePost;
import com.bbms.boardmanagement.cli.board.domain.controller.posting.Posting;
import com.bbms.boardmanagement.cli.board.ui.AppUI;

import javax.xml.soap.Detail;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class PostList { // 글 목록 보여주기

    private static void postList() {
        allDocumentIndexScreen();
        while (true) {
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 자세히
                    new ReadMore();
                    break;
                case 2: //글 검색
                    new PostSearch();
                    break;
                case 3: // 새 글쓰기
                    new Posting();
                    break;
                case 4: //돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력");

            }

        }//while 종료

    }
}
