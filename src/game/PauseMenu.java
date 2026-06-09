package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pause menu for when player wants to take a break or return to main menu
 */
public class PauseMenu extends JFrame {
    private JPanel pauseMenu;

    /**
     * Constructs a new pause menu object.
     * Initializes the pause menu with a new window and UI
     */
    public PauseMenu() {
        pauseMenu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Always call the parent method to ensure proper rendering
                ImageIcon background = new ImageIcon("data/Background/fullBackground.png"); // Provide the correct path to your image
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null); // Draw the image scaled to the panel size
            }
        };
        pauseMenu.setLayout(null);

        //Text for pause menu
        ImageIcon pausedTitle = new ImageIcon(new ImageIcon("data/Icons/paused.png").getImage().getScaledInstance(300, 50, Image.SCALE_SMOOTH));
        JLabel PausedTitleLabel = new JLabel(pausedTitle);
        PausedTitleLabel.setBounds(25, 75, 300, 50);
        pauseMenu.add(PausedTitleLabel);

        //Resume button
        pauseButtonCreate(new ImageIcon("data/Icons/resume.png"),70,90,60,200,100,100).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.currentGame.unPause();
                dispose();
            }
        });

        //Exit to main menu by resetting the world (which means removing it and resetting player stats) then making anew main menu
        pauseButtonCreate(new ImageIcon("data/Icons/exit.png"),50,90,225,200,100,100).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.currentGame.reset();
                new MainMenu();
                dispose();
            }
        });

        //Default settings
        setContentPane(pauseMenu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Makes buttons by first converting icon to resizable images then making the buttons use the images
     * Then the button is added to the main menu JPanel and returned to be used for the actionlistener
     *
     * @param icon icon to display on button.
     * @param width width to scale icon to.
     * @param height height to scale icon to.
     * @param x x-coordinate of button's position.
     * @param y y-coordinate of button's position.
     * @param Bwidth width of button.
     * @param Bheight height of button.
     * @return created JButton.
     */
    public JButton pauseButtonCreate(ImageIcon icon, int width, int height, int x, int y, int Bwidth, int Bheight) {
        JButton button = new JButton(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        button.setBounds(x, y, Bwidth, Bheight);
        pauseMenu.add(button);
        return button;
    }
}