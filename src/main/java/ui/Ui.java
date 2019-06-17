package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The basic structure for a simple Swing GUI.
 * 
 * @author AD
 */
public class Ui extends JFrame {
    private final JLabel fileText;
    private final JButton chooseFileButton;
    private final JButton runButton;
    private final JTextField regexInput;
    private final JTextArea display;

    /**
     * Creates a new UI with an attached controller.
     * 
     * @param controller The attached controller
     */
    public Ui(UiController controller) {
        this.setSize(600, 300);
        this.setTitle("Rexeg-Ctrl-F");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.chooseFileButton = createChooseFileButton(controller);
        this.runButton = createRunButton(controller);
        
        this.regexInput = new JTextField("write your regex here");
        this.regexInput.setBounds(0,40,200,40);
        
        String instructions = "Input your regex, choose file and click run";
        this.display = new JTextArea(instructions);
        this.display.setBounds(0,200,600,400);
        this.display.setEditable(false);
        
        this.fileText = new JLabel("File Chosen: None");
        this.fileText.setBounds(250, 20, 200, 40);
        

        
        this.add(chooseFileButton);
        this.add(regexInput);
        this.add(display);
        this.add(fileText);
        this.add(runButton);
        
        this.setLayout(null);
    }

    private JButton createChooseFileButton(UiController controller) {
        JButton button = new JButton("Choose File");
        button.setBounds(0,0,200,40);
        button.addActionListener(new ChooseFileButtonActionListener(controller));
            
        return button;
    }
        
    private JButton createRunButton(UiController controller) {
        JButton button = new JButton("Run");
        button.setBounds(0,80,200,40);
        button.addActionListener(new RunButtonActionListener(controller));
            
        return button;
    }

    /**
     * Sets the text displaying the file currently chosen.
     * 
     * @param text Text displaying the file currently chosen
     */
    public void setFileTextContent(String text) {
        this.fileText.setText(text);
    }
    
    /**
     * Gets the user input from the regex text field.
     * 
     * @return The content of the regex text field
     */
    public String getRegexFieldContent() {
        return this.regexInput.getText();
    }
    
    /**
     * Sets the display content.
     * 
     * @param text The new display content
     */
    public void setDisplayContent(String text) {
        this.display.setText(text);
    }
    
}