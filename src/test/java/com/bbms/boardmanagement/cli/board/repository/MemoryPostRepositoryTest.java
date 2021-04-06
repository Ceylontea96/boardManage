package com.bbms.boardmanagement.cli.board.repository;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.SearchCondition;
import com.bbms.boardmanagement.cli.comment.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPostRepositoryTest {

    PostRepository repository = new MemoryPostRepository();

    @Test
    @DisplayName("게시글을 저장요청하면 메모리DB에 잘 저장되어야 한다.")
    void insertTest() {
        Comment comment0 = new Comment("아무내용 없음", "글쓴이123");
        Comment comment1 = new Comment("테스트용 댓글이다 이말이야~", "글쓴이333");
        Post post1 = new Post("제목1", "글쓴이1", "내용없음1","");
        post1.addComment(comment0);
        post1.addComment(comment1);
        repository.posting(post1);

        Post a = post1;
        repository.readMore(a);
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
    @DisplayName("삭제")
    void remove() {

    }

    @Test
    @DisplayName("조건별 검색")
    void normalTest() {
        repository.removePost(3);
        List<Post> postList = repository.searchPostList("제목", SearchCondition.ALL);
        for (Post post : postList) {
            System.out.println(post);
            repository.readMore(post);
        }

    }
}