package cristiano.dewes.tictactoe.players;

import cristiano.dewes.tictactoe.core.Board;
import cristiano.dewes.tictactoe.core.InvalidMoveException;
import cristiano.dewes.tictactoe.core.Move;
import cristiano.dewes.tictactoe.ui.Ui;

public class Player  extends Players{

	public Player(String name, Board board, char symbol) {
		super(name, board, symbol);
	}

	@Override
	public boolean play() throws InvalidMoveException{
		Move move = inputMove();

		return this.getBoard().play(this, move);
	}

	private Move inputMove() throws InvalidMoveException{
		String moveStr = Ui.readInput("Jogador '" + this.getName() + "'=>");
		return new Move(moveStr);
	}

}
