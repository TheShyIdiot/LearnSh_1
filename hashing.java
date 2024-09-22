import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hashing extends Frame implements ActionListener {

    // GUI components
    TextField inputField, outputField;
    Button hashButton;

    public hashing() {
        // Create GUI components
        setTitle("SHA-256 Hashing");
        setSize(400, 200);
        setLayout(new FlowLayout());

        Label inputLabel = new Label("Enter data to hash:");
        inputField = new TextField(20);
        
        hashButton = new Button("Generate Hash");
        
        Label outputLabel = new Label("Hash Value:");
        outputField = new TextField(40);
        outputField.setEditable(false); // Output field is read-only

        // Add components to frame
        add(inputLabel);
        add(inputField);
        add(hashButton);
        add(outputLabel);
        add(outputField);

        // Add action listener to the button
        hashButton.addActionListener(this);

        // Setup frame closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        
        setVisible(true);
    }

    // Function to hash the input data
    public static byte[] hashData(String data) {
        try {
            // Creating a SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Updating the digest with the input data and returning the result
            return digest.digest(data.getBytes(StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  // Handling the exception
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = inputField.getText();
        byte[] hashValue = hashData(inputText);

        if (hashValue != null) {
            // Convert hash bytes to hexadecimal string
            StringBuilder hashString = new StringBuilder();
            for (byte b : hashValue) {
                hashString.append(String.format("%02x", b));
            }
            outputField.setText(hashString.toString()); // Display hash value
        } else {
            outputField.setText("Hashing failed.");
        }
    }

    public static void main(String[] args) {
        new hashing(); // Launch the GUI
    }
}
