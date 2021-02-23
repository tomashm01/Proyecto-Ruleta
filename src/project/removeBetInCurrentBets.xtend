package project

import java.util.ArrayList
import java.util.Arrays
import java.util.Iterator
import java.util.List

class removeBetInCurrentBets {
	// Apuestas realizadas con anterioridas anterioridad
	static List<ArrayList<Bet>> allBets = new ArrayList<ArrayList<Bet>>()
	static List<Integer> allBalances = new ArrayList()
	List<Bet> currentBets = new ArrayList()
	// Lo que el jugador ha elegido justo
	// antes de lanzar la bola
	int balance
	// Dinero tras restar el dinero en juego y sumar el beneficio
	int moneyAtStake

	// Dinero en juego
	// Getters & Setters
	def static List<ArrayList<Bet>> getAllBets() {
		return project.removeBetInCurrentBets.allBets
	}

	def void setAllBets() {
		project.removeBetInCurrentBets.allBets.add((currentBets as ArrayList<Bet>))
	}

	def List<Bet> getCurrentBets() {
		return currentBets
	}

	def void setFinalBalance(WinningNumber nuevaBola) {
		this.balance = getProbabilities::calculateProfit(this, nuevaBola) - moneyAtStake
		setAllBets()
		setAllBalances()
	}

	def int getFinalBalance() {
		return this.balance
	}

	def static List<Integer> getAllBalances() {
		return project.removeBetInCurrentBets.allBalances
	}

	def private void setAllBalances() {
		project.removeBetInCurrentBets.allBalances.add(this.balance)
	}

	/** 
	 * La función más difícil de todo el programa.
	 * Esta función crea un iterador que recorre la lista de apuestas actuales
	 * crea un objeto apuesta, y compara con los posibles tipos de apuestas. (color, parImpar..)
	 * Si coincide que hay una apuesta en la lista con la elección del jugador se borrará la apuesta y se añadirá la nueva.
	 * @param choice
	 * @param moneyBetted
	 */
	def void addBet(String choice, int moneyBetted) {
		var Iterator<Bet> it = currentBets.iterator()
		while (it.hasNext()) {
			var Bet apuesta = (it.next() as Bet)
			for (var int i = 0; i < Bet::POSSIBLE_BET_TYPES.length; i++) {
				if (Arrays::asList({
					val _rdIndx_tmpNode = i
					Bet::POSSIBLE_BET_TYPES.get(_rdIndx_tmpNode)
				}).contains(apuesta.getType()) && Arrays::asList({
					val _rdIndx_tmpNode = i
					Bet::POSSIBLE_BET_TYPES.get(_rdIndx_tmpNode)
				}).contains(choice)) {
					it.remove()
				}
			}
		}
		currentBets.add(new Bet(choice, moneyBetted))
		setMoneyAtStake()
	}

	def int getMoneyAtStake() {
		return moneyAtStake
	}

	/** 
	 * Se encarga de lanzar excepciones si la apuesta no es válida
	 * @param bettedMoney
	 * @throws NoMoneyException
	 * @throws NegativeException
	 */
	def void betMoney(int bettedMoney) throws NoMoneyException, NegativeException {
		if (bettedMoney < 0) {
			throw new NegativeException("You can't input negative bets")
		}
		if (moneyAtStake + bettedMoney > Player::getMoney()) {
			throw new NoMoneyException("You have not enough money to bet for this.")
		}
	}

	def void setMoneyAtStake() {
		this.moneyAtStake = currentBets.stream().mapToInt([bets|bets.getAmount()]).sum()
	}
}
