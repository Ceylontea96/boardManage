package com.bbms.boardmanagement.cli.main;


import static com.bbms.boardmanagement.cli.board.ui.AppUI.*;

public class AppMain {
    public static void main(String[] args) {

        while(true) {
            startScreen();
            int selection = inputInteger(">>> ");
            FrontController.firstPage(selection); // 가장 첫 페이지
        }
    }
}
