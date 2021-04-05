package com.bbms.boardmanagement.cli.board.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

//역할: 화면 출력 텍스트 모음 클래스
public abstract class AppUI {


    private static Scanner sc = new Scanner(System.in);

    //입력 유틸메서드
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

    //문자열 입력 메서드
    public static String inputString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}
