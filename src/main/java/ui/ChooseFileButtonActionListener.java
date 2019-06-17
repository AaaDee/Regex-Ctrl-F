package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener for the Choose File -button.
 * 
 * @author AD
 */
public class ChooseFileButtonActionListener implements ActionListener {
    UiController controller;

    /**
     * Creates a new listener with an attached controller.
     * 
     * @param controller The controller attached to the button
     */
    public ChooseFileButtonActionListener(UiController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controller.chooseFileButtonClicked();
    }
    
    
}
