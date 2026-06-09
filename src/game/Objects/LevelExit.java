package game.Objects;

import city.cs.engine.*;
import game.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * Level exit portal that will send player to main menu
 * <p>
 * Also keeps progress of coin score
 */
public class LevelExit extends StaticBody {
    /**
     * Creates a new LevelExit at the specified location in the game world
     *
     * @param world game world
     * @param x x-coordinate of the LevelExit
     * @param y y-coordinate of the LevelExit
     */
    public LevelExit(GameWorld world, int x, int y) {
        super(world);
        SolidFixture exitFixture = new SolidFixture(this, new BoxShape(1,3));
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/exit.gif", 5));
    }
}
