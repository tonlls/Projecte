/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import incidencies.app.PersistenceException;
import incidencies.app.PersistenceFactory;
import incidencies.app.Persistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author tonll
 */
public class UI extends javax.swing.JFrame {
    private int CREATE=1;
    private int UPDATE=2;
    private final Persistencia p;
    int action;
    Atraccio atraccio=null; 
    Incidencia incidencia=null;
    private HashMap<Integer,Atraccio> atraccions=new HashMap<Integer,Atraccio>();
    private List<Incidencia> incidencies;
    /**
     * Creates new form UI
     */
    public UI() throws PersistenceException {
        p=PersistenceFactory.getInstance("incidencies.app.PersistenciaMySql",null);
        initComponents();
        jPanel1.setVisible(false);
        List<EstatOperatiu> ests=p.getEstats();
        //List<String> estats=new ArrayList<String>();
        //for(EstatOperatiu e:ests)
        jComboBox1.setModel(new DefaultComboBoxModel(ests.toArray()));
    }
    public TableModel getIncidencies(){
        
        //final List<Incidencia> incidencies=null;
        //if(atraccio!=null)incidencies= p.getIncidencies(atraccio.getId());
        incidencies=new ArrayList<Incidencia>();
        if(atraccio!=null){
            p.updateAtraccio(atraccio);
            incidencies= atraccio.getIncidenciaList();
        }
        String []  cols =  new String [] {"Id","Inici","Fi","oberta","Estat" };
        TableModel tm = new DefaultTableModel() {
                    public int getRowCount() {
                        return incidencies.size();
                    }
                    public int getColumnCount() {
                        return cols.length;                    
                    }
                    public String getColumnName(int arg0) {
                       return cols[arg0];
                    }
                    public Class<?> getColumnClass(int arg0) {
                        return String.class;
                    }
                    public boolean isCellEditable(int arg0, int arg1) {
                        return false;                    
                    }
                    public Object getValueAt(int row, int col) {
                        if(incidencies==null)return "";
                        Incidencia i = incidencies.get(row);
                        switch(col){
                            case 0:
                                return i.getId();
                            case 1:
                                return i.getDataInici();
                            case 2:
                                return i.getDataFi();
                            case 3:
                                return i.getOberta();
                            case 4:
                                return i.getEstatOperatiuId().toString();
                            default:
                                return "";
                        }
                    }
                }; 
        return tm;
    }
    public TableModel getAtraccions(){
        List<Atraccio> attrs=p.getAtraccions();
        for(Atraccio a: p.getAtraccions()){
            atraccions.put(a.getId(),a);
        }
        String []  cols =  new String [] {"Id","Atraccio", "Estat", "Cua", "Avis", "Data_Inici", "Data_Fi" };
        TableModel tm = new DefaultTableModel() {
                    public int getRowCount() {
                        return atraccions.values().size();
                    }
                    public int getColumnCount() {
                        return cols.length;                    
                    }
                    public String getColumnName(int arg0) {
                       return cols[arg0];
                    }
                    public Class<?> getColumnClass(int arg0) {
                        return String.class;
                    }
                    public boolean isCellEditable(int arg0, int arg1) {
                        return false;                    
                    }
                    public Object getValueAt(int row, int col) {
                        //{"Atraccio", "Estat","clients", "Avis", "Data_Ini", "Data_Fi_Prev" 
                        Atraccio a = attrs.get(row);
                        if (atraccions==null)return "";
                        switch(col){
                            case 0:
                                return a.getId();
                            case 1:
                                return a.getNom();
                            case 2:
                                return a.getEstatActualId().getNom();
                            case 3:
                                return a.getClientsCua();
                            case 4:
                                if(a.getIncidenciaId()== null){
                                    return "";
                                }
                                return a.getIncidenciaId().getMisatgeEstat();
                            case 5:
                                 if(a.getIncidenciaId()== null){
                                    return "";
                                }
                                return a.getIncidenciaId().getDataInici();
                            case 6:
                                 if(a.getIncidenciaId()== null){
                                    return "";
                                }
                                return a.getIncidenciaId().getDataFiPrevista();      
                            default:
                                return "";
                        }
                    }
                }; 
        return tm;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        cua = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        Save = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(getAtraccions());
        jTable1.setName("atraccionsList"); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName("");

        jTable2.setModel(getIncidencies());
        jTable2.setName("incidenciesList"); // NOI18N
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Cua: ");

        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cua.setEditable(false);
        cua.setText("0");
        cua.setToolTipText("");
        cua.setName(""); // NOI18N

        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("Missatge");

        jLabel5.setText("Estat");

        jLabel6.setText("Data Fi");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        Save.setText("Save");
        Save.setToolTipText("");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Save)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3)
                            .addComponent(jComboBox1, 0, 130, Short.MAX_VALUE)
                            .addComponent(jTextField2))))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Save)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jButton4)
                        .addComponent(cua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        //int column = source.columnAtPoint( evt.getPoint() );
        int i=Integer.parseInt(source.getModel().getValueAt(row, 0)+"");
        atraccio=atraccions.get(i);
        jTable2.setModel(getIncidencies());
        cua.setText(atraccio.getClientsCua()+"");
        incidencia=null;
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(atraccio!=null){
            p.moreCua(atraccio);
            cua.setText(atraccio.getClientsCua()+"");
            jTable1.setModel(getAtraccions());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(atraccio!=null){
            p.minusCua(atraccio);
            cua.setText(atraccio.getClientsCua()+"");
            jTable1.setModel(getAtraccions());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(incidencia!=null){
            p.closeIncidencia(incidencia);
            jTable2.setModel(getIncidencies());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        //int column = source.columnAtPoint( evt.getPoint() );
        //int i=Integer.parseInt(source.getModel().getValueAt(row, 0)+"");
        incidencia=atraccio.getIncidenciaList().get(row);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        
    }//GEN-LAST:event_jTextField3ActionPerformed
    private boolean checkDate(String d){
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        try {
            cal.setTime(new Date(d));
            cal.getTime();
            return true;
        }
        catch (Exception e) {
           return false;
        }
    }
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        String msg=jTextField2.getText();
        EstatOperatiu est=(EstatOperatiu)jComboBox1.getSelectedItem();
        String dateO=jTextField3.getText();
        Date d=null;
        if(!dateO.equals("")){
            if(!checkDate(dateO))return;
            try {
                d = new SimpleDateFormat("dd-MM-yyyy").parse(dateO);
            } catch (ParseException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        Incidencia in=new Incidencia();
        in.setDataFi(d);
        in.setEstatOperatiuId(est);
        in.setMisatgeEstat(msg);
        if(action==CREATE){
            in.setAtraccioId(atraccio);
            in.setDataInici(new Date());
            in.setOberta(true);
            p.addIncidencia(in);
        }
        else if(action==UPDATE){
            in.setId(incidencia.getId());
            p.updateIncidencia(in);
        }
        jTable2.setModel(new DefaultTableModel());
        jTable2.setModel(getIncidencies());
        jPanel1.setVisible(false);
    }//GEN-LAST:event_SaveActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jPanel1.setVisible(false);
        if(incidencia!=null&&atraccio!=null){
            action=UPDATE;
            jPanel1.setVisible(true);
            if(incidencia.getDataFi()!=null)jTextField3.setText(incidencia.getDataFi().toString());
            jTextField2.setText(incidencia.getMisatgeEstat());
            jComboBox1.setSelectedItem(incidencia.getEstatOperatiuId());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jPanel1.setVisible(false);
        if(atraccio!=null){
            action=CREATE;
            jPanel1.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI x=new UI();
                    x.setVisible(true);
                } catch (PersistenceException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Save;
    private javax.swing.JTextField cua;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
