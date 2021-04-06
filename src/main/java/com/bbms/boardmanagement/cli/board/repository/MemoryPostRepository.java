package com.bbms.boardmanagement.cli.board.repository;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.SearchCondition;
import com.bbms.boardmanagement.cli.board.domain.SystemMessage;
import com.bbms.boardmanagement.cli.comment.Comment;
import javafx.geometry.Pos;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bbms.boardmanagement.cli.board.domain.Post.getPostCount;
import static com.bbms.boardmanagement.cli.board.domain.Post.setPostCount;

public class MemoryPostRepository implements PostRepository {


    private final static Map<Integer, Post> postMemoryDB = new HashMap<>(); //Map을 이용해서 작성


    static {
        insertTestData();
    }

    private static void insertTestData() {

        Post post1 = new Post("제목1", "글쓴이1", "아무내용없음1", "");
        Post post2 = new Post("제목2", "글쓴이2", "아무내용없음2", "");
        Post post3 = new Post("제목3", "글쓴이3", "아무내용없음3", "");
        Post post4 = new Post("제목4", "글쓴이4", "제목이랑아무내용없음4", "");

        postMemoryDB.put(post1.getPostNumber(), post1);
        postMemoryDB.put(post2.getPostNumber(), post2);
        postMemoryDB.put(post3.getPostNumber(), post3);
        postMemoryDB.put(post4.getPostNumber(), post4);

    }

    @Override
    public void posting(Post post) {        //게시글 추가

        postMemoryDB.put(post.getPostNumber(), post);

    }

    @Override
    public List<Post> searchPostList(String keyword, SearchCondition condition) {

        //호출부에 전달할 검색데이터 리스트
        List<Post> results = null;
        /**
         * search 메서드에서 k는 keyword, p는 post를 의미한다.
         * 제목 검색시 p.getTitle().indexOf(k)를 수행하면 post의 제목을 가져와서 keyword의 인덱스값을 추출한다.
         * post의 제목에 keyword가 포함되어있지 않다면 -1을 반환할것이기 때문에 반환값이 -1이 아니라면 List에 추가한다.
         */
        switch (condition) {
            case TITLE:
                results = search(keyword, (k, p) -> p.getTitle().indexOf(k) != -1);
                break;
            case AUTHOR:
                results = search(keyword, (k, p) -> p.getAuthor().indexOf(k) != -1);
                break;
            case TEXT:
                results = search(keyword, (k, p) -> p.getMainText().indexOf(k) != -1);
                break;
            case TITLE_TEXT:
                results = search(keyword, (k, p) -> p.getTitle().indexOf(k) != -1 ||
                        p.getMainText().indexOf(k) != -1);
                break;
            case ALL:
                results = search(keyword, (k, p) -> true);
                break;
            default:
                return null;
        }
        return results;
    }

    private List<Post> search(String keyword, PostPredicate pp) {
        List<Post> postList = new ArrayList<>();
        for (int key : postMemoryDB.keySet()) {
            Post post = postMemoryDB.get(key);

            //검색 키워드가 포함된 post만 리스트에 추가
            if (pp.test(keyword, post)) {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override // 글 수정
    public void changeTitle(int postNumber,String title) {
        Post post = searchSpecificPost(postNumber);
        post.setTitle(title);
    }

    public void changeText(int postNumber,String mainText) {
        Post post = searchSpecificPost(postNumber);
        post.setMainText(mainText);
    }

    @Override // 게시글 번호로 게시글 검색
    public Post searchSpecificPost(int postNumber) {
        Post post = null;
        for (int key: postMemoryDB.keySet()) {
            if(postNumber == key) {
                post = postMemoryDB.get(key);
            }
        }
        return post;
    }

    @Override
    public void removePost(int postNumber) {
        for (int key : postMemoryDB.keySet()) {
            if(postNumber == key) {
                postMemoryDB.remove(key);
                break;
            }
        }
    }

    @Override
    public void readMore(Post post) {
        System.out.println("================================================================================");
        System.out.println("제목: " + post.getTitle());
        System.out.println("작성자: " + post.getAuthor());
        System.out.println("회원등급: " +post.getUserRank());
        System.out.println("작성시간: " + post.getReportingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(post.getMainText());
        System.out.println("댓글");
        System.out.println("---------------------------------------");
        for (int key : post.getThisComment().keySet()) {
            Comment comment = post.getThisComment().get(key);
            System.out.println(comment);
        }
        System.out.println("==============================================================================");
    }

    //영화 검색 조건을 위한 인터페이스
    @FunctionalInterface
    interface PostPredicate {
        boolean test(String keyword, Post post);
    }


}
