package com.bbms.boardmanagement.cli.user.controller;

import com.bbms.boardmanagement.cli.board.ui.AppUI;
import com.bbms.boardmanagement.cli.user.repository.MemoryUserRepository;
import com.bbms.boardmanagement.cli.user.repository.UserRepository;

import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class ChangeNickName {
    private final UserRepository userRepository;
    public ChangeNickName() {
        userRepository = new MemoryUserRepository();
    }

   private void changeNickName(){
       System.out.println("변경할 닉네임 입력");
       String newNickName = inputString(">>> ");
//       userRepository.changeNickName(nowUserCode, newNickName);
   }





}
