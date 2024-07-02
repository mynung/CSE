import java.util.Scanner;

public class testMain {
    public static Scanner scanner = new Scanner(System.in);
    static final int ROW = 4;
    static final int COL = 3;

    public static void main(String[] args) {
        System.out.println("202311264 김민홍");
        String[][] parkingSpace = new String[ROW][COL];
        String[][] parkingCarNum = new String[ROW][COL];

        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                parkingSpace[i][j] = "♡";
            }
        }
        for(int i=0;i<ROW;i++)
            for(int j=0;j<COL;j++)
                parkingCarNum[i][j] = "";

        while(true){

            printParking(parkingSpace);
            System.out.println("1) 주차하기 2) 차량검색 3) 출차하기 4) 종료");
            System.out.print("메뉴를 선택하세요 : ");
            int menuNum = scanner.nextInt();
            System.out.println();

            switch (menuNum){
                case 1->parking(parkingSpace, parkingCarNum);
                case 2->searching(parkingCarNum);
                case 3->takingOff(parkingSpace, parkingCarNum);
                case 4->sysExit();
                default -> System.out.println("메뉴 번호를 확인 후 다시 입력해 주세요.");
            }
            System.out.println();
        }
    }

    public static void printParking(String[][] parkingSpace){
        System.out.println("**** 주차 현황 ****");
        System.out.print(" ");
        for(int i=1;i<=COL;i++){
            System.out.printf("%3d", i);
        }
        System.out.println();
        for(int i=0;i<ROW;i++){
            System.out.printf("%2d", i+1);
            for(String space : parkingSpace[i])
                System.out.printf("%2s", space);
            System.out.println();
        }
        System.out.println();
    }

    public static void parking(String[][] parkingSpace, String[][] parkingCarNum){
        int setRow, setCol;
        String carNum;
        String check;
        System.out.println("**** 주차 하기 ****");
        System.out.print("주차할 위치를 선택해 주세요(입력예 : 2 1) : ");
        setRow = scanner.nextInt();
        setCol = scanner.nextInt();

        if(setRow<=0 || setRow>ROW || setCol <=0 || setCol>COL){
            System.out.println("위치 번호를 확인해 주세요. 처음부터 다시 시작해 주세요.");
            return;
        }
        if(parkingSpace[setRow-1][setCol-1].equals("♥")){
            System.out.println("다른 차량이 주차되어 있습니다. 처음부터 다시 시작해 주세요.");
            return;
        }

        System.out.print("차량 번호를 입력해 주세요(입력예 : 20가1234 ) : ");
        carNum = scanner.next();
        System.out.println("차량 번호 " + carNum+ " 맞습니까(y/n)? ");
        check = scanner.next();
        if(check.equals("n")){
            System.out.println("처음부터 다시 진행해 주세요.");
            return;
        }

        parkingSpace[setRow-1][setCol-1] = "♥";
        parkingCarNum[setRow-1][setCol-1] = carNum;
        System.out.println(carNum+"차량의 주차를 완료하였습니다.");

    }

    public static void searching(String[][] parkingCarNum){
        String carNum;
        System.out.println("**** 차량검색 ****");
        System.out.print("차량 번호를 입력해 주세요 : ");
        carNum = scanner.next();
        for(int i=0;i<ROW;i++)
            for(int j=0;j<COL;j++){
                if(parkingCarNum[i][j].equals(carNum)){
                    System.out.println(carNum+"는 ("+(i+1)+", "+(j+1)+")에 위치합니다.");
                    return;
                }
            }
        System.out.println("차량이 존재하지 않습니다. 차량번호 확인후 처음부터 다시 진행해 주세요.");
    }

    public static void takingOff(String[][] parkingSpace, String[][] parkingCarNum) {
        String carNum;
        System.out.println("**** 출차하기 ****");
        System.out.print("차량 번호를 입력해 주세요 : ");
        carNum = scanner.next();
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++) {
                if (parkingCarNum[i][j].equals(carNum)) {
                    parkingSpace[i][j] = "♡";
                    parkingCarNum[i][j] = "";
                    System.out.println(carNum+" 차량이 출차되었습니다. 안녕히 가세요.");
                    return;
                }
            }
        System.out.println("차량이 존재하지 않습니다. 차량번호 확인후 처음부터 다시 진행해 주세요.");
    }

    public static void sysExit(){
        System.out.println("시스템을 종료합니다.");
        scanner.close();
        System.exit(0);
    }
}
