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
    public List<Post> searchPostList(String keyword, SearchCondition condition) {   //조건별 검색
        List<Post> results = null;

        switch (condition) {
            case ALL:
                results = showAll();
                break;
            case TITLE:

                break;
            case AUTHOR:

                break;
            case TITLE_TEXT:

                break;
            case TEXT:

                break;
            default:
                return null;
        }

        return results;
    }

    private void show1All() {        //전체 게시글 보기
        System.out.println(" 번호         제목          작성자         조회      추천");
        for (int i = 0; i < postMemoryDB.size(); i++) {
            System.out.printf("  %d   %s      %s      %d  %d\n",
                    i + 1,
                    postMemoryDB.get(i).getTitle(),
                    postMemoryDB.get(i).getAuthor(),
                    postMemoryDB.get(i).getView(),
                    postMemoryDB.get(i).getRecommend());
        }
    }
    //페이지별 보여주는 게시글 수 조정

    private List<Post> showAll() {
        List<Post> matchedPost = new ArrayList<>();
        for (Post post : postMemoryDB) {
            matchedPost.add(post);
        }
        return matchedPost;
    }

    private List<Post> searchAll(String keyword) {
        //양식 msg
        List<Post> matchedPost = new ArrayList<>();
        for (int i = 0; i < postMemoryDB.size(); i++) {
            if (postMemoryDB.get(i).getTitle().indexOf(keyword) != -1 ||
                    postMemoryDB.get(i).getAuthor().indexOf(keyword) != -1 ||
                    postMemoryDB.get(i).getMainText().indexOf(keyword) != -1) {
                matchedPost.add(postMemoryDB.get(i));
            }
        }

        /*for (Post post : postMemoryDB) {
            if (post.getTitle().indexOf(keyword) != -1 ||
                    post.getAuthor().indexOf(keyword) != -1 ||
                    post.getMainText().indexOf(keyword) != -1) {
                matchedPost.add(post);
            }
        }*/
        return matchedPost;
    }

    @Override
    public void changePost(int postNumber, String title, String mainText) {//특정 게시글 수정

    }

    @Override
    public Post searchSpecificPost(int postNumber) {//특정 게시글 검색
        //양식 msg
        for (int i = 0; i < postMemoryDB.size(); i++) {
            if (postNumber == postMemoryDB.get(i).getPostNumber()) {
                //입력받은 번호를 postNumber로 가진 게시글을 찾아내서 리턴
                return postMemoryDB.get(i);
            }
        }
        return null;
    }

    @Override
    public void readMore(Post post) {//게시글 자세히 보기(조회)
        System.out.println(post);
    }


    @Override
    public void removePost(int postNumber) {//특정 게시글 삭제
        //양식 msg
        //삭제하려는 글이 맞는지 확인 후 삭제(관리자)
    }
}
