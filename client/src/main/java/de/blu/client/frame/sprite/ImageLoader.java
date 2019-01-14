package de.blu.client.frame.sprite;

import de.blu.client.PokemonClient;
import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    @Getter
    private static Map<String, BufferedImage> cache = new HashMap<>();

    public static BufferedImage loadImage(String path) {
        if (ImageLoader.getCache().containsKey(path)) {
            return ImageLoader.getCache().get(path);
        }

        try {
            URL url = PokemonClient.class.getClassLoader().getResource(path);
            assert url != null;
            ImageLoader.getCache().put(path, ImageIO.read(url));
            return ImageLoader.getCache().get(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
