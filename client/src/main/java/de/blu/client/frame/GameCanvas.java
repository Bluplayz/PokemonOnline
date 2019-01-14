package de.blu.client.frame;

import de.blu.client.frame.sprite.SpriteSheet;
import de.blu.client.game.Game;
import de.blu.client.game.state.PlayState;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

public class GameCanvas extends Canvas implements Runnable {

    public static final int FPS = 60;
    public static final int FRAME_SIZE_X = 800;
    public static final int FRAME_SIZE_Y = 600;

    @Getter
    private Thread thread;

    @Getter
    private boolean running = false;

    @Getter
    private GameFrame gameFrame;

    @Getter
    private SpriteSheet worldSpriteSheet;

    @Getter
    private SpriteSheet characterSpriteSheet;

    public GameCanvas(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        this.setPreferredSize(new Dimension(GameCanvas.FRAME_SIZE_X, GameCanvas.FRAME_SIZE_Y));
//        this.setBackground(new Color(146, 189, 221));
        this.setFocusable(true);
        this.requestFocus();

        this.addKeyListener(this.getGameFrame().getGameKeyListener());

        this.worldSpriteSheet = new SpriteSheet("tileset.png", 16, 16);
        this.characterSpriteSheet = new SpriteSheet("character.png", 16, 24);

        //this.getGameFrame().getGame().changeGameState(new PlayState(this.getGameFrame().getGame()));
//        this.getGameFrame().getGame().setWorld(new World("test", 16));

        // Force Ingame while debugging
        this.getGameFrame().getGame().changeGameState(new PlayState(this.getGameFrame().getGame()));
        this.getGameFrame().getGame().getGameFrame().setVisible(false);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                GameCanvas.this.getGameFrame().getGame().getGameFrame().setVisible(true);
            }
        }, 100);
    }

    @Override
    public void run() {
        long lastTick = System.nanoTime();
        long lastInfo = System.currentTimeMillis();

        int ticks = 0;
        int frames = 0;

        int minFrames = 10000;
        int maxFrames = 0;
        int averageFrames = 0;

        int totalFrames = 0;
        int totalSeconds = 0;

        double delta = 0;

        while (this.isRunning()) {
            long currentNano = System.nanoTime();

            delta += (currentNano - lastTick) / (1000000000D / GameCanvas.FPS);
            lastTick = currentNano;

            while (delta >= 1) {
                ticks += 1;
                delta -= 1;

                //this.mGameClient.getGameStateManager().fixedTick();
                this.update();
            }
            frames += 1;

            //this.mGameClient.getGameStateManager().getCurrentGameState().tick();
            //this.mGameClient.getScreen().prepareBuffer();
            this.render();

            if ((System.currentTimeMillis() - lastInfo) >= 1000) {
                totalFrames += frames;
                totalSeconds += 1;

                minFrames = (frames < minFrames ? frames : minFrames);
                maxFrames = (frames > maxFrames ? frames : maxFrames);
                averageFrames = totalFrames / totalSeconds;

                System.out.println(ticks + " Ticks | " + frames + " FPS! Minimum: " + minFrames + ", Maximum: " + maxFrames + ", Average: " + averageFrames);

                ticks = 0;
                frames = 0;

                lastInfo = System.currentTimeMillis();
            }
        }
    }

    public void update() {
        this.getGameFrame().getGame().getCurrentState().update();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            bufferStrategy = this.getBufferStrategy();
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        this.getGameFrame().getGame().getCurrentState().render((Graphics2D) graphics);

        // Debug
        if (Game.DEBUGMODE) {
            graphics.setFont(new Font("FRESHMAN", Font.PLAIN, 25));
            graphics.setColor(Color.ORANGE);
            graphics.drawString(Game.getInstance().getCurrentState().toString().toUpperCase(), 0, 25);

            if (Game.getInstance().getCurrentState().getClass() == PlayState.class) {
//                graphics.drawString( "X: " + ( (PlayState) Game.getInstance().getCurrentState() ).getPlayer().getPosition().getX(), 0, 50 );
//                graphics.drawString( "Y: " + ( (PlayState) Game.getInstance().getCurrentState() ).getPlayer().getPosition().getY(), 0, 75 );
                //graphics.drawString( "Z: " + ( (PlayState) Game.getInstance().getCurrentState() ).getPlayer().getPosition().getZ(), 0, 100 );
            }
        }

        //graphics.setColor(Color.RED);
        //graphics.fillRect(5, 5, this.getWidth(), this.getHeight());

        graphics.dispose();
        bufferStrategy.show();
    }
}
