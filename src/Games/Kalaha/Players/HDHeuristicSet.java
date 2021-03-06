package Games.Kalaha.Players;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by florentdelgrange on 03/06/16.
 * You can find here all the heuristics to compute a score for a Player.
 * The goal is to instantiate a HDMiniMaxAI with : new HDMiniMaxAI(HDHeuristicSet.heuristic).
 */
public final class HDHeuristicSet {

    /**
     * Forbid the instantiation of this class.
     */
    private HDHeuristicSet() {}
    public static HDHeuristic kalahaMaximizer = (board1, player) -> 1.0 * board1.getSums(true, false).get(player);
    public static HDHeuristic kalahaMinimizer = (board1, player) -> {
        HashMap<String, Integer> sums = board1.getSums(true, false);
        return -1.0 * ( sums.values().stream().reduce(0, (a, b) -> a + b) - sums.get(player) );
    };
    public static HDHeuristic pitsMaximizer = (board1, player) -> 1.0 * board1.getSums(false, true).get(player);
    public static HDHeuristic squaredPitsMinimizer = (board1, player) -> {
            Double score = 0.0;
            LinkedList<Integer> indices = new LinkedList<>();
            for (int i = 0; i < board1.getLength(); ++i) {
                if (!board1.isKalaha(i) && board1.getPlayer(i).equals(player)) {
                    indices.add(i);
                }
            }
            for(Integer i : indices){
                for(Integer j : indices){
                    score += Math.pow(board1.getPieceAt(i) - board1.getPieceAt(j), 2);
                }
            }
            return -1.0 * score;
        };
}
