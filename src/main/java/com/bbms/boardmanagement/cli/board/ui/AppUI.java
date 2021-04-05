package com.bbms.boardmanagement.cli.board.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AppUI {
   private static Scanner sc = new Scanner(System.in);

    //정수 입력 메서드
    public static int inputInteger(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("정수로 입력해주세요!");
            } finally {
                sc.nextLine();
            }
        }
    }

    //메뉴의 번호를 입력 받는 메서드
    public static String inputString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}
