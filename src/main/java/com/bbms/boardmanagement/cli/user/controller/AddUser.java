package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.main.AppController;
import com.bbms.boardmanagement.cli.user.domain.CheckCondition;
import com.bbms.boardmanagement.cli.user.domain.User;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import java.time.LocalDate;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.inputInteger;
import static com.bbms.boardmanagement.cli.board.ui.AppUI.inputString;

public class AddUser implements AppController {
    UserRepository userRepository = new MemoryUserRepository();

    @Override
    public void start() {

        signUp();

    } //회원가입 기능


    //쪼갤예정
    private void signUp() {
        while (true) {
            System.out.println("아이디를 입력해주세요.");
            String id = inputString(">>> ");
            if(id.equals("0")){
                break;
            }
            boolean idOk = userRepository.isOverlap(id, CheckCondition.ID);
            if (!idOk) {
                while (true) {
                    System.out.println("비밀번호를 입력해주세요.");
                    String pw = inputString(">>> ");
                    if(pw.equals("0")){
                        break;
                    }
                    System.out.println("비밀번호를 확인하겠습니다. 비밀번호를 다시 입력해주세요.");
                    String pw2 = inputString(">>> ");
                    if (pw.equals(pw2)) {
                        while (true) {
                            System.out.println("닉네임을 입력해주세요.");
                            String nickName = inputString(">>> ");
                            if(nickName.equals("0")){
                                break;
                            }
                            boolean nickOk = userRepository.isOverlap(nickName, CheckCondition.NICKNAME);
                            if (!nickOk) {
                                while (true) {
                                    System.out.println("출생년도를 입력해주세요.");
                                    int birthYear = inputInteger(">>> ");
                                    if(birthYear == 0){
                                        break;
                                    }
                                    System.out.println("출생월을 입력해주세요.");
                                    int birthMonth = inputInteger(">>> ");
                                    if(birthMonth == 0){
                                        break;
                                    }
                                    System.out.println("출생일을 입력해주세요.");
                                    int birthDay = inputInteger(">>> ");
                                    if(birthDay == 0){
                                        break;
                                    }
                                    if (LocalDate.now().getYear() >= birthYear && birthYear > 0) {
                                        if(birthMonth <= 12 && birthMonth > 0) {
                                            if (birthDay <= 31 && birthDay > 0) {
                                                System.out.println("성별을 입력해주세요.");
                                                String gender = inputString(">>> ");
                                                if(gender.equals("0")){
                                                    break;
                                                }
                                                System.out.println("자기소개를 입력해주세요.");
                                                String intro = inputString(">>> ");
                                                if(intro.equals("0")){
                                                    break;
                                                }
                                                User user = new User(id, pw, nickName, birthYear, birthMonth, birthDay, gender, intro);
                                                userRepository.join(user);

                                                System.out.println("회원가입이 성공적으로 완료되었습니다.");
                                                return;
                                            } else {
                                                System.out.println("유효하지 않은 숫자입니다.");
                                            }
                                        } else {
                                            System.out.println("유효하지 않은 숫자입니다.");
                                        }
                                    } else {
                                        System.out.println("유효하지 않은 숫자입니다.");
                                    }

                                }
                            } else {
                                System.out.println("이미 존재하는 닉네임입니다.");
                            }
                        }
                    } else {
                        System.out.println("비밀번호가 다릅니다.");
                    }
                }
            } else {
                System.out.println("이미 존재하는 id입니다.");
            }

        }//end while
    }



}
