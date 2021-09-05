package cristiano.dewes.tictactoe.players;

import cristiano.dewes.tictactoe.core.Board;
import cristiano.dewes.tictactoe.core.InvalidMoveException;

public abstract class Players {

	private String name;
	private Board board;
	private char symbol;

	public Players(String name, Board board, char symbol) {
		this.name = name;
		this.board = board;
		this.symbol = symbol;
	}

	public boolean play() throws InvalidMoveException {
		return false;
	}

	public String getName() {
		return name;
	}

	public Board getBoard() {
		return board;
	}

	public char getSymbol() {
		return symbol;
	}

}
