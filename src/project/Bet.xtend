package project

class Bet {
	public static final String[][] POSSIBLE_BET_TYPES = #[#["BLACK", "RED"], #["EVEN", "ODD"], #["HIGH", "LOW"]]
	Integer amount
	String type

	// Constructors
	new(String type, Integer amount) {
		this.amount = amount
		this.type = type
	}

	new(String type) {
		this.type = type
	}

	// Getters
	def int getAmount() {
		return amount
	}

	def String getType() {
		return type
	}

	// Setters
	def void setType(String type) {
		this.type = type
	}

	override String toString() {
		return this.getType()
	}

	override int hashCode() {
		val int prime = 31
		var int result = 1
		result = prime * result + (if(type === null) 0 else type.hashCode() )
		return result
	}

	override boolean equals(Object obj) {
		if(this === obj) return true
		if(obj === null) return false
		if(getClass() !== obj.getClass()) return false
		var Bet other = (obj as Bet)
		if (type === null) {
			if(other.type !== null) return false
		} else if(!type.equals(other.type)) return false
		return true
	}
}
