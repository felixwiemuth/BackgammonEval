/*
 * Copyright (C) 2016 Felix Wiemuth
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package backgammoneval;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felix Wiemuth
 */
public class TurnRecord {

    private final List<MoveRecord> moves = new ArrayList<>();
    private int movesLeft;

    public boolean addMove(MoveRecord move) {
        return moves.add(move);
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public List<MoveRecord> getMoves() {
        return moves;
    }

    public int getMovesLeft() {
        return movesLeft;
    }
}
