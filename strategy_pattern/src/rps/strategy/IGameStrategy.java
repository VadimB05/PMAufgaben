package rps.strategy;

import rps.game.Move;
import rps.game.IGame;

public interface IGameStrategy {

    /**
     * Calculate the next move.
     *
     * @param g the game object
     * @return the calculated move
     */
    Move nextMove(IGame g);
}
