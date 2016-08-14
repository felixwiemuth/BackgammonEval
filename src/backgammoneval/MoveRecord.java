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

/**
 *
 * @author Felix Wiemuth
 */
public class MoveRecord {

    private final int pos;
    private final int steps;
    private final Board board;

    public MoveRecord(int pos, int steps, Board board) {
        this.pos = pos;
        this.steps = steps;
        this.board = board;
    }

    public int getPos() {
        return pos;
    }

    public int getSteps() {
        return steps;
    }

    public Board getBoard() {
        return board;
    }

    public int getDest() {
        int dest = pos - steps;
        if (dest < 0) {
            dest = 0;
        }
        return dest;
    }
}
