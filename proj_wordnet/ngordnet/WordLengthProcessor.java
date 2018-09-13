package ngordnet;

public class WordLengthProcessor implements YearlyRecordProcessor{
	public WordLengthProcessor() {
		
	}

	@Override
	public double process(YearlyRecord yearlyRecord) {
		Double c =  Double.parseDouble("0");
		Double divisor = Double.parseDouble("0");
		for (String wordlength : yearlyRecord.words()) {
			c = c + (wordlength.length() * yearlyRecord.count(wordlength));
			divisor = divisor + yearlyRecord.count(wordlength);
		}
		return c / divisor;
	}
}
