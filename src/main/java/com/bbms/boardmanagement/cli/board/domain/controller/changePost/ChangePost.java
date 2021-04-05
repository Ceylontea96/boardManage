package com.bbms.boardmanagement.cli.board.domain.controller.changePost;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;

public class ChangePost {
    private final PostRepository postRepository;
    public ChangePost() {
        postRepository = new MemoryPostRepository();
    }
    //수정
    private void modify(int postNumber) {
        System.out.println(Post.getPostCount());

    }

    public static void main(String[] args) {

    }

}
