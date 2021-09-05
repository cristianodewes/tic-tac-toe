package cristiano.dewes.tictactoe.players;

import cristiano.dewes.tictactoe.Constants;
import cristiano.dewes.tictactoe.core.Board;
import cristiano.dewes.tictactoe.core.InvalidMoveException;
import cristiano.dewes.tictactoe.core.Move;
import cristiano.dewes.tictactoe.ui.Ui;

public class Player extends Players {

	public Player(String name, Board board, char symbol) {
		super(name, board, symbol);
	}

	@Override
	public boolean play() throws InvalidMoveException {
		Move move = inputMove();

		return this.getBoard().play(this, move);
	}

	private Move inputMove() throws InvalidMoveException {
		return new Move(getMoveStr());
	}

	private String getMoveStr() {
		String moveStr = Ui.readInput("Jogador '" + this.getName() + "' selecione uma jogada=>");
		char positionLetter = 97;

		for (int x = 0; x < Constants.BOARD_SIZE; x++) {
			for (int y = 0; y < Constants.BOARD_SIZE; y++) {
				if (moveStr.equals(String.valueOf(positionLetter))) {
					return x + "," + y;
				}
				positionLetter++;
			}
		}

		return null;
	}

}
