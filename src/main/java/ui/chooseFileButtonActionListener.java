package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chooseFileButtonActionListener implements ActionListener {
    UiController controller;

    public chooseFileButtonActionListener(UiController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controller.chooseFileButtonClicked();
    }
    
    
}
