Backgammon Eval
===============
Copyright (C) 2016 Felix Wiemuth

License
-------

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

About
-----
Backgammon Eval is a Java framework to evaluate strategies for the endgame of Backgammon. The goal in the endgame is, given a situation of stones distributed on the six points, to play all stones out by moving them towards the lower points and out of the board. For each turn, two or, in the case of a double, four dice are available to move the stones. Different strategies are thinkable to move all stones out with as few turns as possible. A strategy is implemented by specifying what to do on a given situation with given dice. The decision can be based on querying the board for specific properties of the situation. The framework includes the possibility to evaluate strategies on given situations or on all possible distributions of a given number of stones. As a result figures like average number of turns or dice values not used are available.

Usage
-----
- For implementing strategies, see the examples in the package `backgammoneval.strategies`.
- For an example evaluation, see the class `BackgammonEval`.
- Note that evaluation with class `Simulation`
  - keeps the records of all games in memory and is thus not suitable for a very large amount of games.
  - uses `java.util.Random` for rolling the dice in class `Game` which is not cryptographically secure.
