package de.blu.client.game;

import lombok.Getter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class GameState {

    @Getter
    private Game game;

    protected GameState(Game game) {
        this.game = game;
    }

    public void onEnter() {
    }

    public void onLeave() {
    }

    public void update() {
    }

    public void render(Graphics2D graphics2D) {
    }

    public void keyPressed(KeyEvent event, int key) {
    }

    public void keyReleased(KeyEvent event, int key) {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
