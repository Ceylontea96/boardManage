package com.bbms.boardmanagement.cli.comment.repository;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.comment.Comment;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryCommentRepository implements CommentRepository {


    private final static Map<Integer, Comment> commentMemoryDB = new HashMap<>();

    static {
        insertTestData();
    }

    private static void insertTestData() {

       Comment comment1 = new Comment("무플", "글쓴이1");
       Comment comment2 = new Comment("아무내용", "글쓴이2");
       Comment comment3 = new Comment("아무런 내용도 없음", "글쓴이3");

       commentMemoryDB.put(comment1.getCommentNumber(), comment1);
       commentMemoryDB.put(comment2.getCommentNumber(), comment2);
       commentMemoryDB.put(comment3.getCommentNumber(), comment3);

    }

    @Override
    public void addComment(Comment comment, User user) {
        commentMemoryDB.put(comment.getCommentNumber(), comment);
        user.addMyComment(comment);
    }

    @Override
    public void deleteComment(int commentNumber) {

    }

    @Override
    public void showComments() {

    }

    @Override
    public void changeComment(int commentNumber, String newText) {
        for (int key : commentMemoryDB.keySet()) {
            if(commentNumber == key) {
                commentMemoryDB.get(key).setCommentText(newText);
            }
        }
    }
}
