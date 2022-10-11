/*
 * DBFrame.java
 */
package database2019;

import java.sql.*;
import javax.swing.*;

/**
 * Classe base per i frame di gestione delle tabelle principali. <br> Permette
 * le operazioni di inserimento, cancellazione, ricerca e navigazione.
 */
abstract public class DBFrame extends javax.swing.JFrame implements ConProprieta {

   /**
    * Crea nuovo form DBFrame.
    */
   public DBFrame() {
      initComponents();
   }
   
   final static public int INIZIO = 1;
   final static public int FK = 5;
   /**
    * Indica che il frame si trova nello stato di inserimento di un nuovo record
    * o dei parametri di ricerca.
    */
   final static public int APPEND_QUERY = 2;
   /**
    * Indica che il frame si trova nello stato di navigazione (ricerca
    * gi&agrave; effettuata).
    */
   final static public int BROWSE = 3;
   /**
    * Indica che il frame si trova nello stato di modifica dei dati.
    */
   final static public int UPDATE = 4;
   /**
    * indica che l'eccezione &egrave; stata sollevata eseguendo un comando
    * <i>SELECT</i>.
    */
   final static public int CONTESTO_ESEGUI_QUERY = 1;
   
   private int modalita; // Stato corrente del Frame
   private int pos = 1; // Posizione del record corrente nel ResultSet.
   private DBTableModel modelloTabella; // modello della tabella di navigazione
   /**
    * ResultSet dell'ultima query eseguita.
    */
   protected ResultSet rs;
   /**
    * Query da eseguire.
    */
   protected String query;
   private String nomeTabella; // nome della tabella (o vista)
   private javax.swing.JTable tabFrameTable;
   private ConProprieta padre = null; // form che usa quello corrente come Lookup

   /**
    * Imposta il Puntatore al Form che usa quello corrente cone Lookup.
    * 
    * @param p la finestra che ha chiamato quella corrente
    */
   public void setPadre(ConProprieta p) {
      padre = p;
      if (padre == null) {
      } else {
      }
   }

   /**
    * Restituisce il puntatore al Form che usa quello corrente come Lookup.
    *
    * @return la finestra che ha chiamato quella corrente
    */
   public ConProprieta getPadre() {
      return padre;
   }

   DBTableModel getModelloTabella() {
      return modelloTabella;
   }

   void setModelloTabella(DBTableModel gmt) {
      modelloTabella = gmt;
   }

   String getQuery() {
      return query;
   }

   void setQuery(String query) {
      this.query = query;
   }

   String getNomeTabella() {
      return nomeTabella;
   }

   final void setNomeTabella(String nomeTabella) {
      this.nomeTabella = nomeTabella;
   }

   /**
    * Metodo usato dai form di lookup per passare dati al form chiamante. <br>
    * In DBFrame &egrave; disponibile un metodo vuoto per le form che non
    * necessitano di lookup.
    */
   @Override
   public void setProprieta(String proprieta, String valore) {
      //non può essere abstract: per alcuni frame non è necessario
   }

   /**
    * Imposta la tabella di navigazione del form. <br> In genere dovrebbe essere
    * richiamato nei costruttori delle classi derivate.
    *
    * @param t il JTable da associare alla finestra
    */
   protected final void setFrameTable(JTable t) {
      tabFrameTable = t;
      modelloTabella = new DBTableModel(this);
      tabFrameTable.setModel(modelloTabella);
      tabFrameTable.getSelectionModel().setSelectionMode(
              ListSelectionModel.SINGLE_SELECTION);
      
      /* aggiunge al JTable due listener per fare in modo che quando la riga
       * selezionata cambia, venga richiamato un metodo per passare
       * l'informazione al ResultSet corrispondente
       */ 
      tabFrameTable.addMouseListener(new java.awt.event.MouseAdapter() {

         @Override
         public void mouseReleased(java.awt.event.MouseEvent evt) {
                  selezioneTabellaCambiata();
         }
      });

      tabFrameTable.getSelectionModel().addListSelectionListener(
              new javax.swing.event.ListSelectionListener() {

                 @Override
                 public void valueChanged(
                         javax.swing.event.ListSelectionEvent e) {
                    selezioneTabellaCambiata();
                 }
              });
   }

   /**
    * Chiave primaria del record corrente.
    * 
    * @return il JTextField contenente la chiave primaria
    */
   

