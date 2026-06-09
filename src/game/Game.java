//Link to free Player sprites used: https://aamatniekss.itch.io/fantasy-knight-free-pixelart-animated-character
//Link to free Background sprites: https://digitalmoons.itch.io/free-parallax-desert-background-seamless
//Link to free Healthbar GUI sprites: https://skristi.itch.io/heart-and-health-bars
//Link to free enemy Skeleton sprites: https://monopixelart.itch.io/skeletons-pack
//Link to free Coin sprites: https://greatdocbrown.itch.io/coins-gems-etc
//Link to free Chest and Key sprites: https://jan-schneider.itch.io/chest-and-coins
//Link to free potion sprites: https://karsiori.itch.io/pixel-art-potion-pack-animated
//Link to free inventory sprites: https://bragorn.itch.io/modular-inventory-sprites
//Link to free keyboard key icons: https://dreammix.itch.io/keyboard-keys-for-ui

package game;

import game.Player.Player;
import javax.swing.*;


/**
 * Represents the game itself,
 * Handles the creation of the game world, player setup, level creation
 * and world and player resetting
 */
public class Game {
    private JFrame frame;
    private int levelNum;

    public static GameWorld world;

    /**
     * Creates new game instance for a specified level
     *
     * @param LevelNum number of level to load
     */
    public Game(int LevelNum) {
        //Creates new level using the levelNum which depends on which level button you click in the level select
        this.levelNum = LevelNum;
        levelCreate(levelNum);

        //Makes a view to look into the game world
        GameView view = new GameView(world, 1000, 800, world.getPlayer());

        //Adds various passive events that are checked every tick
        PassiveEvents passive = new PassiveEvents(world, view);
        
        //Frame creation
        frame = new JFrame("Platformer");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Debug viewer
        //JFrame debugView = new DebugViewer(world, 1000, 800);

        //Adding key listener(ignore useless error)
        frame.addKeyListener(world.getPlayerC());
        frame.addMouseListener(world.getPlayerC());

        //Start the world
        world.addStepListener(passive);
        world.start();
    }

    /**
     * Creates a new level based on the levelNum
     * Each level is a different GameWorld subclass
     *
     * @param levelNum number of the level to be created
     */
    public void levelCreate(int levelNum) {
        switch (levelNum) {
            case 0 -> world = new TestLevel();
            case 1 -> world = new Level1();
            case 2 -> world = new Level2();
            case 3 -> world = new Level3();
        }
        world.setGravity(50);
    }

    /**
     * Gets rid of the completed level JFrame and stops world,
     * also updates coins high score and resets player attributes to default
     */
    public void reset() {
        //Stops current level music track when player exits to main menu or completes a level
        switch (levelNum) {
            case 0 -> MainMenu.sandboxTheme.stop();
            case 1 -> MainMenu.tutorialTheme.stop();
            case 2 -> MainMenu.level2Theme.stop();
            case 3 -> MainMenu.level3Theme.stop();
        }

        //Updates high score of coins collected
        if (Player.coinsCollected > MainMenu.getScore(levelNum)) {
            MainMenu.setScore(levelNum,Player.coinsCollected);
        }

        //Stops previous world and removes it
        world.stop();
        frame.dispose();

        //Resets player stats
        Player.playerHP = 4;
        Player.shieldHP = 0;
        Player.coinsCollected = 0;
        Player.bombsHeld = 0;
        Player.keysHeld = 0;
    }

    /**
     * Resumes the game from paused
     */
    public void unPause() {
        world.start();
    }

    /**
     * Main method to run the game by creating a new MainMenu.
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args){
        new MainMenu();
    }
}
