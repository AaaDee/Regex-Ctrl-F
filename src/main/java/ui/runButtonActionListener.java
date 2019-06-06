package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class runButtonActionListener implements ActionListener {
    UiController controller;

    public runButtonActionListener(UiController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controller.runFileButtonClicked();
    }
    
    
}
