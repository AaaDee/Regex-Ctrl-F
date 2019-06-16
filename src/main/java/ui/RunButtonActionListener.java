package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunButtonActionListener implements ActionListener {
    UiController controller;

    public RunButtonActionListener(UiController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controller.runFileButtonClicked();
    }
    
    
}
