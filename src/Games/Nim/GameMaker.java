package Games.Nim;

import java.util.ArrayList;
import java.util.List;

import FX.BoardMaker;
import FX.PlayerMaker;
import Games.Nim.Boards.Default;
import Games.Nim.Players.Player;
import Piece.AnonymousToken;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;

public class GameMaker implements FX.GameMaker<AnonymousToken, Integer, Default, String, Game, Move, Player> {
	
	private final Spinner<Integer> maxLeapSpinner = new Spinner<>(1, Integer.MAX_VALUE, 3);
	
	@Override
	public String toString() {
		return "Nim";
	}

	@Override
	public Node getConfigPane() {
		return new VBox(maxLeapSpinner);
	}

	@Override
	public Game getGame(Default board, List<String> players) {
		return new Game(players, board, maxLeapSpinner.getValue());
	}

	@Override
	public List<BoardMaker<AnonymousToken, Integer, Default>> getBoardMakers() {
		ArrayList<BoardMaker<AnonymousToken, Integer, Default>> ret = new ArrayList<>();
		ret.add(new Default.Maker());
		return ret;
	}

	@Override
	public List<PlayerMaker> getPlayerMakers() {
		// TODO Auto-generated method stub
		return null;
	}

}
