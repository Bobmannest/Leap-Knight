package game;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.Player.Player;
import org.jbox2d.common.Vec2;

/**
 * Events before each tick
 */
public class PassiveEvents implements StepListener {
    private GameView view;
    private GameWorld world;

    /**
     * Constructs a new passive event with the specified world and view.
     *
     * @param world game world object
     * @param view  game view object
     */
    public PassiveEvents(GameWorld world, GameView view) {this.view = view;this.world = world;}

    /**
     * Called before each simulation step.
     * Updates the camera's position to track player each tick
     *
     * @param stepEvent event triggered before the step.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        //Camera tracks Player
        view.setCentre(new Vec2(world.getPlayer().getPosition().x,5));
    }

    /**
     * After each tick, check if player health is at or below
     * and to make sure player doesn't fall into void indefinitely
     *
     * @param stepEvent event triggered after the step.
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        //If player dies then character disappears
        if (Player.playerHP <= 0) {
            world.getPlayer().destroy();
        }

        //Makes sure player doesn't fall to their doom infinitely
        if (world.getPlayer().getPosition().y < -60) {
            Player.playerHP--;
            world.getPlayer().setPosition(new Vec2(world.getPlayer().getPosition().x, 35));
        }
    }
}
