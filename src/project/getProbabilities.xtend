package project

import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap
import java.util.List
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Collectors
import java.util.stream.Stream

class getProbabilities {
	static package int numberRoll = 0
	static package HashMap<String, String> estadisticas = new HashMap<String, String>()
	static package List<ArrayList<Bet>> historicoDeApuestasGanadoras = new ArrayList<ArrayList<Bet>>()
	static package List<WinningNumber> historicoDeBolas = new ArrayList<WinningNumber>()

	def private static void setHistoricoDeBolas(WinningNumber ultimaBolaLanzada) {
		historicoDeBolas.add(ultimaBolaLanzada)
	}

	def static List<WinningNumber> getHistoricoDeBolas() {
		return historicoDeBolas
	}

	def static List<ArrayList<Bet>> getHistoricoDeApuestasGanadoras() {
		return historicoDeApuestasGanadoras
	}

	def private static void setHistoricoDeApuestasGanadoras(List<Bet> apuestasGanadoras) {
		historicoDeApuestasGanadoras.add((apuestasGanadoras as ArrayList<Bet>))
	}

	/*
	 * This function throw the ball
	 */
	def static void pushRoulette(removeBetInCurrentBets miJugada) {
		var WinningNumber nuevaBola = new WinningNumber()
		miJugada.setFinalBalance(nuevaBola)
		Player::setFinalMoney(miJugada)
		setHistoricoDeBolas(nuevaBola)
		getProbabilities::numberRoll++
	}

	/*
	 * This function calculate the total profit
	 * 
	 */
	def static int calculateProfit(removeBetInCurrentBets miJugada, WinningNumber nuevaBola) {
		var ArrayList<Bet> apuestasGanadoras = new ArrayList<Bet>()
		var List<String> stringApuestasGanadoras = new ArrayList<String>(nuevaBola.getResults())
		stringApuestasGanadoras.retainAll(
			miJugada.getCurrentBets().stream().map([apuesta|apuesta.getType()]).collect(Collectors::toList()))
		stringApuestasGanadoras.forEach([ element |
			apuestasGanadoras.add(
				miJugada.getCurrentBets().get(miJugada.getCurrentBets().indexOf(new Bet(element))))
		])
		setHistoricoDeApuestasGanadoras(apuestasGanadoras)
		return apuestasGanadoras.stream().mapToInt([winningBets|winningBets.getAmount() * 2]).sum() // Devuelve
		// las
		// ganancias
	}

	def static void stadistics() {
		calculateStatsType()
	}

	def static void calculateStatsType() {
		estadisticas.clear()
		var AtomicInteger redCount = new AtomicInteger()
		var AtomicInteger evenCount = new AtomicInteger()
		var AtomicInteger highCount = new AtomicInteger()
		historicoDeBolas.stream().forEach([ bola |
			{
				if (bola.color.equals("RED")) {
					redCount.getAndIncrement()
				}
				if (bola.EvenOdd.equals("EVEN")) {
					evenCount.getAndIncrement()
				}
				if (bola.HighLow.equals("HIGH")) {
					highCount.getAndIncrement()
				}
			}
		])
		var double redCountDouble = redCount.doubleValue() / historicoDeBolas.size()
		var double evenCountDouble = evenCount.doubleValue() / historicoDeBolas.size()
		var double highCountDouble = highCount.doubleValue() / historicoDeBolas.size()
		var ArrayList<Double> counters = new ArrayList<Double>(
			Arrays::asList(redCountDouble, 1 - redCountDouble, evenCountDouble, 1 - evenCountDouble, highCountDouble,
				1 - highCountDouble))
		var List<String> aux = new ArrayList()
		// Lista de una dimension
		for (var int i = 0; i < Bet::POSSIBLE_BET_TYPES.length; i++) {
			for (var int j = 0; j < Bet::POSSIBLE_BET_TYPES.get(0).length; j++) {
				aux.add({
					val _rdIndx__tmpNode = j
					{
						val _rdIndx_tmpNode = i
						Bet::POSSIBLE_BET_TYPES.get(_rdIndx_tmpNode)
					}.get(_rdIndx__tmpNode)
				})
			}
		}
		var int j = 0
		while (j < counters.size()) {
			estadisticas.put(aux.get(j), String::format("%.2f%%", ((counters.get(j) / historicoDeBolas.size()) * 100)))
			j++
		}
	}
}
