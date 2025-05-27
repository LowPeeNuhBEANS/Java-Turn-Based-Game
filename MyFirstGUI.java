import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class MyFirstGUI {

    public static void main(String[] args) {
        // Create the frame (window)
        JFrame frame = new JFrame("SUMBAGAY SA KADALANAN SA DAVAO: The Game");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Using absolute layout for simplicity

        //Title Card test envoironment
        JLabel titleCard = new JLabel();

        // Load the image and handle potential file not found exception
        ImageIcon title = new ImageIcon("C:/Users/kvlop/OneDrive/Desktop/TurnBasedJavaGame/TitleCard.png");

       // Scale image if needed
        Image image = title.getImage().getScaledInstance(800, 400,  java.awt.Image.SCALE_SMOOTH);
        titleCard.setIcon(new ImageIcon(image));


        //Text FIelds
        JTextField textField = new JTextField();
        textField.setBounds(50, 80, 200, 30);

        JButton button = new JButton("Greet Me");
        button.setBounds(50, 130, 120, 30);

        JLabel outputLabel = new JLabel("");
        outputLabel.setBounds(50, 180, 300, 30);

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                if (!name.isEmpty()) {
                    outputLabel.setText("Hello, " + name + "!");
                } else {
                    outputLabel.setText("Please enter your name.");
                }
            }
        });

        // Add components to the frame
        frame.add(titleCard);
        frame.add(textField);
        frame.add(button);
        frame.add(outputLabel);

        // Show the frame
        frame.setVisible(true);
    }
}