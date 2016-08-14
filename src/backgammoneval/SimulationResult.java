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

import java.util.List;

/**
 * Combines the results of multiple games.
 *
 * @author Felix Wiemuth
 */
public class SimulationResult {

    private final List<GameRecord> games;

    public SimulationResult(List<GameRecord> games) {
        this.games = games;
    }

    public List<GameRecord> getGames() {
        return games;
    }

    public int getTotalTurns() {
        int totalTurns = 0;
        for (GameRecord game : games) {
            totalTurns += game.getRounds();
        }
        return totalTurns;
    }

    public int getTotalOver() {
        int totalOver = 0;
        for (GameRecord game : games) {
            totalOver += game.getTotalOver();
        }
        return totalOver;
    }

    public int getNumberOfGames() {
        return games.size();
    }

    public double getAvergageTurns() {
        return (double) getTotalTurns() / getNumberOfGames();
    }

    public double getAvergageOver() {
        return (double) getTotalOver() / getNumberOfGames();
    }

    public double getAvergageOverFactor() {
        return 1 / getAvergageOver();
    }

    public double getAverageTurnsPerOverFactor() {
        return getAvergageTurns() / getAvergageOverFactor();
    }

    @Override
    public String toString() {
        return String.format("#games: %6d  #turns: %3d AVG %7.4f  #over: %3d AVG %7.4f  1/AVG %7.4f  T/O: %7.2f", getNumberOfGames(), getTotalTurns(), getAvergageTurns(), getTotalOver(), getAvergageOver(), getAvergageOverFactor(), getAverageTurnsPerOverFactor());
    }
}
