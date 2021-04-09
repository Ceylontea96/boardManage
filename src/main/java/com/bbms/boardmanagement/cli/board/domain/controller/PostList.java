package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.SearchCondition;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.Rank;
import com.bbms.boardmanagement.cli.user.domain.RankPolicy;
import com.bbms.boardmanagement.cli.user.domain.User;

import java.util.List;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;
import static com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository.*;

public class PostList implements AppController { // 글 목록 보여주기

    private static AppController appController;
    PostRepository postRepository = new MemoryPostRepository();

    @Override
    public void start() {
        while (true) {
            List<Post> abc = postRepository.searchPostList("", SearchCondition.ALL);
            postRepository.showList(abc);
            allDocumentIndexScreen();
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 자세히
                    System.out.println("게시글 번호를 입력해주세요.");
                    int postNum = inputInteger(">>> ");
                    if (postRepository.integrity(abc).contains(postNum)) {
                        ReadMore.readMore(postNum); //상세보기 페이지
                    } else if (postNum == 0) {
                        System.out.println("자세히 보기를 종료합니다.");
                    } else {

                        System.out.println("게시글 목록중에서 선택해주세요.");
                    }
                    break;
                case 2: //글 검색
                    appController = new PostSearch();
                    appController.start();
                    break;
                case 3: // 새 글쓰기
                    User userNow = getCurrentSession().getUserNow();
                    insertPost(userNow);
                    RankPolicy.changeRank(userNow);
                    continue;
//                    break;
                case 4: //돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
            }

        }//while 종료
    }

    //기능
    private void insertPost(User userNow) {
        System.out.println("\n====== 새로운 게시글을 추가합니다. ======");
        String title = inputString("# 제목: ");
        if (title.equals("0")) {
            System.out.println("게시글 추가를 종료합니다.");
        } else {
            String author = userNow.getNickName();
            String mainText = inputString("# 본문: ");
            if (mainText.equals("0")) {
                System.out.println("게시글 추가를 종료합니다.");
            } else {
                String authorCode = userNow.getUserCode();
                Rank userRank = userNow.getUserRank();

                //저장할 게시글 객체화
                Post post = new Post(title, author, mainText, authorCode, userRank);
                //저장소에 저장
                postRepository.posting(post, userNow);
                System.out.printf("\n### [%s] 게시글이 정상 추가되었습니다.\n", post.getTitle());
            }
        }
    }
}
