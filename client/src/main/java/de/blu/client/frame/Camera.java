package de.blu.client.frame;

import de.blu.client.entity.Entity;
import lombok.Getter;

public class Camera {

    @Getter
    private Entity entity;

    /*
    public Camera(Entity entity ) {
        this.entity = entity;
    }
    */

    public int getCamX() {
        return 0;
//        return (int) ( this.getEntity().getPosition().getX() + this.getEntity().getWidth() / 2 - Game.getInstance().getGameFrame().getGameCanvas().getWidth() / 2 );
    }

    public int getCamY() {
        return 0;
//        return (int) ( this.getEntity().getPosition().getY() + this.getEntity().getHeight() / 2 - Game.getInstance().getGameFrame().getGameCanvas().getHeight() / 2 );
    }
}
