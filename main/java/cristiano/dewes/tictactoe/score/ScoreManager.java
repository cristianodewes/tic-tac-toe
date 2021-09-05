package cristiano.dewes.tictactoe.score;

import java.io.IOException;

import cristiano.dewes.tictactoe.players.Player;

public interface ScoreManager {
	
	public Integer getScore(Player player);
	
	public void saveScore(Player player) throws IOException;
}
