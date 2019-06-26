package com.revature.service;

public class Display {
	
	public static void loginMenu() {
		System.out.println("- - - - - - - - --");
		System.out.println("-   lOGIN MENU   -");
		System.out.println("- - - - - - - - --");
		System.out.print(" Enter Username:  ");
	}
	public static void passMenu() {
		System.out.println();
		System.out.print("Enter Password:  ");
	}
	public static void welcome() {
		System.out.println("- - - - - - - - - - - -");
		System.out.println("- W E L C O M E ! !   -");
		System.out.println("- - - - - - - - - - - -");
	}
	public static void invalid() {
		System.out.println("- - - - - - - - - - - -");
		System.out.println("- Invalid Selection   -");
		System.out.println("- - - - - - - - - - - -");
	}
	public static void line() {
		System.out.println("- - - - - - - - - - - - - - - - -");
	}
	public static void thank() {
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("- T H A N K   Y O U  ,  C O M E  A G A I N !  -");
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
	}
	public static void incorrectPass() {
		System.out.println("- - - - - - - - - - - -");
		System.out.println("- Incorrect Password  -");
		System.out.println("- - - - - - - - - - - -");
	}
	public static void noUser() {
		System.out.println("- - - - - - - - - - -");
		System.out.println("- No Username Found -");
		System.out.println("- - - - - - - - - - -");
	}
	public static void usernameTaken() {
		System.out.println("- - - - - - - - - - -");
		System.out.println("- Username Taken     -");
		System.out.println("- - - - - - - - - - -");
	}
	public static void quitMenu() {
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - --");
		System.out.println("- Press \"1\" and \"Enter\" to register                  -");
		System.out.println("- Press \"2\" and \"Enter\" to exit                      -");
		System.out.println("- Press any other key and \"Enter\" to Log In.   -");
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - --");
	}
	public static void mainMenu() {
		System.out.println("- - - - - - - - - - - --");
		System.out.println("-   MAIN MENU          -");
		System.out.println("- - - - - - - - - - - --");
		System.out.println("- 1. View my balance   -");
		System.out.println("- 2. Withdraw money    -");
		System.out.println("- 3. Deposit money     -");
		System.out.println("- 4. View Transaction  -");
		System.out.println("- 5. Log out           -");
		System.out.println("- - - - - - - - - - - --");
	}
	public static void registerMenu() {
		System.out.println("- - - - - - - - - - - - -");
		System.out.println("- Register Menu          -");
		System.out.println("- - - - - - - - - - - - - ");
		System.out.println(" Enter NEW Username:   ");
	}
	public static void registerMenu2() {
		System.out.println(" Enter NEW Password:   ");
	}
	public static void registerMenu3() {
		System.out.println(" Enter first deposit amount:   ");
	}
}
