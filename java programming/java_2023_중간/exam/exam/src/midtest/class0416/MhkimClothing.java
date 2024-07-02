package midtest.class0416;

public class MhkimClothing extends MhkimMBSProduct {
	
	public String season;//계절
	
	public MhkimClothing(String productName, int productPrice, String season) {
		super(productName, productPrice);
		this.season = season;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		String str = super.toString()+"계   절 : "+season+"\n";
		str += "구매개수 : " + sellCount+"개\n";
		str += "구매금액 : "+totalSell+"원\n";
		return str;
	}

}
