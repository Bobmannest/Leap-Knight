package game;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * MainMenu class displays a background image, high scores and buttons for starting each level
 * Background music and menu and level transitions are also handled here
 */
public class MainMenu extends JFrame {
    //The current game instance
    public static Game currentGame = null;
    //Main menu panel
    private JPanel startMenu;

    //Music tracks
    private SoundClip menuTheme;
    public static SoundClip sandboxTheme;
    public static SoundClip tutorialTheme;
    public static SoundClip level2Theme;
    public static SoundClip level3Theme;

    //Tracks each level high score of coins collected
    public static int level0score = 0;
    public static int level1score = 0;
    public static int level2score = 0;
    public static int level3score = 0;

    ImageIcon coin = new ImageIcon("data/Coins/coinIcon.gif");

    /**
     * Constructs a new MainMenu and sets up the menu UI, buttons, and music.
     */
    public MainMenu() {
        startMenu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Always call the parent method to ensure proper rendering
                ImageIcon background = new ImageIcon("data/Background/fullBackground.png"); // Provide the correct path to your image
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null); // Draw the image scaled to the panel size
            }
        };
        startMenu.setLayout(null);

        //Loads sound files
        try {
            menuTheme = new SoundClip("data/Themes/menuTheme.wav");
            tutorialTheme = new SoundClip("data/Themes/tutorialTheme.wav");
            sandboxTheme = new SoundClip("data/Themes/sandboxTheme.wav");
            level2Theme = new SoundClip("data/Themes/level2Theme.wav");
            level3Theme = new SoundClip("data/Themes/level3Theme.wav");

            menuTheme.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        //Title
        ImageIcon title = new ImageIcon(new ImageIcon("data/Icons/levelSelect.png").getImage().getScaledInstance(700, 100, Image.SCALE_SMOOTH));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(150, 75, 700, 100);
        startMenu.add(titleLabel);

        //High score text
        textWithIcon(coin,level0score,285,400,100,50,15);
        textWithIcon(coin,level1score,410,400,100,50,15);
        textWithIcon(coin,level2score,535,400,100,50,15);
        textWithIcon(coin,level3score,660,400,100,50,15);

        //Sandbox/Test level 0 button
        mainButtonCreate(new ImageIcon("data/Icons/sandbox.png"),70,90,275,450,100,100).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentGame = new Game(0);
                menuTheme.stop();
                sandboxTheme.loop();
                dispose();
            }
        });

        //Level 1 button code
        mainButtonCreate(new ImageIcon("data/Icons/num1.png"),70,90,400,450,100,100).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentGame = new Game(1);
                menuTheme.stop();
                tutorialTheme.loop();
                dispose();
            }
        });

        //Level 2 button code
        mainButtonCreate(new ImageIcon("data/Icons/num2.png"),70,90,525,450,100,100).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentGame = new Game(2);
                menuTheme.stop();
                level2Theme.loop();
                dispose();
            }
        });

        //Level 3 button code
        mainButtonCreate(new ImageIcon("data/Icons/num3.png"),70,90,650,450,100,100).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentGame = new Game(3);
                menuTheme.stop();
                level3Theme.loop();
                dispose();
            }
        });

        //Sets frame size, location(centre) and visibility
        setContentPane(startMenu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Makes buttons by first converting icon to resizable images then making the buttons use the images
     * Then the button is added to the main menu JPanel and returned to be used for the actionlistener
     *
     * @param icon icon for button
     * @param width width of icon
     * @param height height of icon
     * @param x x-position of button
     * @param y y-position of button
     * @param Bwidth width of button
     * @param Bheight height of button
     * @return created JButton
     */
    public JButton mainButtonCreate(ImageIcon icon, int width, int height, int x, int y, int Bwidth, int Bheight) {
        JButton button = new JButton(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        button.setBounds(x, y, Bwidth, Bheight);
        startMenu.add(button);
        return button;
    }

    /**
     * Creates text with icons
     *
     * @param icon icon to be displayed next to the text
     * @param num score value to be displayed
     * @param x x-position of label
     * @param y y-position of label
     * @param width width of label
     * @param height height of label
     * @param size font size for text
     */
    public void textWithIcon(ImageIcon icon, int num, int x, int y, int width, int height, int size) {
        JLabel text = new JLabel("Score: " + num, icon, JLabel.LEFT);
        text.setFont(new Font("Arial", Font.BOLD, size));
        text.setForeground(Color.BLACK);
        text.setBounds(x, y, width, height);
        startMenu.add(text);
    }

    /**
     * Retrieves high score for specified level.
     *
     * @param levelNum level number
     * @return high score
     */
    public static int getScore(int levelNum) {
        switch (levelNum) {
            case 0: return level0score;
            case 1: return level1score;
            case 2: return level2score;
            case 3: return level3score;
            default: return 0;
        }
    }

    /**
     * Sets high score for a specified level.
     *
     * @param level level number
     * @param newScore new score
     */
    public static void setScore(int level, int newScore) {
        switch (level) {
            case 0: level0score = newScore;break;
            case 1: level1score = newScore;break;
            case 2: level2score = newScore;break;
            case 3: level3score = newScore;break;
        }
    }
}
