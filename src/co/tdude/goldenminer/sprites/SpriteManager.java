package co.tdude.goldenminer.sprites;

import co.tdude.goldenminer.Sprite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tristan on 2016-12-01.
 *
 */
public class SpriteManager {
    public static SpriteManager INSTANCE;
    private Map<String, Sprite> sprites;

    public static SpriteManager getInstance() {
        return INSTANCE;
    }

    public SpriteManager() {
        this.INSTANCE = this;
        this.sprites = new HashMap<String, Sprite>();
    }

    public void addSprite(String name, Sprite sprite) {
        if (!sprites.containsKey(sprite)) {
            sprites.put(name, sprite);
        }
    }

    public Sprite getSprite(String s) {
        return sprites.get(s);
    }

    public Collection<Sprite> getSprites() {
        return sprites.values();
    }
}
