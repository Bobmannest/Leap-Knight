package game.Player;
import city.cs.engine.*;
import game.Enemy;
import game.GameWorld;
import game.PauseMenu;
import game.Pickups.Bomb.BombPhysics;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Keyboard and Mouse controls
 * Handles player walking, sprinting, jumping, attacks, and game pausing
 */
public class PlayerControl implements KeyListener, MouseListener {
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    //Passing playerChar to this class so it can be recognised
    private Walker player;
    private GameWorld world;

    private int playerspeed=0;
    //Keeps track of player direction(false is Right)(true is Left)
    private boolean facingRight = true;

    //Speed and jump speed variables
    private final int maxspeed = 20;
    private final int jumpPower = 35;

    //Sprite images
    private final BodyImage idle = new BodyImage("data/Sprites/Idle.gif", 4);
    private final BodyImage walk = new BodyImage("data/Sprites/walk.gif", 4);
    private final BodyImage jump = new BodyImage("data/Sprites/Jump1.png", 4);
    private final BodyImage run = new BodyImage("data/Sprites/Run.gif", 4);

    //Sprite GIFs
    private final BodyImage attack = new BodyImage("data/Sprites/Attack.gif", 4);

    /**
     * Constructs new PlayerControl object for controlling the player
     *
     * @param player player character controlled by this instance
     * @param world game world in which the player exists
     */
    public PlayerControl(Walker player, GameWorld world) {this.player = player; this.world=world;}

    /**
     * Check direction and adds images accordingly
     *
     * @param image image to add to player
     */
    public void directionCheck(BodyImage image) {
        if (facingRight){player.addImage(image);}
        else {player.addImage(image).flipHorizontal();}
    }

    //Unused
    @Override
    public void keyTyped(KeyEvent e){}

    /**
     * Responds to key presses to control the player
     * Handles movement, jumping, attacking, sprinting, and pausing
     *
     * @param e key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //Prevents image stacking
        player.removeAllImages();

        //Move right
        if (e.getKeyCode() == KeyEvent.VK_D) {
            facingRight = true;
            playerspeed = 10;
            player.addImage(walk);
        }

        //Move left
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            facingRight = false;
            playerspeed = -10;
            player.addImage(walk).flipHorizontal();
        }

        //Throw Bomb
        else if (e.getKeyCode() == KeyEvent.VK_E && Player.bombsHeld > 0) {
            Player.bombsHeld--;
            if (facingRight) {
                player.addImage(idle);
                new BombPhysics(world, (int)player.getPosition().x+1, (int)player.getPosition().y+2).applyForce(new Vec2(3000,3000));
            } else {
                player.addImage(idle).flipHorizontal();
                new BombPhysics(world, (int)player.getPosition().x-1, (int)player.getPosition().y+2).applyForce(new Vec2(-3000,3000));}
        }

        //Sprint(also works as mid air dash!)
        //Sprint(Right)
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT && facingRight) {
            playerspeed = maxspeed;
            player.addImage(run);
        }
        //Sprint(Left)
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT && !facingRight ) {
            playerspeed =- maxspeed;
            player.addImage(run).flipHorizontal();
        }

        //Jump
        else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_W) {
            player.jump(jumpPower);

            //Jump sprites
            if (playerspeed > 0){player.addImage(jump);}
            else if (playerspeed < 0){player.addImage(jump).flipHorizontal();}

            //Check for flipped player sprite during jump
            directionCheck(jump);
        }

        //Pause the game
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            world.stop();
            new PauseMenu();
        }

        //Stops other keys from crashing the game
        else {
            directionCheck(idle);
        }

        //Maximum speed check
        if (playerspeed > maxspeed) {playerspeed = maxspeed;}
        else if (playerspeed < -maxspeed) {playerspeed = -maxspeed;}
        player.startWalking(playerspeed);
    }

    /**
     * Speed Resets in other direction as well as animation
     *
     * @param e key event
     */
    @Override
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
            playerspeed=0;
            player.removeAllImages();
            directionCheck(idle);
        }
        player.startWalking(playerspeed);
    }



    //Mouse Controls
    @Override
    public void mouseClicked(MouseEvent e) {
        //Resets images
        player.removeAllImages();

        //Player attacking
        if (e.getButton() == MouseEvent.BUTTON1) {
            //Creates new attack hitbox which is always infront of the player(depends on where player faces)
            if (facingRight) {new AttackHitbox(world).setPosition(new Vec2(player.getPosition().x+3, player.getPosition().y));}
            else {new AttackHitbox(world).setPosition(new Vec2(player.getPosition().x-3, player.getPosition().y));}

            //Checks which direction to display attacking animation and after 1 second of the attack animation playing, player retruns to idle animation
            directionCheck(attack);
            executorService.schedule(() -> {
                player.removeAllImages();
                directionCheck(idle);
                }, 1, TimeUnit.SECONDS);
        }
    }

    //Unused
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}





    //Getters
    public boolean getFacingRight() {
        return facingRight;
    }

    public int getPlayerspeed() {
        return playerspeed;
    }

    /**
     * Image getter which depends on requested image name
     *
     * @param image image name as a string
     * @return corresponding BodyImage for the player
     */
    public BodyImage getImage(String image) {
        return switch (image) {
            case "idle" -> idle;
            case "jump" -> jump;
            case "walk" -> walk;
            case "run" -> run;
            default -> null;
        };
    }

    //Setters
    public void setPlayerspeed(int newSpeed) {
        this.playerspeed = newSpeed;
    }
}

/**
 * Hitbox for melee attacking.
 * Used when player performs attacks, detects collisions with enemies.
 */
class AttackHitbox extends StaticBody {
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    /**
     * New AttackHitbox for detecting melee attacks using sensor
     * <p>
     * the hitbox will automatically be destroyed after 1 second.
     *
     * @param world game world in which hitbox exists
     */
    public AttackHitbox(GameWorld world) {
        super(world);
        //Creates a new sensor and adds a listener to it
        new Sensor(this, new BoxShape(1.5f,2)).addSensorListener(new AttackSensorListener());
        executorService.schedule(this::destroy, 1, TimeUnit.SECONDS);
    }
}

/**
 * Attack sensor listener detects when an enemy is hit by the player attacks.
 */
class AttackSensorListener implements SensorListener {
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof Enemy) {
            Enemy enemy = (Enemy) e.getContactBody();
            Player.coinsCollected++;
            enemy.destroy();
        }
    }

    //Unused
    @Override
    public void endContact(SensorEvent sensorEvent) {}
}
