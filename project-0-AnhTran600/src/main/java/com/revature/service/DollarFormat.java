package com.revature.service;

public class DollarFormat {

	public static void write(double d) {
		int allCents = (int)(Math.round(d * 100));
		int dollars = allCents / 100;
		int cents = allCents % 100;
		System.out.print("$");
		System.out.print(dollars);
		System.out.print(".");
		if (cents < 10) {
			System.out.print("0");
			System.out.print(cents);
		}
		else {
			System.out.print(cents);
		}
	}
	public static void writeln(double d) {
		write(d);
		System.out.println();
	}
}
