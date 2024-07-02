package midtest.class0416;

public class MhkimKupang {
	public int saveCount;//저장제품개수
	public int preSaveCount;//현재 저장개수
	public MhkimProduct[] products;

	public MhkimKupang(int saveCount) {
		super();
		this.saveCount = saveCount;
		products = new MhkimProduct[saveCount];
	}
	
	public void inBound(MhkimProduct product) {
		if(preSaveCount < saveCount) {
			products[preSaveCount++] = product;
			System.out.println(product.productName+" 제품 입고를 완료하였습니다.");
		}else {
			System.out.println("더이상 입고할 수 없습니다.");
//			MhkimProduct.productNum -= 1;
		}
	}
	
	public MhkimProduct[] searchItem(String productName){
		int count=0;
		MhkimProduct[] tmp = new MhkimProduct[preSaveCount];
	
		for(int i=0;i<preSaveCount;i++) {
			if(productName.equals(this.products[i].productName)) {
				tmp[count++] = this.products[i];
			}
		}
		MhkimProduct[] products = new MhkimProduct[count];
		for(int i=0;i<count;i++) {
			products[i] = tmp[i];
		}
		
		return products;
	}
	
	public MhkimMBSProduct[] searchMBSProduct() {
		int count=0;
		MhkimMBSProduct[] tmp = new MhkimMBSProduct[preSaveCount];
		for(int i=0;i<preSaveCount;i++) {
			if(products[i] instanceof MhkimMBSProduct) {
				tmp[count++] = (MhkimMBSProduct) products[i];
			}
		}
		MhkimMBSProduct[] MBSProducts = new MhkimMBSProduct[count];
		for(int i=0;i<count;i++) {
			MBSProducts[i]=tmp[i];
		}
		return MBSProducts;
	}
	
}
