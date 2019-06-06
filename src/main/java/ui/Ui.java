package ui;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class Ui extends JFrame{
    private JLabel fileText;
    private JButton chooseFileButton;
    private JButton runButton;
    private JTextField regexInput;
    private JTextArea display;
            
public Ui(UiController controller){
        setSize(600, 300);
        setTitle("Rexeg-Ctrl-F");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.chooseFileButton = createChooseFileButton(controller);
        this.runButton = createRunButton(controller);
        
        this.regexInput = new JTextField("write your regex here");
        regexInput.setBounds(0,40,200,40);
        
        String instructions = "Input your regex, choose file and click run";
        this.display = new JTextArea(instructions);
        display.setBounds(0,200,600,400);
        display.setEditable(false);
        
        this.fileText = new JLabel("File Chosen: None");
        fileText.setBounds(250, 20, 200, 40);
        

        
        this.add(chooseFileButton);
        this.add(regexInput);
        this.add(display);
        this.add(fileText);
        this.add(runButton);
        
        this.setLayout(null);
    }

        private JButton createChooseFileButton(UiController controller){
            JButton button = new JButton("Choose File");
            button.setBounds(0,0,200,40);
            button.addActionListener(new chooseFileButtonActionListener(controller));
            
            return button;
        }
        
        private JButton createRunButton(UiController controller){
            JButton button = new JButton("Run");
            button.setBounds(0,80,200,40);
            button.addActionListener(new runButtonActionListener(controller));
            
            return button;
        }

    public void setFileTextContent(String text) {
        this.fileText.setText(text);
    }
    
    public String getRegexFieldContent(){
        return this.regexInput.getText();
    }
    
    public void setDisplayContent(String text){
        this.display.setText(text);
    }
    
}