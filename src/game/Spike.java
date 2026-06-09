package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * Standard spike
 * <p>
 * Abstract class is only used as a base and is not meant to be used
 */
public abstract class Spike extends StaticBody {
    /**
     * Constructs a new spike
     *
     * @param world game world where spike is placed.
     * @param halfWidth Half the width of spike
     * @param x x-coordinate of the spike’s position in world.
     * @param y y-coordinate of the spike’s position in world.
     * @param image file path of the image to be used for spike.
     * @param size scaling factor for spike's image.
     */
    public Spike(GameWorld world, int halfWidth, float x, int y, String image, float size) {
        super(world, new BoxShape(halfWidth, 1));
        this.setPosition(new Vec2(x, y));
        addImage(new BodyImage(image,size));
    }
}

/**
 * Single spike
 * Width:1
 */
class SSpike extends Spike {
    /**
     * Constructs a new spike
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public SSpike(GameWorld world, float x, int y) {
        super(world, 1, x, y, "data/Spike/SSpike.png",3);
    }
}

/**
 * Medium spike section
 * Width:3
 */
class MSpike extends Spike {
    /**
     * Constructs a new medium spike
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public MSpike(GameWorld world, float x, int y) {
        super(world, 3, x, y, "data/Spike/MSpike.png",3);
    }
}

/**
 * Large spike section
 * Width:6
 */
class LSpike extends Spike {
    /**
     * Constructs a new large spike
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public LSpike(GameWorld world, float x, int y) {
        super(world, 6, x, y, "data/Spike/LSpike.png",3);
    }
}


