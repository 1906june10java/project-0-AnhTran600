package test;

//import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.exception.IllegalChar;


public class EvalTest {
	
	//private static final IllegalChar myEval = new IllegalChar();

	@Test
	public void incorrectUsername() {
		final String enterUsername = "hello*&";
		//final boolean expectedUsername = IllegalChar.illegalCharacter(enterUsername);
		assertTrue(IllegalChar.illegalCharacter(enterUsername));
	}
	@Test
	public void correctUsername() {
		final String enterUsername = "fhef4ffY";
		//final boolean expectedUsername = IllegalChar.illegalCharacter(enterUsername);
		assertFalse(IllegalChar.illegalCharacter(enterUsername));
	}
	@Test
	public void incorrectUsername2() {
		final String enterUsername = " anh";
		//final boolean expectedUsername = IllegalChar.illegalCharacter(enterUsername);
		assertFalse(IllegalChar.illegalCharacter(enterUsername));
	}
	@Test
	public void correctUsername2() {
		final String enterUsername = "theanh123";
		//final boolean expectedUsername = IllegalChar.illegalCharacter(enterUsername);
		assertFalse(IllegalChar.illegalCharacter(enterUsername));
	}
}
