package ngordnet;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class WordNetTest {

	public class NgordnetUI {

	}

	public interface YearlyRecordProcessor {

	}

	/*@SuppressWarnings("deprecation")
	@Test
	public void testWordNet() throws FileNotFoundException {
		Test my synsetlist construction
		WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
		String[] str = {"action"};
		Synset synTest = new Synset(0, str, "dummy");
		assertEquals(wn.synSetList.get(0).getID(), synTest.getID());
		assertEquals(wn.synSetList.get(0).getSynList(), synTest.getSynList());
		assertEquals(wn.synSetList.get(0).getDefinition(), synTest.getDefinition());
		String[] str1 = {"demotion"};
		synTest = new Synset(2, str1, "dummy");
		assertEquals(wn.synSetList.get(2).getID(), synTest.getID());
		assertEquals(wn.synSetList.get(2).getSynList(), synTest.getSynList());
		assertEquals(wn.synSetList.get(2).getDefinition(), synTest.getDefinition());
		
	}*/
	
	@Test
	public void testHyponym() throws FileNotFoundException {
		WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
		List<String> testHypList = new ArrayList<String>(wn.hyponyms("increase"));
		assertEquals(testHypList.contains("banana"), false);
		assertEquals(testHypList.contains("change"), false);
		assertEquals(testHypList.contains("increase"), true);
		assertEquals(testHypList.contains("augmentation"), true);
		assertEquals(testHypList.contains("leap"), true);
		assertEquals(testHypList.contains("jump"), true);
		
		WordNet wn1 = new WordNet("./wordnet/synsets14.txt", "./wordnet/hyponyms14.txt");
		List<String> testHypList1 = new ArrayList<String>(wn1.hyponyms("change"));
		assertEquals(testHypList1.contains("banana"), false);
		assertEquals(testHypList1.contains("change"), true);
		assertEquals(testHypList1.contains("saltation"), true);
		assertEquals(testHypList1.contains("transition"), true);
		
	}
	
	@Test
	public void testIsNoun() throws FileNotFoundException {
		WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
		assertEquals(wn.isNoun("increase"), true);
		List<String> testNounList = new ArrayList<String>(wn.nouns());
		assertEquals(testNounList.contains("action"), true);
		assertEquals(testNounList.contains("parachuting"), true);
		assertEquals(testNounList.contains("jump"), true);
		assertEquals(testNounList.contains("antihistamine"), true);
		assertEquals(testNounList.contains("actifed"), true);
		 
	}
	
	
	
}
