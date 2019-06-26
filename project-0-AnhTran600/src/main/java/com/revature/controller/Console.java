package com.revature.controller;

import com.revature.exception.IllegalChar;

import com.revature.exception.IllegalUsername;
import com.revature.exception.NegativeBalance;
import com.revature.model.BankUser;
import org.apache.log4j.Logger;
import com.revature.repository.*;
import com.revature.service.Display;
import com.revature.service.DollarFormat;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(BankUserRepositoryJdbc.class);
	public static void main(String[] args) throws java.util.InputMismatchException, NegativeBalance, IllegalUsername {
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
				continue;
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
					continue;
				}
				if (userInput.equals(oldUser.getPassword())) {
					Display.welcome();
					do {
						Display.mainMenu();
						double amount;
						userInput = keyboard.next();
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
							try {
								amount = keyboard.nextDouble();
								if (oldUser.getBalance() < 0) throw new NegativeBalance("Negative Balance");
								if (amount > oldUser.getBalance()) {
									System.out.println("Not enought fund in balance");
								}
								else if (amount < 0) {
									System.out.println("Negative Input");
								}
								else {
									oldUser.setBalance(oldUser.getBalance()-amount);
									rp.withdraw(oldUser);
								}
						    }
							catch (InputMismatchException exc) {
						    	System.out.println("Invalid Entry");
						        System.out.print(exc);
						        break;
						    }
							catch (NegativeBalance exc) {
								System.out.println("Negative Balance. Need to deposit");
								break;
							}
							break;
						case "3":
							Display.line();
							System.out.println("Enter amount to deposit:  ");
							try {
								amount = keyboard.nextDouble();
								if (amount < 0) {
									System.out.println("Negative Input");
								}
								else {
									oldUser.setBalance(oldUser.getBalance()+amount);
									rp.deposit(oldUser);
								}
						    } catch (InputMismatchException exc) {
						        System.out.println("Invalid entry.");
						        System.out.println(exc);
						        break;
						    }
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
							Display.line();
							break;
						}
					} while (!quit);
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
				oldUser = rp.findBankUsername(userInput);
				if (userInput.equals(oldUser.getUsername())) {
					Display.usernameTaken();
					continue;
				}
				try {
					if(IllegalChar.illegalCharacter(userInput)) throw new IllegalUsername();
				}
				catch (IllegalUsername exc) {
					System.out.println(exc);
					continue;
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
					continue;
				}
				newUser.setPassword(userInput);
				Display.registerMenu3();
				double amount;
				try {
					amount = keyboard.nextDouble();
					if (amount < 0) {
						System.out.println("Negative Input");
					}
					else {
						newUser.setBalance(amount);
						newUser.setId(rp2.findMaxId() + 1);
						rp2.createBankUser(newUser);
					}
					
			    }
				catch (InputMismatchException e) {
			        System.out.print("Invalid entry.");
			    }
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
