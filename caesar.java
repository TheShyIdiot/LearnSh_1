import java.awt.*;
import java.awt.event.*;

public class caesar extends Frame implements ActionListener {

    static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String alpha = "abcdefghijklmnopqrstuvwxyz";

    // GUI 
    TextField inputField, keyField, outputField;
    Button encryptButton, decryptButton;

    public caesar() {
        setTitle("Caesar Cipher");
        setSize(400, 300);
        setLayout(new FlowLayout());

        Label inputLabel = new Label("Enter text:");
        inputField = new TextField(20);
        
        Label keyLabel = new Label("Enter key:");
        keyField = new TextField(5);
        
        encryptButton = new Button("Encrypt");
        decryptButton = new Button("Decrypt");
        
        Label outputLabel = new Label("Output:");
        outputField = new TextField(20);
        outputField.setEditable(false); 

        
        add(inputLabel);
        add(inputField);
        add(keyLabel);
        add(keyField);
        add(encryptButton);
        add(decryptButton);
        add(outputLabel);
        add(outputField);

        
        encryptButton.addActionListener(this);
        decryptButton.addActionListener(this);

        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        
        setVisible(true);
    }

    // Function to encrypt the plaintext
    public static String encryption(String plain, int key) {
        int n = plain.length();
        String c = ""; // String to store the encrypted data

        for (int i = 0; i < n; i++) {
            char b;
            char a = plain.charAt(i);
            int x;

            if (Character.isUpperCase(a)) {
                x = ALPHA.indexOf(a);
                x = (x + key) % 26; // Encrypt character
                b = ALPHA.charAt(x);
                c += b;
            } else if (Character.isLowerCase(a)) {
                x = alpha.indexOf(a);
                x = (x + key) % 26; // Encrypt character
                b = alpha.charAt(x);
                c += b;
            } else {
                c += a; // Append special characters as they are
            }
        }

        return c;
    }

    // Function to decrypt the ciphertext
    public static String decryption(String e, int key) {
        int n = e.length();
        String p = ""; // String to store the decrypted data

        for (int i = 0; i < n; i++) {
            char b;
            char a = e.charAt(i);
            int x;

            if (Character.isUpperCase(a)) {
                x = ALPHA.indexOf(a);
                x = (x - key + 26) % 26; // Decrypt character
                b = ALPHA.charAt(x);
                p += b;
            } else if (Character.isLowerCase(a)) {
                x = alpha.indexOf(a);
                x = (x - key + 26) % 26; // Decrypt character
                b = alpha.charAt(x);
                p += b;
            } else {
                p += a; // Append special characters as they are
            }
        }

        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String inputText = inputField.getText();
        int key = Integer.parseInt(keyField.getText());
        String outputText = "";

        if (command.equals("Encrypt")) {
            outputText = encryption(inputText, key);
        } else if (command.equals("Decrypt")) {
            outputText = decryption(inputText, key);
        }

        outputField.setText(outputText); // Display output
    }

    public static void main(String[] args) {
        new caesar(); // Launch the GUI
    }
}