   /**
    * Imposta lo stato corrente del form. <br> In base allo stato vengono
    * abilitati o disabilitati alcuni oggetti del form.
    * 
    * @param modo intero che rappresenta lo stato
    */
   public void setModalita(int modo) {
      modalita = modo;
      switch (modo) {
         case INIZIO:
            bNuovo.setEnabled(true);
            bApri.setEnabled(false);
            bSalva.setEnabled(false);
            bCerca.setEnabled(false);
            bAnnulla.setEnabled(false);
            bElimina.setEnabled(false);
            bChiave_Esterna.setEnabled(false);
            if (tabFrameTable != null) {
               tabFrameTable.setEnabled(false);
            }
            break;
         case APPEND_QUERY:
            bNuovo.setEnabled(true);
            bApri.setEnabled(false);
            bSalva.setEnabled(true);
            bCerca.setEnabled(true);
            bAnnulla.setEnabled(false);
            bElimina.setEnabled(false);
            bChiave_Esterna.setEnabled(false);
            if (tabFrameTable != null) {
               tabFrameTable.setEnabled(false);
            }
            break;
         case BROWSE:
            bNuovo.setEnabled(true);
            bApri.setEnabled(true);
            bSalva.setEnabled(false);
            bCerca.setEnabled(false);
            bAnnulla.setEnabled(false);
            bElimina.setEnabled(false);
            bChiave_Esterna.setEnabled(false);
            if (tabFrameTable != null) {
               tabFrameTable.setEnabled(true);
            }
            break;
         case UPDATE:
            bNuovo.setEnabled(true);
            bApri.setEnabled(false);
            bSalva.setEnabled(true);
            bCerca.setEnabled(false);
            bAnnulla.setEnabled(true);
            bElimina.setEnabled(true);
            bChiave_Esterna.setEnabled(false);
            if (tabFrameTable != null) {
               tabFrameTable.setEnabled(false);
            };
            break;
            case FK:
            bNuovo.setEnabled(false);
            bApri.setEnabled(false);
            bSalva.setEnabled(false);
            bCerca.setEnabled(false);
            bAnnulla.setEnabled(false);
            bElimina.setEnabled(false);
            bChiave_Esterna.setEnabled(true);
            if (tabFrameTable != null) {
               tabFrameTable.setEnabled(false);
            };
      }
   }

   /**
    * Mostra una descrizione di un errore SQL in un linguaggio comprensibile per
    * l'utente finale. <br> L'implementazione di DBFrame fa eccezione, essa
    * fornisce un messaggio standard per gli errori non previsti esplicitamente.
    * 
    * @param e eccezione SQLException catturata
    * @param query l'istruzione SQL che ha causato l'errore
    * @param contesto intero per distinguere se l'eccezione ha avuto origine
    * da una query
    */
   protected void mostraErrori(SQLException e, String query, int contesto) {
      String msg;
      if ((e.getErrorCode() == 17068 | e.getErrorCode() == 17011) & 
              contesto != CONTESTO_ESEGUI_QUERY) {
         return; //questi errori non mi interessano
      }
      msg = "ErrorCode= " + e.getErrorCode() + "\n";
      msg += "Message= " + e.getMessage() + "\n";
      // msg += "SQLState= " + e.getSQLState() + "\n";

      JOptionPane.showMessageDialog(this, msg, "Errore",
              JOptionPane.ERROR_MESSAGE);
   }

   /**
    * Mostra una descrizione di un errore SQL in un linguaggio comprensibile per
    * l'utente finale. <br> L'implementazione di DBFrame fa eccezione, essa
    * fornisce un messaggio standard per gli errori non previsti esplicitamente.
    *
    * @param e l'eccezione catturata
    */
   protected void mostraErrori(SQLException e) {
      mostraErrori(e, "", 0);
   }

   /**
    * Mostra una descrizione di un errore in un linguaggio comprensibile per
    * l'utente finale. <br> L'implementazione di DBFrame fa eccezione, essa
    * fornisce un messaggio standard per gli errori non previsti esplicitamente.
    *
    * @param e l'eccezione catturata
    * @param contesto intero che distingue la situazione che ha causato
    * l'errore
    */
   protected void mostraErrori(Exception e, int contesto) {
      String msg;
      msg = "Message= " + e.getMessage() + "\n";

      JOptionPane.showMessageDialog(this, msg, "Errore",
              JOptionPane.ERROR_MESSAGE);
   }

