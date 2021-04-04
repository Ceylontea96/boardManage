package com.bbms.boardmanagement.cli.board.repository;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.SearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPostRepositoryTest {

    PostRepository repository = new MemoryPostRepository();

    @Test
    @DisplayName("게시글을 저장요청하면 메모리DB에 잘 저장되어야 한다.")
    void insertTest() {
        Post post1 = new Post("제목1", "글쓴이1", "내용없음1");

        repository.posting(post1);

        Post a = repository.searchSpecificPost(1);
        System.out.println("a = " + a);
    }

    @Test
    @DisplayName("전체조회시 모든 게시글 정보가 출력되어야 한다.")
    void selectAll() {
        List<Post> postList = repository.searchPostList("", SearchCondition.ALL);
        for (Post post : postList) {
            System.out.println(post);
        }
    }

    @Test
    @DisplayName("모든 게시글 조회")
    void showAll() {

    }

    @Test
    @DisplayName("간단 테스트")
    void normalTest() {

    }
}