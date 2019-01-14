package de.blu.client.frame.listener;

import de.blu.client.frame.GameFrame;
import lombok.Getter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {

    @Getter
    private GameFrame gameFrame;

    public GameActionListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void actionPerformed(ActionEvent e) {
        if (this.getGameFrame().getGameCanvas() == null) {
            return;
        }
    }
}
