package game.Pickups.Key;

import city.cs.engine.*;
import game.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * Visual floating keys above the player
 * <p>
 * Indicates how many keys the player currently has
 */
public class KeyFloating extends DynamicBody {
    private int x;
    private int y;

    /**
     * Creates a new floating key at the specified coordinates
     *
     * @param world game world where the floating key exists
     * @param x x-coordinate where the key is placed
     * @param y y-coordinate where the key is placed
     */
    public KeyFloating(GameWorld world, int x, int y) {
        super(world);
        GhostlyFixture floatingKeyFixture = new GhostlyFixture(this, new BoxShape(1,1));
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/ChestKey/Key.png", 1));
        this.setGravityScale(0);
    }

    /**
     * Returns x-coordinate of the floating key.
     *
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns y-coordinate of the floating key.
     *
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * Sets x-coordinate of the floating key.
     *
     * @param newx new x-coordinate
     */
    public void setX(int newx) {
        this.x=newx;
    }

    /**
     * Sets y-coordinate of the floating key.
     *
     * @param newy new x-coordinate
     */
    public void setY(int newy) {
        this.y=newy;
    }
}
