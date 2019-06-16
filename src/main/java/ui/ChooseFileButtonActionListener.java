package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseFileButtonActionListener implements ActionListener {
    UiController controller;

    public ChooseFileButtonActionListener(UiController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controller.chooseFileButtonClicked();
    }
    
    
}
