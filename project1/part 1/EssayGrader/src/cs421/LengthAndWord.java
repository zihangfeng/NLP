package cs421;
/* This the methods for length and words level analysis
 * 3.a how many sentence
 * 3.b how many big words
 * 3.c how many adv words
 * maybe other features
 */

public class LengthAndWord {
	private static final LengthAndWord  LAWinstance= new LengthAndWord ();
	private LengthAndWord(){}
	public static LengthAndWord getInstance() {
		return LAWinstance;
	}
	

}
