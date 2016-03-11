/*
 Copyright 2015-2016 Fabian Pijcke

 This file is part of MetaBoard.

 MetaBoard is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 MetaBoard is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with MetaBoard. If not, see <http://www.gnu.org/licenses/>.
 */

package Core;

import java.util.ArrayList;
import java.util.List;

import Board.IBoard;

/**
 * An entity making decisions. This can be either an AI or a user interface a
 * human can use to input moves.
 * 
 * @author Fabian Pijcke
 * @param <Piece>
 * @param <Coordinate>
 * @param <BoardProxy>
 * @param <Avatar>
 * @param <Move>
 */
public abstract class Player<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>> {
	
	private Board board;
	private final ArrayList<Avatar> avatars = new ArrayList<>();

	/**
	 * This method will be called at the beginning of each game to inform the
	 * decision maker on which board he has to play.
	 * 
	 * @param board
	 */
	public void informBoard(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}
	
	/**
	 * This method will be called at the beginning of each game to inform the
	 * player which avatar he is playing with. Nothing excludes that a player
	 * might play with several avatars. The player has to store this information
	 * in order to use the informEnd method.
	 * 
	 * @param avatar
	 */
	public void informAvatar(Avatar avatar) {
		avatars.add(avatar);
	}

	/**
	 * This method will be called when the decision maker has to pick a move. He
	 * then has to give back a move that the game will try to apply.
	 * 
	 * @return the move chosen by the decision maker.
	 */
	public abstract Move pickMove();

	/**
	 * When the game is over, this method is called so that the decision maker
	 * knows who the winners are.
	 * 
	 * @param winners
	 */
	public void informEnd(List<Avatar> winners) {
		for (Avatar avatar : winners) {
			if (avatars.contains(avatar)) {
				System.out.println("Your avatar " + avatar + " has won !");
			}
		}
	}

}
