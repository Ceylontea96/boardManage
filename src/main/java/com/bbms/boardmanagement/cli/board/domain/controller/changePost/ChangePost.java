package com.bbms.boardmanagement.cli.board.domain.controller.changePost;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.board.ui.AppUI;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class ChangePost {
    private final PostRepository postRepository;

    public ChangePost() {
        postRepository = new MemoryPostRepository();
    }

    //수정
    private void modify() {
        System.out.println("변경할 글번호를 입력하세요");
        int postNumber = inputInteger(">>> ");
        if(postRepository.searchSpecificPost(postNumber)==null){
            System.out.println("없는 글입니다");
        }else {
            System.out.println("변경할 제목을 입력해 주세요");
            String newTitle = inputString(">>> ");
            System.out.println("변경할 내용을 입력해 주세요");
            String newMainText = inputString(">>> ");
            postRepository.changePost(postNumber,newTitle,newMainText);
        }

    }

}
