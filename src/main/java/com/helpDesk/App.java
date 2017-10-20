package com.helpDesk;

import com.helpDesk.util.Util;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Help Desk");
		do {

		} while (!showOptions());
	}

	private static boolean showOptions() {
		String opcaoInvalida = "Escolha uma opção válida";
		System.out.println("1 - Atendente");
		System.out.println("2 - Técnico");

		String opt = System.console().readLine();
		if (opt.isEmpty()) {

			System.out.println(opcaoInvalida);
			return false;
		}
		if (!Util.isNumeric(opt)) {
			System.out.println(opcaoInvalida);
			return false;
		}
		int value = Integer.parseInt(opt);
		switch (value) {
		case 1:
			return new Atendente().Initialize();
		case 2:
		default:
			System.out.println(opcaoInvalida);
		}
		return false;
	}

	
}
