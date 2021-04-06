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

    //1.최초 화면
    public static void startScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 로그인");
        System.out.println("### 2. 회원가입");
        System.out.println("### 3. 프로그램 종료");
        System.out.println("=======================================");
    }

    //2.메인 목차
    public static void boardMainScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 내 정보");
        System.out.println("### 2. 글 목록");
        System.out.println("### 3. 로그아웃");
        System.out.println("=======================================");
    }

    //3.내 정보 메뉴
    public static void myInformationScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 내 글");
        System.out.println("### 2. 내 댓글");
        System.out.println("### 3. 내 정보 변경");
        System.out.println("### 4. 회원 탈퇴");
        System.out.println("### 5. 돌아가기");
        System.out.println("=======================================");
    }
    //4.글 목록 메뉴
    public static void allDocumentIndexScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 자세히");
        System.out.println("### 2. 글 검색");
        System.out.println("### 3. 새 글쓰기");
        System.out.println("### 4. 돌아가기");
        System.out.println("=======================================");
    }
    //5. 내 글 메뉴, 내 댓글 메뉴 (공동으로 사용)
    public static void myDocumentAndCommentScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 수정");
        System.out.println("### 2. 삭제");
        System.out.println("### 3. 자세히 보기");
        System.out.println("### 4. 돌아가기");
        System.out.println("=======================================");
    }
    //6. 내 정보 변경 메뉴
    public static void myInformationChangeScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 닉네임 변경");
        System.out.println("### 2. 비밀번호 변경");
        System.out.println("### 3. 개인정보 변경");
        System.out.println("### 4. 돌아가기");
        System.out.println("=======================================");
    }

    //7. 글 목록, 글 검색 제세히 보기 매뉴 (공동으로 사용)
    public static void allDocumentIndexSearchMoreScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 수정");
        System.out.println("### 2. 삭제");
        System.out.println("### 3. 추천");
        System.out.println("### 4. 댓글 쓰기");
        System.out.println("### 5. 돌아가기");
        System.out.println("=======================================");
    }

    //8. 글 검색 메뉴
    public static void allDocumentIndexSearchScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 자세히 보기");
        System.out.println("### 2. 돌아가기");
        System.out.println("=======================================");
    }

    //9. 수정 내용 메뉴
    public static void modifyChoiceScreen() {
        System.out.println("\n============== 메뉴 =================");
        System.out.println("### 1. 제목 수정하기");
        System.out.println("### 2. 본문 수정하기");
        System.out.println("### 3. 돌아가기");
        System.out.println("=======================================");
    }



}
