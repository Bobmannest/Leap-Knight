package game.Pickups;

import city.cs.engine.*;
import game.GameWorld;
import game.Player.Player;
import org.jbox2d.common.Vec2;

/**
 * Heart pickup
 * <p>
 * Can be picked up by the player for max health
 */
public class Heart extends DynamicBody {
    /**
     * Creates a new heart pickup at the specified location
     *
     * @param world game world where heart exists
     * @param x x-coordinate where heart spawns
     * @param y y-coordinate where heart spawns
     */
    public Heart(GameWorld world, int x, int y) {
        super(world);
        SolidFixture heartFixture = new SolidFixture(this, new BoxShape(1,2));
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/heart.png", 2.5f));
        addCollisionListener(new HeartCollisionDetect());
    }
}

//Heart collision detection
/**
 * Heart collision detection
 * <p>
 * When a player collides with a heart, heart is collected and the player's health is maxxed
 */
class HeartCollisionDetect implements CollisionListener {
    /**
     * Responds to a collision event involving heart
     * If the player collects heart, it is destroyed and the player's health is set to max
     *
     * @param e collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Destroys heart objects when they are picked up by the player
        Heart heart = (Heart) e.getReportingBody();

        if (e.getOtherBody() instanceof Player) {
            heart.destroy();
            Player.playerHP = 4;
        }
    }
}
