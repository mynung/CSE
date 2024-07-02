package mhkim.finaltest;
public class mhkimTestMain {

	public static void main(String[] args) {

		System.out.println("1) 학번이름 출력하기");
		System.out.println("202311264 김민홍");
		
		System.out.println("**********************************\n");
		
		System.out.println("2) KuAuction 클래스 객체 생성");
		mhkimKuAuction auction = new mhkimKuAuction("customers.txt");
		
		System.out.println("**********************************\n");
		
		System.out.println("3) auction 객체 출력하기");
		System.out.println(auction);
		
		System.out.println("**********************************\n");
		
		System.out.println("4) 공동구매 제품 불러오기");
		auction.readItemFile("items.txt");
		
		System.out.println("**********************************\n");
		
		System.out.println("5) 고객 아이디로 고객 검색하기");
		mhkimCustomer user1 = auction.searchCustomer("orangejoa");		
		mhkimCustomer user2 = auction.searchCustomer("greenjoa");
		mhkimCustomer user3 = auction.searchCustomer("bluejoa");
		mhkimCustomer user4 = auction.searchCustomer("redjoa");
		
		System.out.println("**********************************\n");
		
		System.out.println("6) 제품이름으로 제품 검색하기");
		mhkimItem item1 = auction.searchItem("삼성TV");
		if(item1!=null)
			System.out.println(item1);
		
		System.out.println("**********************************\n");
		
		System.out.println("7) 제품객체로 제품 검색하기");
		mhkimItem item2 = auction.searchItem(new mhkimItem("건국TV", "", 0));
		System.out.println(item2);
		
		mhkimItem item3 = auction.searchItem("LGTV");
		mhkimItem item4 = auction.searchItem("건국냉장고");
		mhkimItem item5 = auction.searchItem(new mhkimItem("삼성냉장고", "", 0));
		mhkimItem item6 = auction.searchItem("LG냉장고");
		
		System.out.println("**********************************\n");
		
		System.out.println("8) 고객이 공동구매 신청하기");
		boolean buyResult = auction.join(user1, item1);
		
		if(buyResult)
			System.out.println("신청 완료");
		else
			System.out.println("신청 실패");
		
		auction.join(user1, item4);
		
		System.out.println(user1);
		
		auction.join(user2, item1);
		auction.join(user2, item3);
		auction.join(user2, item2);
		auction.join(user2, item2);
		
		System.out.println(user2);
		
		auction.join(user3, item6);
		auction.join(user3, item1);
		auction.join(user3, item1);
		auction.join(user3, item1);
		
		auction.join(user4, item5);
		auction.join(user4, item5);
		auction.join(user4, item3);
		
		System.out.println("**********************************\n");
//		
		System.out.println("9) 고객이 공동 구매 신청한 제품 취소하기");		
		auction.cancel(user2, item2);
		System.out.println(user2);
		auction.cancel(user2, item2);
		System.out.println(user2);	
		auction.cancel(user2, item4);
		
		System.out.println("**********************************\n");
//		
//		System.out.println("10) 총 공동구매 신청금액이 가장 많은 고객 찾기");
//		System.out.println(auction.getBestCustomer());
//		
//		System.out.println("**********************************\n");
//		
//		System.out.println("11) 가장 많은 공동구매 신청한 제품 2개 찾기");
//		System.out.println(auction.getBestItem(2));
//				
//		System.out.println("**********************************\n");
//		
//		System.out.println("12) 4000원 이상인 제품 찾기");
//		System.out.println(auction.searchItems(4000));		
//		
//		System.out.println("**********************************\n");
//		
//		System.out.println("13) 구매 신청금액이 큰 고객 순으로 출력하기");
//		System.out.println(auction);
//		
//		System.out.println("**********************************\n");
//		
//		System.out.println("14) 프레임 생성하기");
//		mhkimMyFrame f = new mhkimMyFrame(auction);
	}	
}
