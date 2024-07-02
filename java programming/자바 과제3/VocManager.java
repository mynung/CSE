package homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class VocManager {
	String userName;
	ArrayList <Word> voc =  new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	
	public VocManager(String userName) {
		super();
		this.userName = userName;
	}
	
	void addWord(Word w) {
		voc.add(w);
	}

	void makeVoc(String fileName) {
		try(Scanner file = new Scanner(new File(fileName));) {
			while(file.hasNextLine()) {
				String str = file.nextLine();
				String[] result = str.split("\t");
				this.addWord(new Word(result[0].trim(), result[1].trim()));
			}
			if(voc.size()>=10) {
				System.out.println(this.userName+"의 단어장이 생성되었습니다.\n");
				menu();
			}else {
				System.out.println("단어장이 올바르지 않습니다.");
				System.exit(1);
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		}
	}
	
	private void menu() {
		while(true) {
			System.out.println("------ "+userName+"의 영단어 퀴즈 ------");
			System.out.println("1)주관식 퀴즈 2)객관식 퀴즈 3)오답노트 4)단어검색 5)종료");
			System.out.print("메뉴를 선택하세요 : ");
			int choice;
			try{
				choice = scan.nextInt();
				scan.nextLine();
			}catch (InputMismatchException e) {
				choice = -1;
				scan.nextLine();
			}
			
			switch(choice) {
			case 1 -> subjectivityQuiz();
			case 2 -> multipleChoiceQuiz();
			case 3 -> answerNote();
			case 4 -> searchVoc();
			case 5 -> {
				System.out.println("\n"+this.userName+"의 단어장 프로그램을 종료합니다."); return;}
			
			}
		}
	}
	
	private void subjectivityQuiz() {
		// TODO Auto-generated method stub
		ArrayList <Word>checkList = new ArrayList<>(); 
		int win = 0;
		long beforeTime = System.currentTimeMillis();
		for(int i=0;i<10;i++) {
			System.out.println("------ 주관식 퀴즈 "+(i+1)+"번 ------");
			int randIndex;
			while(true) {
				randIndex=rand.nextInt(voc.size());
				if(!(checkList.contains(voc.get(randIndex)))) {
					checkList.add(voc.get(randIndex));
					break;
				}
			}
			
			for(Word w : voc) {
				if(voc.get(randIndex).kor.equals(w.kor)) {
					w.frequent++;
				}
			}
			System.out.println("\""+voc.get(randIndex).kor+"\"뜻을 가진 영어 단어는 무엇일까요?");
			System.out.print("답을 입력하세요 : ");
			String answer = scan.nextLine();
			if(voc.contains(new Word(answer, voc.get(randIndex).kor))) {
				System.out.println("정답입니다.");
				win++;
			}else {
				System.out.println("틀렸습니다. 정답은 "+voc.get(randIndex).eng+"입니다.");
				for(Word w : voc) {
					if(voc.get(randIndex).kor.equals(w.kor)) {
						w.wrong++;
					}
				}
			}	
		}
		long totalTime = (System.currentTimeMillis() - beforeTime) / 1000;
		System.out.println(userName+"님 10문제 중 "+win+"개 맞추셨고, 총"+totalTime+"초 소요되었습니다.");
	}


	private void answerNote() {
		// TODO Auto-generated method stub
		ArrayList <Word> wrongList = new ArrayList<Word>();
		for(Word w : voc) {
			if(w.wrong>0) {
				wrongList.add(w);
			}
		}
		if(!wrongList.isEmpty()) {
			for(Word w:wrongList) {
				System.out.println(w+"\n-------------------");
			}
		}else {
			System.out.println("\n틀린 문제가 없습니다.");
		}
	}


	
	private void multipleChoiceQuiz() {
		// TODO Auto-generated method stub
		int win=0;
		long beforeTime = System.currentTimeMillis();
		for(int i=0;i<10;i++) {
			ArrayList<Word> quizList = new ArrayList<Word>();
			System.out.println("------ 객관식 퀴즈 "+(i+1)+"번 ------");
			while(quizList.size() < 4) {
				int tmp = 0; //같은 한글 뜻이 확인하는 변수
				int randIndex = rand.nextInt(voc.size());
				if(!quizList.contains(voc.get(randIndex))) {
					for(Word w : quizList) {
						if(w.kor.equals(voc.get(randIndex).kor)) {
							tmp = 1;
						}
					}
					if(tmp == 0) {
						quizList.add(voc.get(randIndex));
					}
				}
			}
			int answer = rand.nextInt(4);
			System.out.println(quizList.get(answer).eng+"의 뜻은 무엇일까요?");
			quizList.get(answer).frequent++;
			for(int j =1;j<=4;j++) {
				System.out.println(j+") "+quizList.get(j-1).kor);
			}
			System.out.print("답을 입력하세요 : ");
			int input;
			try {
				input = scan.nextInt();
				scan.nextLine();
			}catch(InputMismatchException e) {
				input = -1;
				scan.nextLine();
			}
			if(input-1 == answer) {
				System.out.println("정답입니다.");
				win++;
			}else {
				System.out.println("틀렸습니다. 정답은 "+(answer+1)+"번입니다.");
				quizList.get(answer).wrong++;
			}
		}
		long totalTime = (System.currentTimeMillis() - beforeTime) / 1000;
		System.out.println(userName+"님 10문제 중 "+win+"개 맞추셨고, 총"+totalTime+"초 소요되었습니다.");
	}

	

	private void searchVoc() {
		System.out.println("------ 단어 검색 ------");
		System.out.print("검색할 영단어를 입력하세요 (영단어) : ");
		String word = scan.nextLine();
		word = word.trim();
		boolean ch = false;
		for(Word w : voc) {
			if(w != null) {
				if(w.eng.equals(word)) {
					System.out.println(w);
					ch = true;
					break;
				}
			}
		}
		if(!ch) {
			System.out.println("단어장에 등록된 단어가 아닙니다.");
		}
		
		System.out.println("---------------------\n");

		
	}
}


