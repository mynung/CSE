package mhkim.finaltest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;

public class mhkimKuAuction {
	HashMap<String, mhkimCustomer> customers; // 등록된 회원 정보
	HashSet<mhkimItem> items; // 공동구매 제품 정보
	Scanner reader;
	FileInputStream io;

	
	
	public mhkimKuAuction(String customerfile) {
		customers = new HashMap<String, mhkimCustomer>();
		try {
			io = new FileInputStream(customerfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일을 찾을 수 없습니다.");
		}
		
		reader = new Scanner(io);
		String[] tmp;
		int cnt=0;
		while(reader.hasNext()) {
			String str = reader.nextLine();
			tmp = str.split(" : ");
			if(tmp.length == 3) {
				if(!customers.containsKey(tmp[0])) {
					customers.put(tmp[0], new mhkimCustomer(tmp[0], tmp[1], Integer.parseInt(tmp[2])));
					cnt++;
				}else {
					System.out.println("증복된 아이디는 등록될 수 없습니다.");
				}
			}else {
				System.out.println("올바르지 않은 고객정보는 무시하고 계속 진행합니다.");
			}
		}
		System.out.println(cnt+"건의 고객정보가 등록되었습니다.");
		
		
	}
	
	public void readItemFile(String itemfile) {
		items = new HashSet<mhkimItem>();
		try {
			io = new  FileInputStream(itemfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일을 찾을 수 없습니다.");
		}
		reader = new Scanner(io);
		String tmp[];
		int cnt=0;
		while(reader.hasNext()) {
			String str = reader.nextLine();
			tmp = str.split(" : ");
			if(!items.contains(new mhkimItem(tmp[0], "", 0))) {
				items.add(new mhkimItem(tmp[0], tmp[1], Integer.parseInt(tmp[2])));
				cnt++;
			}else {
				System.out.println(tmp[0]+"은 중복으로 저장되지 않았습니다.");
			}
		}
		System.out.println(cnt+"건의 제품정보가 등록되었습니다.");
		
		
	}
	
	public mhkimCustomer searchCustomer(String sid) {
		if(customers.containsKey(sid)) {
			return customers.get(sid);
		}
		return null;
	}
	
	public mhkimItem searchItem(String name) {		
		for(mhkimItem item : items) {
			if(item.name.equals(name)) {
				return item;
			}
		}
		return null;

				
	}

	public mhkimItem searchItem(mhkimItem item) {
		ArrayList<mhkimItem> arr = new ArrayList<mhkimItem>();
		arr.addAll(items);
		arr.sort((o1, o2)->o1.hashCode()-o2.hashCode());
		for(mhkimItem i : items) {
			if(i.equals(item)) {
				return i;
			}
		}
		return null;			
	}
		
	
	public boolean join(mhkimCustomer client, mhkimItem item) {
		if(customers.containsKey(client.customerID)) {
			if(items.contains(item)) {
				if(!client.buyList.contains(item)) {
					client.buyList.add(item);
				}
				item.count++;
				client.total += item.price;
				return true;
			}
		}
		
		return false;
	}
	
	public void cancel(mhkimCustomer client, mhkimItem item) {
		if(client.buyList.contains(item)) {
			System.out.println(item.name+"의 구매 신청이 취소되었습니다.");
			client.total -= item.price;
			if(item.count<=1) {
				client.buyList.remove(item);
			}else {
				item.count--;
			}
			
		}else {
			System.out.println(item.name+"은 신청된 제품이 아닙니다.");
		}
		
	}

	public mhkimCustomer getBestCustomer() {
		return null;
	}

	@Override
	public String toString() {
		String resultStr="";
		Object[] tmp = customers.values().toArray();
		for(Object ob : tmp) {
			if(ob instanceof mhkimCustomer) {
				mhkimCustomer cus = (mhkimCustomer)ob;
				resultStr += ob;
			}
		}
		return resultStr;
	}

	public List<mhkimItem> getBestItem(int num) {
		return null;
	}

	public List<mhkimItem> searchItems(int i) {
		return null;
	}	
	
}
