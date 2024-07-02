package midtest.class0416;

public class MhkimTestMain {

	public static void main(String[] args) {
		System.out.println("1) 학번이름 출력하기");
		System.out.println("202311264 김민홍");
		
		System.out.println("2) Product 클래스 만들기");
		//MhkimProduct product = new MhkimProduct("Tv", 1000);
		
		System.out.println("3) HA 클래스 만들기");
		MhkimProduct ha1 = new MhkimHA("건국TV1", 1000, "1등급");
		System.out.println(ha1);
		MhkimProduct ha2 = new MhkimHA("건국TV2", 1000, "3등급");
		System.out.println(ha2);
		
		System.out.println("4) Snack 클래스 만들기");
		MhkimProduct snack = new MhkimSnack("새우깡", 500, "농심");
		System.out.println(snack);
		
		System.out.println("5) Clothing 클래스 만들기");
		MhkimProduct clothing = new MhkimClothing("청바지", 1000, "여름");
		System.out.println(clothing);
		
		System.out.println("6) Kupang 클래스 만들기 및 제품 입고하기");
		MhkimKupang shop = new MhkimKupang(5);
		shop.inBound(new MhkimHA("건국TV1", 1000, "1등급"));
		shop.inBound(new MhkimHA("건국TV2", 1000, "3등급"));
		shop.inBound(new MhkimSnack("새우깡", 500, "농심"));
		shop.inBound(new MhkimClothing("청바지", 1000, "여름"));
		shop.inBound(new MhkimClothing("청바지", 1000, "가을"));
		shop.inBound(new MhkimClothing("청바지", 1000, "가을"));
		
		System.out.println();
		
		System.out.println("7) 제품 검색하기");
		MhkimProduct[] sResult1 = shop.searchItem("건국TV2");
		System.out.println("=====TV2 검색 결과=====");
		if (sResult1 != null) {
			for (MhkimProduct item : sResult1) {
				System.out.println(item);				
			}			
		}
		
		MhkimProduct[] sResult2 = shop.searchItem("청바지");
		System.out.println("=====청바지 검색 결과=====");
		if (sResult2 != null) {
			for (MhkimProduct item : sResult2) {
				System.out.println(item);				
			}
		}
		
		System.out.println();
		
		System.out.println("8) 회원전용 할인제품 검색하기");
		MhkimMBSProduct[] sResult3 = shop.searchMBSProduct();
		if(sResult3!=null) {
			for(MhkimMBSProduct item : sResult3) {
				System.out.println(item);
			}
		}
		
		System.out.println();

		System.out.println("9) Client 클래스 만들기, 객체생성 및 출력하기");
		MhkimClient gdhong = new MhkimClient("홍길동", 3, true);
		System.out.println(gdhong);
		MhkimClient gdkim = new MhkimClient("김길동", 4, false);
		
		
		System.out.println();

		System.out.println("10) 주문하기");
		System.out.println("=== 홍길동 주문 ===");
		gdhong.order(sResult1[0]);
		gdhong.order(sResult1[0]);
		gdhong.order(sResult2[1]);
		gdhong.order(sResult3[0]);
		gdhong.order(sResult2[1]);
		gdhong.order(sResult3[1]);
		gdhong.order(sResult3[2]);
		
		System.out.println();
		
		
		System.out.println("=== 김길동 주문 ===");
		gdkim.order(sResult1[0]);
		gdkim.order(sResult2[0]);
		gdkim.order(sResult3[0]);
		gdkim.order(sResult2[0]);
		
		System.out.println();
		
	}

}
