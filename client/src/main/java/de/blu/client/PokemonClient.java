package de.blu.client;

import de.blu.client.game.Game;
import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonClient {

    @Getter
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        // Set Property OpenGL to true
        System.setProperty("sun.java2d.opengl", "true");

        new Game();

        Runtime.getRuntime().addShutdownHook(new Thread(PokemonClient.getPool()::shutdownNow));
    }
}
