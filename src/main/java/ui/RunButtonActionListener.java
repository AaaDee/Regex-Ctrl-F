package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener for the Run -button.
 * 
 * @author AD
 */
public class RunButtonActionListener implements ActionListener {
    UiController controller;

    /**
     * Creates a new listener with an attached controller.
     * 
     * @param controller The controller attached to the button
     */
    public RunButtonActionListener(UiController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controller.runFileButtonClicked();
    }
    
    
}
