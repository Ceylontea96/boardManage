package com.bbms.boardmanagement.cli.comment.repository;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.comment.Comment;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryCommentRepository implements CommentRepository {

    PostRepository postRepository = new MemoryPostRepository();

    private final static Map<Integer, Comment> commentMemoryDB = new HashMap<>();


    static {
        insertTestData();
    }

    private static void insertTestData() {

        User user = new User("isec3", "isec123", "아이섹2", 1888, 04, 03, "남자", "안녕하세요1");
        User user1 = new User("isec5", "isec123", "아이섹5", 1975, 05, 27, "남자", "안녕하세요2");
        User user2 = new User("isec10", "isec123", "아이섹10", 2001, 07, 03, "남자", "안녕하세요3");

        Comment comment1 = new Comment("무플", user);
        Comment comment2 = new Comment("아무내용", user1);
        Comment comment3 = new Comment("아무런 내용도 없음", user2);


        commentMemoryDB.put(comment1.getCommentNumber(), comment1);
        commentMemoryDB.put(comment2.getCommentNumber(), comment2);
        commentMemoryDB.put(comment3.getCommentNumber(), comment3);

    }

    @Override
    public void addComment(Comment comment, User user) {
        //DB에 댓글 추가
        commentMemoryDB.put(comment.getCommentNumber(), comment);

        //현재 유저의 댓글 목록에 댓글 추가
        MemoryUserRepository.getCurrentSession().getUserNow().addMyComment(comment);
        //현재 게시글의 댓글 목록에 댓글 추가
        Post postNow = MemoryPostRepository.getCurrentSession().getPostNow();
        postNow.addComment(comment);
    }

    @Override
    public void deleteComment(int commentNumber) {
        //DB에서 댓글 지우기
        commentMemoryDB.remove(commentNumber);

        //현재 유저의 댓글 목록에서 댓글 지우기
        MemoryUserRepository.getCurrentSession().getUserNow().delMyComment(commentNumber);
        //현재 게시글의 댓글 목록에서 댓글 지우기
        Post postNow = MemoryPostRepository.getCurrentSession().getPostNow();

//        List<Integer> commentList = postNow.findCommentNumber();
//        int targetNumber = commentList.get(commentNumber - 1);
        postNow.delComment(commentNumber);


    }

    public void deleteComment2(int commentNumber) {

        //현재 유저의 댓글 목록에서 댓글 지우기
        MemoryUserRepository.getCurrentSession().getUserNow().delMyComment(commentNumber);
        //게시글 댓글 목록에서 댓글 지우기
        Comment comment = findCommentByCommentNum(commentNumber);
        int targetPostNum = comment.getcMainPostNum();
        Post targetPost = postRepository.searchSpecificPost(targetPostNum);
        targetPost.delComment(commentNumber);

        //DB에서 댓글 지우기
        commentMemoryDB.remove(commentNumber);
    }

    @Override
    public int findComment(int commentNumber) {
        Post postNow = MemoryPostRepository.getCurrentSession().getPostNow();

        List<Integer> commentList = postNow.findCommentNumber();
        int targetNumber = commentList.get(commentNumber - 1);
        return targetNumber;
    }

    @Override
    public void showComments() {

    }

    @Override
    public void changeComment(int commentNumber, String newText) {
        for (int key : commentMemoryDB.keySet()) {
            if (commentNumber == key) {
                Comment comment = commentMemoryDB.get(key);
                comment.setChange(true);
                comment.setCommentText(newText);

            }
        }
    }

    @Override
    public Comment findCommentByCommentNum(int commentNumber) {
        Comment comment = null;
        for (int key : commentMemoryDB.keySet()) {
            if (commentNumber == key) {
                comment = commentMemoryDB.get(key);
            }
        }
        return comment;
    }
}
