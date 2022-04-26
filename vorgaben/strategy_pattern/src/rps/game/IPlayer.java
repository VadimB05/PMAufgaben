package rps.game;

import rps.strategy.IGameStrategy;

public interface IPlayer {

    /**
     * Calculate the next move
     *
     * @param g the game object
     * @return the calculated move
     */
    Move nextMove(IGame g);
}
