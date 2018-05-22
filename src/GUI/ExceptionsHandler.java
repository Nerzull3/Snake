package GUI;

import javax.swing.*;
import java.awt.*;

class ExceptionsHandler {

    static void Message(String msg, String title){
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
    }
    static void CloseWindowMsg(String msg, String title)
    {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
