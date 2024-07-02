package midtest.class0416;

public class MhkimSnack extends MhkimMBSProduct {
	
	public String makeName;//제조업체
	
	public MhkimSnack(String productName, int productPrice, String makeName) {
		super(productName, productPrice);
		this.makeName = makeName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		String str = super.toString()+"제조업체 : "+makeName+"\n";
		str += "구매개수 : " + sellCount+"개\n";
		str += "구매금액 : "+totalSell+"원\n";
		return str;
	}

}
