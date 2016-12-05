package co.tdude.goldenminer.sprites;

import co.tdude.goldenminer.GoldenMiner;
import co.tdude.goldenminer.Sprite;
import javafx.scene.image.Image;

/**
 * Created by tristan on 2016-12-01.
 */
public class Hook extends Sprite {
    protected Direction idleDirection;
    protected Direction reelDirection;
    protected boolean reeling;

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public Hook() {
        super();
        this.setImage(new Image("file:assets/hook.png"));
        this.setDegdegRotation(0);
        this.setPosition(375, 115);
        this.idleDirection = Direction.RIGHT;
        this.reelDirection = Direction.DOWN;
        this.reeling = false;
    }

    @Override
    public void update(double time) {
        super.update(time);
        if (getBoundary().getMaxY() > GoldenMiner.getInstance().HEIGHT
                || getBoundary().getMaxX() > GoldenMiner.getInstance().WIDTH
                || getBoundary().getMinX() < 0 && reeling) {
            this.reelDirection = Direction.UP;
        }

        if (reeling && getBoundary().getMinY() < 115) {
            this.reeling = false;
            this.reelDirection = Direction.DOWN;
            setVelocity(0, 0);
            setPosition(375, 115);
        }

        if (reeling) {
            double radRotation = Math.toRadians(this.getDegRotation() + 270);
            double x = 100 * -Math.cos(radRotation);
            double y = 100 * -Math.sin(radRotation);
            switch (reelDirection) {
                case UP:
                    this.setVelocity(-x, -y);
                    break;
                case DOWN:
                    this.setVelocity(x, y);
                    break;
            }
            return;
        }

        if (this.degRotation >= 70) {
            this.idleDirection = Direction.RIGHT;
        } else if (this.degRotation <= -70) {
            this.idleDirection = Direction.LEFT;
        }

        switch (idleDirection) {
            case LEFT:
                degRotation++;
                break;
            case RIGHT:
                degRotation--;
                break;
        }

        //System.out.println(reeling);

    }

    public int getDegRotation() {
        return degRotation;
    }

    public void setDegdegRotation(int degRotation) {
        while (degRotation > 360.0) {
            degRotation -= 360.0;
        }
        while (degRotation < 0) {
            degRotation += 360;
        }
        this.degRotation = degRotation;
    }

    public void idle() {

    }

    public void reel() {
        if (reeling) {
            return;

        }

        reeling = true;

        //Booster
    }
}
