import java.util.ArrayList;
import java.util.Set;

public class PigLatin {
	/**
	 * Write a program with a main method
	 */
	public static void main(String[] args) {
		String op = Question.askChoice("Would you like to encode or decode a string?",
				Set.of("encode", "decode"));
		boolean isEncoding = op.equals("encode");
		if (isEncoding) {
			System.out.println("Encoding into pig latin!");
		} else {
			System.out.println("Decoding from pig latin!");
		}
		String phrase = Question.ask("Enter a phrase:");
		if (isEncoding) {
			System.out.println("Encoded as pig latin:");
			System.out.println(encode(phrase));
		} else {
			System.out.println("Decoded from pig latin:");
			System.out.println(decode(phrase));
		}
	}

	private static String encode(String phrase) {
		ArrayList<String> out = new ArrayList<>();
		for (String word_ : phrase.split(" ")) {
			if (word_.length() <= 0)
				continue;
			Punctuated word = new Punctuated(word_);
			if (!word.isWord()) {
				out.add(word.toString());
				continue;
			}
			word.inner = encodeWord(word.inner);
			out.add(word.toString());
		}
		return String.join(" ", out);
	}

	private static String encodeWord(String word) {
		// vowels
		for (char vowel : Constants.vowels) {
			if (word.charAt(0) == vowel) {
				if (word.length() == 1)
					return word;
				return word + "ay";
			}
		}
		// consonants (not vowels)
		int vowelStart = -1;
		for (int i = 0; i < word.length(); i++) {
			boolean isVowel = false;
			for (char vowel : Constants.vowels) {
				if (Character.toLowerCase(word.charAt(i)) == vowel) {
					isVowel = true;
					break;
				}
			}
			if (isVowel) {
				vowelStart = i;
				break;
			}
		}
		if (vowelStart == -1)
			return word;
		return word.substring(vowelStart) + word.substring(0, vowelStart) + "ay";
	}

	private static String decodeWord(String word_) {
		if (word_.lastIndexOf("ay") == -1)
			return word_;
		// strip "ay"
		String word = word_.substring(0, word_.lastIndexOf("ay"));
		// existing dictionary words are unchanged, probably
		if (Constants.words.contains(word))
			return word;
		// look for last consonant or consonant cluster
		int lastConsonant = -1;
		for (String cluster : Constants.initialConsonantClusters) {
			if (word.toLowerCase().endsWith(cluster))
				lastConsonant = word.length() - cluster.length();
			if (lastConsonant != -1)
				break;
		}
		if (lastConsonant == -1) {
			for (char consonant : Constants.consonants) {
				if (Character.toLowerCase(word.charAt(word.length() - 1)) == consonant) {
					lastConsonant = word.length() - 1;
					break;
				}
			}
		}
		if (lastConsonant == -1)
			return word_;
		return word.substring(lastConsonant) + word.substring(0, lastConsonant);
	}

	private static String decode(String latin) {
		ArrayList<String> out = new ArrayList<>();
		for (String word_ : latin.split(" ")) {
			if (word_.length() <= 0)
				continue;
			Punctuated word = new Punctuated(word_);
			if (!word.isWord()) {
				out.add(word.toString());
				continue;
			}
			word.inner = decodeWord(word.inner);
			out.add(word.toString());
		}
		return String.join(" ", out);
	}
}