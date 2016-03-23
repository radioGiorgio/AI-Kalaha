package Games.Kalaha;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import FX.AvatarMaker;
import FX.BoardMaker;
import FX.PlayerMaker;
import FX.StringAvatarMaker;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Boards.FromFile;
import Games.Kalaha.Boards.Uniform;
import Games.Kalaha.Players.HumanGUI;
import Games.Kalaha.Players.Player;
import Games.Kalaha.Players.RandomAI;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameMaker implements FX.GameMaker<Integer, Integer, Board, String, Game, Move, Player, GameRunner> {
	
	private final RadioButton rbOwner, rbFinisher, rbNoone;
	private final CheckBox cbEmptyCaptures;
	
	public GameMaker() {
		ToggleGroup ltg = new ToggleGroup();
		rbOwner = new RadioButton("Owner");
		rbOwner.setToggleGroup(ltg);
		rbFinisher = new RadioButton("Finisher");
		rbFinisher.setToggleGroup(ltg);
		rbNoone = new RadioButton("No one");
		rbNoone.setToggleGroup(ltg);
		rbOwner.setSelected(true);
		
		cbEmptyCaptures = new CheckBox("Empty captures");
		cbEmptyCaptures.setSelected(true);
	}

	@Override
	public Node getConfigPane() {
		HBox ltg = new HBox();
		ltg.getChildren().add(new Text("Remaining tokens are for: "));
		ltg.getChildren().add(rbOwner);
		ltg.getChildren().add(rbFinisher);
		ltg.getChildren().add(rbNoone);
		
		VBox box = new VBox();
		box.getChildren().add(ltg);
		box.getChildren().add(cbEmptyCaptures);
		
		return box;
	}

	@Override
	public List<BoardMaker<Integer, Integer, Board, String>> getBoardMakers() {
		List<BoardMaker<Integer, Integer, Board, String>> l = new ArrayList<>();
		l.add(new Uniform.Maker());
		l.add(new FromFile.Maker());
		return l;
	}

	@Override
	public List<PlayerMaker<Integer, Integer, Board, String, Game, Move, Player>> getPlayerMakers() {
		List<PlayerMaker<Integer, Integer, Board, String, Game, Move, Player>> l = new ArrayList<>();
		l.add(new RandomAI.Maker());
		l.add(new HumanGUI.Maker());
		//l.add(new HeuristicAI.Maker());
		return l;
	}

	@Override
	public AvatarMaker<String> getNewAvatarMaker() {
		return new StringAvatarMaker();
	}

	@Override
	public Game getGame(Board board, List<String> avatars) {
		Game.LeftTokensGrantee ltg;
		if (rbOwner.isSelected()) {
			ltg = Game.LeftTokensGrantee.OWNER;
		}
		else if (rbFinisher.isSelected()) {
			ltg = Game.LeftTokensGrantee.ENDER;
		}
		else if (rbNoone.isSelected()) {
			ltg = Game.LeftTokensGrantee.NOBODY;
		}
		else {
			ltg = Game.LeftTokensGrantee.OWNER;
		}
		
		return new Game(board, ltg, cbEmptyCaptures.isSelected(), avatars);
	}

	@Override
	public GameRunner getGameRunner(Game game, Map<String, Player> players) {
		return new GameRunner(game, players);
	}
	
	@Override
	public String toString() {
		return "Kalaha";
	}

}
