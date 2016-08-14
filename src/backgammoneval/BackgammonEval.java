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

import backgammoneval.strategies.FStrategyA1;
import backgammoneval.strategies.FStrategyA2;
import backgammoneval.strategies.SStrategyA1;
import combinatorics.BoardGenerator;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Felix Wiemuth
 */
public class BackgammonEval {

    private static final Random RANDOM = new Random();

    /**
     * @param args the command line arguments
     * @throws backgammoneval.IllegalTurnException
     */
    public static void main(String[] args) throws IllegalTurnException {
        example1();
        evaluate();
        evaluateAll();
    }

    /**
     * An example of printing a game.
     *
     * @throws IllegalTurnException
     */
    public static void example1() throws IllegalTurnException {
        int[] board = {5, 5, 5, 0, 0, 0}; //{0, 5, 0, 2, 3, 5};
        GameRecord rec1 = new Game(board, new SStrategyA1(), RANDOM).play();
        rec1.print(System.out);
        GameRecord rec2 = new Game(board, new FStrategyA1(), RANDOM).play();
        rec2.print(System.out);
    }

    /**
     * An example evaluation of the three strategies in
     * {@link backgammoneval.strategies} on a specific board.
     *
     * @throws IllegalTurnException
     */
    public static void evaluate() throws IllegalTurnException {
        int[] b1 = {5, 5, 5, 0, 0, 0};
        List<int[]> boards = Arrays.asList(b1);
        List<Strategy> strategies = Arrays.asList(new SStrategyA1(), new FStrategyA1(), new FStrategyA2());
        Simulation sim = new Simulation();
        sim.evaluateStrategies(boards, strategies, 300000);
    }

    public static void evaluateAll() throws IllegalTurnException {
        System.out.println("Generating boards...");
        List<int[]> boards = BoardGenerator.generate(15, 6);
        List<Strategy> strategies = Arrays.asList(new SStrategyA1(), new FStrategyA1(), new FStrategyA2());
        Simulation sim = new Simulation();
        sim.evaluateStrategies(boards, strategies, 10);
    }
}
