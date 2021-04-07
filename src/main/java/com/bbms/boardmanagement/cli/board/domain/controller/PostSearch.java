package com.bbms.boardmanagement.cli.board.domain.controller;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.board.domain.SearchCondition;
import com.bbms.boardmanagement.cli.board.repository.MemoryPostRepository;
import com.bbms.boardmanagement.cli.board.repository.PostRepository;
import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.main.AppController;
import javafx.geometry.Pos;

import java.util.List;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class PostSearch implements AppController {
    PostRepository postRepository = new MemoryPostRepository();

    @Override
    public void start() {
        while (true) {
            System.out.println("검색어를 입력해주세요.");
            String keyword = inputString(">>> ");
            System.out.println("검색 조건을 정해주세요.");
            System.out.println("### 1. 제목, 2. 작성자, 3. 내용, 4. 제목+내용, 5. 돌아가기");
            int condition = inputInteger(">>> ");

            List<Post> results = null;
            switch (condition) {
                case 1:
                    results = postRepository.searchPostList(keyword, SearchCondition.TITLE);
                    break;
                case 2:
                    results = postRepository.searchPostList(keyword, SearchCondition.AUTHOR);
                    break;
                case 3:
                    results = postRepository.searchPostList(keyword, SearchCondition.TEXT);
                    break;
                case 4:
                    results = postRepository.searchPostList(keyword, SearchCondition.TITLE_TEXT);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    continue;
            }
            if (results.size() == 0) {
                System.out.println("검색결과가 없습니다.");
                continue;
            }
            allDocumentIndexSearchScreen();
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 자세히 보기
                    System.out.println("게시글 번호를 입력해주세요.");
                    int postNum = inputInteger(">>> ");
                    if (postRepository.integrity(results).contains(postNum)) {
                        ReadMore.readMore(postNum);
                    }
                    break;
                case 2: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    continue;
            }
        }//while 종료
    }
}
