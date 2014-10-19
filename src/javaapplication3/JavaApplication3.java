/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import javax.swing.JFrame;

/**
 *
 * @author leonardo
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame j = new JFrame("First");
        
        NewJPanel np = new NewJPanel();
        j.add(np);
        j.setSize(500,500);
        j.setVisible(true);
    }
}
