package game;
import city.cs.engine.UserView;
import game.Pickups.Key.Key;
import game.Pickups.Key.KeyFloating;
import game.Player.Player;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * View which the player can see into the world and all the GUI elements
 * and parallax background
 */
public class GameView extends UserView {
    private GameWorld world;
    private Player player;
    private List<KeyFloating> floatingKeys;

    //Math variables for smooth floating key movement
    private float floatDistance = 0;
    private float floatMax = 90;
    private float floatIncrement = 0.1f;
    private float floatSin = 0;
    private boolean positiveFloat = true;

    /**
     * Constructs a GameView for rendering the game view and GUI and background
     *
     * @param world game world to be rendered
     * @param width width of view
     * @param height height of view
     * @param player player whose position affects the view
     */
    public GameView(GameWorld world, int width, int height, Player player) {
        super(world, width, height);
        this.world = world;
        this.player = player;
        this.floatingKeys = new ArrayList<>();

        //Camera positioning
        setView(player.getPosition(), 15);
    }

    /**
     * Paints the parallax background
     * The background layers move at different speeds to create parallax effect.
     *
     * @param g the Graphics2D object used for rendering
     */
    @Override
    public void paintBackground(Graphics2D g){
        //Parallax background
        int xLocation = (int) player.getPosition().x+200;

        g.drawImage(new ImageIcon("data/Background/sky1.png").getImage(), - (xLocation/30), 0, this);
        g.drawImage(new ImageIcon("data/Background/stars2.png").getImage(), - (xLocation/26), 0, this);
        g.drawImage(new ImageIcon("data/Background/moon3.png").getImage(), - (xLocation/22), 0, this);
        g.drawImage(new ImageIcon("data/Background/cloud4.png").getImage(), - (xLocation/18), 0, this);
        g.drawImage(new ImageIcon("data/Background/cloud5.png").getImage(), - (xLocation/14), 0, this);
        g.drawImage(new ImageIcon("data/Background/cloud6.png").getImage(), - (xLocation/10), 0, this);
        g.drawImage(new ImageIcon("data/Background/cloud7.png").getImage(), - (xLocation/8), 0, this);
        g.drawImage(new ImageIcon("data/Background/cloud8.png").getImage(), - (xLocation/6), 0, this);
        g.drawImage(new ImageIcon("data/Background/cloudS9.png").getImage(), - (xLocation/5), 0, this);
        g.drawImage(new ImageIcon("data/Background/cloudS10.png").getImage(), - (xLocation/4), 0, this);
        g.drawImage(new ImageIcon("data/Background/mount11.png").getImage(), - (xLocation/3), 0, this);
        g.drawImage(new ImageIcon("data/Background/desert12.png").getImage(), - (xLocation/2), 0, this);
    }

    /**
     * Paints the foreground GUI including health and shield bar and the collected items.
     * Updates health and shield bars dynamically based on player stats
     * Also handles rendering of the floating keys, coins, and bombs
     *
     * @param g Graphics2D object used for rendering
     */
    @Override
    public void paintForeground(Graphics2D g){
        //Healthbar dynamic dimensions
        int widthHP = 256 * Player.playerHP/4;
        int xHP = 4 - Player.playerHP;
        //Shieldbar dynamic dimensions
        int widthShieldHP = 256 * Player.shieldHP/4;
        int xShieldHP = 4 - Player.shieldHP;

        //Coins collected and bombs held text
        String coinsCollectedText = String.valueOf(Player.coinsCollected);
        String bombsCollectedText = String.valueOf(Player.bombsHeld);

        //Draws healthbar and shieldbar assets
        g.drawImage(new ImageIcon("data/Foreground/fullBar.png").getImage(),25+xHP*8,25,widthHP,64,this);
        g.drawImage(new ImageIcon("data/Foreground/shieldBar.png").getImage(),25+xShieldHP*8,25,widthShieldHP,64,this);
        g.drawImage(new ImageIcon("data/Foreground/emptyBar.png").getImage(),25,25,256,64,this);
        if (Player.shieldHP > 0) {g.drawImage(new ImageIcon("data/Foreground/shieldIcon.png").getImage(),19,18,75,75,this);}

        //Bomb and coin icon
        g.drawImage(new ImageIcon("data/Bomb/bombPickup.png").getImage(),34,150,44,52,this);
        g.drawImage(new ImageIcon("data/Coins/coinIcon.gif").getImage(),33,90,48,48,this);

        //Bomb and coins text
        g.scale(3,3);
        g.drawString(bombsCollectedText,30,65);
        g.drawString(coinsCollectedText,30,42);

        //Adds floating keys
        if (Key.floatKeyControl) {
            floatingKeys.add(new KeyFloating(world, (int) player.getPosition().x, (int) player.getPosition().y));
            Key.floatKeyControl = false;
        }

        //Destroys keys when they are used
        if (Player.keysHeld < floatingKeys.size()) {
            floatingKeys.get(Player.keysHeld).destroy();
            floatingKeys.remove(Player.keysHeld);
        }

        //Makes keys follow player and float in place up and down SMOOTHLY
        if (positiveFloat) {
            floatSin += floatIncrement;
        } else if (!positiveFloat) {
            floatSin -= floatIncrement;
        }

        if (floatSin >= floatMax) {
            positiveFloat = false;
        } else if (floatSin <= -floatMax) {
            positiveFloat = true;
        }

        //Calculates using sine for smooth key floating
        floatDistance = (float) Math.sin(floatSin)*0.5f;

        //Center of key movement(3.5 units above player)
        float floaty = 3.5f;

        //Key position is updated for every floating key
        for (int i=0; i < Player.keysHeld; i++ ) {
            floatingKeys.get(i).setPosition(new Vec2(player.getPosition().x, player.getPosition().y + floaty + floatDistance));
            floaty+=1;
        }
    }
}
