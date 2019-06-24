package com.revature.exception;

import java.util.ArrayList;

public class IllegalChar {

	public static boolean illegalCharacter(String notexe) {
		ArrayList<Character> notExcepted = new ArrayList<>(33);
		notExcepted.add('~');	// 1
		notExcepted.add('`');	// 2
		notExcepted.add('!');	// 3
		notExcepted.add('@');	// 4
		notExcepted.add('#');	// 5
		notExcepted.add('$');	// 6
		notExcepted.add('%');	// 7
		notExcepted.add('^');	// 8
		notExcepted.add('&');	// 9
		notExcepted.add('*');	// 10
		notExcepted.add('(');	// 11
		notExcepted.add(')');	// 12
		notExcepted.add('-');	// 13
		notExcepted.add('_');	// 14
		notExcepted.add('=');	// 15
		notExcepted.add('+');	// 16
		notExcepted.add('[');	// 17
		notExcepted.add(']');	// 18
		notExcepted.add('{');	// 19
		notExcepted.add('}');	// 20
		notExcepted.add('\\');	// 21
		notExcepted.add('|');	// 22
		notExcepted.add(';');	// 23
		notExcepted.add(':');	// 24
		notExcepted.add('\'');	// 25
		notExcepted.add('"');	// 26
		notExcepted.add('<');	// 27
		notExcepted.add('>');	// 28
		notExcepted.add(',');	// 29
		notExcepted.add('.');	// 30
		notExcepted.add('/');	// 31
		notExcepted.add('?');	// 32
		for (int i = 0; i < notexe.length(); i++) {
			if (notExcepted.contains(notexe.charAt(i))) return true;
		}
		return false;
	}
}
