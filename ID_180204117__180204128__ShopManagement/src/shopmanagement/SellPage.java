
package shopmanagement;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shizan
 */
public class SellPage extends javax.swing.JFrame {
    
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
   
    Date date = new Date();
    
    Calendar Cal = Calendar.getInstance();
    DefaultTableModel mode1;
    
    private java.sql.Connection con;
    private Statement stmnt;
    private ResultSet rs;

    public Connection DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
           
            con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mydata", "root", "");
            System.out.println("Connection Established");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return (Connection) con;
    }

    public void getData() throws SQLException {
        con = DBConnection();
        if (con != null) {
            try {
                stmnt = con.createStatement();
                rs = stmnt.executeQuery("SELECT * FROM `addproduct`");
                DefaultTableModel tblmode2 = (DefaultTableModel) jTable2.getModel();
                
                int rtr = tblmode2.getRowCount();
                for(int i = rtr-1; i>=0; i--){
                    tblmode2.removeRow(i);
                }
                
                while (rs.next()) {
                    String d[] = {rs.getString("productname"),rs.getString("price") ,rs.getString("quantity"),rs.getString("expiry")};
                    tblmode2.addRow(d);   
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                stmnt.close();
                con.close();
            }
        } else {
            System.out.println("Connection Failed!!!");
        }

    }
    
    
    
    public void search() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mydata", "root", "");
            
            String sql = "select * from addproduct where productname = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, SProductName.getText());
            
            rs = pstmt.executeQuery();
            DefaultTableModel tblmode2 = (DefaultTableModel) jTable2.getModel();
            int rtr = tblmode2.getRowCount();
            for(int i = rtr-1; i>=0; i--){
                    tblmode2.removeRow(i);
            }
                
            if(rs.next()){
                
                String d[] = {rs.getString("productname"),rs.getString("price") ,rs.getString("quantity"),rs.getString("expiry")};
             
                tblmode2.addRow(d);
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally{
            stmnt.close();
            con.close();
        }

    }
    
    public void updateData() throws SQLException {
        
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mydata", "root", "");
                
                int q = Integer.parseInt(SQuantity.getText());
                
                String sql = "select * from addproduct where productname = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                pstmt.setString(1, SProductName.getText());
                
                rs = pstmt.executeQuery();
                rs.next();
                String present_quantity = rs.getString("quantity");
                int Q = Integer.parseInt(present_quantity);
                int QQ = Q - q;
                String Qy = String.valueOf(QQ);
                
                if(QQ > 0){
                    
                    String qry = "UPDATE `addproduct` SET `quantity`=? WHERE `productname`=?";
                    PreparedStatement pst = con.prepareStatement(qry);
                    pst.setString(1, Qy);
                    pst.setString(2, SProductName.getText());
                    pst.executeUpdate();
                }
                
                if(QQ==0){
                    String sq = "DELETE FROM `addproduct` WHERE `productname`=?";
                    PreparedStatement psmt = con.prepareStatement(sq);
                    psmt.setString(1, SProductName.getText());
                    psmt.executeUpdate();
                }
                

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                stmnt.close();
                con.close();
            }
        

    }
    
   
   
    public SellPage() {
        initComponents();
        
       ShowDate.setText("  "+dateFormat.format(date));
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ShowDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SDelete = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        SAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        SPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        SQuantity = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        SProductName = new javax.swing.JTextField();
        sum = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("DATE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 60, 30));

        ShowDate.setEditable(false);
        ShowDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ShowDate.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        ShowDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowDateActionPerformed(evt);
            }
        });
        jPanel1.add(ShowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 130, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("My Company ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 250, 40));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 410, 320));

        SDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SDelete.setText("Delete");
        SDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(SDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 120, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 650, 120, 40));

        refresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 663, -1, 30));

        SAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SAdd.setText("ADD");
        SAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAddActionPerformed(evt);
            }
        });
        jPanel1.add(SAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 80, 30));

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Price", "Quantity", "Expiry"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 400, 490));

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 70, 30));

        jButton8.setBackground(new java.awt.Color(196, 186, 228));
        jButton8.setFont(new java.awt.Font("MS PMincho", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("<-");
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        jPanel2.setBackground(new java.awt.Color(112, 146, 190));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setBackground(new java.awt.Color(70, 84, 159));
        jButton6.setFont(new java.awt.Font("Microsoft Sans Serif", 3, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("X");
        jButton6.setToolTipText("");
        jButton6.setAutoscrolls(true);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, -1, 30));

        jButton7.setBackground(new java.awt.Color(70, 84, 159));
        jButton7.setFont(new java.awt.Font("Microsoft Uighur", 3, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("_");
        jButton7.setAutoscrolls(true);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, -1, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Available Product");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 170, 30));

        SPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPriceActionPerformed(evt);
            }
        });
        jPanel1.add(SPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 170, 30));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Price");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 60, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Name");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 60, 30));

        SQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SQuantityActionPerformed(evt);
            }
        });
        jPanel1.add(SQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 170, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quantiy");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 70, 30));

        SProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SProductNameActionPerformed(evt);
            }
        });
        jPanel1.add(SProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 220, 30));
        jPanel1.add(sum, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 590, 110, 30));

        jButton3.setText("Total");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 593, 60, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 720));

        setSize(new java.awt.Dimension(947, 711));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SProductNameActionPerformed
         
    }//GEN-LAST:event_SProductNameActionPerformed

    private void SQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SQuantityActionPerformed
         
    }//GEN-LAST:event_SQuantityActionPerformed

    private void SPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPriceActionPerformed
         
    }//GEN-LAST:event_SPriceActionPerformed

    private void SAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAddActionPerformed
         
       String data[]={SProductName.getText(),SQuantity.getText(),SPrice.getText()};
       DefaultTableModel tblmode1= (DefaultTableModel)jTable1.getModel();

       tblmode1.addRow(data);
       
        try {
            updateData();
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(SellPage.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        SProductName.setText("");
        SQuantity.setText("");
        SPrice.setText("");
      
        
    }//GEN-LAST:event_SAddActionPerformed

    private void ShowDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowDateActionPerformed
         
        
    }//GEN-LAST:event_ShowDateActionPerformed

    private void SDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SDeleteActionPerformed
         
           DefaultTableModel tblmode1= (DefaultTableModel)jTable1.getModel();
        if(jTable1.getSelectedRowCount()==1)
            tblmode1.removeRow(jTable1.getSelectedRow());
        else
        {
            if(jTable1.getRowCount()==0){
                JOptionPane.showMessageDialog(this,"Table is Empty");
            }
        }
    }//GEN-LAST:event_SDeleteActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        
        try {
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(SellPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_refreshActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
             
            search();
        } catch (SQLException ex) {
            Logger.getLogger(SellPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         
        System.exit(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         
        this.setExtendedState(loginAddpage.ICONIFIED);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         
        new HomePage().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       MessageFormat header = new MessageFormat(dateFormat.format(date)+"--Item Report");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        String sp[]={""};
       String total[]={" ","Tolal: ",sum.getText()};
       DefaultTableModel tblmode1= (DefaultTableModel)jTable1.getModel();
       tblmode1.addRow(sp);
       tblmode1.addRow(total);
        try {
            
         
            jTable1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      
        int s=0;
        for(int i=0;i<jTable1.getRowCount();i++){
           int t1= Integer.parseInt(jTable1.getValueAt(i, 1).toString());
           int t2= Integer.parseInt(jTable1.getValueAt(i, 2).toString());
           int t=t1*t2;
           s=s+t;     
        }
       sum.setText(Integer.toString(s));
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*String PN,P;
        AddPage ap=new AddPage();
        PN = ap.PN;
        P= ap.PN;
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SellPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SAdd;
    private javax.swing.JButton SDelete;
    private javax.swing.JTextField SPrice;
    private javax.swing.JTextField SProductName;
    private javax.swing.JTextField SQuantity;
    private javax.swing.JTextField ShowDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public javax.swing.JButton refresh;
    private javax.swing.JTextField sum;
    // End of variables declaration//GEN-END:variables
}
