public class PhraseOMatic_r0 {
	public static void main(String[] args) {
    	final int NUMOFPHRASES = 5;  // Number of phrases to generate
    	
		// Word list arrays
		String[] wordListOne = {"24/7", "multi-Tier", "30,000 foot", "B-to-B",
			"win-win", "front-end", "web-based", "pervasive", "smart", "six-sigma",
			"critical-path", "dynamic"};
		String[] wordListTwo = {"empowered", "sticky", "value-added", "oriented", "centric", 
			"distributed", "clustered", "branded", "outside-the-box", "positioned",
			"networked","focused", "leveraged", "aligned", "targeted", "shared",
			"cooperative", "accelerated"};
		String[] wordListThree = {"process", "tipping-point", "solution", "architecture",
			"core competency","strategy", "mindshare", "portal", "space", "vision"};

		// Number of words in each list
		int oneLength = wordListOne.length;
		int twoLength = wordListTwo.length;
		int threeLength = wordListThree.length;

		// generate and display phrases
		for (int i = 0; i < NUMOFPHRASES; i++) {
			int rand1 = (int) (Math.random() * oneLength);
			int rand2 = (int) (Math.random() * twoLength);
			int rand3 = (int) (Math.random() * threeLength);       
			String phrase = wordListOne[rand1] + " " + wordListTwo[rand2] + " " + 
				wordListThree[rand3];
			System.out.println("What we need is a " + phrase);
       	} // generate and display phrases 
    } // end main()  
} // end PhraseOMatic
