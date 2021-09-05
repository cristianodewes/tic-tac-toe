package cristiano.dewes.tictactoe.players;

import java.util.Random;

import cristiano.dewes.tictactoe.Constants;
import cristiano.dewes.tictactoe.core.*;

public class ArtificialIntelligence extends Players {

	private Players enemy = null;
	private String nextMove = null;
	private Random num = new Random();

	public ArtificialIntelligence(String name, Board board, char symbol, Players opponent) {
		super(name, board, symbol);
		this.enemy = opponent;
	}

	public boolean play() throws InvalidMoveException {
		Move move = inputMove();

		return getBoard().play(this, move);
	}

	private Move inputMove() throws InvalidMoveException {
		if (checkAISituation()) {
			return new Move(nextMove);
		}

		if (checkPlayerSituation()) {
			return new Move(nextMove);
		}
		nextMove = num.nextInt(Constants.BOARD_SIZE-1) + "," + num.nextInt(Constants.BOARD_SIZE-1);
		return new Move(nextMove);
	}

	private boolean checkAISituation() {

		if (checkRows(getSymbol(), getBoard()) || checkColumns(getSymbol(), getBoard())
				|| checkDiagonal1(getSymbol(), getBoard()) || checkDiagonal2(getSymbol(), getBoard())) {
			return true;
		}

		return false;
	}

	private boolean checkPlayerSituation() {

		if (checkRows(enemy.getSymbol(), getBoard()) || checkColumns(enemy.getSymbol(), getBoard())
				|| checkDiagonal1(enemy.getSymbol(), getBoard()) || checkDiagonal2(enemy.getSymbol(), getBoard())) {
			return true;
		}

		return false;
	}

	private boolean checkRows(char symbol, Board board) {
		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			if (checkRow(x, symbol, board)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkRow(int x, char symbol, Board board) {
		int numOfSymbol = 0, numOfSpace = 0;
		String position = null;
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			if (board.getMatrix()[x][y] == symbol) {
				numOfSymbol++;
			}
			if (board.getMatrix()[x][y] == ' ') {
				position = x + "," + y;
				numOfSpace++;
			}
		}

		if (numOfSymbol == Constants.BOARD_SIZE-1 && numOfSpace == 1) {
			nextMove = position;
			return true;
		}

		return false;
	}

	private boolean checkColumns(char symbol, Board board) {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			if (checkColumn(y, symbol, board)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumn(int y, char symbol, Board board) {
		int numOfSymbol = 0, numOfSpace = 0;
		String position = null;
		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			if (board.getMatrix()[x][y] == symbol) {
				numOfSymbol++;
			}
			if (board.getMatrix()[x][y] == ' ') {
				position = x + "," + y;
				numOfSpace++;
			}
		}

		if (numOfSymbol == Constants.BOARD_SIZE-1 && numOfSpace == 1) {
			nextMove = position;
			return true;
		}

		return false;
	}

	private boolean checkDiagonal1(char symbol, Board board) {
		int numOfSymbol = 0, numOfSpace = 0;
		String position = null;
		for (int x = 0, y = 0; y < Constants.BOARD_SIZE; x++, y++) {
			if (board.getMatrix()[x][y] == symbol) {
				numOfSymbol++;
			}
			if (board.getMatrix()[x][y] == ' ') {
				position = x + "," + y;
				numOfSpace++;
			}
		}

		if (numOfSymbol == Constants.BOARD_SIZE-1 && numOfSpace == 1) {
			nextMove = position;
			return true;
		}

		return false;
	}

	private boolean checkDiagonal2(char symbol, Board board) {
		int numOfSymbol = 0, numOfSpace = 0;
		String position = null;
		for (int x = Constants.BOARD_SIZE - 1, y = 0; y < Constants.BOARD_SIZE; x--, y++) {
			if (board.getMatrix()[x][y] == symbol) {
				numOfSymbol++;
			}
			if (board.getMatrix()[x][y] == ' ') {
				position = x + "," + y;
				numOfSpace++;
			}
		}
		if (numOfSymbol == Constants.BOARD_SIZE-1 && numOfSpace == 1) {
			nextMove = position;
			return true;
		}

		return false;
	}

}
