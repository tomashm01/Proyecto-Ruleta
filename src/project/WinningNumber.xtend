package project

import java.util.List
import java.util.stream.IntStream

class WinningNumber {
	public int winningNumber
	public String color
	public String EvenOdd
	public String HighLow

	new() {
		setRandomNumber()
		if (this.winningNumber === 0) {
			this.color = "GREEN"
			this.EvenOdd = "NULL"
			this.HighLow = "NULL"
		} else {
			setColorBall()
			setEvenOdd()
			setHighLow()
		}
	}

	// Getter list of results
	def List<String> getResults() {
		return List::of(this.color, this.EvenOdd, this.HighLow)
	}

	// Getters atributes
	def String getColor() {
		return color
	}

	def void setColor(String color) {
		this.color = color
	}

	def int getNumber() {
		return winningNumber
	}

	def String getEvenOdd() {
		return EvenOdd
	}

	def String getHighLow() {
		return HighLow
	}

	def void setRandomNumber() {
		this.winningNumber = (Math::random() * (36) + 1) as int
	}

	def void setColor() {
		var int[] red = #[1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36]
		// int black[] = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
		var boolean isRed = IntStream::of(red).anyMatch([redNumber|redNumber === this.winningNumber])
		if (isRed) {
			this.color = "RED"
		} else {
			this.color = "BLACK"
		}
	}

	def void setEvenOdd() {
		if (this.winningNumber % 2 === 0) {
			this.EvenOdd = "EVEN"
		} else {
			this.EvenOdd = "ODD"
		}
	}

	/*
	 * This function return if the number is between 1-18, 19-36 or is 0.
	 */
	def void setHighLow() {
		if (this.winningNumber < 19) {
			this.HighLow = "LOW"
		} else {
			this.HighLow = "HIGH"
		}
	}
}
