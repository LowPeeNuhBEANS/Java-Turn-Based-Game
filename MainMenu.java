import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Example");
        JLabel imageLabel = new JLabel();

        // Load the image and handle potential file not found exception
        ImageIcon imageIcon = new ImageIcon("C:/Users/kvlop/OneDrive/Desktop/TurnBasedJavaGame/TitleCard.png");

       // Scale image if needed
        Image image = imageIcon.getImage().getScaledInstance(800, 400,  java.awt.Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    
        frame.add(imageLabel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}