package com.bbms.boardmanagement.cli.board.repository;

import java.util.List;
import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.SearchCondition;
import com.bbms.boardmanagement.cli.user.domain.User;

import java.util.List;

public interface PostRepository {

    //게시글 작성
    void posting(Post post, User user);

    //조건별 게시글 검색
    List<Post> searchPostList(String keyword, SearchCondition condition);

    //특정 게시글 수정
//    void changePost(int postNumber, String title, String mainText);
    void changeTitle(int postNumber, String Title);

    void changeText(int postNumber, String mainText);

    //게시글 목록 출력
    void showList(List<Post> searchList);

    //특정 게시글 찾기
    Post searchSpecificPost(int postNumber);

    //특정 게시글 삭제
    void removePost(int postNumber);

    //게시글 검증
    List<Integer> integrity (List<Post> postList);

    //게시글 상세보기기
    void readMore(Post post);
}
