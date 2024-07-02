package mhkim.finaltest;

import java.util.ArrayList;

public class mhkimCustomer {
	String customerID; // 회원아이디
	String customerName; // 회원이름
	int customerAge; // 회원나이
	ArrayList<mhkimItem> buyList = new ArrayList<mhkimItem>();
	int total;
	
	public mhkimCustomer(String customerID, String customerName, int customerAge) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAge = customerAge;		
	}

	@Override
	public String toString() {
		String str = "아이디 : " + customerID + "\n";
		str += "이  름 : " + customerName + "\n";
		str += "나  이 : " + customerAge + "\n";
		str += "-".repeat(15)+"\n";
		if(!buyList.isEmpty()) {
			for(mhkimItem item : buyList) {
				str += item+"\n";
			}
			str += "-".repeat(15)+"\n";
			str += "총 신청금액 : "+this.total+"원";
		}else {
			str += "공동 구매 신청한 내역이 없습니다.";
		}
		
		
		return str + "\n";
	}

}
