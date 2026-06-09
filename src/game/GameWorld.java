package game;
import city.cs.engine.*;
import game.Objects.*;
import game.Pickups.Bomb.Bomb;
import game.Pickups.Coin;
import game.Pickups.Heart;
import game.Pickups.Key.Key;
import game.Pickups.Shield;
import game.Player.Player;
import game.Player.PlayerCollisionDetect;
import game.Player.PlayerControl;
import org.jbox2d.common.Vec2;

/**
 * The abstract class game world
 * Always creates a new player and one level which depends on the input level number
 */
public abstract class GameWorld extends World {
    private PlayerControl playerC;
    private Player player;

    /**
     * Constructor that initializes game world and sets up the level content.
     * Also creates a new player and links player to world
     */
    public GameWorld() {
        super();

        //This method is unique for each level
        setupLevel();

        //Creates new Player class
        player = new Player(this, -20, 0);
        //Creates new player Control and MouseControl fields using player
        playerC = new PlayerControl(player, this);
        player.addCollisionListener(new PlayerCollisionDetect(playerC, player));
    }

    //Constructor to set up level bodies
    protected abstract void setupLevel();

    //Getter
    public Player getPlayer(){
        return player;
    }

    public PlayerControl getPlayerC(){
        return playerC;
    }
}

/**
 * A test level that includes every mechanic for testing or messing around
 */
class TestLevel extends GameWorld {
    public TestLevel() {
        super();
    }

    @Override
    protected void setupLevel() {
        //Platforms (this,x,y)
        //Test Ground
        StaticBody Ground = new StaticBody(this, new BoxShape(90, 0.5f));
        Ground.setPosition(new Vec2(0, -12));

        //Platforms
        new MPlatform(this,3,5);
        new LPlatform(this, -6, -3);
        new MPlatform(this,-19,5);

        //Smallest platforms
        new XSPlatform(this,26,-11);
        new XSPlatform(this,6,-11);
        new XSPlatform(this,-12,-2);
        new XSPlatform(this,0,-2);

        //Enemies
        new Enemy(this, -4, -1);

        new Enemy(this, 18, -10);
        new Enemy(this, 24, -10);


        //Coins
        new Coin(this,3,8,1);
        new Coin(this,5,-4,1);
        new Coin(this,-19,8,1);

        //Shield potion
        new Shield(this,-3, -9);

        //Healing heart
        new Heart(this,-45,-8);

        //Chests and Keys
        new Chest(this,-5, -9);
        new Chest(this,-8, -9);

        new Key(this,-28, -9);
        new Key(this,-14, -9);

        //Jump pad
        new JumpPad(this, -25,-11.1f);

        //Spikes
        new SSpike(this, -40,-10);
        new MSpike(this, -55,-10);
        new LSpike(this, -75,-10);


        //Bomb
        new Bomb(this, -34, -4);
        new Bomb(this, -34, -4);
        new Bomb(this, -34, -4);

        //Exit
        new LevelExit(this, 14, -4);
    }
}

/**
 * Level 1 - Intro to movement, jumping, sprinting, level exits and coins
 */
class Level1 extends GameWorld {
    public Level1() {
        super();
    }

    @Override
    protected void setupLevel() {
        //Tutorial tips
        new ArrowTutorial(this,-20,8);
        new PauseTutorial(this,0,12);
        new MoveTutorial(this,-20,12);
        new JumpTutorial(this,22,12);
        new SprintTutorial(this,85,13);
        new ExitTutorial(this,107,14);


        //Platforms
        new MPlatform(this,-42,-1);

        new XLPlatform(this,-15,-1);
        new XLPlatform(this,16.5f,-1);

        new LPlatform(this,41,4);

        new XSPlatform(this,43, 16);
        new MPlatform(this,55,11);
        new XSPlatform(this,67, 16);
        
        new XLPlatform(this,64,4);
        new XLPlatform(this,95.5f,4);

        //Coins(2y above platforms)
        new Coin(this,-40,1,1);
        new Coin(this,-44,1,1);

        new Coin(this,-5,1,1);
        new Coin(this,0,1,1);
        new Coin(this,5,1,1);

        new Coin(this,35,6,1);

        new Coin(this,43,18,1);
        new Coin(this,67,18,1);
        new Coin(this,53,13,1);
        new Coin(this,57,13,1);

        //Exit
        new LevelExit(this, 107, 8);
    }
}

