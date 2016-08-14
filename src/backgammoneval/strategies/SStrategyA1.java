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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Felix Wiemuth
 */
public class SStrategyA1 implements Strategy {

    @Override
    public void doMoves(GameAccess game) throws NoSuchDieAvailableException, NoStoneException, NotFreeException {
        List<Integer> dice = game.dice();
        Collections.sort(dice, new Comparator<Integer>() {
            @Override
            public int compare(Integer t, Integer t1) {
                if (t < t1) {
                    return 1;
                } else if (Objects.equals(t, t1)) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        for (int d : dice) {
            if (game.stone(d)) {
                game.move(d, d);
            } else if (d <= 3) {
                if (game.amount(2 * d) > 1) {
                    game.move(2 * d, d);
                } else {
                    game.move(game.highestStone(), d);
                }
            } else {
                game.move(game.highestStone(), d);
            }
        }
    }
}
