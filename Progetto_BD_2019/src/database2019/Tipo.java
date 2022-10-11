/*
 * DBFrame.java
 */
package database2019;

import java.sql.*;
import javax.swing.*;
import java.util.regex.*;

public class Tipo extends DBFrame {

   public Tipo() {
      super();
      initComponents();
      setModalita(INIZIO);
      setFrameTable(tabTipo);
      setNomeTabella("Tipo");
   }

   /**
    * Imposta lo stato corrente del form. <br> In base allo stato vengono
    * abilitati o disabilitati alcuni oggetti del form.
    * 
    * @param modo intero che rappresenta lo stato
    */
   @Override
   final public void setModalita(int modo) {
      super.setModalita(modo);
      switch (modo) {
          case INIZIO:
            tCodiceT.setEnabled (false);
            tTipoPrimitivo.setEnabled(false);
            tTipoClasse.setEnabled(false);
            tIdC.setEnabled(false);
            tCodiceE.setEnabled(false);
            break;
         case APPEND_QUERY:
            tCodiceT.setEnabled (true);
            tTipoPrimitivo.setEnabled(true);
            tTipoClasse.setEnabled(true);
            tIdC.setEnabled(true);
            tCodiceE.setEnabled(true);
            break;
         case BROWSE:
            tCodiceT.setEnabled (false);
            tTipoPrimitivo.setEnabled(false);
            tTipoClasse.setEnabled(false);
            tIdC.setEnabled(false);
            tCodiceE.setEnabled(false);
            break;
         case UPDATE:
            tCodiceT.setEnabled (false);
            tTipoPrimitivo.setEnabled(false);
            tTipoClasse.setEnabled(true);
            tIdC.setEnabled(true);
            tCodiceE.setEnabled(true);
            break;
      }
   }

   /**
    * Mostra una descrizione di un errore SQL in un linguaggio comprensibile per
    * l'utente finale.
    * 
    * @param e eccezione SQLException catturata
    * @param query l'istruzione SQL che ha causato l'errore
    * @param contesto intero per distinguere se l'eccezione ha avuto origine
    * da una query
    */
   @Override
   protected void mostraErrori(SQLException e, String query, int contesto) {
      String msg;
      if (e.getErrorCode() == 1) {
         msg = "Esiste già un'altro Tipo con lo stesso CodiceT";
         JOptionPane.showMessageDialog(this, msg, "Errore",
                 JOptionPane.ERROR_MESSAGE);
      } else {
         super.mostraErrori(e, query, contesto);
      }
   }

   /**
    * Metodo da usare nei form di lookup per passare i dati al form
    * chiamante.
    */
   @Override
   protected void premutoOK() {
      if (getPadre() != null) {
         try {
            rs.close();
         } catch (SQLException e) {
            mostraErrori(e);
         }
         dispose();
      }
   }



   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jCodiceT = new javax.swing.JLabel();
        jTipoPrimitivo = new javax.swing.JLabel();
        tCodiceT = new javax.swing.JTextField();
        spTipo = new javax.swing.JScrollPane();
        tabTipo = new javax.swing.JTable();
        jTipoClasse = new javax.swing.JLabel();
        tTipoPrimitivo = new javax.swing.JTextField();
        tTipoClasse = new javax.swing.JTextField();
        jIdC = new javax.swing.JLabel();
        jCodiceE = new javax.swing.JLabel();
        tIdC = new javax.swing.JTextField();
        tCodiceE = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tipo");
        setBackground(new java.awt.Color(102, 255, 255));
        setLocation(new java.awt.Point(640, 480));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setForeground(new java.awt.Color(102, 255, 255));

        jCodiceT.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jCodiceT.setForeground(new java.awt.Color(255, 255, 255));
        jCodiceT.setText("CodiceT");

        jTipoPrimitivo.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jTipoPrimitivo.setForeground(new java.awt.Color(255, 255, 255));
        jTipoPrimitivo.setText("TipoPrimitivo");

