import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ImageTestEnvironment {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Example");
        frame.setLayout(null); // Set layout to null for absolute positioning
        JLabel imageLabel = new JLabel();

        // Load the image and handle potential file not found exception
        ImageIcon imageIcon = new ImageIcon("C:/Users/kvlop/OneDrive/Desktop/TurnBasedJavaGame/TitleCard.png");

       // Scale image if needed
        Image image = imageIcon.getImage().getScaledInstance(800, 400,  java.awt.Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    


        //Buttons
        JButton playButton = new JButton("PLAY");
        

        JButton optionsButton = new JButton("OPTIONS");
        

        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        

        frame.add(imageLabel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(playButton);
        frame.add(optionsButton);
        frame.add(exitButton);

        // Calculate horizontal center
        int frameWidth = frame.getWidth();
        int imageWidth = 800;
        int imageX = (frameWidth - imageWidth) / 2;

        // Set the bounds of the imageLabel
        imageLabel.setBounds(imageX, 0, 800, 400);
        // Calculate horizontal center for buttons
        int buttonWidth = 100;
        int buttonGap = 50;
        int totalButtonWidth = 3 * buttonWidth + 2 * buttonGap;
        int startX = (frameWidth - totalButtonWidth) / 2;

        playButton.setBounds(startX, 450, buttonWidth, 30);
        optionsButton.setBounds(startX + buttonWidth + buttonGap, 450, buttonWidth, 30);
        exitButton.setBounds(startX + 2 * buttonWidth + 2 * buttonGap, 450, buttonWidth, 30);
    }
}