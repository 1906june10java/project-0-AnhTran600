package com.revature.controller;

import com.revature.exception.IllegalChar;
import com.revature.exception.IllegalUsername;
import com.revature.exception.NegativeBalance;
import com.revature.model.BankUser;
import org.apache.log4j.Logger;
import com.revature.repository.*;
import com.revature.service.Display;
import com.revature.service.DollarFormat;

import java.util.Scanner;

public class Console {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(BankUserRepositoryJdbc.class);
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		boolean quit = false;
		while (!quit) {
			Display.loginMenu();
			String userInput = keyboard.next();
			try {
				if(IllegalChar.illegalCharacter(userInput)) throw new IllegalUsername();
			}
			catch (IllegalUsername exc) {
				System.out.println(exc);
				break;
			}
			BankUserRepository rp = new BankUserRepositoryJdbc();
			BankUser oldUser = rp.findBankUsername(userInput);
			if (userInput.equals(oldUser.getUsername())) {
				Display.passMenu();
				userInput = keyboard.next();
				try {
					if(IllegalChar.illegalCharacter(userInput)) throw new IllegalUsername();
				}
				catch (IllegalUsername exc) {
					System.out.println(exc);
					break;
				}
				if (userInput.equals(oldUser.getPassword())) {
					Display.welcome();
					while (!quit) {
						Display.mainMenu();
						userInput = keyboard.next();
						double amount;
						switch (userInput) {
						case "1":
							Display.line();
							System.out.println("Your balance: ");
							DollarFormat.writeln(oldUser.getBalance());
							Display.line();
							break;
						case "2":
							Display.line();
							System.out.println("Enter amount to withdraw:  ");
							amount = keyboard.nextDouble();
							try {
								if (oldUser.getBalance() < 0) throw new NegativeBalance("Negative Balance");
							}
							catch (NegativeBalance exc) {
								System.out.println(exc);
							}
							if (amount >= oldUser.getBalance()) {
								System.out.println("Not enought fund in balance");
							}
							else {
								oldUser.setBalance(oldUser.getBalance()-amount);
								rp.withdraw(oldUser);
							}
							break;
						case "3":
							Display.line();
							System.out.println("Enter amount to deposit:  ");
							amount = keyboard.nextDouble();
							oldUser.setBalance(oldUser.getBalance()+amount);
							rp.deposit(oldUser);
							break;
						case "4":
							Display.line();
							System.out.println("View all transaction:");
							break;
						case "5":
							Display.thank();
							quit = true;
							break;
							default:
								Display.invalid();
								break;
						}
					}
				}
				else {
					Display.incorrectPass();
				}
			}
			else {
				Display.noUser();
			}
			Display.quitMenu();
			String quitOption = keyboard.next();
			if (quitOption.equals("1")) {
				Display.registerMenu();
				userInput = keyboard.next();
				try {
					if(IllegalChar.illegalCharacter(userInput)) throw new IllegalUsername();
				}
				catch (IllegalUsername exc) {
					System.out.println(exc);
					break;
				}
				BankUserRepository rp2 = new BankUserRepositoryJdbc();
				BankUser newUser = new BankUser();
				newUser.setUsername(userInput);
				Display.registerMenu2();
				userInput = keyboard.next();
				try {
					if(IllegalChar.illegalCharacter(userInput)) throw new IllegalUsername();
				}
				catch (IllegalUsername exc) {
					System.out.println(exc);
					break;
				}
				newUser.setPassword(userInput);
				Display.registerMenu3();
				double amount;
				amount = keyboard.nextDouble();
				newUser.setBalance(amount);
				newUser.setId(rp2.findMaxId() + 1);
				rp2.createBankUser(newUser);
			}
			if (quitOption.equals("2")) {
				Display.thank();
				quit = true;
			}
			else quit = false;
		}
		keyboard.close();
	}
}