   /**
    * Mostra una descrizione di un errore in un linguaggio comprensibile per
    * l'utente finale. <br> L'implementazione di DBFrame fa eccezione, essa
    * fornisce un messaggio standard per gli errori non previsti esplicitamente.
    *
    * @param e l'eccezione catturata
    */
   protected void mostraErrori(Exception e) {
      mostraErrori(e, 0);
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bNuovo = new javax.swing.JButton();
        bSalva = new javax.swing.JButton();
        bCerca = new javax.swing.JButton();
        bApri = new javax.swing.JButton();
        bAnnulla = new javax.swing.JButton();
        bElimina = new javax.swing.JButton();
        bChiave_Esterna = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DBFrame");
        setBackground(new java.awt.Color(102, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        bNuovo.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        bNuovo.setForeground(new java.awt.Color(0, 0, 153));
        bNuovo.setText("Nuova Operazione");
        bNuovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNuovoActionPerformed(evt);
            }
        });

        bSalva.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        bSalva.setForeground(new java.awt.Color(0, 51, 153));
        bSalva.setText("Salva");
        bSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvaActionPerformed(evt);
            }
        });

        bCerca.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        bCerca.setForeground(new java.awt.Color(0, 0, 153));
        bCerca.setText("Cerca");
        bCerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCercaActionPerformed(evt);
            }
        });

        bApri.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        bApri.setForeground(new java.awt.Color(0, 51, 153));
        bApri.setText("Modifica");
        bApri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApriActionPerformed(evt);
            }
        });

        bAnnulla.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        bAnnulla.setForeground(new java.awt.Color(0, 51, 153));
        bAnnulla.setText("Annulla");
        bAnnulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnnullaActionPerformed(evt);
            }
        });

        bElimina.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        bElimina.setForeground(new java.awt.Color(0, 51, 153));
        bElimina.setText("Elimina");
        bElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminaActionPerformed(evt);
            }
        });

        bChiave_Esterna.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        bChiave_Esterna.setForeground(new java.awt.Color(255, 0, 0));
        bChiave_Esterna.setText("ChiaveEsterna");
        bChiave_Esterna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChiave_EsternaActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(62, 62, 62)
                .add(bNuovo)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 57, Short.MAX_VALUE)
                .add(bCerca)
                .add(31, 31, 31)
                .add(bSalva)
                .add(32, 32, 32)
                .add(bApri)
                .add(37, 37, 37)
                .add(bElimina)
                .add(35, 35, 35)
                .add(bAnnulla)
                .add(55, 55, 55))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(bChiave_Esterna)
                .add(341, 341, 341))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {bAnnulla, bElimina}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(47, 47, 47)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(bNuovo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bCerca, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bSalva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bApri, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bElimina, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bAnnulla, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(bChiave_Esterna)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {bAnnulla, bApri, bCerca, bElimina, bNuovo, bSalva}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 285, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

   /**
    * Metodo da usare nei form di lookup per passare i dati al form
    * chiamante.
    */
   protected void premutoOK() {
      
   }
    

    private void bEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminaActionPerformed
      
        
       PreparedStatement st;
       Connection c = null;
       try {
           c = Database.nuovaConnessione();
           getComandoElimina(c);
       } catch (SQLException e) {
           mostraErrori(e);
       }
       pulisci();
       eseguiQuery();
       
    }//GEN-LAST:event_bEliminaActionPerformed

    private void bAnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnnullaActionPerformed
       eseguiQuery();
       if (getPadre() != null) {
          try {
             rs.close();
          } catch (SQLException e) {
             mostraErrori(e);
          } finally {
             dispose();
          }
       }
    }//GEN-LAST:event_bAnnullaActionPerformed

   private void selezioneTabellaCambiata() {
      try {
         rs.absolute(
                 tabFrameTable.getSelectionModel().getMinSelectionIndex() + 1);
                 mostraDati();
      } catch (SQLException e) {
                 mostraErrori(e);
      } catch (Exception a) {
      }
   }

    private void bApriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApriActionPerformed
          setModalita(UPDATE);
    }//GEN-LAST:event_bApriActionPerformed

   /**
    * Mostra i dati presenti nel record corrente. <br> Necessita di overriding
    * in tutte le classi derivate di DBFrame.  
    */
   protected void mostraDati() {
   }
   /**
    * Crea lo statement di ricerca. <br> Necessita di overriding in tutte le
    * classi derivate di DBFrame. 
    * 
    * @return query, come {@link PreparedStatement}
    */
   protected PreparedStatement creaSelectStatement() {
       query = "select * from " + Database.schema + "." + nomeTabella + " ";
       return null;
   }
    private void bCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCercaActionPerformed
        eseguiQuery();
    }//GEN-LAST:event_bCercaActionPerformed

   /**
    * Esegue una ricerca in base ai valori impostati nel form. <br> Non
    * necessita di overriding nella classi derivate, occorre invece
    * specializzare il metodo <i>creaSelectStatement</i>.
    */
   private void eseguiQuery() {

      PreparedStatement st;
      try {
         st = creaSelectStatement();
         rs = st.executeQuery();
         modelloTabella.setRS(rs);
         rs.absolute(pos);
         mostraDati();
         setModalita(BROWSE);
      } catch (SQLException e) {
         mostraErrori(e, query, CONTESTO_ESEGUI_QUERY);
      } catch (java.lang.NullPointerException e) {
         /* non devo mostrare nessun errore
          * si dovrebbe verificare solo se st=null
          * quando la connessione è caduta
          */
      }
   }

   /**
    * Cancella i dati presenti in tutti i controlli presenti sul form. <br>
    * Necessita di overriding in tutte le classi derivate di DBFrame.  
    */
   protected void pulisci() {
   }
    private void bNuovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNuovoActionPerformed
       pulisci();
       pos = 1;
       try {
          if (rs != null) {
             rs.close();
          }
          rs = null;
          modelloTabella.setRS(rs);
          setModalita(APPEND_QUERY);
       } catch (SQLException e) {
          mostraErrori(e);
       }
    }//GEN-LAST:event_bNuovoActionPerformed

   /**
    * Crea lo statement di inserimento. <br> Necessita di overriding in tutte le
    * classi derivate di DBFrame.  

    * @param c la connessione al DB
    * @return il comando, come {@link PreparedStatement}
    * @throws java.sql.SQLException in caso di problemi nel formare
    * lo statement
    */
   abstract protected PreparedStatement getComandoInserimento(Connection c)
           throws SQLException;

   /**
    * Crea lo statement di aggiornamento. <br> Necessita di overriding in tutte
    * le classi derivate di DBFrame.
    * 
    * @param c la connessione al DB
    * @return il comando, come {@link PreparedStatement}
    * @throws java.sql.SQLException in caso di problemi nel formare
    * lo statement
    */
   abstract protected PreparedStatement getComandoAggiornamento(Connection c)
           throws SQLException;
   
   
    abstract protected void getComandoElimina(Connection c)
           throws SQLException;

    private void bSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvaActionPerformed
       PreparedStatement st;
       boolean ret;  // se inserite o aggiornate righe
       Connection c = null;

       
       try {
          c = Database.nuovaConnessione();
          if (modalita == APPEND_QUERY) {
             st = getComandoInserimento(c);
          } else {
             st = getComandoAggiornamento(c);
          }
          c.setAutoCommit(false);
          ret = st.executeUpdate() >= 0;
          if (ret) {
             ret = eseguiSalva(c);
          }
          if (ret) {
             c.commit();
          } else {
             c.rollback();
          }
          c.setAutoCommit(true);
       } catch (SQLException e) {
          mostraErrori(e);
          ret = false;
       }
       if (ret) {
          if (modalita == APPEND_QUERY) {
             bCercaActionPerformed(evt);
          } else {
             eseguiQuery();
          }
       } else {
          try {
              if (c != null) {
                  c.rollback();
                  c.setAutoCommit(true);
              }
          } catch (SQLException e) {
             mostraErrori(e);
             //ret = false;
          }
       }
    }//GEN-LAST:event_bSalvaActionPerformed

    private void bChiave_EsternaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChiave_EsternaActionPerformed
        PreparedStatement st;
        try {
           st = creaSelectStatement();
           rs = st.executeQuery();
           modelloTabella.setRS(rs);
           rs.absolute(pos);
           mostraDati();
           setModalita(FK);
      } catch (SQLException e) {
            mostraErrori(e, query, CONTESTO_ESEGUI_QUERY);
      } catch (java.lang.NullPointerException e) {
      }
    }//GEN-LAST:event_bChiave_EsternaActionPerformed

   /**
    * Esegue le operazioni collaterali al salvataggio del record principale, ad
    * esempio tutti i dati nelle relazioni. 
    * 
    * @param con connessione corrente
    * @return sempre true
    */
   protected boolean eseguiSalva(Connection con) {
      return true;
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnnulla;
    private javax.swing.JButton bApri;
    private javax.swing.JButton bCerca;
    private javax.swing.JButton bChiave_Esterna;
    private javax.swing.JButton bElimina;
    private javax.swing.JButton bNuovo;
    private javax.swing.JButton bSalva;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
