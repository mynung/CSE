package mhkim.finaltest;

import java.util.HashMap;
import java.util.Objects;

public class mhkimItem{
	String name; //제품이름
	String company; //제조회사
	int price; //제품가격	
	int count=0; // 신청개수
	HashMap<mhkimCustomer, Integer> cnt = new HashMap<mhkimCustomer, Integer>();
	
	public mhkimItem(String name, String company, int price) {
		super();
		this.name = name;
		this.company = company;
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof mhkimItem) {
			mhkimItem other = (mhkimItem) obj;
			return Objects.equals(name, other.name);
		}
		return this.equals(obj);
		
	}

	@Override
	public String toString() {
		return name + " / " + company + " / " + price + "원"+" / 주문개수 : "+count+"개";
	}

}
