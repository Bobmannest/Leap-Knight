package game.Objects;

import city.cs.engine.*;
import game.GameWorld;
import game.Pickups.Coin;
import game.Pickups.Key.Key;
import game.Player.Player;
import org.jbox2d.common.Vec2;


/**
 * Loot chests that drop coin pickups when opened
 */
public class Chest extends DynamicBody {
    private boolean open = false;

    /**
     * Creates new chest object in the game world at the specified coordinates
     *
     * @param world game world where the chest exists
     * @param x x-coordinate of the chest
     * @param y y-coordinate of the chest
     */
    public Chest(GameWorld world, int x, int y) {
        super(world);
        SolidFixture chestFixture = new SolidFixture(this, new BoxShape(1.5f,1.2f));
        this.setPosition(new Vec2(x,y));
        this.setGravityScale(15);
        addImage(new BodyImage("data/ChestKey/Locked_chest.png", 3));
        addCollisionListener(new ChestCollisionDetect(world));
    }

    /**
     * Checks whether chest is open
     *
     * @return true if the chest is open, false otherwise
     */
    public boolean getOpen() {
        return open;
    }

    /**
     * Sets the open variable of the chest to either true or false
     *
     * @param newOpen true if chest has been opened, false if not
     */
    public void setOpen(boolean newOpen) {
        this.open = newOpen;
    }
}

/**
 * Chest collision detection
 */
class ChestCollisionDetect implements CollisionListener{
    private GameWorld world;

    /**
     * Constructs a collision detector for chests within the game world
     *
     * @param world the game world where the chest exists
     */
    public ChestCollisionDetect(GameWorld world) {
        this.world = world;
    }

    /**
     * Responds to a chest collision event
     *
     * @param e the collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Player collision with ground or Platform
        Chest chest = (Chest) e.getReportingBody();

        //Chest opens only if the player has a key and the chest is not already open
        if (e.getOtherBody() instanceof Player && !chest.getOpen() && Player.keysHeld > 0) {
            chest.removeAllImages();
            chest.addImage(new BodyImage("data/ChestKey/Open_chest.png", 3));
            Player.keysHeld--;
            Key.floatKeyControl = false;
            chest.setOpen(true);
            //Chest bursts open with 5 coins emerging
            for (int i=0; i<5; i++) {
                new Coin(world,(int)chest.getPosition().x, (int)chest.getPosition().y+3, 0.5f);
            }
        }
    }
}