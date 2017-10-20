package com.helpDesk;

import com.helpDesk.util.Util;

public class Atendente {

	public Atendente() {
		// TODO Auto-generated constructor stub
	}

	public boolean initialize() {
		String opcaoInvalida = "Escolha uma opção válida";
		do {
			printOptions();

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
			case 0:
				return true;
			case 1:
				buscarRequisicao();
				break;
			case 2:
				NovaRequisicao();
				break;
			default:
				System.out.println(opcaoInvalida);
				break;
			}
		} while (true);

	}

	private void NovaRequisicao() {
		String opcaoInvalida = "Escolha uma opção válida";
		Util.clearConsole();

		System.out.println("1 - Buscar Requisição");
		System.out.println("2 - Nova Requisição");
		System.out.println();
		System.out.println("0 - Sair");

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
		case 0:
			return true;
		case 1:
			buscarRequisicao();
			break;
		case 2:
			NovaRequisicao();
			break;
		default:
			System.out.println(opcaoInvalida);
			break;
		}

	}

	private void buscarRequisicao() {
		// TODO Auto-generated method stub

	}

	private void printOptions() {
		Util.clearConsole();

		System.out.println("1 - Buscar Requisição");
		System.out.println("2 - Nova Requisição");
		System.out.println();
		System.out.println("0 - Sair");

	}

}
