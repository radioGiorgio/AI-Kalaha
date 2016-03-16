package Games.Kalaha;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import Core.IMove;
import Games.Kalaha.Boards.Board;

public final class Move implements IMove<Integer, Integer, Board, String, Game> {
	
	private final int pit;
	private int tokens = 0;
	private HashMap<Integer, Integer> captures;
	
	public Move(Integer pit) {
		super();
		this.pit = pit;
	}
	
	public Integer getPit() {
		return pit;
	}
	
	private Integer add(Board board, Integer start, int tokens, String player, int value) {
		if (tokens == 0) {
			return start - 1;
		}
		
		if (board.isKalaha(start) && board.getPlayer(start) != player) {
			return add(board, start + 1, tokens, player, value);
		}
		else {
			board.setPieceAt(start, board.getPieceAt(start) + value);
			return add(board, start + 1, tokens - value, player, value);
		}
	}
	
	@Override
	public void apply(Game game) {
		assert (tokens == 0); // We check that players do not re-use moves.
		
		Board board = game.getBoard();
		String curPlayer = board.getPlayer(pit);
		tokens = board.getPieceAt(pit);
		
		board.setPieceAt(pit, 0);
		Integer finalPit = add(board, pit + 1, tokens, curPlayer, 1);
		boolean finalKalaha = board.isKalaha(finalPit);
		
		if (!finalKalaha) {
			game.setNextPlayer();
		}
		
		if (!board.getPieceAt(finalPit).equals(1) || finalKalaha) {
			return;
		}
		
		// Capture
		captures = new HashMap<>();
		board.getCaptures(finalPit).forEach(p -> captures.put(p, board.getPieceAt(p)));
		int total = captures.values().stream().reduce(0, (a, b) -> a + b);
		if (total != 0 || game.getEmptyCapture()) {
			captures.keySet().forEach(p -> board.setPieceAt(p, 0));
			board.setPieceAt(finalPit, 0);
			for (int i = 0; i < board.getLength(); ++i) {
				if (board.getPlayer(i) == curPlayer && board.isKalaha(i)) {
					board.setPieceAt(i, board.getPieceAt(i) + total + 1);
				}
			}
		}
	}

	@Override
	public void cancel(Game game) {
		Board board = game.getBoard();
		String curPlayer = board.getPlayer(pit);

		if (captures != null) {
			board.setPieceAt(pit, 1);
			captures.forEach((p, n) -> board.setPieceAt(p, n));
			int total = captures.values().stream().reduce(0, (a, b) -> a + b);
			for (int i = 0; i < board.getLength(); ++i) {
				if (board.getPlayer(i) == curPlayer && board.isKalaha(i)) {
					board.setPieceAt(i, board.getPieceAt(i) - total - 1);
				}
			}
		}
		
		add(board, pit + 1, tokens, curPlayer, -1);
		game.getBoard().setPieceAt(pit, tokens);
		tokens = 0;
		if (game.getCurrentPlayer() != curPlayer) {
			game.setPreviousPlayer();
		}
	}

	@Override
	public boolean isLegal(Game game) {
		return game.getBoard().getPieceAt(pit) > 0 && game.getBoard().getPlayer(pit) == game.getCurrentPlayer() && !game.getBoard().isKalaha(pit);
	}
	
}
