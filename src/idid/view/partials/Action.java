/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Action.java
 *
 * Created on Apr 21, 2010, 11:08:13 AM
 */

package idid.view.partials;

/**
 *
 * @author Max.Franks
 */
public class Action extends javax.swing.JPanel {

    /** Creates new form Action */
    public Action(int number, String text, String time) {
        initComponents();
        this.id.setText(String.valueOf(number));
        this.action.setText(text);
        this.timestamp.setText(time);
    }

    public Action() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        id = new javax.swing.JLabel();
        action = new javax.swing.JLabel();
        timestamp = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        id.setFont(new java.awt.Font("Cabal1", 0, 14)); // NOI18N
        id.setForeground(new java.awt.Color(0, 51, 102));
        id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        id.setText("0");

        action.setText("jLabel2");
        action.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        timestamp.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(timestamp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(action, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(action, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timestamp)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel action;
    private javax.swing.JLabel id;
    private javax.swing.JLabel timestamp;
    // End of variables declaration//GEN-END:variables

}
