package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.controller.changePost.ChangePost;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.comment.Comment;
import com.bbms.boardmanagement.cli.comment.repository.CommentRepository;
import com.bbms.boardmanagement.cli.comment.repository.MemoryCommentRepository;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class ReadMore {

    public static void readMore(int postNum) {


        while (true) {
            PostRepository postRepository = new MemoryPostRepository();
            User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
            Post postNow = postRepository.searchSpecificPost(postNum);
            postRepository.readMore(postNow);


            allDocumentIndexSearchMoreScreen();
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 수정
                    if (userNow.getUserCode().equals(postNow.getAuthorCode())) {
                        modify(postNow);
                    } else {
                        System.out.println("작성자 본인만 수정할 수 있습니다.");
                    }
                    break;
                case 2: // 삭제
                    if (userNow.getUserCode().equals(postNow.getAuthorCode())) {
                        delete(postNow);
                        return;
                    } else {
                        System.out.println("작성자 본인만 수정할 수 있습니다.");
                    }
                case 3: // 추천
                    postNow.recommend++;
                    System.out.println("추천되었습니다.");
                    // 글 추천 로직
                    break;
                case 4: // 댓글 쓰기
                    comment(postNow, userNow);
                    break;
                case 5: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    return;
            }
        }


    }

    //기능
    //수정
    private static void modify(Post post) {
        PostRepository postRepository = new MemoryPostRepository();
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();

        if (userNow.getUserCode().equals(post.getAuthorCode())) {
            modifyChoiceScreen();
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: //제목 수정
                    System.out.println("새로운 제목을 입력해주세요.");
                    String newTitle = inputString(">>> ");
                    postRepository.changeTitle(post.getPostNumber(), newTitle);

                    break;
                case 2: //본문 수정
                    System.out.println("새로운 본문을 입력해주세요.");
                    String newText = inputString(">>> ");
                    postRepository.changeText(post.getPostNumber(), newText);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    return;
            }
        } else {
            System.out.println("작성자 본인만 수정할 수 있습니다.");
        }
    }

    //삭제
    private static void delete(Post post) {
        PostRepository postRepository = new MemoryPostRepository();
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
        if (userNow.getUserCode().equals(post.getAuthorCode())) {
            postRepository.removePost(post.getPostNumber());
            System.out.println("게시글이 삭제되었습니다.");
        } else {
            System.out.println("작성자 본인만 삭제할 수 있습니다.");
        }
    }

    //댓글 쓰기
    private static void comment(Post post, User user) {
        CommentRepository commentRepository = new MemoryCommentRepository();
        System.out.println("댓글 내용을 입력하세요.");
        String ment = inputString(">>> ");
        Comment comment = new Comment(ment, user.getUserCode());
        commentRepository.addComment(comment, user);
        System.out.println("댓글이 추가되었습니다.");
    }



}
