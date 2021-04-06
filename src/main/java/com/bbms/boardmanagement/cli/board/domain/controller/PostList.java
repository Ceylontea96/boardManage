package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.controller.changePost.ChangePost;
import com.bbms.boardmanagement.cli.board.domain.controller.posting.Posting;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;

import javax.xml.soap.Detail;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class PostList implements AppController { // 글 목록 보여주기

    private static AppController appController;
    PostRepository postRepository = new MemoryPostRepository();

    @Override
    public void start() {
        while (true) {
        allDocumentIndexScreen();
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 자세히
                    appController = new ReadMore();
                    break;
                case 2: //글 검색
                    appController = new PostSearch();
                    break;
                case 3: // 새 글쓰기
                    insertPost(MemoryUserRepository.getCurrentSession().getUserNow());
                    break;
                case 4: //돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    return;
            }
            appController.start();
        }//while 종료
    }

    //기능
    private void insertPost(User userNow) {
        System.out.println("\n====== 새로운 게시글을 추가합니다. ======");
        String title = inputString("# 제목: ");
        String author = userNow.getNickName();
        String mainText = inputString("# 본문: ");
        String authorCode = userNow.getUserCode();


        //저장할 게시글 객체화
        Post post = new Post(title, author, mainText,authorCode);
        //저장소에 저장
        postRepository.posting(post);
        System.out.printf("\n### [%s] 게시글이 정상 추가되었습니다.\n", post.getTitle());
    }
}
