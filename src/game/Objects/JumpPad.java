package game.Objects;

import city.cs.engine.*;
import game.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * Jumppad which will make the player jump upon collision with it
 * <p>
 * Note: collision is in playercontrol class for easy collision with any jumppad
 */
public class JumpPad extends StaticBody {
    /**
     * Creates a new JumpPad at the specified location in the game world.
     *
     * @param world game world
     * @param x x-coordinate of the jump pad
     * @param y y-coordinate of the jump pad
     */
    public JumpPad(GameWorld world, int x, float y) {
        super(world);
        SolidFixture JumpFixture = new SolidFixture(this, new BoxShape(1, 0.3f));
        this.setPosition(new Vec2(x,y));
        addImage(new BodyImage("data/jumpPad.png", 0.8f));
    }
}
