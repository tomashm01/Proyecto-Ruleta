package project
/** 
 * javac -d . Main.java Menu.java Ruleta.java Jugada.java Bola.java HUD.java Jugador.java
 * NoMoneyException.java NegativeException.java ConsoleColors.java Apuesta.java1
 * java project.Main
 */
import java.util.Scanner
class Main {
	public static int count=5
	def static void main(String[] args) {
		var Scanner s=new Scanner(System.in) 
		var int option=0 
		var boolean betCompleted=false 
		var boolean spunRoulette=false 
		var removeBetInCurrentBets jugadaDeEstaRonda=new removeBetInCurrentBets() 
		// User have 3 attemps to input a valid dni or a random dni is going to be asign to him
		clearScreen() 
		if (!project.Main.dniExists()) {
			setRandomDni() 
		}
		do {
			System.out.printf('''«ConsoleColors.PURPLE»
%82s:%d
''', "MONEY", Player.getMoney()) 
			System.out.printf('''%80s:%s
 «ConsoleColors.RESET»''', "DNI", Player.getDni()) 
			if (betCompleted) {
				System.out.print(ConsoleColors.RESET) 
				System.out.printf("%89s:%s\n ", "MONEY IN GAME", jugadaDeEstaRonda.getMoneyAtStake()) 
				System.out.printf("%88s: ", "Current Bets") 
				System.out.println(jugadaDeEstaRonda.getCurrentBets()) 
			}
			if (spunRoulette) {
				getLastWinningNumber.printWinningNumber() 
				getLastWinningNumber.printWinningNumberResults() 
				getLastWinningNumber.printBets() 
				getLastWinningNumber.printSuccessfulBet() 
				getLastWinningNumber.printBalanceRoll() 
			}
			option=rouletteMenu() 
			
			switch (option) {
				case 1:/* FIXME unsupported fall-through */{
					var int moneyBetted=insertAmount(jugadaDeEstaRonda) var String red=Bet.POSSIBLE_BET_TYPES.get(0).get(0) var String black=Bet.POSSIBLE_BET_TYPES.get(0).get(1) var String colorChoice=insertChoice(red, black) jugadaDeEstaRonda.addBet(colorChoice, moneyBetted) betCompleted=true /* FIXME Unsupported BreakStatement */ break
				}
				case 2:/* FIXME unsupported fall-through */{
					moneyBetted=insertAmount(jugadaDeEstaRonda) var String even=Bet.POSSIBLE_BET_TYPES.get(1).get(0) var String odd=Bet.POSSIBLE_BET_TYPES.get(1).get(1) var String evenOddChoice=insertChoice(even, odd) jugadaDeEstaRonda.addBet(evenOddChoice, moneyBetted) betCompleted=true /* FIXME Unsupported BreakStatement */ break
				}
				case 3:/* FIXME unsupported fall-through */{
					moneyBetted=insertAmount(jugadaDeEstaRonda) var String high=Bet.POSSIBLE_BET_TYPES.get(2).get(0) var String low=Bet.POSSIBLE_BET_TYPES.get(2).get(1) var String highLowChoice=insertChoice(high, low) jugadaDeEstaRonda.addBet(highLowChoice, moneyBetted) betCompleted=true /* FIXME Unsupported BreakStatement */ break
				}
				case 4:/* FIXME unsupported fall-through */{
					getProbabilities.pushRoulette(jugadaDeEstaRonda) jugadaDeEstaRonda=new removeBetInCurrentBets() spunRoulette=true betCompleted=false /* FIXME Unsupported BreakStatement */ break
				}
				case 5:/* FIXME unsupported fall-through */{
					if (spunRoulette) {
						getProbabilities.stadistics() 
						getLastWinningNumber.printStatistics() 
					} else System.out.println("No stadistics generated") /* FIXME Unsupported BreakStatement */ break
				}
				case 6:/* FIXME unsupported fall-through */{
					Player.restartGame() /* FIXME Unsupported BreakStatement */ break
				}
				case 7:/* FIXME unsupported fall-through */{
					System.out.println('''«ConsoleColors.CYAN»Goodbye''') /* FIXME Unsupported BreakStatement */ break
				}
				default :{
					System.err.println(showAsError("Error in the input option")) 
				}
			}
			clearScreen() 
		} while (option !== 7)
	}
	/*
   * This function clean the terminal screen
   */
	def static void clearScreen() {
		System.out.print("u000033[Hu000033[2J") 
		System.out.flush() 
	}
	/*
   * This function try to input the dni of the user
   * 
   * @return boolean
   */
	def private static boolean dniExists() {
		var Scanner s=new Scanner(System.in) 
		var int count=3 
		do {
			// User have 3 attemps to introduce a valid DNI
			System.out.printf('''«ConsoleColors.CYAN»Please enter your DNI. (You have %d attempts):''', count) 
			var String dni=s.nextLine() 
			if (!Player.isValidDni(dni)) {
				count-- 
			} else {
				Player.setDni(dni) 
				return true 
			}
		} while (count !== 0)
		return false 
	}
	/*
   * This function set a random dni
   */
	def private static void setRandomDni() {
		System.out.println(ConsoleColors.RED) 
		System.out.printf("%107s", "We have created a DNI for you.") 
		Player.setDni(Player.generateRandomDni()) 
	}
	/*
   * This function request a type of bet to the player, valide this type and returns it
   */
	def private static String insertChoice(String possibleChoice1, String possibleChoice2) {
		var Scanner s=new Scanner(System.in) 
		var String choice 
		do {
			getLastWinningNumber.printInputType(possibleChoice1, possibleChoice2) 
			choice=(s.nextLine().toUpperCase()) 
			if (!choice.equals(possibleChoice1) && !choice.equals(possibleChoice2)) {
				System.out.printf(showAsError("%s %s %s %s\n"), "\nPlease insert", possibleChoice1, " OR ", possibleChoice2) 
			}
		} while (!choice.equals(possibleChoice1) && !choice.equals(possibleChoice2))
		return choice 
	}
	/*
   * This function print the roulette menu
   */
	def static package int rouletteMenu() {
		var Menu roulette=new Menu("--ROULETTE MENU--","Color bet","Even or odd bet","Higher or lower bet","Spin roulette","Stadistics","Reset Game") 
		return roulette.manage() 
	}
	/*
   * This function request a amount of money for the bet
   */
	def static package int insertAmount(removeBetInCurrentBets jugadaDeEstaRonda) {
		var Scanner s=new Scanner(System.in) 
		var boolean invalid=true 
		var int moneyBetted=0 
		do {
			System.out.print("Please insert the amount to bet: ") 
			moneyBetted=validateNumber() 
			try {
				jugadaDeEstaRonda.betMoney(moneyBetted) 
				invalid=false 
			} catch (NoMoneyException | NegativeException noMoney) {
				System.err.println(noMoney) 
			}
			
		} while (invalid)
		return moneyBetted 
	}
	/*
   * This function validate if the input fact is a number
   * 
   * @return int
   */
	def static int validateNumber() {
		var Scanner s=new Scanner(System.in) 
		var boolean invalid=true 
		var int numToValidate=0 
		do {
			// Checks that the input can be parsed as an int
			if (s.hasNextInt()) {
				numToValidate=s.nextInt() 
				// Advances the scanner to prevent input errors
				s.nextLine() 
				// Sets the condition to false to break the loop
				invalid=false 
			} else {
				System.err.print(showAsError("Invalid input.\nInsert number again:")) 
				// Advances the scanner to prevent input errors
				s.nextLine() 
			}
		} while (invalid)
		// The loop ends when the input is valid
		return numToValidate 
	}
	/*
   * This function returns an error in the menu option
   * 
   * @param error
   * 
   * @return String
   */
	def private static String showAsError(String error) {
		return ConsoleColors.RESET + ConsoleColors.RED + error + ConsoleColors.RESET 
	}
}