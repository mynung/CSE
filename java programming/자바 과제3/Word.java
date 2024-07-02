package homework;

public class Word {
	String eng;
	String kor;
	int frequent;
	int wrong;
	
	

	public Word(String eng, String kor) {
		super();
		this.eng = eng;
		this.kor = kor;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.eng + " 뜻 : " +this.kor +"\n출제회수 : "+this.frequent+"\t오답회수 : "+this.wrong;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Word) {
			Word w = (Word)obj;
			return this.eng.equals(w.eng) && this.kor.equals(w.kor);
		}else{
			return super.equals(obj);
		}
		
	}
}
