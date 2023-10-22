import java.util.Scanner;
import java.util.Set;

public class Question {
	private static Scanner scan = new Scanner(System.in);

	public static String ask(String question) {
		System.out.print(question + " ");
		return scan.nextLine().strip();
	}

	public static String askChoice(String question, Set<String> choices) {
		String res;
		do {
			res = Question.ask(question);
		} while (!choices.contains(res));
		return res;
	}
}
