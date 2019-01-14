package de.blu.client.frame.sprite;

import lombok.Getter;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

public class SpriteSheet {

    @Getter
    private int spriteSizeX;

    @Getter
    private int spriteSizeY;

    @Getter
    private BufferedImage spriteSheet;

    @Getter
    private BufferedImage[] sprites;

    public SpriteSheet(String filename, int spriteSizeX, int spriteSizeY) {
        this.spriteSizeX = spriteSizeX;
        this.spriteSizeY = spriteSizeY;

        // Load Sprites from SpriteSheet
        this.spriteSheet = ImageLoader.loadImage(filename);

        this.sprites = new BufferedImage[(this.getSpriteSheet().getWidth() / spriteSizeX) * (this.getSpriteSheet().getHeight() / spriteSizeY)];

        int id = 0;
        for (int y = 0; y < this.getSpriteSheet().getHeight(); y += spriteSizeY) {
            for (int x = 0; x < this.getSpriteSheet().getWidth(); x += spriteSizeX) {
                try {
                    this.getSprites()[id] = this.getSpriteSheet().getSubimage(x, y, spriteSizeX, spriteSizeY);
                } catch (RasterFormatException e){
                    // Not a full Sprite found -> Will be ignored
                }
                id++;
            }
        }
    }

    public BufferedImage getSprite(int id) {
        return this.getSprites()[id];
    }
}