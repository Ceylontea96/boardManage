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

    UserRepository userRepository = new MemoryUserRepository();
    PostRepository postRepository = new MemoryPostRepository();

    List<Integer> postNums = new ArrayList<>();

    @Override
    public void start() {
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
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
        while (true) {
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
                    //자세히 보기 기능
                    break;
                case 4: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    return;

            }
        }
    }

    //기능
    //수정
    private void modify() {
        while (true) {
            modifyChoiceScreen();
            int selection = inputInteger(">>> ");

            int postNumber;
            switch (selection) {
                case 1:
                    postNumber = inputInteger("수정할 게시글 번호를 입력해주세요: ");
                    if (postNums.contains(postNumber)) {
                        String newTitle = inputString("새로운 제목을 입력해주세요.");
                        postRepository.changeTitle(postNumber, newTitle);
                    } else {
                        System.out.println("내 게시글 목록중에서만 수정할 수 있습니다.");
                    }

                    break;
                case 2:
                    postNumber = inputInteger("수정할 게시글 번호를 입력해주세요: ");
                    if (postNums.contains(postNumber)) {
                        System.out.println("새로운 본문을 입력해주세요.");
                        String newText = inputString(">>> ");
                    } else {
                        System.out.println("내 게시글 목록중에서만 수정할 수 있습니다.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    return;
            }
            postRepository.readMore(postRepository.searchSpecificPost(postNumber));
        }

    }

    //삭제
    private void delete() {
        while (true) {
            System.out.println("삭제할 게시글 번호를 입력해주세요.");
            int postNumber = inputInteger(">>> ");

            if (postRepository.searchSpecificPost(postNumber) == null) {
                System.out.println("해당 게시글은 존재하지 않습니다.");
                continue;
            }
            if (postNums.contains(postNumber)) {
                postRepository.removePost(postNumber);
                System.out.println("게시글이 정상적으로 삭제되었습니다.");
            }
        }
    }


}
