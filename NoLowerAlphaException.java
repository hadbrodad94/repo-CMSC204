
public class NoLowerAlphaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8239724052859538507L;

	public NoLowerAlphaException() {
		super("The password must contain at least one lowercase alphabetic character");
	}
}
