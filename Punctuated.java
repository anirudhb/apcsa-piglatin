public class Punctuated {
	private String leading;
	public String inner;
	private String trailing;
	private boolean isCapitalized = false;

	public Punctuated(String s) {
		int innerStart = -1;
		for (int i = 0; i < s.length(); i++) {
			boolean isLetter = false;
			for (char a : Constants.alphabet) {
				if (Character.toLowerCase(s.charAt(i)) == a) {
					isLetter = true;
					break;
				}
			}
			if (!isLetter)
				continue;
			innerStart = i;
			break;
		}
		this.leading = innerStart != -1 ? s.substring(0, innerStart) : "";
		String remaining = innerStart != -1 ? s.substring(innerStart) : s;
		int innerEnd = -1;
		for (int i = remaining.length() - 1; i >= 0; i--) {
			boolean isLetter = false;
			for (char a : Constants.alphabet) {
				if (Character.toLowerCase(remaining.charAt(i)) == a) {
					isLetter = true;
					break;
				}
			}
			if (!isLetter)
				continue;
			innerEnd = i;
			break;
		}
		this.inner = innerEnd != -1 ? remaining.substring(0, innerEnd + 1) : remaining;
		this.trailing = innerEnd != -1 ? remaining.substring(innerEnd + 1) : "";
		if (Character.isUpperCase(this.inner.charAt(0))) {
			isCapitalized = true;
			this.inner = Character.toLowerCase(this.inner.charAt(0)) + this.inner.substring(1);
		}
	}

	public boolean isWord() {
		return !this.inner.contains("'");
	}

	@Override
	public String toString() {
		String inner2 = this.inner;
		if (isCapitalized) {
			inner2 = Character.toUpperCase(inner2.charAt(0)) + inner2.substring(1);
		
		}
		return this.leading + inner2 + this.trailing;
	}

	
	public static void main(String[] args) {
		String s = Question.ask("punctuated word:");
		Punctuated w = new Punctuated(s);
		System.out.println("leading: " + w.leading);
		System.out.println("inner: " + w.inner);
		System.out.println("trailing: " + w.trailing);
		System.out.println("is word: " + w.isWord());
	}
}