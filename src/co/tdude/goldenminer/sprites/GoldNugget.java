package co.tdude.goldenminer.sprites;

import co.tdude.goldenminer.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Created by tristan on 2016-12-07.
 */
public class GoldNugget extends Sprite {
    public GoldNugget() {
        this.setImage(new Image("file:assets/nugget.png"));
        Random r = new Random();
        this.setPosition(50 + r.nextInt(700), 150 + r.nextInt(400));
    }

    @Override
    public void update(double time) {
        super.update(time);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX +20,positionY +20,width -20,height -20);
    }
}
