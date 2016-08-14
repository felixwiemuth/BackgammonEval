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
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Simulation of a backgammon end game (only one player, taking place in own
 * home board). Always all dice have to be used, except in the last move (any
 * can be used).
 *
 * @author Felix Wiemuth
 */
public class Game implements GameAccess {

    private final Board initialBoard;
    private final Board board;
    private final Strategy strategy;
    private final Random random;
    private final List<Integer> dice = new ArrayList<>(); // currently available dices
    private final List<TurnRecord> turns = new ArrayList<>();
    private int movesLeft;
    private int totalOver = 0;
    private TurnRecord currentTurn;

    public Game(int[] board, Strategy strategy, Random random) {
        this.initialBoard = new Board(board);
        this.board = new Board(initialBoard);
        movesLeft = this.board.movesLeft();
        this.strategy = strategy;
        this.random = random;
    }

    @Override
    public List<Integer> dice() {
        return new ArrayList<>(dice);
    }

    @Override
    public final int higher() throws NoSuchElementException {
        return Collections.max(dice);
    }

    @Override
    public final int lower() throws NoSuchElementException {
        return Collections.min(dice);
    }

    @Override
    public int amount(int i) {
        return board.amount(i);
    }

    @Override
    public boolean stone(int i) {
        return board.stone(i);
    }

    @Override
    public boolean free(int i) {
        return board.free(i);
    }

    @Override
    public int highestStone() {
        return board.highestStone();
    }

    @Override
    public int lowestStoneAbove(int above) {
        return board.lowestStoneAbove(above);
    }

    @Override
    public final void move(int pos, int die) throws NoSuchDieAvailableException, NoStoneException, NotFreeException {
        // Ignore moves on finished boards.
        if (board.finished()) {
            return;
        }
        if (!dice.remove(new Integer(die))) {
            throw new NoSuchDieAvailableException(die);
        }
        int over = board.move(pos, die);
        totalOver += over;
        movesLeft -= die - over;
        currentTurn.addMove(new MoveRecord(pos, die, new Board(board)));
    }

    @Override
    public final void moveH(int pos) throws NoSuchDieAvailableException, NoStoneException, NotFreeException {
        move(pos, higher());
    }

    @Override
    public final void moveL(int pos) throws NoSuchDieAvailableException, NoStoneException, NotFreeException {
        move(pos, lower());
    }

    private void rollDice() {
        int a = random.nextInt(6) + 1;
        int b = random.nextInt(6) + 1;
        dice.add(a);
        dice.add(b);
        if (a == b) { // a double
            dice.add(a);
            dice.add(b);
        }
    }

    private void playTurn() throws NoSuchDieAvailableException, NoStoneException, NotFreeException, NotAllDiceUsedException {
        currentTurn = new TurnRecord();
        rollDice();
        strategy.doMoves(this);
        if (!dice.isEmpty() && !board.finished()) {
            throw new NotAllDiceUsedException();
        }
        currentTurn.setMovesLeft(movesLeft);
        turns.add(currentTurn);
    }

    public GameRecord play() throws NoSuchDieAvailableException, NoStoneException, NotFreeException, NotAllDiceUsedException {
        while (!board.finished()) {
            playTurn();
        }
        // Add remaining dice to over count.
        for (Integer d : dice) {
            totalOver += d;
        }
        return new GameRecord(turns, totalOver, initialBoard);
    }

    public int getRounds() {
        return turns.size();
    }

    public int getTotalOver() {
        return totalOver;
    }
}
