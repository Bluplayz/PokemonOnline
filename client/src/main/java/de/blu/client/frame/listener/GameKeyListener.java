package de.blu.client.frame.listener;

import de.blu.client.frame.GameFrame;
import lombok.Getter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    @Getter
    private GameFrame gameFrame;

    public GameKeyListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        this.getGameFrame().getGame().getCurrentState().keyPressed(e, e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        this.getGameFrame().getGame().getCurrentState().keyReleased(e, e.getKeyCode());
    }
}
