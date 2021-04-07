package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class MyPost implements AppController { //내 글 보기
    private static AppController appController;

    UserRepository userRepository = new MemoryUserRepository();
    PostRepository postRepository = new MemoryPostRepository();

    List<Integer> postNums = new ArrayList<>();

    @Override
    public void start() {
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();

        while (true) {
            userRepository.myPost(userNow);
            if (userNow.getMyPost().size() == 0) {
                System.out.println("내가 작성한 글이 없습니다.");
                return;
            }

            for (int key : userNow.getMyPost().keySet()) {
                Post post = userNow.getMyPost().get(key);

                postNums.add(post.getPostNumber());
            }
            myDocumentAndCommentScreen();
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 수정
                    //글 수정 기능
                    modify();
                    break;
                case 2: // 삭제
                    //글 삭제 기능
                    delete();
                    break;
                case 3: // 자세히 보기
                    appController = new PostDetail();
                    break;
                case 4: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    return;

            }
            appController.start();
        }
    }

    //기능
    //수정
    public void modify() {
        while (true) {
            System.out.println("수정할 게시글 번호를 입력해주세요.");
            int postNumber = inputInteger(">>> ");
            if (postNums.contains(postNumber)) {
                modifyChoiceScreen();
                int selection = inputInteger(">>> ");
                switch (selection) {
                    case 1: //제목 수정
                        System.out.println("새로운 제목을 입력해주세요.");
                        String newTitle = inputString(">>> ");
                        postRepository.changeTitle(postNumber, newTitle);
                        break;
                    case 2: //본문 수정
                        System.out.println("새로운 본문을 입력해주세요.");
                        String newText = inputString(">>> ");
                        postRepository.changeText(postNumber, newText);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("잘 못 입력하셨습니다.");
                        return;
                }
            } else {
                System.out.println("내 게시글 목록중에서만 수정할 수 있습니다.");
                continue;
            }
            postRepository.readMore(postRepository.searchSpecificPost(postNumber));
            break;

        }

    }

    //삭제
    public void delete() {
        while (true) {
            System.out.println("삭제할 게시글 번호를 입력해주세요.");
            int postNumber = inputInteger(">>> ");
            if (postNums.contains(postNumber)) {
                postRepository.removePost(postNumber);
                System.out.println("게시글이 정상적으로 삭제되었습니다.");
            } else {
                System.out.println("내 게시글 목록중에서만 삭제할 수 있습니다.");
                continue;
            }
            break;
        }
    }


}
