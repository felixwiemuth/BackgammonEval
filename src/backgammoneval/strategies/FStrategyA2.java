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
package backgammoneval.strategies;

import backgammoneval.GameAccess;
import backgammoneval.NoStoneException;
import backgammoneval.NoSuchDieAvailableException;
import backgammoneval.NotFreeException;
import backgammoneval.Strategy;

/**
 *
 * @author Felix Wiemuth
 */
public class FStrategyA2 implements Strategy {

    @Override
    public void doMoves(GameAccess game) throws NoSuchDieAvailableException, NoStoneException, NotFreeException {
        for (int d : game.dice()) {
            if (game.stone(d)) {
                game.move(d, d);
            } else if (game.free(d)) {
                game.move(game.highestStone(), d);
            } else {
                game.move(game.highestStone(), d);
            }
        }
    }
}
