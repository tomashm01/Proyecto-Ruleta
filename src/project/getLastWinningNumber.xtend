package project

import java.util.ArrayList
import java.util.Collections
import java.util.List

class getLastWinningNumber {
	/*
	 * This function print the number on the roulette
	 */
	def static void printWinningNumber() {
		if (getProbabilities::getHistoricoDeBolas() !== null) {
			System::out.println("")
			System::out.printf("%60s\n",
				'''NEW NUMBER: |«ConsoleColors::CYAN_BOLD»«getProbabilities::getHistoricoDeBolas().get(getProbabilities::getHistoricoDeBolas().size() - 1).getNumber()»«ConsoleColors::RESET»|'''.
					toString)
			System::out.printf("%42s", "NUMBERS->")
			var List<WinningNumber> reverse = new ArrayList<WinningNumber>(getProbabilities::getHistoricoDeBolas())
			Collections::reverse(reverse)
			if (reverse.size() > 10) {
				reverse.removeIf([bola|reverse.indexOf(bola) > 10])
			}
			reverse.forEach([bola|System::out.print('''«bola.getNumber()» '''.toString)])
		}
	}

	def static void printWinningNumberResults() {
		System::out.printf("\n%41s", "Results:")
		System::out.println(getProbabilities::getHistoricoDeBolas().get(getProbabilities::getHistoricoDeBolas().size() - 1).getResults())
	}

	def static void printSuccessfulBet() {
		System::out.printf("%46s", "Winning bets:")
		if (getProbabilities::getHistoricoDeApuestasGanadoras().get(getProbabilities::getHistoricoDeApuestasGanadoras().size() - 1).
			size() === 0) {
			System::out.println("No ganaste ninguna apuesta :(")
		} else {
			System::out.println(
				getProbabilities::getHistoricoDeApuestasGanadoras().get(getProbabilities::getHistoricoDeApuestasGanadoras().size() - 1))
		}
	}

	def static void printBets() {
		System::out.printf("%48s", "Your last bets:")
		if (removeBetInCurrentBets::project.Move.allBets().get(removeBetInCurrentBets::project.Move.allBets().size() - 1).size() === 0) {
			System::out.println("No realizaste ninguna apuesta :(")
		} else {
			System::out.println(removeBetInCurrentBets::project.Move.allBets().get(removeBetInCurrentBets::project.Move.allBets().size() - 1))
		}
	}

	def static void printBalanceRoll() {
		System::out.printf("%53s:", "Balance of this roll")
		if (removeBetInCurrentBets::getAllBalances().get(removeBetInCurrentBets::getAllBalances().size() - 1) > 0) {
			System::out.print(ConsoleColors::GREEN)
			System::out.println(
				'''+«removeBetInCurrentBets::getAllBalances().get(removeBetInCurrentBets::getAllBalances().size() - 1)»'''.toString)
			System::out.println(ConsoleColors::RESET)
		} else {
			System::out.print(ConsoleColors::RED)
			System::out.println(removeBetInCurrentBets::getAllBalances().get(removeBetInCurrentBets::getAllBalances().size() - 1))
			System::out.println(ConsoleColors::RESET)
		}
	}

	/*
	 * This function print the type of bet
	 */
	def static void printInputType(String possibleChoice1, String possibleChoice2) {
		System::out.printf("%s", '''«ConsoleColors::PURPLE»Input the type'''.toString)
		System::out.print(''' («possibleChoice1» | «possibleChoice2»):'''.toString)
	}

	def static void printStatistics() {
		getProbabilities::estadisticas.forEach([key, value|System::out.println('''«key» : «value»'''.toString)])
	}
}