/**
 * Level 2 - Intro to enemies, attacking, keys and chests, spikes, shield potion
 */
class Level2 extends GameWorld {
    public Level2() {
        super();
    }

    @Override
    protected void setupLevel() {
        //Tutorial tips
        new ArrowTutorial(this,-20,8);
        new AttackTutorial(this,22,9);


        //Platforms
        new XSPlatform(this,-74,3);
        new XSPlatform(this,-58,3);
        new MPlatform(this,-42,-1);

        new XLPlatform(this,-15,-1);
        new XLPlatform(this,15,-1);
        new XSPlatform(this,31,0);
        new XLPlatform(this,45,-1);
        new XSPlatform(this,51,0);
        new XLPlatform(this,75,-1);

        new XSPlatform(this,69,9);
        new LPlatform(this,75,8);
        new XSPlatform(this,75,18);
        new XSPlatform(this,81,9);

        new XLPlatform(this,105,-1);


        //Coins
        new Coin(this,-42,1,1);

        new Coin(this,55,1,1);
        new Coin(this,58,1,1);

        new Coin(this,31,3,1);
        new Coin(this,51,3,1);

        new Coin(this,90,1,1);
        new Coin(this,101,1,1);

        //Heart
        new Heart(this,65,1);

        //Shield
        new Shield(this,75,20);

        //Enemies
        new Enemy(this,50,2);
        new Enemy(this,81,11);

        //Key
        new Key(this,-74,5);

        //Chest
        new Chest(this,-6,1);

        //Spike
        new MSpike(this,96,1);

        //Exit
        new LevelExit(this,115,3);
    }
}

/**
 * Level 3 - Intro to bombs, jump pads
 */
class Level3 extends GameWorld {
    public Level3() {
        super();
    }

    @Override
    protected void setupLevel() {
        //Tutorials
        new ArrowTutorial(this,-20,8);
        new bombTutorial(this,26,8);

        //Platforms
        new XLPlatform(this,-15,-1);
        new XLPlatform(this,15,-1);

        new XLPlatform(this,46.5f,-6);

        new XLPlatform(this,78.5f,-1);

        new XSPlatform(this,55, 16);
        new MPlatform(this,70,14);
        new MPlatform(this,90,14);

        new XLPlatform(this,108.5f,-1);

        new XSPlatform(this,84,0);
        new XSPlatform(this,119,0);

        new XSPlatform(this,132,-1);

        //Enemies
        new Enemy(this,34,2);
        new Enemy(this,39,2);
        new Enemy(this,44,2);
        new Enemy(this,49,2);
        new Enemy(this,54,2);
        new Enemy(this,59,2);

        new Enemy(this,90,8);
        new Enemy(this,105,8);
        new Enemy(this,115,8);

        //Coins
        new Coin(this,-12,1,1);

        new Coin(this,8,1,1);
        new Coin(this,12,1,1);

        //Bombs
        new Bomb(this,25,2);
        new Bomb(this,26,5);
        new Bomb(this,28,2);

        new Bomb(this,68,17);
        new Bomb(this,71,17);

        //Hearts
        new Heart(this, 65,2);

        //Shields
        new Shield(this,84,4);

        //Jump pads
        new JumpPad(this,80,0);

        //Key
        new Key(this,55,18);
        new Key(this,132,3);

        //Chest
        new Chest(this,88,17);
        new Chest(this,92,17);

        //Spikes
        new SSpike(this,-29,1);
        new MSpike(this,-2,1);
        new SSpike(this,73,1);


        //Exit
        new LevelExit(this,123,3);
    }
}