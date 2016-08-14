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
import java.util.NoSuchElementException;

/**
 *
 * @author Felix Wiemuth
 */
public interface GameAccess {

    /**
     * Check whether there is at least one stone on a given point.
     *
     * @param i
     * @return
     */
    boolean stone(int i);

    /**
     * Get the amount of stones on a given point.
     *
     * @param i
     * @return
     */
    int amount(int i);

    /**
     * Check whether the given point is free, i.e., that no stones are on this
     * or any higher point.
     *
     * @param i
     * @return
     */
    boolean free(int i);

    /**
     * Get the list of dice not yet used at the current turn.
     *
     * @return
     */
    List<Integer> dice();

    /**
     * Get the higher die.
     *
     * @return
     * @throws NoSuchElementException
     */
    int higher() throws NoSuchElementException;

    /**
     * Get the lower die.
     *
     * @return
     * @throws NoSuchElementException
     */
    int lower() throws NoSuchElementException;

    /**
     * Get the highest point which is not free, i.e., the highest point with at
     * least one stone on it.
     *
     * @return
     */
    int highestStone();

    /**
     * Get the lowest point above a given point with at least one stone on it.
     *
     * @param above
     * @return
     */
    int lowestStoneAbove(int above);

    /**
     * Move a stone from a given point using a given die.
     *
     * @param pos
     * @param die
     * @throws NoSuchDieAvailableException
     * @throws NoStoneException
     * @throws NotFreeException
     */
    void move(int pos, int die) throws NoSuchDieAvailableException, NoStoneException, NotFreeException;

    /**
     * Move a stone from a given point using the higher die.
     *
     * @param pos
     * @throws NoSuchDieAvailableException
     * @throws NoStoneException
     * @throws NotFreeException
     */
    void moveH(int pos) throws NoSuchDieAvailableException, NoStoneException, NotFreeException;

    /**
     * Move a stone from a given point using the lower die.
     *
     * @param pos
     * @throws NoSuchDieAvailableException
     * @throws NoStoneException
     * @throws NotFreeException
     */
    void moveL(int pos) throws NoSuchDieAvailableException, NoStoneException, NotFreeException;
}
