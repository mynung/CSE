package midtest.class0416;

public class MhkimClient {
	public String name;
	public int possiBuy;//구매가능개수
	public int preBuys;
	public boolean user;
	public MhkimProduct[] buys;
	
	
	public MhkimClient(String name, int possiBuy, boolean user) {
		super();
		this.name = name;
		this.possiBuy = possiBuy;
		this.user = user;
		buys = new MhkimProduct[possiBuy];
	}
	
	public void order(MhkimProduct product) {
		int check=0;
		for(int i=0;i<preBuys;i++) {
			if(buys[i] == product) {
				check=1;
				preBuys--;
			}
		}
		if(preBuys > possiBuy && check==0) {
			System.out.println("더이상 주문 할 수 없습니다.");
			return;
		}
		product.sellCount++;
		if(product instanceof MhkimMBSProduct) {
			if(user) {
				product.totalSell += product.productPrice * 0.8;
				buys[preBuys++] = product;
			}else {
				System.out.println("회원전용 할인제품은 주문할 수 없습니다.");
			}
		}else {
			product.totalSell += product.productPrice;
			buys[preBuys++] = product;
		}
	}


	@Override
	public String toString() {
		String str = name+"님의 주문내역\n";
		str += "회원여부 : "+user+"\n";
		str += "-".repeat(20)+"\n";
		if(buys[0] == null) str +="주문 내역이 없습니다.\n";
		else {
			for(int i=0;i<preBuys;i++) {
				str += buys.toString();
			}
		}
		return str;
	}
	
	
}
