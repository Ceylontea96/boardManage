package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.CheckCondition;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class ChangeMyData implements AppController { // 내 정보 변경

    UserRepository userRepository = new MemoryUserRepository();

    @Override
    public void start() {
        User userNow = MemoryUserRepository.getCurrentSession().getUserNow();
        while (true) {
        myInformationChangeScreen(); // 내 정보 변경 메뉴
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1: // 닉네임 변경
                    changeNickName(userNow);
                    break;
                case 2: // 비밀번호 변경
                    changePw(userNow);
                    break;
                case 3: // 내 소개 변경
                    changeIntro(userNow);
                    break;
                case 4: // 돌아가기
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다.");
                    continue;
            }
        }//while 종료
    }

    //기능
    //닉네임 변경
    private void changeNickName(User user) {
        System.out.println("닉네임을 변경합니다.");
        System.out.println("새로운 닉네임을 입력해주세요.");
        String newNick = inputString(">>> ");
        if (userRepository.isOverlap(newNick, CheckCondition.NICKNAME)) {
            //닉네임 중복이면
            System.out.println("이미 존재하는 닉네임입니다.");
        } else {
            user.setNickName(newNick);
            System.out.println("닉네임 변경이 완료되었씁니다.");
        }
    }

    //비밀번호 변경
    private void changePw(User user) {
        System.out.println("비밀번호를 변경합니다.");
        System.out.println("새로운 비밀번호를 입력해주세요.");
        String newPw1 = inputString(">>> ");
        System.out.println("비밀번호 확인을 위해 다시 한 번 입력해주세요.");
        String newPw2 = inputString(">>> ");
        if(newPw1.equals(newPw2)) {
            user.setPassword(newPw1);
        } else {
            System.out.println("비밀번호가 다릅니다.");
        }
    }

    //내 소개 변경
    private void changeIntro(User user) {
        System.out.println("새로운 자기소개를 입력해주세요.");
        String newIntro = inputString(">>> ");
        user.setIntroduce(newIntro);
    }
}
