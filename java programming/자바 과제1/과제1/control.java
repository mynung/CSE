package week03;

import java.util.Random;
import java.util.Scanner;

public class control {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("202311264 김민홍");
        while(true){
            int input = selectMenu();
            switch (input){
                case 1->cuponAndChoco();
                case 2-> puzzle();
                case 3->gugudan();
                case 4-> sysExit();
                default -> System.out.println("잘못된 입력");
            }
        }
    }

    public static int selectMenu(){
        System.out.print("1)쿠폰으로 초콜랏 구매하기 ");
        System.out.print("2)문자 산수 퍼즐 ");
        System.out.print("3)구구단 출력하기 ");
        System.out.print("4)종료\n");
        System.out.print("메뉴를 선택하세요 :");
        int menu = scanner.nextInt();
        return menu;
    }

    public static void cuponAndChoco(){
        int cupon;
        int choco;
        System.out.println("문제1. 쿠폰으로 초콜릿 구매하기");
        System.out.print("돈을 넣어 주세요 :");

        int money = scanner.nextInt();
        choco = money;
        cupon = choco;
        while(cupon>=7){
            int addTmp = 0;
            addTmp = cupon/7;
            cupon %= 7;
            cupon += addTmp;
            choco += addTmp;
        }

        System.out.println("-".repeat(20));
        System.out.println("초콜릿 "+choco+"개, 쿠폰 "+cupon+"개");
        System.out.println("-".repeat(20));
    }

    public static void sysExit(){
        scanner.close();
        System.out.println("프로그램을 종료합니다");
        System.exit(0);
    }

    public static void puzzle(){
        Random random = new Random();
        int[] num = new int[4];
        int GOOD;
        int TOO;

        do{
            for(int i=0;i<num.length;i++){
                num[i] = random.nextInt(10);
                for(int j=0;j<i;j++){
                    if(num[i] == num[j]){
                        i--;
                    }
                }
            }

            GOOD = num[2] * 1000 + num[1]*100 + num[1]*10 + num[3];
            TOO = num[0]*100 + num[1]*10 + num[1];
        }while(GOOD != TOO*4);
        System.out.println("T = "+ num[0] + " O = "+ num[1]+" G = " + num[2]+ " D = "+ num[3]);
        System.out.println();
    }

    public static void gugudan(){
        int printNum; //가로로 출력할 단수
        System.out.println("=".repeat(5)+"구구단 출력하기"+"=".repeat(5));
        while(true){
            System.out.print("출력 단수 : ");
            printNum = scanner.nextInt();
            if(printNum >= 1 && printNum <=8){
                break;
            }
            System.out.println("출력 단수 입력이 잘못 되었습니다.");
        }
        int cnt =2; //몇단까지 출력이 되었는지 나타내는 변수
        int tmp=0;
        while(cnt <=9){
            for(int i=1;i<=9;i++) {
                for (int j = cnt; (j < printNum + cnt) && (j<10); j++) {
                    System.out.printf("%2d *%2d = %2d", j, i, i*j);
                    System.out.print("\t\t");
                    tmp = j;
                }
                System.out.println();
            }
            cnt = ++tmp;
            System.out.println("\n");
        }
    }
}
