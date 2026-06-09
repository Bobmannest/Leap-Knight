package game.Pickups;
import city.cs.engine.*;
import game.GameWorld;
import game.Player.Player;
import org.jbox2d.common.Vec2;

/**
 * Shield potions
 * <p>
 * Can be picked up by the player for extra 2 shield health(up to 4)
 */
public class Shield extends DynamicBody {
    /**
     * Creates  new shield pickup at the specified location.
     *
     * @param world game world where shield exists
     * @param x x-coordinate where shield spawns
     * @param y y-coordinate where shield spawns
     */
    public Shield(GameWorld world, int x, int y) {
        super(world);
        SolidFixture shieldFixture = new SolidFixture(this, new BoxShape(1.2f,1.8f));
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/Shield/shield.gif", 3));
        addCollisionListener(new ShieldCollisionDetect());
    }
}

/**
 * Shield collision detection
 * <p>
 * When the player collides with a shield potion, shield health is increased by 2
 * But it cannot exceed the max value of 4.
 */
class ShieldCollisionDetect implements CollisionListener{
    /**
     * Responds to a collision event involving shield potion
     * Increases the player shield health by 2, up to a maximum of 4
     *
     * @param e collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Destroys shield objects when they are picked up by the player
        Shield shield = (Shield) e.getReportingBody();

        if (e.getOtherBody() instanceof Player) {
            shield.destroy();
            Player.shieldHP += 2;
            if (Player.shieldHP > 4) {Player.shieldHP=4;}
        }
    }
}
