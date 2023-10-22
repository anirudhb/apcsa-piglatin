import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Constants {
	public static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	public static char[] vowels = "aeiou".toCharArray();
	public static char[] consonants = "bcdfghjklmnpqrstvwxyz".toCharArray();
	// https://www.lupinworks.com/os/spelling/initial.html
	public static String[] initialConsonantClusters = {
			// Regular -i
			"bl", "cl", "fl", "gl", "pl", "sl",
			// Regular -r
			"br", "cr", "dr", "fr", "gr", "pr", "scr",
			// Regular s-
			"sc", "sk", "sch", "sm", "sn", "sp", "sph", "spl", "spr", "str", "tr", "thr",
			// Irregular
			"dw", "sw", "tw", "qu", "squ", "st", "shr",
			// x is already a consonant
			// ???
			"th",
	};
	// https://github.com/dwyl/english-words/blob/master/words_alpha.txt
	public static Set<String> words = new HashSet<>();

	static {
		try (Stream<String> stream = Files.lines(Paths.get("words_alpha.txt"))) {
			stream.forEach(line -> words.add(line.strip()));
		} catch (IOException e) {
			System.err.println("Failed to read dictionary: " + e);
		}
	}
}
