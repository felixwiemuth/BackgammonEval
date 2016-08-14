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
import java.util.Random;

/**
 *
 * @author Felix Wiemuth
 */
public class Simulation {

    //
    private final Random random = new Random();

    /**
     * Evaluate a strategy on the given boards. Each board is played the given
     * number of times with the given strategy.
     *
     * @param boards
     * @param strategy
     * @param runs number of times to simulate on each board
     * @return the results of all games
     * @throws backgammoneval.IllegalTurnException
     */
    public SimulationResult evaluate(List<int[]> boards, Strategy strategy, int runs) throws IllegalTurnException {
        List<GameRecord> games = new ArrayList<>();
        for (int run = 0; run < runs; run++) {
            for (int[] board : boards) {
                Game game = new Game(board, strategy, random);
                GameRecord record = game.play();
                games.add(record);
            }
        }
        return new SimulationResult(games);
    }

    /**
     * Evaluate the given strategies on the given boards. For each strategy,
     * each board is played the given number of times and the result is printed.
     *
     * @param boards
     * @param strategies
     * @param runs
     * @throws IllegalTurnException
     */
    public void evaluateStrategies(List<int[]> boards, List<Strategy> strategies, int runs) throws IllegalTurnException {
        System.out.println("Simulating Strategies...");
        for (Strategy strategy : strategies) {
            SimulationResult res = evaluate(boards, strategy, runs);
            System.out.println(strategy.getClass().getSimpleName() + ": " + res);
        }
    }
}
