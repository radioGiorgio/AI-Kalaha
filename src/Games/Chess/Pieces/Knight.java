/*
 Copyright 2015 Fabian Pijcke

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

package Games.Chess.Pieces;

import Board.Grid.GridCoordinate;
import Core.NameAvatar;
import Games.Chess.StepPiece;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fabian Pijcke
 */
public class Knight extends StepPiece {
    
    public Knight(NameAvatar player) {
        super(player);
    }

    @Override
    public List<GridCoordinate> getDirections() {
        return Arrays.asList(
                new GridCoordinate(1, 2),
                new GridCoordinate(1, -2),
                new GridCoordinate(-1, 2),
                new GridCoordinate(-1, -2),
                new GridCoordinate(2, 1),
                new GridCoordinate(2, -1),
                new GridCoordinate(-2, 1),
                new GridCoordinate(-2, -1)
        );
    }
    
    @Override
    public char getLetter() {
        return 'N';
    }

}
