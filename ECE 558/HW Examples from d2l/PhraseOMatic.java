
/**
 * @author Roy Kravitz (roy.kravitz@pdx.edu) <BR> <BR>
 * 
 * Generates random (but plausible sounding) business-speak
 * Example is from HeadFirst Java by Kathy Sierra and Bert Bates <BR>
 * 
 * This version of the program uses a class to abstract and encapsulate the word lists
 *
 */
public class PhraseOMatic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int NUM_OF_PHRASES = 5;		// Number of phrases to generate
		
		WordList mWordListOne, mWordListTwo, mWordListThree;
		
		// word list arrays
		// Add all of the words in the constructor
		String[] oneWords = {"24/7", "multi-Tier", "30,000 foot", "B-to-B",
				"win-win", "front-end", "web-based", "pervasive", "smart", "six-sigma",
				"critical-path", "dynamic"
		};
		mWordListOne = new WordList(oneWords);
		System.out.println("Word List 1 contains " + mWordListOne.getNumWords() + " words");
		
		// Add some of the words in constructor, add the rest one at a time
		String[] twoWords = {"empowered", "sticky", "value-added", "oriented", "centric",
				"distributed", "clustered", "branded", "outside-the-box", "positioned"
		};
		mWordListTwo = new WordList(twoWords);
		mWordListTwo.addWord("cooperative");
		mWordListTwo.addWord("accelerated");
		System.out.println("Word List 2 contains " + mWordListTwo.getNumWords() + " words");
		
		// Add the words to the list one at a time
		 mWordListThree = new WordList();
		 mWordListThree.addWord("process");
		 mWordListThree.addWord("tipping-point");
		 mWordListThree.addWord("solution");
		 mWordListThree.addWord("architecture");
		 mWordListThree.addWord("core-competency");
		 mWordListThree.addWord("strategy");
		 mWordListThree.addWord("mindshare");
		 mWordListThree.addWord("portal");
		 mWordListThree.addWord("space");
		 mWordListThree.addWord("vision");
		 System.out.print("Word List 3 contains " + mWordListThree.getNumWords() + " words");
		 System.out.println(mWordListThree);
		 System.out.println();
			
		// generate and display phrases
		for (int i = 0; i < NUM_OF_PHRASES; i++) {		
			String phrase = mWordListOne.getRandomWord() + " " + mWordListTwo.getRandomWord() + " " +
					mWordListThree.getRandomWord();
			System.out.println("What we need is a " + phrase);
		}  // generate and display phrases
	}

}