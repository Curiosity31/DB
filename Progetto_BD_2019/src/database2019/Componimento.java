/*
 * DBFrame.java
 */
package database2019;

import java.sql.*;
import javax.swing.*;
import java.util.regex.*;

public class Componimento extends DBFrame {

   public Componimento() {
      super();
      initComponents();
      setModalita(INIZIO);
      setFrameTable(tabComponimento);
      setNomeTabella("Componimento");
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
            tN_valori.setEnabled (false);
            tNomeV.setEnabled(false);
            tCodiceE.setEnabled(false);
            break;
         case APPEND_QUERY:
            tN_valori.setEnabled (true);
            tNomeV.setEnabled(true);
            tCodiceE.setEnabled(true);
            break;
         case BROWSE:
            tN_valori.setEnabled (false);
            tNomeV.setEnabled(false);
            tCodiceE.setEnabled(false);
            break;
         case UPDATE:
            tN_valori.setEnabled (true);
            tNomeV.setEnabled(false);
            tCodiceE.setEnabled(false);
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
         msg = "Esiste già un'altro Componimento ";
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
        jN_valori = new javax.swing.JLabel();
        jNomeV = new javax.swing.JLabel();
        tN_valori = new javax.swing.JTextField();
        spComponimento = new javax.swing.JScrollPane();
        tabComponimento = new javax.swing.JTable();
        jCodiceE = new javax.swing.JLabel();
        tNomeV = new javax.swing.JTextField();
        tCodiceE = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Componimento");
        setBackground(new java.awt.Color(102, 255, 255));
        setLocation(new java.awt.Point(640, 480));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setForeground(new java.awt.Color(102, 255, 255));

        jN_valori.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jN_valori.setForeground(new java.awt.Color(255, 255, 255));
        jN_valori.setText("N_valori");

        jNomeV.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jNomeV.setForeground(new java.awt.Color(255, 255, 255));
        jNomeV.setText("NomeV");

        tN_valori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tN_valoriActionPerformed(evt);
            }
        });

        tabComponimento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        spComponimento.setViewportView(tabComponimento);

        jCodiceE.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jCodiceE.setForeground(new java.awt.Color(255, 255, 255));
        jCodiceE.setText("CodiceE");

        tNomeV.setText("Chiave Esterna Valore");

        tCodiceE.setText("Chiave Esterna Enumeration");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(204, 204, 204)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jCodiceE)
                            .add(jNomeV)
                            .add(jN_valori))
                        .add(67, 67, 67)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(tN_valori)
                            .add(tNomeV)
                            .add(tCodiceE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 385, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(91, 91, 91)
                        .add(spComponimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 863, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jN_valori)
                    .add(tN_valori, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jNomeV)
                    .add(tNomeV, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tCodiceE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCodiceE))
                .add(42, 42, 42)
                .add(spComponimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 288, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23))
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

    private void tN_valoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tN_valoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tN_valoriActionPerformed

   /**
    * Ricopia i dati della riga selezionata del JTable
    * sugli altri controlli della finestra.
    */
   @Override
    protected void mostraDati() {
      try {
            rs.previous(); rs.next();
	    tN_valori.setText(rs.getString("N_valori"));
            tNomeV.setText(rs.getString("NomeV"));
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
        tN_valori.setText("");
        tNomeV.setText("");
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
      String   n_valori, nomev, codicee;
      Pattern pat;
      Matcher matc;
      int k = 1;
      super.creaSelectStatement();
      n_valori= String.valueOf(tN_valori.getText());
      nomev = tNomeV.getText();
      codicee = String.valueOf(tCodiceE.getText());
      query += " where";
      
      if (n_valori.length() > 0) {
		  if (n_valori.contains("%")) {
            query += " N_valori like ? and";
         } else {
            query += " N_valori = ? and";
         }
      }
      if (nomev.length() > 0) {
         if (nomev.contains("%")) {
            query += " NomeV like ? and";
         } else {
            query += " NomeV = ? and";
         }
      }
      if (codicee.length() > 0) {
         if (codicee.contains("%")) {
            query += " CodiceE like ? and";
         } else {
            query += " CodiceE= ? and";
         }
      }
      
      pat = Pattern.compile("where$|and$"); //cancella where o and finali
      matc = pat.matcher(query);
      query = matc.replaceAll("");
      try {
         con = Database.getDefaultConnection();
         st = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_READ_ONLY);

         if (n_valori.length() > 0) {
            st.setString(k++, n_valori);
         }
         if (nomev.length() > 0) {
            st.setString(k++, nomev);
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
        cmdIns = "insert into " + Database.schema + ".Componimento (N_valori,"
                + "NomeV,CodiceE)  values(?,?,?)";
        st = c.prepareStatement(cmdIns);
        st.setString(1, String.valueOf(tN_valori.getText()));
        st.setString(2, tNomeV.getText());
        st.setString(3, String.valueOf(tCodiceE.getText()));
     
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
       cmdUp = "update " + Database.schema + ".Componimento set N_valori=?,"
               + "NomeV=?,CodiceE=? where NomeV=? and CodiceE=?" ; 
       st = c.prepareStatement(cmdUp);
       st.setString(5, String.valueOf(tCodiceE.getText()));
       st.setString(4, tNomeV.getText());
       st.setString(1, String.valueOf(tN_valori.getText()));
       st.setString(2, tNomeV.getText());
       st.setString(3, String.valueOf(tCodiceE.getText()));
       
       return st;
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jCodiceE;
    private javax.swing.JLabel jN_valori;
    private javax.swing.JLabel jNomeV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane spComponimento;
    private javax.swing.JTextField tCodiceE;
    private javax.swing.JTextField tN_valori;
    private javax.swing.JTextField tNomeV;
    private javax.swing.JTable tabComponimento;
    // End of variables declaration//GEN-END:variables



   @Override
   protected void getComandoElimina(Connection c)
    throws SQLException {
        String cmd;
        cmd = "delete from " + Database.schema + "." + "Componimento"
               + " where NomeV=? and CodiceE=? " ;
        try {
            Connection conn = Database.getDefaultConnection();
            PreparedStatement st = conn.prepareStatement(cmd);
            st.setString(2, tCodiceE.getText());
	    st.setString(1, tNomeV.getText());
            st.executeUpdate();
            } catch (SQLException e) {
             mostraErrori(e);
        }     
   }
}