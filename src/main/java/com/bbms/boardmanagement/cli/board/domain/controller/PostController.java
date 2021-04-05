package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.board.ui.AppUI;
import javafx.geometry.Pos;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class PostController {
    private final PostRepository postRepository;
    public PostController() {
        postRepository = new MemoryPostRepository();
    }
    //게시글 추가 기능
    private void insertPost() {
        System.out.println("\n====== 새로운 게시글을 추가합니다. ======");
        String title = inputString("# 제목: ");
        String author = inputString("# 글쓴이: ");
        String mainText = inputString("# 본문: ");

        //저장할 게시글 객체화
        Post post = new Post(title, author, mainText);
        //저장소에 저장
        postRepository.posting(post);
        System.out.printf("\n### [%s] 게시글이 정상 추가되었습니다.\n", post.getTitle());
    }


}
