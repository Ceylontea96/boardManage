package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.comment.Comment;
import com.bbms.boardmanagement.cli.comment.repository.CommentRepository;
import com.bbms.boardmanagement.cli.comment.repository.MemoryCommentRepository;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class MyComment implements AppController { // 내 댓글

    UserRepository userRepository = new MemoryUserRepository();


    List<Integer> commentNums = new ArrayList<>();

    @Override
    public void start() {
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();

        while (true) {
            userRepository.myComment(userNow);
            if (userNow.getMyComment().size() == 0) {
                System.out.println("내가 작성한 댓글이 없습니다.");
                return;
            }

            for (int key : userNow.getMyComment().keySet()) {
                Comment comment = userNow.getMyComment().get(key);

                commentNums.add(comment.getCommentNumber());
            }
            myDocumentAndCommentScreen(); // 내 댓글 메뉴
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 수정
                    modify();
                    break;
                case 2: // 삭제
                    delete();
                    break;
                case 3: // 자세히 보기
                    movePost();
                    break;
                case 4: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
            }
        }//while 종료
    }


    //기능
    private void movePost() {
        while (true){
            CommentRepository commentRepository = new MemoryCommentRepository();
            System.out.println("자세히 볼 댓글 번호를 입력해주세요.");
            int commentNum = inputInteger(">>> ");
            if(commentNum == 0) {
                System.out.println("자세히 보기를 종료합니다");
                break;
            }
            Comment targetComment = commentRepository.findCommentByCommentNum(commentNum);
            ReadMore.readMore(targetComment.getcMainPostNum());
            break;


        }//while 종료
    }

    public void modify() {
        CommentRepository commentRepository = new MemoryCommentRepository();
        UserRepository userRepository = new MemoryUserRepository();
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
        while (true) {
            System.out.println("수정할 댓글 번호를 입력해주세요.");
            int commentNum = inputInteger(">>> ");
            if (commentNum == 0) {
                System.out.println("댓글 수정을 종료합니다.");
                break;
            }
            if (commentNum > 0 && commentNum <= userNow.getMyComment().size()) {
                List<Integer> targetCommentNums = userNow.findCommentNumber();
                int targetCommentNum = targetCommentNums.get(commentNum-1);
                System.out.println("새로운 댓글 내용을 입력해주세요.");
                String newComment = inputString(">>> ");
                if(newComment.equals("0")){
                    System.out.println("댓글 수정을 종료합니다.");
                    break;
                }
                commentRepository.changeComment(targetCommentNum, newComment);
                System.out.println("댓글 수정 완료!");
                return;
            } else {
                System.out.println("내 댓글 목록중에서만 수정할 수 있습니다.");
            }
        }
    }

    public void delete() {
        CommentRepository commentRepository = new MemoryCommentRepository();
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
        while (true) {

            System.out.println("삭제할 댓글 번호를 입력해주세요.");
            int commentNum = inputInteger(">>> ");
            if(commentNum == 0){
                System.out.println("댓글 삭제를 종료합니다");
                break;
            }
            if (commentNum > 0 && commentNum <= userNow.getMyComment().size()) {
                List<Integer> targetCommentNums = userNow.findCommentNumber();
                int targetCommentNum = targetCommentNums.get(commentNum-1);
                System.out.println("정말 삭제하시겠습니까?");
                System.out.println("1. 네,  2. 아니요");
                int selection = inputInteger(">>> ");
                if (selection == 1) {
                    commentRepository.deleteComment2(targetCommentNum);
                    System.out.println("댓글 삭제 완료!");
                } else {
                    System.out.println("댓글 삭제를 취소했습니다.");
                }
                break;
            } else {
                System.out.println("내 댓글 목록중에서만 삭제할 수 있습니다.");
            }
        }
    }
}

