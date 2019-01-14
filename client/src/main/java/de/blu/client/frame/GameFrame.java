package de.blu.client.frame;

import de.blu.client.frame.listener.GameActionListener;
import de.blu.client.frame.listener.GameKeyListener;
import de.blu.client.game.Game;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public static final String FRAME_TITLE = "PokemonOnline";

    @Getter
    private Game game;

    @Getter
    private GameCanvas gameCanvas;

    @Getter
    private GameKeyListener gameKeyListener;

    @Getter
    private GameActionListener gameActionListener;

    @Getter
    @Setter
    private Camera camera;

    public GameFrame(Game game) {
        super(GameFrame.FRAME_TITLE);
        this.game = game;
        this.game.setGameFrame(this);

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.gameKeyListener = new GameKeyListener(this);
        this.gameActionListener = new GameActionListener(this);
        this.gameCanvas = new GameCanvas(this);

        this.add(this.getGameCanvas(), BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }
}
