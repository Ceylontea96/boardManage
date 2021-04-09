package com.bbms.boardmanagement.cli.board.repository;

import com.bbms.boardmanagement.cli.Session;
import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.SearchCondition;
import com.bbms.boardmanagement.cli.board.domain.SystemMessage;
import com.bbms.boardmanagement.cli.comment.Comment;
import com.bbms.boardmanagement.cli.comment.repository.CommentRepository;
import com.bbms.boardmanagement.cli.comment.repository.MemoryCommentRepository;
import com.bbms.boardmanagement.cli.user.domain.Rank;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;
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
    private static Session currentSession = new Session();


    static {
        insertTestData();
    }

    private static void insertTestData() {

        Post post1 = new Post("코인 하는법", "카페 매니저", "코인이란 무엇인가? 어쩌구 저쩌꾸", "", Rank.EARTH);
        Post post2 = new Post("코인추천종목", "DTS12319441", "아하코인 풀매수하세요", "", Rank.BRANCH);
        Post post3 = new Post("돈 잘버는법", "일론 머스크", "테슬라 주식 사고 화성으로 갑시다 여러분", "", Rank.FOREST);
        Post post4 = new Post("한강물 온도", "비트코인75층", "비트코인 75층에서 물렸습니다. 혹시 근처에 주민분들 계신가요?", "", Rank.BRANCH);


        postMemoryDB.put(post1.getPostNumber(), post1);
        postMemoryDB.put(post2.getPostNumber(), post2);
        postMemoryDB.put(post3.getPostNumber(), post3);
        postMemoryDB.put(post4.getPostNumber(), post4);

    }

    public static Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public void posting(Post post, User user) {        //게시글 추가

        postMemoryDB.put(post.getPostNumber(), post);

        user.addMyPost(post);

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

    @Override // 제목 수정
    public void changeTitle(int postNumber, String title) {
        Post post = searchSpecificPost(postNumber);
        post.setTitle(title);
    }

    @Override // 본문 수정
    public void changeText(int postNumber, String mainText) {
        Post post = searchSpecificPost(postNumber);
        post.setMainText(mainText);
    }

    @Override // 게시글 번호로 게시글 검색
    public Post searchSpecificPost(int postNumber) {
        Post post = null;
        for (int key : postMemoryDB.keySet()) {
            if (postNumber == key) {
                post = postMemoryDB.get(key);
            }
        }
        return post;
    }

    @Override
    public void showList(List<Post> searchList) {
        if (searchList.size() == 0) {
            System.out.println("---------------------------------- 게시글 없음 --------------------------------------");
        }
        System.out.println("========================================== 글 목록 =======================================\n");
        System.out.println("번호            제목            작성자             작성일                조회    추천");
        for (Post post : searchList) {
            System.out.println(post);
        }
        System.out.println("==========================================================================================");
    }

    @Override
    public void removePost(int postNumber) {
        for (int key : postMemoryDB.keySet()) {
            if (postNumber == key) {
                postMemoryDB.remove(key);
                break;
            }
        }
    }

    @Override
    public void readMore(Post post) {
//        post.setView(post.getView() + 1); //조회수 +1
        System.out.println("================================================================================");
        System.out.println("제목: " + post.getTitle());// + "[" +post.getThisComment().size()+ "]"
        System.out.println("작성자: " + post.getAuthor());
        System.out.println("회원등급: " + post.getUserRank() + "         조회: " + post.getView());
        System.out.println("작성시간: " + post.getReportingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(post.getMainText());
        System.out.println("추천: " + post.getRecommend());
        System.out.println("---------------------------------------");
        System.out.println("댓글");
        int i = 1;
        for (int key : post.getThisComment().keySet()) {
            Comment comment = post.getThisComment().get(key);
            System.out.println(i + "\t" + comment);
            i++;
        }

        System.out.println("==============================================================================");
    }

    //게시글 검증
    public List<Integer> integrity(List<Post> postList) {
        List<Integer> postNums = new ArrayList<>();

        for (Post post : postList) {
            postNums.add(post.getPostNumber());
        }
        return postNums;
    }




    //게시글 검색 조건을 위한 인터페이스
    @FunctionalInterface
    interface PostPredicate {
        boolean test(String keyword, Post post);
    }


}
