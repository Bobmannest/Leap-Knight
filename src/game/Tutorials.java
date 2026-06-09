package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Player tutorials
 * <p>
 * Abstract class so it's not meant to be used directly
 */
public abstract class Tutorials extends StaticBody {
    /**
     * Constructs a new tutorial
     *
     * @param world game world where the tutorial image is placed.
     * @param x x-coordinate of the tutorial image's position in world.
     * @param y y-coordinate of the tutorial image's position in world.
     * @param image the image to be used for tutorial.
     * @param size scaling factor for tutorial image.
     */
    public Tutorials(GameWorld world, int x, int y, String image, float size) {
        super(world);
        GhostlyFixture tutorialFixture = new GhostlyFixture(this, new BoxShape(1,1));
        this.setPosition(new Vec2(x, y));
        addImage(new BodyImage(image,size));
    }
}

/**
 * Tutorial image for moving
 */
class MoveTutorial extends Tutorials {
    /**
     * Constructs a new move tutorial
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public MoveTutorial(GameWorld world, int x, int y) {
        super(world, x, y, "data/Tutorials/moveTip.png", 2.5f);
    }
}

/**
 * Tutorial image for jumping
 */
class JumpTutorial extends Tutorials {
    /**
     * Constructs a new jump tutorial
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public JumpTutorial(GameWorld world, int x, int y) {
        super(world, x, y, "data/Tutorials/jumpTip.png", 2.5f);
    }
}

/**
 * Tutorial image for sprinting
 */
class SprintTutorial extends Tutorials {
    /**
     * Constructs a new sprint tutorial
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public SprintTutorial(GameWorld world, int x, int y) {
        super(world, x, y, "data/Tutorials/sprintTip.png",2.5f);
    }
}

/**
 * Tutorial image for using exit portals
 */
class ExitTutorial extends Tutorials {
    /**
     * Constructs a new exit tutorial
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public ExitTutorial(GameWorld world, int x, int y) {
        super(world, x, y, "data/Tutorials/exitTip.png",5);
    }
}

/**
 * Tutorial image for attacking
 */
class AttackTutorial extends Tutorials {
    /**
     * Constructs a new attack tutorial
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public AttackTutorial(GameWorld world, int x, int y) {
        super(world, x, y, "data/Tutorials/attackTip.png",3);
    }
}

/**
 * Tutorial image for throwing bombs
 */
class bombTutorial extends Tutorials {
    /**
     * Constructs a new bomb tutorial
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public bombTutorial(GameWorld world, int x, int y) {
        super(world, x, y, "data/Tutorials/bombTip.png",2.5f);
    }
}

/**
 * Tutorial image for pausing the game
 */
class PauseTutorial extends Tutorials {
    /**
     * Constructs a new pause tutorial
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public PauseTutorial(GameWorld world, int x, int y) {
        super(world, x, y, "data/Tutorials/pauseTip.png",2.5f);
    }
}

/**
 * Arrow image to show which direction to go
 */
class ArrowTutorial extends Tutorials {
    /**
     * Constructs a new arrow
     *
     * @param world game world where platform is placed.
     * @param x x-coordinate of the platform’s position in world.
     * @param y y-coordinate of the platform’s position in world.
     */
    public ArrowTutorial(GameWorld world, int x, int y) {
        super(world, x, y, "data/Tutorials/arrowTip.png",3);
    }
}
