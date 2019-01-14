package de.blu.client.world;

import de.blu.client.math.Position;
import lombok.Getter;
import lombok.Setter;

public class GameObject {

    @Getter
    @Setter
    private Position position;

    @Getter
    private int width;

    @Getter
    private int height;

    public GameObject(Position position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }
}
