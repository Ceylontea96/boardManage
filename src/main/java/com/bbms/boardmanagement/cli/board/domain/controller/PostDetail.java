package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class PostDetail implements AppController {
    private static AppController appController;

    PostRepository postRepository = new MemoryPostRepository();

    @Override
    public void start() {
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
        List<Integer> postNums = new ArrayList<>();
        postNums = postRepository.integrity(userNow.myPostList());

        System.out.println("게시글 번호를 입력해주세요.");
        int postNum = inputInteger(">>> ");

        if(postNums.contains(postNum)) {
            ReadMore.readMore(postNum); //상세보기 페이지
        } else {
            System.out.println("게시글 목록중에서 선택해주세요.");
        }

    }
}
