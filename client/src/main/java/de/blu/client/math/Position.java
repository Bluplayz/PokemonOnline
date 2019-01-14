package de.blu.client.math;

import lombok.Getter;
import lombok.Setter;

public class Position {

    @Getter
    @Setter
    private int x = 0;

    @Getter
    @Setter
    private int y = 0;

    @Getter
    @Setter
    private int z = 0;

    public Position() {
        this( 0, 0, 0 );
    }

    public Position(int x, int y ) {
        this( x, y, 0 );
    }

    public Position(int x, int y, int z ) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position add( int x, int y ) {
        return this.add( x, y, 0 );
    }

    public Position subtract( int x, int y ) {
        return this.subtract( x, y, 0 );
    }

    public Position add( int x, int y, int z ) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public Position subtract( int x, int y, int z ) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }
}
