package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**Standard platform
 * <p>
 * Abstract class is only used as a base and is not meant to be used
 */
public abstract class Platform extends StaticBody {
    /**
     * Constructs a new platform
     *
     * @param world game world where platform is placed.
     * @param halfWidth Half the width of platform
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     * @param image file path of the image to be used for platform.
     */
    public Platform(GameWorld world, int halfWidth, float x, int y, String image) {
        super(world, new BoxShape(halfWidth, 0.5f));
        this.setPosition(new Vec2(x, y));
        addImage(new BodyImage(image));
    }
}

/**
 * Smallest platform
 * Width:2
 */
class XSPlatform extends Platform {
    /**
     * Constructs a new XSPlatform
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public XSPlatform(GameWorld world, float x, int y) {
        super(world, 2, x, y, "data/Platform/XSPlatform.png");
    }
}

/**
 * Medium platform
 * Width:4
 */
class MPlatform extends Platform {
    /**
     * Constructs a new MPlatform
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public MPlatform(GameWorld world, float x, int y) {
        super(world, 4, x, y, "data/Platform/MPlatform.png");
    }
}

/**
 * Large platform
 * Width:8
 */
class LPlatform extends Platform {
    /**
     * Constructs a new LPlatform
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public LPlatform(GameWorld world, float x, int y) {
        super(world, 8, x, y, "data/Platform/LPlatform.png");
    }
}

/**
 * Extra large platform
 * Width:16
 */
class XLPlatform extends Platform {
    /**
     * Constructs a new XLPlatform
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public XLPlatform(GameWorld world, float x, int y) {
        super(world, 16, x, y, "data/Platform/XLPlatform.png");
    }
}