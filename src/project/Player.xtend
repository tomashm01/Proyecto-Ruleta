package project

class Player {
	static int money = 500
	static String dni = ""

	// Getters
	def static String getDni() {
		return dni
	}

	def static int getMoney() {
		return money
	}

	// Setters 
	def static void setDni(String dni) {
		Player::dni = dni
	}

	def static void restartGame() {
		Player::money = 500
	}

	/*
	 * This function calculate the total money to deliver to the player
	 */
	def static void setFinalMoney(removeBetInCurrentBets miJugada) {
		Player::money += miJugada.getFinalBalance()
	}

	/*
	 * This function validate if the dni which is passed by parameter is correct or incorrect
	 * 
	 * @param dni
	 * 
	 * @return boolean
	 */
	def static package boolean isValidDni(String dni) {
		var String pattern = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$(?i)"
		// ^[0-9]{8} -> los 8 primeras caracteres deben ser numeros
		// $ -> el caracter final
		// (?i) -> es case insensitive (le da igual mayusculas o minusculas)
		if (!dni.matches(pattern)) {
			// Si no se ajusta al patrón de la expresión regular -> no será válido
			return false
		}
		var String validChars = pattern.substring(10, 33)
		// Guardo las letras en una cadena
		var char letter = (dni.toUpperCase().charAt(8)) as char
		// Cojo la última letra del dni
		var int charIndex = Integer::parseInt(dni.replace(dni.charAt(8), Character.valueOf(' ').charValue).trim()) % 23
		// Paso el dni a número y devuelvo el resto
		if (validChars.charAt(charIndex) === letter) {
			// Si coincide la posicion de la letra con la cadena de letras será válido
			return true
		} else {
			return false
		}
	}

	/*
	 * This function generate a random Dni and return it
	 * 
	 * @return String
	 */
	def static String generateRandomDni() {
		var int MAX = 99999999
		// Generate a random dni
		var String dni
		var int num = ((Math::random() * MAX) as int)
		var String numDni = Integer::toString(num)
		var int rest = num % 23
		// Calculate the letter of the dni that corresponds to the number
		var String[] letters = #["T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
			"V", "H", "L", "C", "K", "E"]
		var String letterDni = {
			val _rdIndx_letters = rest
			letters.get(_rdIndx_letters)
		}
		// Concatenate all and returns a String
		dni = numDni + letterDni
		return dni
	}
}
