package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.Rank;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class Posting implements AppController {
    private final PostRepository postRepository;
    public Posting() {
        postRepository = new MemoryPostRepository();
    }
    //게시글 추가 기능
    private void insertPost(User userNow) {
        System.out.println("\n====== 새로운 게시글을 추가합니다. ======");
        String title = inputString("# 제목: ");
        String author = userNow.getNickName();
        String mainText = inputString("# 본문: ");
        String authorCode = userNow.getUserCode();
        Rank userRank = userNow.getUserRank();


        //저장할 게시글 객체화
        Post post = new Post(title, author, mainText, authorCode, userRank);
        //저장소에 저장
        postRepository.posting(post, userNow);
        System.out.printf("\n### [%s] 게시글이 정상 추가되었습니다.\n", post.getTitle());
    }


    @Override
    public void start() {

    }
}
