package pl.edu.agh.mwo;

public class Scanner {

	private static final String PARENTHESES = "()";
	private static final String WHITE_SPACES = " \t\n";
	private static final String NEWLINE = "\n";
	private static final String COMMENT = "%";
	private static final String END_OF_TOKEN_CHARS = WHITE_SPACES + COMMENT + PARENTHESES;

	String text;

	public Scanner(String inputText) {
		text = inputText;
	}

	public String getToken() {
		String token = "";
		readUntilToken();

		if (headMatches(regExp(PARENTHESES))) {
			token = readOnlyHead();
		} else {
			token = readUntil(regExp(END_OF_TOKEN_CHARS));
		}

		return token.toLowerCase();
	}

	private String readOnlyHead() {
		String token;
		token = text.substring(0, 1);
		text = text.substring(1);
		return token;
	}

	private String readUntil(String pattern) {
		String token = "";
		while (text.length() > 0 && !headMatches(pattern)) {
			token += text.substring(0, 1);
			text = text.substring(1);
		}
		return token;
	}

	private void readUntilToken() {
		while (headNotToken()) {
			if (headMatches(COMMENT)) {
				readUntil(NEWLINE);
			}

			readOnlyHead();
		}
	}

	private boolean headNotToken() {
		return headMatches(regExp(WHITE_SPACES)) || headMatches(COMMENT);
	}

	private boolean headMatches(String pattern) {
		return text.length() > 0 && text.substring(0, 1).matches(pattern);
	}

	private String regExp(String pattern) {
		return "[" + pattern + "]";
	}

}
