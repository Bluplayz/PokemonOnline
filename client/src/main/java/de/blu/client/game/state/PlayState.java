package de.blu.client.game.state;

import de.blu.client.game.Game;
import de.blu.client.game.GameState;

import java.awt.*;

public class PlayState extends GameState {

    public PlayState(Game game) {
        super(game);
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics2D graphics2D) {
        /*
        graphics2D.clearRect(0, 0, this.getGame().getGameFrame().getWidth(), this.getGame().getGameFrame().getHeight());
        SpriteSheet spriteSheet = this.getGame().getGameFrame().getGameCanvas().getWorldSpriteSheet();
        for (int i = 0; i < 25; i++) {
            int x = i * (spriteSheet.getSpriteSizeX() * 2);
            graphics2D.drawImage(spriteSheet.getSprite(i).getScaledInstance(spriteSheet.getSpriteSizeX() * 2, spriteSheet.getSpriteSizeY() * 2, Image.SCALE_FAST), x, 50, null);
        }

        spriteSheet = this.getGame().getGameFrame().getGameCanvas().getCharacterSpriteSheet();
        for (int i = 0; i < spriteSheet.getSprites().length; i++) {
            int x = i * (spriteSheet.getSpriteSizeX() * 2);
            graphics2D.drawImage(spriteSheet.getSprite(i).getScaledInstance(spriteSheet.getSpriteSizeX() * 2, spriteSheet.getSpriteSizeY() * 2, Image.SCALE_FAST), x, 100, null);
        }


        if (this.getSpriteSheet() == null) {
            return;
        }
        this.id++;

//        if (this.getSpriteSheet().getSprites().length == this.getId()) {
//            this.id = 0;
//        }
        if (this.getId() == 4) {
            this.id = 0;
        }


        graphics2D.drawImage(this.getSpriteSheet().getSprite(this.getId()).getScaledInstance(this.getSpriteSheet().getSpriteSizeX() * 2, this.getSpriteSheet().getSpriteSizeY() * 2, Image.SCALE_FAST), 200, 200, null);
        graphics2D.drawImage(this.getSpriteSheet().getSprite(this.getId() + 4).getScaledInstance(this.getSpriteSheet().getSpriteSizeX() * 2, this.getSpriteSheet().getSpriteSizeY() * 2, Image.SCALE_FAST), 250, 200, null);
        graphics2D.drawImage(this.getSpriteSheet().getSprite(this.getId() + 8).getScaledInstance(this.getSpriteSheet().getSpriteSizeX() * 2, this.getSpriteSheet().getSpriteSizeY() * 2, Image.SCALE_FAST), 300, 200, null);
        graphics2D.drawImage(this.getSpriteSheet().getSprite(this.getId() + 12).getScaledInstance(this.getSpriteSheet().getSpriteSizeX() * 2, this.getSpriteSheet().getSpriteSizeY() * 2, Image.SCALE_FAST), 350, 200, null);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }
}
