/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author freddy
 */
public class ThirdView extends javax.swing.JFrame {

    /**
     * Creates new form ThirdView
     */
    public ThirdView() {
        initComponents();
    }
    public String logType;
    
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Log table for SQUID%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
    public ArrayList<SquidLogs> squidLogsList(String param, String valeur){
        ArrayList<SquidLogs> squidLogsListe=new ArrayList<>();
        try {
        String sql = ("SELECT * FROM squidLog WHERE "+param+" = '"+valeur+"'");
        String url = "jdbc:sqlite:test.db";
        Connection connex = null;
        
        connex = DriverManager.getConnection(url);
        Statement st = connex.createStatement();
        ResultSet rs = st.executeQuery(sql);
        SquidLogs squid;
        while(rs.next()){
            squid = new SquidLogs(rs.getString("remoteHost"),rs.getString("dateExacte"),rs.getString("url"),rs.getString("peerHost"),rs.getString("bytes"),rs.getString("contentType"),rs.getString("duration"),rs.getString("requestMethod"),rs.getString("status"));
            squidLogsListe.add(squid);
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return squidLogsListe;
    }
    public void showSquid(String param, String valeur){
        ArrayList<SquidLogs> squidlist = squidLogsList(param, valeur);
        DefaultTableModel squidModel = (DefaultTableModel)view3JTable.getModel();
        Object[] row = new Object[9];
        for(int i=0; i<squidlist.size();i++){
            row[0]=squidlist.get(i).getRemoteHost();
            row[1]=squidlist.get(i).getDateExacte();
            row[2]=squidlist.get(i).getUrl();
            row[3]=squidlist.get(i).getPeerHost();
            row[4]=squidlist.get(i).getBytes();
            row[5]=squidlist.get(i).getContentType();
            row[6]=squidlist.get(i).getDuration();
            row[7]=squidlist.get(i).getRequestMethod();
            row[8]=squidlist.get(i).getStatus();
            squidModel.addRow(row);
               
        } 
    }
    
    //$$$$$$$$$$$$$$$$$$$$$$$$Log table for apache$$$$$$$$$$$$$$$$$$$$$$$$$
    public ArrayList<LogApache2> apacheLogsList(String param, String valeur){
        ArrayList<LogApache2> apacheLogsListe=new ArrayList<>();
        try {
        String sql = ("SELECT * FROM apacheLog WHERE "+param+" = '"+valeur+"'");
        String url = "jdbc:sqlite:test.db";
        Connection connex = null;
        
        connex = DriverManager.getConnection(url);
        Statement st = connex.createStatement();
        ResultSet rs = st.executeQuery(sql);
        LogApache2 apache;
        while(rs.next()){
            apache = new LogApache2(rs.getString("date"),rs.getString("temps"),rs.getString("username"),rs.getString("identity"),rs.getString("requestType"),rs.getString("codeStatus"),rs.getString("sizeResponse"),rs.getString("refererUrl"),rs.getString("userAgent"));
            apacheLogsListe.add(apache);
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return apacheLogsListe;
    }
    public void showApache(String param, String valeur){
        ArrayList<LogApache2> apachelist = apacheLogsList(param, valeur);
        String[] columnNames = {"date","temps","username","identity","requestType","codeStatus","sizeResponse","refererUrl","userAgent"};
        DefaultTableModel apacheModel = new DefaultTableModel(0, columnNames.length);
        apacheModel.setColumnIdentifiers(columnNames);
        
        Object[] row = new Object[9];
        for(int i=0; i<apachelist.size();i++){
            row[0]=apachelist.get(i).getLaDate();
            row[1]=apachelist.get(i).getLeTemps();
            row[2]=apachelist.get(i).getUsername();
            row[3]=apachelist.get(i).getIdentity();
            row[4]=apachelist.get(i).getRequestType();
            row[5]=apachelist.get(i).getCodeStatus();
            row[6]=apachelist.get(i).getSizeResponse();
            row[7]=apachelist.get(i).getRefererUrl();
            row[8]=apachelist.get(i).getUserAgent();
            apacheModel.addRow(row);
               
        }
        view3JTable.setModel(apacheModel);
    }
    
    //¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨Log table for samba¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨
    public ArrayList<LogSamba> sambaLogsList(String param, String valeur){
        ArrayList<LogSamba> sambaLogsListe=new ArrayList<>();
        try {
        String sql = ("SELECT * FROM sambaLog WHERE "+param+" = '"+valeur+"'");
        String url = "jdbc:sqlite:test.db";
        Connection connex = null;
        
        connex = DriverManager.getConnection(url);
        Statement st = connex.createStatement();
        ResultSet rs = st.executeQuery(sql);
        LogSamba samba;
        while(rs.next()){
            samba = new LogSamba(rs.getString("leDate"),rs.getString("lheure"),rs.getString("IPConnectee"),rs.getString("service"),rs.getString("user"),rs.getString("salle"),rs.getString("state"));
            sambaLogsListe.add(samba);
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sambaLogsListe;
    }
    public void showSamba(String param, String valeur){
        ArrayList<LogSamba> sambalist = sambaLogsList(param, valeur);
        String[] columnNamesSamba = {"leDate","lheure","IPConnectee","service","user","salle","state"};
        DefaultTableModel sambaModel = new DefaultTableModel(0, columnNamesSamba.length);
        sambaModel.setColumnIdentifiers(columnNamesSamba);
        
        Object[] row = new Object[7];
        for(int i=0; i<sambalist.size();i++){
            row[0]=sambalist.get(i).getDate();
            row[1]=sambalist.get(i).getHeure();
            row[2]=sambalist.get(i).getIP();
            row[3]=sambalist.get(i).getService();
            row[4]=sambalist.get(i).getUser();
            row[5]=sambalist.get(i).getSalle();
            row[6]=sambalist.get(i).getState();
            
            sambaModel.addRow(row);
               
        }
        view3JTable.setModel(sambaModel);
    }
    
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Log table for SSH$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    
    public ArrayList<SshLog> sshLogsList(String param, String valeur){
        ArrayList<SshLog> sshLogsListe=new ArrayList<>();
        try {
        String sql = ("SELECT * FROM sshLog WHERE "+param+" = '"+valeur+"'");
        String url = "jdbc:sqlite:test.db";
        Connection connex = null;
        
        connex = DriverManager.getConnection(url);
        Statement st = connex.createStatement();
        ResultSet rs = st.executeQuery(sql);
        SshLog ssh;
        while(rs.next()){
            ssh = new SshLog(rs.getString("date"),rs.getString("temps"),rs.getString("server"),rs.getString("typeConnection"),rs.getString("session"));
            sshLogsListe.add(ssh);
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sshLogsListe;
    }
    public void showSsh(String param, String valeur){
        ArrayList<SshLog> sshlist = sshLogsList(param, valeur);
        String[] columnNamesSsh = {"date","temps","server","typeConnection","session"};
        DefaultTableModel sshModel = new DefaultTableModel(0, columnNamesSsh.length);
        sshModel.setColumnIdentifiers(columnNamesSsh);
        
        Object[] row = new Object[5];
        for(int i=0; i<sshlist.size();i++){
            row[0]=sshlist.get(i).getDate();
            row[1]=sshlist.get(i).getTemps();
            row[2]=sshlist.get(i).getNomServer();
            row[3]=sshlist.get(i).getTypeConnection();
            row[4]=sshlist.get(i).getSession();
            
            sshModel.addRow(row);
               
        }
        view3JTable.setModel(sshModel);
    }
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        view1exitbutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        view3JTable = new javax.swing.JTable();
        view3parameterscroller = new javax.swing.JComboBox<>();
        view3castparameter = new javax.swing.JTextField();
        view3nextbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        view1exitbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server/exit.png"))); // NOI18N
        view1exitbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view1exitbuttonActionPerformed(evt);
            }
        });

        view3JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "remoteHost", "dateExacte", "url", "peerHost", "Bytes", "contentType", "duration", "requestMethod", "status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(view3JTable);

        view3parameterscroller.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        view3parameterscroller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view3parameterscrollerActionPerformed(evt);
            }
        });

        view3castparameter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view3castparameterActionPerformed(evt);
            }
        });

        view3nextbutton.setBackground(new java.awt.Color(237, 172, 84));
        view3nextbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server/next.png"))); // NOI18N
        view3nextbutton.setText("            Next");
        view3nextbutton.setToolTipText("");
        view3nextbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view3nextbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(view3parameterscroller, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(view3castparameter, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(view1exitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(view3nextbutton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(view1exitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(view3parameterscroller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(view3castparameter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view3nextbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void view1exitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view1exitbuttonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_view1exitbuttonActionPerformed

    private void view3castparameterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view3castparameterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_view3castparameterActionPerformed

    private void view3nextbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view3nextbuttonActionPerformed
        // TODO adview3nextbutton code here:
        String selectedParam = view3parameterscroller.getSelectedItem().toString();//this method returns an object so we had to cast into an string
        String valeur = view3castparameter.getText();
        
        switch(logType){
            case "exemple.apache2.access.log":
                showApache(selectedParam,valeur);
                break;
            case "exemple.linux.auth.sshd.log":
                showSsh(selectedParam,valeur);
                break;
            case "exemple.samba.host.log":
                showSamba(selectedParam,valeur);
                break;
            case "exemple.squid.access.log":
                showSquid(selectedParam,valeur);
                break;
        }

        
      

    }//GEN-LAST:event_view3nextbuttonActionPerformed

    private void view3parameterscrollerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view3parameterscrollerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_view3parameterscrollerActionPerformed

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
            java.util.logging.Logger.getLogger(ThirdView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThirdView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThirdView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThirdView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThirdView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton view1exitbutton;
    public javax.swing.JTable view3JTable;
    private javax.swing.JTextField view3castparameter;
    private javax.swing.JButton view3nextbutton;
    public javax.swing.JComboBox<String> view3parameterscroller;
    // End of variables declaration//GEN-END:variables
}
