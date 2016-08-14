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

import java.io.PrintStream;

/**
 *
 * @author Felix Wiemuth
 */
public class Board {

    private int MAXPOS;
    private int[] stones; // stones[0] are the stones taken out, stones[i] are the stones on point i

    private Board() {
    }

    /**
     * Takes an array of size 6 with the initial board situation.
     *
     * @param stones
     */
    public Board(int[] stones) {
        MAXPOS = stones.length;
        int[] stonesInit = new int[MAXPOS + 1];
        System.arraycopy(stones, 0, stonesInit, 1, MAXPOS);
        this.stones = stonesInit;
    }

    public Board(Board board) {
        this.stones = board.stones.clone();
//copy.stones = new int[MAXPOS + 1];
        //System.arraycopy(board.stones, 0, copy.stones, 0, board.stones.length);
        //board.stones.c
        this.MAXPOS = board.MAXPOS;
    }

    public int amount(int i) {
        return stones[i];
    }

    public boolean stone(int i) {
        return amount(i) != 0;
    }

    public boolean free(int i) {
        return highestStone() < i;
    }

    public int highestStone() {
        for (int i = MAXPOS; i > 0; i--) {
            if (stone(i)) {
                return i;
            }
        }
        return 0;
    }

    int lowestStoneAbove(int above) {
        for (int i = above + 1; i <= MAXPOS; i++) {
            if (stone(i)) {
                return i;
            }
        }
        return -1;
    }

    public int movesLeft() {
        int moves = 0;
        for (int i = 1; i <= MAXPOS; i++) {
            moves += stones[i];
        }
        return moves;
    }

    /**
     * Moves stone from position 'pos' by 'step' steps. Checks whether the move
     * is possible and allowed.
     *
     * @param pos
     * @param steps
     * @return number of steps moved over if stone was taken out (0 otherwise)
     * @throws NoStoneException if no stone is available on the given position
     * @throws NotFreeException if a stone is not allowed to take out with a
     * higher number because not all positions starting from that number are
     * free
     */
    public int move(int pos, int steps) throws NoStoneException, NotFreeException {
        if (!stone(pos)) {
            throw new NoStoneException(pos);
        }
        stones[pos]--;
        int over = 0;
        int dest = pos - steps;
        if (dest < 0) { // using a higher number than the position of a stone to take it out - only allowed if the position of that number and all above are free
            if (!free(steps)) {
                throw new NotFreeException(steps);
            }
            over = -dest;
            dest = 0;
        }
        stones[dest]++;
        return over;
    }

    public boolean finished() {
        return free(1);
    }

    public void print(PrintStream out) {
        for (Integer s : stones) {
            out.print(String.format("%2d", s) + " ");
        }
        out.println();
    }
}
