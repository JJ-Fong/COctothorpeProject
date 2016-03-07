/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDE;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Javier Fong
 */
public class codePane extends JScrollPane {
    JTextArea text; 
    
    public codePane() {
        text = new JTextArea(); 
        this.setViewportView(text);
    }
    
    public String getText() { 
        return text.getText(); 
    }
    
    public void setText(String txt) {
        text.setText(txt);
    }
}
