package game.Pickups.Bomb;
import city.cs.engine.*;
import game.GameWorld;
import game.Player.Player;
import org.jbox2d.common.Vec2;

/**
 * Bomb pickup
 * <p>
 * Allows the player to store bombs for later
 */
public class Bomb extends DynamicBody {
    /**
     * Creates a new Bomb pickup at the specified location in the game world.
     *
     * @param world game world where the bomb exists
     * @param x x-coordinate of the bomb
     * @param y y-coordinate of the bomb
     */
    public Bomb(GameWorld world, int x, int y) {
    super(world);
    SolidFixture bombFixture = new SolidFixture(this, new BoxShape(1.3f,1.3f));
    this.setPosition(new Vec2(x,y));
    addImage(new BodyImage("data/Bomb/bombPickup.png", 2.6f));
    addCollisionListener(new BombCollisionDetect());
    }
}

/**
 * Bomb collision detection
 */
class BombCollisionDetect implements CollisionListener{
    /**
     * Responds to collision events involving the bomb pickup.
     *
     * @param e collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Player collision with ground or Platform
        Bomb bomb = (Bomb) e.getReportingBody();

        //Increment player bomb count by 1 and destroy the bomb pickup object
        if (e.getOtherBody() instanceof Player) {
            bomb.destroy();
            Player.bombsHeld++;
        }
    }
}
