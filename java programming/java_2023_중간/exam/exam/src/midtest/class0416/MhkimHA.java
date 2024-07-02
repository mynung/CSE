package midtest.class0416;

public class MhkimHA extends MhkimProduct {
	public String energyLev; //에너지 등급
	 

	public MhkimHA(String productName, int productPrice, String energyLev) {
		super(productName, productPrice);
		this.energyLev = energyLev;
	}

	@Override
	public String toString() {
		String str = super.toString()+"에너지 등급 : "+energyLev+"\n";
		str += "구매개수 : " + sellCount+"개\n";
		str += "구매금액 : "+totalSell+"원\n";
		return str;
	}

}
