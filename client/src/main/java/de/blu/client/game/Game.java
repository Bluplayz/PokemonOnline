package de.blu.client.game;

import de.blu.client.frame.GameFrame;
import lombok.Getter;
import lombok.Setter;

public class Game {

    public static boolean DEBUGMODE = true;

    @Getter
    private static Game instance;

    @Getter
    @Setter
    private GameFrame gameFrame;

    @Getter
    private GameState currentState;

    public Game() {
        // Save Instance
        Game.instance = this;

        this.gameFrame = new GameFrame(this);
    }

    public void changeGameState(GameState newGameState) {
        if (this.getCurrentState() != null) {
            this.getCurrentState().onLeave();
        }

        this.currentState = newGameState;
        this.currentState.onEnter();
    }
}
