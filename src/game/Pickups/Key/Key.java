package game.Pickups.Key;

import city.cs.engine.*;
import game.GameWorld;
import game.Player.Player;
import org.jbox2d.common.Vec2;

/**
 * Key pickup
 * <p>
 * Keys are used to open locked chests
 */
public class Key extends DynamicBody {
    /** Controls whether the floating key is shown. */
    public static boolean floatKeyControl = false;
    private int x;
    private int y;

    /**
     * Creates a new Key pickup at the specified location
     *
     * @param world game world where the key exists
     * @param x x-coordinate of the key
     * @param y y-coordinate of the key
     */
    public Key(GameWorld world, int x, int y) {
        super(world);
        this.x = x;
        this.y = y;

        SolidFixture keyFixture = new SolidFixture(this, new BoxShape(1,1.5f));
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/ChestKey/Key.png", 1.2f));
        addCollisionListener(new KeyCollisionDetect(world));
    }

    /**
     * Returns key x-coordinate
     *
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns key y-coordinate
     *
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }
}

/**
 * Key pickup collision detection
 * <p>
 * When a player collides with a key, it is collected and a floating key is created
 */
class KeyCollisionDetect implements CollisionListener {
    private GameWorld world;

    /**
     * Creates a collision detector for key pickups
     *
     * @param world game world where collision occurs
     */
    public KeyCollisionDetect(GameWorld world) {
        this.world = world;
    }

    /**
     * Responds to a collision event involving key pickup
     *
     * @param e collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Player collision with ground or Platform
        Key key = (Key) e.getReportingBody();

        //If key collides with Player, the key is destroyed and a floating key is created
        if (e.getOtherBody() instanceof Player) {
            key.destroy();
            Player.keysHeld++;
            Key.floatKeyControl = true;
        }
    }
}
