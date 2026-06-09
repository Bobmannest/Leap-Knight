package game.Player;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.*;
import game.Objects.JumpPad;
import game.Objects.LevelExit;
import game.Spike;

/**
 * Player collision detection
 * <p>
 * This class manages how player interacts with platforms, enemies, jumppads, and level exits
 * It handles knockback, damage, jumping, and transitioning to the next level
 */
public class PlayerCollisionDetect implements CollisionListener{
    //Knockback speed after hitting a wall
    private int playerKnockback = 2;
    //Stops player from sliding when knocked back and still holding the same key
    private boolean collisionSlideControl = false;

    private PlayerControl playerC;
    private Player player;

    /**
     * Constructs new PlayerCollisionDetect object.
     *
     * @param playerC PlayerControl instance controlling player
     * @param player player object being controlled
     */
    public PlayerCollisionDetect(PlayerControl playerC, Player player) {
        this.playerC = playerC;
        this.player = player;
    }

    /**
     * Handles collisions between player and other bodies
     * Collision results depend on the type of object that was collided with
     *
     * @param e collision event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Player collision with ground or Platform
        if (e.getOtherBody() instanceof Platform) {
            if (collisionSlideControl) {
                collisionSlideControl = false;
                player.startWalking(0);
            }

            //Resets player images
            player.removeAllImages();

            //Makes sure Player cannot glide on the sides of platforms
            if (player.getLinearVelocity().y != 0) {
                collisionSlideControl = true;
                if (playerC.getFacingRight()) {
                    playerC.setPlayerspeed(-playerKnockback);
                } else {
                    playerC.setPlayerspeed(playerKnockback);
                }

                player.startWalking(playerC.getPlayerspeed());
            }

            //Collision direction checks so when player lands on a platform, they do correct idle animation
            if (playerC.getPlayerspeed() == 0) {
                if (playerC.getFacingRight()) {player.addImage(playerC.getImage("idle"));}
                else {player.addImage(playerC.getImage("idle")).flipHorizontal();}
            } else {
                if (playerC.getFacingRight()) {player.addImage(playerC.getImage("walk"));}
                else {player.addImage(playerC.getImage("walk")).flipHorizontal();}
            }
        }

        //If player collides with an enemy, player loses health
        if (e.getOtherBody() instanceof Enemy || e.getOtherBody() instanceof Spike) {
            if (Player.shieldHP > 0) {Player.shieldHP--;}
            else {Player.playerHP--;}
        }

        //Makes player jump when a jumppad has been collided with
        if (e.getOtherBody() instanceof JumpPad) {
            player.jump(50);
        }

        //Goes back to level select when Player reaches level exit
        if (e.getOtherBody() instanceof LevelExit) {
            MainMenu.currentGame.reset();
            new MainMenu();
        }
    }
}
