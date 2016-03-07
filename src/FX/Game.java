package FX;

import Board.IBoard;
import Core.Player;
import Core.IMove;
import javafx.stage.Window;

public interface Game<B extends IBoard<?, ?>, M extends IMove<B>, A, DM extends Player<? super B, M, A>> extends Core.Game<B, M, A, DM> {
	Window statusPane();
}
