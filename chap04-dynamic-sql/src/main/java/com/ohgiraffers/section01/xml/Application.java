package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.SearchCriteria;

import java.sql.SQLOutput;
import java.util.*;

public class Application {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);

        do{
            System.out.println("===== 마이바티스 동적 SQL =====");
            System.out.println("1. if 확인하기");
            System.out.println("2. choose 확인하기");
            System.out.println("3. foreach 확인하기");
            System.out.println("4. trim 확인하기 ");
            System.out.println("9. 종료하기");
            System.out.println("메뉴를 선택하세요 ");
            int no = scr.nextInt();

            switch(no){
                case 1: ifSubMenu(); break;
                case 2: chooseSubMenu(); break;
                case 3: foreachSubMenu(); break;
                case 9:
                    System.out.println("프로그램을 종료합니다");return;
                default:
                    System.out.println("잘못된 번호입니다.");break;
            }

        }while (true);

    }

    private static void ifSubMenu(){

        Scanner scr = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("===== if 서브 메뉴 =====");
            System.out.println("1. 원하는 금역대에 적합한 추천 메뉴 목록 보여주기");
            System.out.println("2. 메뉴 이름 혹은 카테고리명으로 검색하여 메뉴 목록 보여주기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력 해주세요 : ");
            int no = scr.nextInt();

            switch (no){
                case 1: menuService.selectMenuByPrice(inputPrice()); break;
                case 2: menuService.searchMenu(inputSearchCriteria()); break;
                case 9 : return;
            }

        }while(true);
    }

    private static int inputPrice(){
        Scanner scr = new Scanner(System.in);
        System.out.println("검색하실 가격의 최대 금액을 입력 해주세요 : ");
        int price = scr.nextInt();
        return price;
    }

    private static SearchCriteria inputSearchCriteria(){
        Scanner scr = new Scanner(System.in);

        System.out.println("검색 기준을 입력 해주세요 (name or category) : ");
        String condition = scr.nextLine();
        System.out.println("검색어를 입력 해주세요 : ");
        String value = scr.nextLine();

        return new SearchCriteria(condition, value);
    }

    private static void chooseSubMenu(){
             // java 의 switch 역할
        Scanner scr = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do{
            System.out.println("====== choose 서브 메뉴 ======");
            System.out.println("1. 카테고리 상위 분류별 메뉴 보여주기(식사, 음료, 디저트)");
            System.out.println("9. 이전 메뉴로 ");
            int no = scr.nextInt();

            switch (no){
                case 1 : menuService.searchMenuBySupCategory(inputSupCategory());break;
                case 9 : return;
            }
        } while(true);
    }

    private static SearchCriteria inputSupCategory(){
        Scanner scr = new Scanner(System.in);
        System.out.println("상위 분류를 선택 해주세요 (식사, 음료, 디저트)");
        String value = scr.nextLine();

        return new SearchCriteria("category", value);
    }

    private static void foreachSubMenu(){
        Scanner scr = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do{
            System.out.println("===== foreach 서브 메뉴 =====");
            System.out.println("1. 랜덤한 메뉴 5개 추출해서 조회하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("번호를 입력 해주세요 : ");
            int no = scr.nextInt();

            switch (no){
                case 1 : menuService.searchMenuByRandomMenuCode(createRandomMenuCodeList()); break;
                case 9 : return;
            }
        }while(true);
    }

    public static List<Integer> createRandomMenuCodeList(){
        Set<Integer> set = new HashSet<>();
        while(set.size() < 5){   // 해당 코드의 번호가 없으면 없이 출력되므로 5개가 꼭 안나온다!
            int temp = (int) (Math.random()*21)+1;
            set.add(temp);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    private static void trimSubMenu(){
        Scanner scr = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do{
            System.out.println("===== trim 서브 메뉴 =====");
            System.out.println("1. 검색조건이 있는 경우 메뉴코드로 조회, 단 없으면 전체 조회");
            System.out.println("2. 메뉴 혹은 카테고리 코드로 검색. 단, 메뉴와 카테고리 코드" +
                    "둘 다 일치하는 경우도 검색하며, 검색조건이 없는 경우 전체 조회");
            System.out.println("3. 원하는 메뉴 정보만 수정하기");
            System.out.println("9. 이전 메뉴로");
            int no = scr.nextInt();

            switch (no){
                case 1 : menuService.searchMenuByCodeOrSearchAll(inputAllOrOne()); break;

            }
        }while(true);
    }

    private static SearchCriteria inputAllOrOne(){
        Scanner scr = new Scanner(System.in);
        System.out.println("검색 조건을 입력 하시겠습니까?");
        boolean hasSearchValue = "예".equals(scr.nextLine())? true:false;

        SearchCriteria searchCriteria = new SearchCriteria();
        if(hasSearchValue){
            System.out.println("검색할 메뉴 코드를 입력 해주세요 : ");
            String code = scr.nextLine();
            searchCriteria.setCondition("menuCode");
            searchCriteria.setValue(code);
        }
        return searchCriteria;
    }
}
