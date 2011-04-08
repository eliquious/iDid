/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idid;

import idid.view.iDidView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Max.Franks
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel());
                }  catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                iDidView idid = new iDidView();
                idid.setAlwaysOnTop(true);
                idid.toFront();
                idid.setVisible(true);
            }
        });
    }
}

