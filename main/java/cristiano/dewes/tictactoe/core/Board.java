package cristiano.dewes.tictactoe.core;

import cristiano.dewes.tictactoe.Constants;
import cristiano.dewes.tictactoe.players.Players;
import cristiano.dewes.tictactoe.ui.Ui;

public class Board {

	

	private char[][] matrix = new char[Constants.BOARD_SIZE][Constants.BOARD_SIZE];

	public Board() {
		clear();
	}
	
	public char[][] getMatrix() {
		return matrix;
	}

	public void clear() {

		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			for (int y = 0; y < Constants.BOARD_SIZE; y++) {
				matrix[x][y] = ' ';
			}
		}

	}

	public void print() {

		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			Ui.printTextWithNoNewLine(" ");
			for (int y = 0; y < Constants.BOARD_SIZE; y++) {
				Ui.printTextWithNoNewLine(String.valueOf(matrix[x][y]));

				if (y < Constants.BOARD_SIZE - 1) {
					Ui.printTextWithNoNewLine(" | ");
				}
			}

			Ui.printNewLine();

			if (x < Constants.BOARD_SIZE - 1) {
				for (int a = 0; a < Constants.BOARD_SIZE; a++) {

					if (a == Constants.BOARD_SIZE - 1) {
						Ui.printTextWithNoNewLine("---");
						break;
					}

					Ui.printTextWithNoNewLine("----");

				}
			}

			Ui.printNewLine();
		}

	}

	public boolean isFull() {
		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			for (int y = 0; y < Constants.BOARD_SIZE; y++) {
				if (matrix[x][y] == ' ') {
					return false;
				}
			}
		}

		return true;
	}

	public boolean play(Players player, Move move) throws InvalidMoveException{
		int x = move.getX();
		int y = move.getY();
		
		if(x<0 || y<0 || x >= Constants.BOARD_SIZE || y >= Constants.BOARD_SIZE) {
			throw new InvalidMoveException("O intervalo da jogada é inválido");
		}
		
		if(matrix[x][y] != ' ') {
			throw new InvalidMoveException("Essa jogada já foi realizada");
		}

		matrix[move.getX()][move.getY()] = player.getSymbol();

		return checkRows(player) || checkColumns(player) || checkDiagonal1(player) || checkDiagonal2(player);

	}

	private boolean checkRows(Players player) {
		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			if (checkRow(x, player)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkRow(int x, Players player) {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			if (matrix[x][y] != player.getSymbol()) {
				return false;
			}
		}

		return true;
	}

	private boolean checkColumns(Players player) {
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			if (checkColumn(y, player)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumn(int y, Players player) {
		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			if (matrix[x][y] != player.getSymbol()) {
				return false;
			}
		}

		return true;
	}

	private boolean checkDiagonal1(Players player) {

		for (int x = 0, y = 0; y < Constants.BOARD_SIZE; x++, y++) {
			if (matrix[x][y] != player.getSymbol()) {
				return false;
			}
		}

		return true;
	}

	private boolean checkDiagonal2(Players player) {

		for (int x = Constants.BOARD_SIZE - 1, y = 0; y < Constants.BOARD_SIZE; x--, y++) {
			if (matrix[x][y] != player.getSymbol()) {
				return false;
			}
		}
		return true;
	}

}
