package de.blu.protocol.network.packet;

import lombok.Getter;

import java.util.UUID;

public class Packet {

    @Getter
    private UUID uniqueId = UUID.randomUUID();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "uniqueId=" + uniqueId +
                '}';
    }
}
