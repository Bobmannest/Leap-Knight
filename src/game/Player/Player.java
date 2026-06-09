package game.Player;
import city.cs.engine.*;
import game.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * Player character
 * <p>
 * The player has health, shield, coin count, bomb count, and key count which change dynamically
 */
public class Player extends Walker {
    //Player stats
    //HP and shield HP has a max of 4
    public static int playerHP = 4;
    public static int shieldHP = 0;

    public static int coinsCollected = 0;
    public static int bombsHeld = 0;
    public static int keysHeld = 0;

    /**
     * Creates player character with a solidFixture at the specified position.
     *
     * @param world game world in which the player exists
     * @param x x-coordinate of the player's initial position
     * @param y y-coordinate of the player's initial position
     */
    public Player(GameWorld world, int x, int y) {
        super(world);
        SolidFixture playerFixture = new SolidFixture(this, new BoxShape(1, 2));
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/Sprites/Idle.gif", 4));
    }
}
