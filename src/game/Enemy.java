package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Enemy skeleton
 * The enemy moves back and forth across the screen
 * Changes direction when it collides with a platform or another enemy.
 */
public class Enemy extends Walker {
    private int walkSpeed = 5;
    private final BodyImage skeleton_walk = new BodyImage("data/Enemy/Skeleton_walk.gif", 4);

    /**
     * Creates new enemy skeleton at a specified location.
     *
     * @param world game world in which enemy will be placed
     * @param x x-coordinate of enemy's initial position
     * @param y y-coordinate of enemy's initial position
     */
    public Enemy(GameWorld world, int x, int y) {
        super(world);
        SolidFixture enemyFixture = new SolidFixture(this, new BoxShape(1, 2));
        this.setPosition(new Vec2(x,y));
        addImage(skeleton_walk);
        startWalking(walkSpeed);
        addCollisionListener(new EnemyCollisionDetect(skeleton_walk));
    }

    //Getter
    public int getWalkSpeed() {
        return walkSpeed;
    }

    //Setter
    public int setWalkSpeed(int newWalkSpeed) {
        this.walkSpeed = newWalkSpeed;
        return this.walkSpeed;
    }
}

/**
 * Enemy skeleton collision detection
 * When an enemy collides with a platform or another enemy, it changes direction.
 */
class EnemyCollisionDetect implements CollisionListener{
    private BodyImage skeleton_walk;

    /**
     * Creates new enemy collision listener.
     *
     * @param skeleton_walk walking animation gif of the skeleton
     */
    public EnemyCollisionDetect(BodyImage skeleton_walk) {
        this.skeleton_walk = skeleton_walk;
    }

    /**
     * Handles collisions between enemy and other bodies.
     *
     * @param e collision event that triggered this method
     */
    @Override
    public void collide(CollisionEvent e) {
        Enemy enemy = (Enemy) e.getReportingBody();

        //If enemy runs into a platform or another enemy, it turns in the other direction
        if (e.getOtherBody() instanceof Platform || e.getOtherBody() instanceof Enemy) {
            //Clears images
            enemy.removeAllImages();
            //If enemy hits a wall to the Right it turns to Left
            if (enemy.getWalkSpeed() == 5) {
                enemy.setWalkSpeed(-5);
                enemy.addImage(skeleton_walk).flipHorizontal();
            }
            //If enemy hits a wall to the Left it turns to Right
            else {
                enemy.setWalkSpeed(5);
                enemy.addImage(skeleton_walk);
            }
            //Enemy starts walking at normal speed
            enemy.startWalking(enemy.getWalkSpeed());
        }
    }
}
