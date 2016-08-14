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
import java.util.List;

/**
 *
 * @author Felix Wiemuth
 */
public class GameRecord {

    private final Board initialBoard;
    private final List<TurnRecord> turns;
    private final int totalOver;

    public GameRecord(List<TurnRecord> turns, int totalOver, Board initialBoard) {
        this.turns = turns;
        this.totalOver = totalOver;
        this.initialBoard = initialBoard;
    }

    public int getRounds() {
        return turns.size();
    }

    public List<TurnRecord> getTurns() {
        return turns;
    }

    public int getTotalOver() {
        return totalOver;
    }

    public void print(PrintStream out) {
        out.print("    ");
        initialBoard.print(out);
        for (TurnRecord turn : turns) {
            for (MoveRecord move : turn.getMoves()) {
                StringBuilder sb = new StringBuilder();
                sb.append(move.getSteps()).append(":   ");
                for (int i = 0; i < 3 * move.getDest(); i++) {
                    sb.append(' ');
                }
                sb.append("\\");
                for (int i = 0; i < 3 * (move.getPos() - move.getDest()) - 1; i++) {
                    sb.append('-');
                }
                sb.append('/');
                out.println(sb.toString());
                out.print("    ");
                move.getBoard().print(out);
            }
        }
        out.println("Turns: " + turns.size() + " Total over: " + totalOver);
    }
}
