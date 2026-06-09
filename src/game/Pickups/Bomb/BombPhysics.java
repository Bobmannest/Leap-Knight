package game.Pickups.Bomb;
import city.cs.engine.*;
import game.Enemy;
import game.GameWorld;
import game.Player.Player;
import org.jbox2d.common.Vec2;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This is the physical bomb object which the player throws
 */
public class BombPhysics extends DynamicBody {
    /** Executor service to time explosion and destroy the bomb object after use */
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    /**
     * Creates a new bomb physics at the specified location.
     *
     * @param world game world where the bomb exists
     * @param x x-coordinate where the bomb spawns
     * @param y y-coordinate where the bomb spawns
     */
    public BombPhysics(GameWorld world, int x, int y) {
        super(world);
        Shape bombPhysicsBody = new CircleShape(1);
        SolidFixture bombPhysicsFixture = new SolidFixture(this, bombPhysicsBody);
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/Bomb/Bomb.png", 2));
        //After 3 seconds of the object existing, it explodes
        executorService.schedule(() -> {boom();}, 3, TimeUnit.SECONDS);
    }

    /**
     * Bomb explosion
     * <p>
     * The explosion creates a sensor radius that can destroy enemies
     * After 1 second the sensor and explosion are removed
     */
    public void boom() {
        removeAllImages();
        Shape bombExplosionBody = new CircleShape(4);
        Sensor bombExplosionSensor = new Sensor(this, bombExplosionBody);
        addImage(new BodyImage("data/Bomb/boom.gif", 15));
        bombExplosionSensor.addSensorListener(new BombExplosionSensorListener());

        //After 1 second the explosion dissapears
        executorService.schedule(() -> {
            destroy();
            bombExplosionSensor.destroy();
            }, 1, TimeUnit.SECONDS);
    }
}

/**
 * Bomb explosion sensor detection
 * <p>
 * When an enemy enters the explosion radius, enemy is destroyed and the player coin count increases.
 */
class BombExplosionSensorListener implements SensorListener{
    /**
     * Called when an enemy enters the explosion radius.
     *
     * @param e sensor event triggered by a body entering
     */
    @Override
    public void beginContact(SensorEvent e) {
        //When the explosion hitbox collides with an enemy, enemy is destroyed and a coin is collected
        if (e.getContactBody() instanceof Enemy) {
            Enemy enemy = (Enemy) e.getContactBody();
            enemy.destroy();
            Player.coinsCollected++;
        }
    }

    /**
     * Unused
     *
     * @param e the sensor event triggered by a body leaving
     */
    @Override
    public void endContact(SensorEvent e) {}
}
