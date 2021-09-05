package cristiano.dewes.tictactoe.core;

import java.io.IOException;

import cristiano.dewes.tictactoe.Constants;
import cristiano.dewes.tictactoe.players.ArtificialIntelligence;
import cristiano.dewes.tictactoe.players.Player;
import cristiano.dewes.tictactoe.players.Players;
import cristiano.dewes.tictactoe.score.FileScoreManager;
import cristiano.dewes.tictactoe.score.ScoreManager;
import cristiano.dewes.tictactoe.ui.Ui;

public class Game {

	private Board board = new Board();
	private Players[] players = new Players[Constants.SYMBOL_PLAYERS.length];
	private int currentPlayerIndex = -1;
	private ScoreManager scoreManager;

	public void play() throws IOException {
		scoreManager = createCoreManager();

		Ui.printGameTitle();
		int choice = 0;

		do {
			try {
				choice = Integer.parseInt(Ui.readInput("Selecione:\n1 - Player vs Player\n2 = Player vs Computador"));
			} catch (Exception e) {
				Ui.printText("Valor inválido!");
			}
			if (choice != 1 && choice != 2) {
				Ui.printText("Opção inválida");
			}
		} while (choice != 1 && choice != 2);

		switch (choice) {

		case 1:
			for (int x = 0; x < players.length; x++) {
				players[x] = createPlayer(x);
			}
			break;

		case 2:
			players[0] = createPlayer(0);
			for (int index = 1; index < players.length; index++) {
				players[index] = new ArtificialIntelligence("Computador", board, Constants.SYMBOL_PLAYERS[index],
						players[0]);
			}
			break;
		}

		boolean gameEnded = false;
		Players currentPlayer = nextPlayer();
		Players winner = null;

		while (!gameEnded) {
			if(currentPlayer.getClass() == Player.class) {
				board.print();
			}

			boolean sequenceFound;
			try {
				sequenceFound = currentPlayer.play();
			} catch (InvalidMoveException e) {
				Ui.printText("Erro = " + e.getMessage());
				continue; // volta para o comeco do loop
			}

			if (sequenceFound) {
				gameEnded = true;
				winner = currentPlayer;
			} else if (board.isFull()) {
				gameEnded = true;
			} else {
				currentPlayer = nextPlayer();
			}
		}

		Ui.printNewLine();

		if (winner == null) {
			Ui.printText("O jogo terminou empatado");
		} else {
			Ui.printNewLine();
			Ui.printNewLine();
			Ui.printNewLine();
			Ui.printNewLine();

			if (winner.getClass() == Player.class) {
				Ui.printText("PARABÉNS '" + winner.getName() + "'!!!!!\nVOCÊ VENCEU O JOGO!!!");
				scoreManager.saveScore((Player) winner);

			} else if (winner.getClass() == ArtificialIntelligence.class) {
				Ui.printText("INFELIZMENTE VOCÊ PERDEU O JOGO");
			}

		}

		board.print();
		Ui.printText("FIM DO JOGO!");

	}

	private Players createPlayer(int index) {
		String name = Ui.readInput("Jogador " + (index + 1));
		Player player = new Player(name, board, Constants.SYMBOL_PLAYERS[index]);

		Integer score = scoreManager.getScore(player);

		if (score != null) {
			Ui.printText("O jogador '" + player.getName() + "' já possui " + score + " vitória(s)!");
		}

		Ui.printText("O jogador '" + name + "' vai utilizar o símbolo " + Constants.SYMBOL_PLAYERS[index] + ".");

		return player;
	}

	private Players nextPlayer() {

		/*
		 * currentPlayerIndex++;
		 * 
		 * if (currentPlayerIndex >= players.length - 1) { currentPlayerIndex = 0; }
		 */

		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

		return players[currentPlayerIndex];
	}

	private ScoreManager createCoreManager() throws IOException {
		return new FileScoreManager();
	}

}
