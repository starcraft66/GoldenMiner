package co.tdude.goldenminer.sprites;

import co.tdude.goldenminer.Sprite;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        this.sprites = new ConcurrentHashMap<String, Sprite>();
    }

    public void addSprite(String name, Sprite sprite) {
        if (!sprites.containsKey(name)) {
            sprites.put(name, sprite);
        }
    }

    public Sprite getSprite(String s) {
        return sprites.get(s);
    }

    public void removeSprite(Sprite s) {
        while (sprites.values().remove(s));
        return;
    }

    public Collection<Sprite> getSprites() {
        return sprites.values();
    }
}