        tCodiceT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCodiceTActionPerformed(evt);
            }
        });

        tabTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null,null,null},
                {null, null, null,null,null},
                {null, null, null,null,null},
                {null, null, null,null,null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        spTipo.setViewportView(tabTipo);

        jTipoClasse.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jTipoClasse.setForeground(new java.awt.Color(255, 255, 255));
        jTipoClasse.setText("TipoClasse");

        jIdC.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jIdC.setForeground(new java.awt.Color(255, 255, 255));
        jIdC.setText("IdC");

        jCodiceE.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jCodiceE.setForeground(new java.awt.Color(255, 255, 255));
        jCodiceE.setText("CodiceE");

        tIdC.setText("Chiave Esterna Classe");

        tCodiceE.setText("Chiave Esterna Enumeration");

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("*");

        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("*");

        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("*");

        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("*");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .add(spTipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 835, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(95, 95, 95))
            .add(jPanel1Layout.createSequentialGroup()
                .add(231, 231, 231)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jCodiceE)
                    .add(jIdC)
                    .add(jTipoClasse)
                    .add(jTipoPrimitivo)
                    .add(jCodiceT))
                .add(67, 67, 67)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(tCodiceT)
                    .add(tTipoPrimitivo)
                    .add(tTipoClasse, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .add(tIdC)
                    .add(tCodiceE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jLabel3)
                    .add(jLabel4))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jCodiceT)
                    .add(tCodiceT, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTipoPrimitivo)
                    .add(tTipoPrimitivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTipoClasse)
                    .add(tTipoClasse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .add(15, 15, 15)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jIdC)
                    .add(tIdC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jCodiceE)
                    .add(tCodiceE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .add(18, 18, 18)
                .add(spTipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 235, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tCodiceTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCodiceTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCodiceTActionPerformed

    private void tCodiceEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

   /**
    * Ricopia i dati della riga selezionata del JTable
    * sugli altri controlli della finestra.
    */
   @Override
    protected void mostraDati() {
      try {
	    rs.previous(); rs.next();
            tCodiceT.setText(rs.getString("CodiceT"));
            tTipoPrimitivo.setText(rs.getString("TipoPrimitivo"));
            tTipoClasse.setText(rs.getString("TipoClasse"));
            tIdC.setText(rs.getString("IdC"));
            tCodiceE.setText(rs.getString("CodiceE"));
			
        
      } catch (SQLException e) {
         mostraErrori(e);
      }
   }

   /**
    * Cancella i dati presenti in tutti i controlli presenti sul form.
    */
   @Override
    protected void pulisci() {
      super.pulisci();
        tCodiceT.setText("");
        tTipoPrimitivo.setText("");
        tTipoClasse.setText("");
        tIdC.setText("");
        tCodiceE.setText("");
        
   }

   /**
    * Forma una query corrispondente ai dati inseriti nei
    * controlli della finestra.
    * 
    * @return query, come {@link PreparedStatement}
    */
   @Override
   protected PreparedStatement creaSelectStatement() {
      Connection con;
      PreparedStatement st;
      String   codicet, tipoprimitivo, tipoclasse, idc, codicee;
      Pattern pat;
      Matcher matc;
      int k = 1;
      super.creaSelectStatement();
      codicet = String.valueOf(tCodiceT.getText());
      tipoprimitivo = tTipoPrimitivo.getText();
      tipoclasse = tTipoClasse.getText();
      idc = String.valueOf(tIdC.getText());
      codicee = String.valueOf(tCodiceE.getText());

      query += " where";
      //}
      if (codicet.length() > 0) {
         query += " CodiceT= ? and";
      }
      if (tipoprimitivo.length() > 0) {
         if (tipoprimitivo.contains("%")) {
            query += " TipoPrimitivo like ? and";
         } else {
            query += " TipoPrimitivo = ? and";
         }
      }
      if (tipoclasse.length() > 0) {
         if (tipoclasse.contains("%")) {
            query += " TipoClasse like ? and";
         } else {
            query += " TipoClasse= ? and";
         }
      }
      if (idc.length() > 0) {
         if (idc.contains("%")) {
            query += " IdC like ? and";
         } else {
            query += " IdC = ? and";
         }
      }
      if (codicee.length() > 0) {
         if (codicee.contains("%")) {
            query += " CodiceE like ?";
         } else {
            query += " CodiceE = ?";
         }
      }
      pat = Pattern.compile("where$|and$"); //cancella where o and finali
      matc = pat.matcher(query);
      query = matc.replaceAll("");
      try {
         con = Database.getDefaultConnection();
         st = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_READ_ONLY);

         if (codicet.length() > 0) {
            st.setString(k++, codicet);
         }
         if (tipoprimitivo.length() > 0) {
            st.setString(k++, tipoprimitivo);
         }
         if (tipoclasse.length() > 0) {
            st.setString(k++, tipoclasse);
         }
         if (idc.length() > 0) {
            st.setString(k++, idc);
         }
         if (codicee.length() > 0) {
            st.setString(k++, codicee);
         }
         return st;
      } catch (SQLException e) {
         mostraErrori(e);
         return null;
      }
   }

   /**
    * Prepara il comando SQL di inserimento in base ai dati
    * inseriti nei controlli.
    * 
    * @param c la connessione al DB
    * @return il comando, come {@link PreparedStatement}
    * @throws SQLException in caso di errori nel preparare il
    * comando
    */
   
   @Override
    protected PreparedStatement getComandoInserimento(Connection c)
        throws SQLException {
        String cmdIns;
        PreparedStatement st;
        cmdIns = "insert into " + Database.schema + ".Tipo (CodiceT,TipoPrimitivo,"
                + "TipoClasse,IdC,CodiceE) values(?,?,?,?,?)";
        st = c.prepareStatement(cmdIns);
        st.setString(1, String.valueOf(tCodiceT.getText()));
        st.setString(2, tTipoPrimitivo.getText());
        st.setString(3, tTipoClasse.getText());
        st.setString(4, String.valueOf(tIdC.getText()));
        st.setString(5, String.valueOf(tCodiceE.getText ()));
        return st;
   }

   /**
    * Prepara il comando SQL di aggiornamento in base ai dati
    * inseriti nei controlli.
    * 
    * @param c la connessione al DB
    * @return il comando, come {@link PreparedStatement}
    * @throws SQLException in caso di errori nel preparare il
    * comando
    */
   
   @Override
    protected PreparedStatement getComandoAggiornamento ( Connection c) throws SQLException {
       String cmdUp;
       PreparedStatement st;
       cmdUp = "update " + Database.schema + ".Tipo set TipoPrimitivo=?,TipoClasse=?,"
               + "IdC=?,CodiceE=? where CodiceT=?" ;
       st = c.prepareStatement(cmdUp);
       st.setString(5, String.valueOf(tCodiceT.getText()));
       st.setString(1, tTipoPrimitivo.getText());
       st.setString(2, tTipoClasse.getText());
       st.setString(3, String.valueOf(tIdC.getText()));
       st.setString(4, String.valueOf(tCodiceE.getText()));
       return st;
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jCodiceE;
    private javax.swing.JLabel jCodiceT;
    private javax.swing.JLabel jIdC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jTipoClasse;
    private javax.swing.JLabel jTipoPrimitivo;
    private javax.swing.JScrollPane spTipo;
    private javax.swing.JTextField tCodiceE;
    private javax.swing.JTextField tCodiceT;
    private javax.swing.JTextField tIdC;
    private javax.swing.JTextField tTipoClasse;
    private javax.swing.JTextField tTipoPrimitivo;
    private javax.swing.JTable tabTipo;
    // End of variables declaration//GEN-END:variables



   @Override
   protected void getComandoElimina(Connection c)
    throws SQLException {
        String cmd;
        cmd = "delete from " + Database.schema + "." + "Tipo"
               + " where CodiceT=?";
        try {
            Connection conn = Database.getDefaultConnection();
            PreparedStatement st = conn.prepareStatement(cmd);
            st.setString(1, tCodiceT.getText());
            st.executeUpdate();
            } catch (SQLException e) {
             mostraErrori(e);
        }     
   }
}