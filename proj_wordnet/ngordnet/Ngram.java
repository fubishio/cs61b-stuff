package ngordnet;

public class Ngram {
	private String word;
	private int year;
	private int tCount; 
	private int distinctCount;
	public Ngram(String word1, int year1, int tCount1, int distinctCount1) {
		word = word1;
		year = year1;
		tCount = tCount1;
		distinctCount = distinctCount1;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public int getTotalCount(){
		return this.tCount;
	}
	
	public int getDistinctCount() {
		return this.distinctCount;
	}
}
