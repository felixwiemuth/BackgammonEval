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
package combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Manually tested for (n,0), (0,p), (n,1), (1,p), (2,2) (2,3) For (15,6) the
 * number of combinations matches the calculated.
 *
 * @author Felix Wiemuth
 */
public class BoardGenerator {

    /**
     * Generate all possible distributions of a given amount of stones on a
     * board of a given size.
     *
     * @param n the amount of stones
     * @param p the size of the board (number of points)
     * @return
     */
    public static List<int[]> generate(int n, int p) {
        List<int[]> boards = new ArrayList<>();
        //int[] boards = new int[p];
        if (p == 0) {
            return boards;
        } else if (n == 0) {
            boards.add(new int[p]);
        } else if (p == 1) {
            int[] b = new int[1];
            b[0] = n;
            boards.add(b);
        } else { // n>=1, p>=2
            // split board into two parts, the left half containing all but one position - for all combinations of distributing the stones between the two halfs
            for (int nL = 0; nL <= n; nL++) {
                // recursively generate all possible left boards
                List<int[]> boardsL = generate(nL, p - 1);
                // combine each left board with the remaining position
                for (int[] boardL : boardsL) {
                    int[] board = new int[p];
                    // copy left part into new board
                    System.arraycopy(boardL, 0, board, 0, p - 1);
                    // fill last position with remaining stones
                    board[p - 1] = n - nL;
                    boards.add(board);
                }
            }
        }
        return boards;
    }

    public static void printBoard(int[] board) {
        for (Integer i : board) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void printBoards(List<int[]> boards) {
        System.out.println("Number of boards: " + boards.size());
        for (int[] board : boards) {
            printBoard(board);
        }
    }

}
