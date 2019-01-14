package de.blu.client.entity;

import de.blu.client.math.Position;
import de.blu.client.world.GameObject;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Entity extends GameObject {

    @Getter
    @Setter
    private String name = "";

    @Getter
    @Setter
    private float movementSpeed = 1f;

    public Entity(Position position, int width, int height) {
        super(position, width, height);
    }

    public Entity(Position position, int width, int height, float movementSpeed) {
        super(position, width, height);
        this.setMovementSpeed(movementSpeed);
    }

    public void render(Graphics2D graphics2D) {
    }

    public void update() {
    }
}