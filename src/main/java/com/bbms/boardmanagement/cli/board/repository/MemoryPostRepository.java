package com.bbms.boardmanagement.cli.board.repository;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.SearchCondition;
import com.bbms.boardmanagement.cli.board.domain.SystemMessage;

import java.util.ArrayList;
import java.util.List;

import static com.bbms.boardmanagement.cli.board.domain.Post.getPostCount;
import static com.bbms.boardmanagement.cli.board.domain.Post.setPostCount;

public class MemoryPostRepository implements PostRepository {

    private final static List<Post> postMemoryDB = new ArrayList<>();

    static {
        insertTestData();
    }

    private static void insertTestData() {
        Post post1 = new Post("제목1", "글쓴이1", "아무내용없음1");
        Post post2 = new Post("제목2", "글쓴이2", "아무내용없음2");
        Post post3 = new Post("제목3", "글쓴이3", "아무내용없음3");
        Post post4 = new Post("제목4", "글쓴이4", "아무내용없음4");

        postMemoryDB.add(post1);
        postMemoryDB.add(post2);
        postMemoryDB.add(post3);
        postMemoryDB.add(post4);


    }

    @Override
    public void posting(Post post) {        //게시글 추가
        postMemoryDB.add(post);

    }

    @Override
    public List<Post> searchPostList(String keyword, SearchCondition condition) {
        return null;
    }

    @Override
    public void changePost(int postNumber, String title, String mainText) {

    }

    @Override
    public Post searchSpecificPost(int postNumber) {
        return null;
    }

    @Override
    public void removePost(int postNumber) {

    }

    @Override
    public void readMore(Post post) {

    }


}
