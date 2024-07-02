package midtest.class0416;

public abstract class MhkimProduct {
	public static int productNum;//제품번호
	public String productName;//제품이름
	public int productPrice;//제품가격
	public int sellCount;//구매개수
	public int totalSell;//구매금액
	
	
	public MhkimProduct(String productName, int productPrice) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		productNum += 1;
	}


	@Override
	public String toString() {
		String str = "제품 번호 : "+productNum+"\n";
		str += "제품이름 : "+productName+"\n";
		str += "제품가격 : "+productPrice+"원\n";
		return str;
	}
	
	
	
}
