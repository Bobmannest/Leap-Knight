package game.Pickups;
import city.cs.engine.*;
import game.GameWorld;
import game.Player.Player;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Coin objects
 * <p>
 * Can be picked up by the player or spawned from chests
 */
public class Coin extends DynamicBody {
    /**
     * Creates a new coin at the specified location and specified size.
     *
     * @param world game world where coin exists
     * @param x x-coordinate where coin spawns
     * @param y y-coordinate where coin spawns
     * @param size size scale for coin
     */
    public Coin(GameWorld world, int x, int y, float size) {
        super(world);
        SolidFixture coinFixture = new SolidFixture(this, new BoxShape(size,size*2));
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/Coins/coinPickup.gif", size*2));
        addCollisionListener(new CoinCollisionDetect());
    }
}

/**
 * Coin collision detection
 * <p>
 * When a player collides with a coin, increments player coin count by 1
 * and plays a sound
 */
class CoinCollisionDetect implements CollisionListener {
    /**
     * Responds to a collision event involving a coin.
     *
     * @param e collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Loads audio
        SoundClip coinSfx;
        try {
            coinSfx = new SoundClip("data/Sfx/coinSfx.wav");
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            throw new RuntimeException(ex);
        }

        //Destroys coin objects when they are picked up by the player
        Coin coin = (Coin) e.getReportingBody();

        if (e.getOtherBody() instanceof Player) {
            coin.destroy();
            coinSfx.play();
            Player.coinsCollected++;
        }
    }
}