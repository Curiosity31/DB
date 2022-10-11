/*
 * DBFrame.java
 */
package database2019;

import java.awt.event.ItemEvent;
import java.sql.*;
import javax.swing.*;
import java.util.regex.*;
import java.time.Year;
import java.time.LocalDate;

public class Attributo extends DBFrame {
   private final SelettoreData dataInserimento, dataModifica;
   
   public Attributo() {
      super();
      initComponents();
      dataInserimento = new SelettoreData(gDataInserimento, mDataInserimento,  aDataInserimento, Year.of(1908), Year.now());
      dataModifica = new SelettoreData( gDataModifica,  mDataModifica, aDataModifica, Year.of(1908), Year.now());
      setModalita(INIZIO);
      setFrameTable(tabAttributo);
      setNomeTabella("Attributo");
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
            tCodiceA.setEnabled (false);
            tNomeAt.setEnabled(false);
            tCardinalità.setEnabled(false);
            tModifyAcc.setEnabled(false);
            tDescrizione.setEnabled(false);
	    tCodiceT.setEnabled(false);
	    tIdC.setEnabled(false);
            tIdAss.setEnabled(false);
	    dataInserimento.setEnabled(false);
	    dataModifica.setEnabled(false);
            break;
         case APPEND_QUERY:
            tCodiceA.setEnabled (true);
            tNomeAt.setEnabled(true);
            tCardinalità.setEnabled(true);
            tModifyAcc.setEnabled(true);
            tDescrizione.setEnabled(true);
	    tCodiceT.setEnabled(true);
	    tIdC.setEnabled(true);
            tIdAss.setEnabled(true);
	    dataInserimento.setEnabled(true);
	    dataModifica.setEnabled(false);
            break;
         case BROWSE:
            tCodiceA.setEnabled (false);
            tNomeAt.setEnabled(false);
            tCardinalità.setEnabled(false);
            tModifyAcc.setEnabled(false);
            tDescrizione.setEnabled(false);
	    tCodiceT.setEnabled(false);
	    tIdC.setEnabled(false);
            tIdAss.setEnabled(false);
	    dataInserimento.setEnabled(false);
	    dataModifica.setEnabled(false);
            break;
         case UPDATE:
            tCodiceA.setEnabled (false);
            tNomeAt.setEnabled(true);
            tCardinalità.setEnabled(true);
            tModifyAcc.setEnabled(true);
            tDescrizione.setEnabled(true);
	    tCodiceT.setEnabled(true);
	    tIdC.setEnabled(true);
            tIdAss.setEnabled(true);
	    dataInserimento.setEnabled(true);
	    dataModifica.setEnabled(true);
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
         msg = "Esiste già un'altro Attributo con lo stesso CodiceA";
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
        jModifyAcc = new javax.swing.JLabel();
        jCodiceA = new javax.swing.JLabel();
        spAttributo = new javax.swing.JScrollPane();
        tabAttributo = new javax.swing.JTable();
        jDescrizione = new javax.swing.JLabel();
        jCardinalità = new javax.swing.JLabel();
        tCodiceA = new javax.swing.JTextField();
        tCodiceT = new javax.swing.JTextField();
        tCardinalità = new javax.swing.JTextField();
        jNomeAt = new javax.swing.JLabel();
        tNomeAt = new javax.swing.JTextField();
        jIdAss = new javax.swing.JLabel();
        jIdC = new javax.swing.JLabel();
        jDataInserimento = new javax.swing.JLabel();
        jDataModifica = new javax.swing.JLabel();
        jCodiceT = new javax.swing.JLabel();
        tIdAss = new javax.swing.JTextField();
        tIdC = new javax.swing.JTextField();
        tDescrizione = new javax.swing.JTextField();
        tModifyAcc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        gDataInserimento = new javax.swing.JComboBox<>();
        mDataInserimento = new javax.swing.JComboBox<>();
        aDataInserimento = new javax.swing.JComboBox<>();
        gDataModifica = new javax.swing.JComboBox<>();
        mDataModifica = new javax.swing.JComboBox<>();
        aDataModifica = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Attributo");
        setBackground(new java.awt.Color(102, 255, 255));
        setLocation(new java.awt.Point(640, 480));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setForeground(new java.awt.Color(102, 255, 255));

        jModifyAcc.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jModifyAcc.setForeground(new java.awt.Color(255, 255, 255));
        jModifyAcc.setText("ModifyAcc");

        jCodiceA.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jCodiceA.setForeground(new java.awt.Color(255, 255, 255));
        jCodiceA.setText("CodiceA");

        tabAttributo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null ,null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        spAttributo.setViewportView(tabAttributo);

        jDescrizione.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jDescrizione.setForeground(new java.awt.Color(255, 255, 255));
        jDescrizione.setText("Descrizione");

        jCardinalità.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jCardinalità.setForeground(new java.awt.Color(255, 255, 255));
        jCardinalità.setText("Cardinalità");

        tCodiceA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCodiceAActionPerformed(evt);
            }
        });

        tCodiceT.setText("Chiave Esterna Tipo");
        tCodiceT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCodiceTActionPerformed(evt);
            }
        });

        tCardinalità.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCardinalitàActionPerformed(evt);
            }
        });

        jNomeAt.setBackground(new java.awt.Color(255, 255, 255));
        jNomeAt.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jNomeAt.setForeground(new java.awt.Color(255, 255, 255));
        jNomeAt.setText("NomeAt");

        tNomeAt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNomeAtActionPerformed(evt);
            }
        });

        jIdAss.setBackground(new java.awt.Color(255, 255, 255));
        jIdAss.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jIdAss.setForeground(new java.awt.Color(255, 255, 255));
        jIdAss.setText("IdAss");

        jIdC.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jIdC.setForeground(new java.awt.Color(255, 255, 255));
        jIdC.setText("IdC");

        jDataInserimento.setBackground(new java.awt.Color(255, 255, 255));
        jDataInserimento.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jDataInserimento.setForeground(new java.awt.Color(255, 255, 255));
        jDataInserimento.setText("DataInserimento");

        jDataModifica.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jDataModifica.setForeground(new java.awt.Color(255, 255, 255));
        jDataModifica.setText("DataModifica");

        jCodiceT.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jCodiceT.setForeground(new java.awt.Color(255, 255, 255));
        jCodiceT.setText("CodiceT");

        tIdAss.setText("Chiave Esterna Classe_Di_Associazione");
        tIdAss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tIdAssActionPerformed(evt);
            }
        });

        tIdC.setText("Chiave Esterna Classe");
        tIdC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tIdCActionPerformed(evt);
            }
        });

        tDescrizione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tDescrizioneActionPerformed(evt);
            }
        });

        tModifyAcc.setText("Modificatore di accesso");
        tModifyAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tModifyAccActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("*");

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("*");

        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("*");

        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("*");

        gDataInserimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5","6","7","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"  }));
        gDataInserimento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gDataInserimentoItemStateChanged(evt);
            }
        });
        gDataInserimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gDataInserimentoActionPerformed(evt);
            }
        });

        mDataInserimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre" }));
        mDataInserimento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mDataInserimentoItemStateChanged(evt);
            }
        });
        mDataInserimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mDataInserimentoActionPerformed(evt);
            }
        });

        aDataInserimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0000" }));
        aDataInserimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aDataInserimentoActionPerformed(evt);
            }
        });

        gDataModifica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5","6","7","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31" }));
        gDataModifica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gDataModificaItemStateChanged(evt);
            }
        });

        mDataModifica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre" }));
        mDataModifica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mDataModificaItemStateChanged(evt);
            }
        });

        aDataModifica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0000"}));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .add(spAttributo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 962, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(110, 110, 110)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jNomeAt)
                        .add(44, 44, 44)
                        .add(tNomeAt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 181, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                            .add(jCodiceA)
                            .add(44, 44, 44)
                            .add(tCodiceA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 181, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                            .add(jCardinalità)
                            .add(44, 44, 44)
                            .add(tCardinalità, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 181, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jModifyAcc))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jDescrizione)
                        .add(48, 48, 48)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(tModifyAcc)
                            .add(tDescrizione, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(117, 117, 117)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jDataInserimento)
                            .add(jCodiceT)
                            .add(jIdC)
                            .add(jIdAss)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jDataModifica)))
                .add(50, 50, 50)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(tCodiceT)
                            .add(tIdC)
                            .add(tIdAss, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                        .add(18, 18, 18)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(gDataInserimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mDataInserimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(aDataInserimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(gDataModifica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mDataModifica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(aDataModifica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tCodiceA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCodiceA)
                    .add(tCodiceT, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCodiceT))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jNomeAt)
                    .add(tNomeAt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(tIdC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jIdC)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jCardinalità)
                        .add(tCardinalità, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(tIdAss, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel2))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jIdAss))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jDataInserimento)
                    .add(jModifyAcc)
                    .add(tModifyAcc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(gDataInserimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mDataInserimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(aDataInserimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jDataModifica)
                    .add(jDescrizione)
                    .add(tDescrizione, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(gDataModifica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mDataModifica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(aDataModifica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .add(32, 32, 32)
                .add(spAttributo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 240, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
/*
    private void tCodiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCodiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCodiceActionPerformed

    private void tDataAggiornamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tDataAggiornamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tDataAggiornamentoActionPerformed
*/
    private void tCardinalitàActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCardinalitàActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCardinalitàActionPerformed

    private void tNomeAtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNomeAtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNomeAtActionPerformed

    private void tCodiceAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCodiceAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCodiceAActionPerformed

    private void tCodiceTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCodiceTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCodiceTActionPerformed

    private void tIdAssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tIdAssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tIdAssActionPerformed

    private void tIdCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tIdCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tIdCActionPerformed

    private void tDescrizioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tDescrizioneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tDescrizioneActionPerformed

    private void tModifyAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tModifyAccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tModifyAccActionPerformed

    private void gDataInserimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gDataInserimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gDataInserimentoActionPerformed

    private void mDataInserimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mDataInserimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mDataInserimentoActionPerformed

    private void aDataInserimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aDataInserimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aDataInserimentoActionPerformed

    private void gDataInserimentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gDataInserimentoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
         dataInserimento.cambiatoGiorno();
      }
    }//GEN-LAST:event_gDataInserimentoItemStateChanged

    private void mDataInserimentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mDataInserimentoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            dataInserimento.cambiatoMese();
        }
           }//GEN-LAST:event_mDataInserimentoItemStateChanged

    private void gDataModificaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gDataModificaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
         dataModifica.cambiatoGiorno();
      }
    }//GEN-LAST:event_gDataModificaItemStateChanged

    private void mDataModificaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mDataModificaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
         dataModifica.cambiatoMese();
      }
    }//GEN-LAST:event_mDataModificaItemStateChanged

   
    
    
    
   /**
    * Ricopia i dati della riga selezionata del JTable
    * sugli altri controlli della finestra.
    */
   @Override
   protected void mostraDati() {
      try {
	        rs.previous(); rs.next();
		tCodiceA.setText(rs.getString("CodiceA"));
                tNomeAt.setText(rs.getString("NomeAt"));
                tCardinalità.setText(rs.getString("Cardinalità"));
                tModifyAcc.setText(rs.getString("ModifyAcc"));
                tDescrizione.setText(rs.getString("Descrizione"));
                tCodiceT.setText(rs.getString("CodiceT"));
		tIdC.setText(rs.getString("IdC"));
                tIdAss.setText(rs.getString("IdAss"));
		dataInserimento.setDate(rs.getDate("DataInserimento"));
               dataModifica.setDate(rs.getDate("DataModifica"));
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
      tCodiceA.setText("");
      tNomeAt.setText("");
      tCardinalità.setText("");
      tModifyAcc.setText("");
      tDescrizione.setText("");
      tCodiceT.setText("");
      tIdC.setText("");
      tIdAss.setText("");
      LocalDate d = null;
      dataInserimento.setDate(d);
      dataModifica.setDate(d);
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
      String codicea,nomeat,cardinalità,modifyacc,descrizione,codicet,idc,idass;
      Pattern pat;
      Matcher matc;
      LocalDate inserimento, modifica;
      int k = 1;
      super.creaSelectStatement();
      codicea = String.valueOf(tCodiceA.getText());
      nomeat = tNomeAt.getText();
      cardinalità = tCardinalità.getText();
      modifyacc = tModifyAcc.getText();
      descrizione = tDescrizione.getText();
      codicet = String.valueOf(tCodiceT.getText());
      idc = String.valueOf(tIdC.getText());
      idass= String.valueOf(tIdAss.getText());
      inserimento = dataInserimento.getDate();
      modifica = dataModifica.getDate();
      query += " where";
      
      if (codicea.length() > 0) {
         query += " CodiceA=? and";
      }
      if (nomeat.length() > 0) {
         if (nomeat.contains("%")) {
            query += " NomeAt like ? and";
         } else {
            query += " NomeAt =? and";
         }
      }
      if (cardinalità.length() > 0) {
         if (cardinalità.contains("%")) {
            query += " Cardinalità like ? and";
         } else {
            query += " Cardinalità =? and";
         }
      }
	  if (modifyacc.length() > 0) {
         if (modifyacc.contains("%")) {
            query += " ModifyAcc like ? and";
         } else {
            query += " ModifyAcc =? and";
         }
      }
	  if (descrizione.length() > 0) {
         if (descrizione.contains("%")) {
            query += " Descrizione like ? and";
         } else {
            query += " Descrizione =? and";
         }
      }
	  if (codicet.length() > 0) {
         if (codicet.contains("%")) {
            query += " CodiceT like ? and";
         } else {
            query += " CodiceT =? and";
         }
      }
	  if (idc.length() > 0) {
         if (idc.contains("%")) {
            query += " IdC like ? and";
         } else {
            query += " IdC =? and";
         }
      }
	  if (idass.length() > 0) {
         if (idass.contains("%")) {
            query += " IdAss like ? and";
         } else {
            query += " IdAss =? and";
         }
      }
     if (inserimento != null) {
            query += " DataInserimento =? and";
         }
     

      pat = Pattern.compile("where$|and$"); //cancella where o and finali
      matc = pat.matcher(query);
      query = matc.replaceAll("");
      try {
         con = Database.getDefaultConnection();
         st = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_READ_ONLY);

         if (codicea.length() > 0) {
            st.setString(k++, codicea);
         }
         if (nomeat.length() > 0) {
            st.setString(k++, nomeat);
         }
         if (cardinalità.length() > 0) {
            st.setString(k++, cardinalità);
         }
		 if (modifyacc.length() > 0) {
            st.setString(k++, modifyacc);
         }
		 if (descrizione.length() > 0) {
            st.setString(k++, descrizione);
         }
		 if (codicet.length() > 0) {
            st.setString(k++, codicet);
         }
		 if (idc.length() > 0) {
            st.setString(k++, idc);
         }
		 if (idass.length() > 0) {
            st.setString(k++, idass);
         }
         if (inserimento != null) {
            st.setDate(k++, Date.valueOf(inserimento));
         }
        /* if (modifica != null) {
            st.setDate(k++, Date.valueOf(modifica));
         }*/
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
        cmdIns = "insert into " + Database.schema + ".Attributo (CodiceA,NomeAt,"
                + "Cardinalità,ModifyAcc,Descrizione,CodiceT,IdC,IdAss,"
                + "DataInserimento)  values(?,?,?,?,?,?,?,?,?)";
        st = c.prepareStatement(cmdIns);
        st.setString(1, String.valueOf(tCodiceA.getText()));
        st.setString(2, tNomeAt.getText());
        st.setString(3, tCardinalità.getText());
	st.setString(4, tModifyAcc.getText());
	st.setString(5, tDescrizione.getText());
	st.setString(6, String.valueOf(tCodiceT.getText()));
        st.setString(7, String.valueOf(tIdC.getText()));
	st.setString(8, String.valueOf(tIdAss.getText()));
        
        LocalDate creazione = dataInserimento.getDate();
        if (creazione == null) st.setDate(9, null);
        else st.setDate(9, Date.valueOf(dataInserimento.getDate()));
      
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
    protected PreparedStatement getComandoAggiornamento(Connection c) throws SQLException {
       String cmdUp;
       PreparedStatement st;
       cmdUp = "update " + Database.schema + ".Attributo set NomeAt=?,"
               + "Cardinalità=?,ModifyAcc=?,Descrizione=?,CodiceT=?,"
               + "IdC=?,IdAss=?,DataInserimento=? where CodiceA=?";
       st = c.prepareStatement(cmdUp);
       st.setString(9, String.valueOf(tCodiceA.getText()));
       st.setString(1, tNomeAt.getText());
       st.setString(2, tCardinalità.getText());
       st.setString(3, tModifyAcc.getText());
       st.setString(4, tDescrizione.getText());
       st.setString(5, String.valueOf(tCodiceT.getText()));
       st.setString(6, String.valueOf(tIdC.getText()));
       st.setString(7, String.valueOf(tIdAss.getText()));
       LocalDate creazione = dataInserimento.getDate();
      if (creazione == null) st.setDate(8, null);
      else st.setDate(8, Date.valueOf(dataInserimento.getDate()));
      
   
       return st;
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> aDataInserimento;
    private javax.swing.JComboBox<String> aDataModifica;
    private javax.swing.JComboBox<String> gDataInserimento;
    private javax.swing.JComboBox<String> gDataModifica;
    private javax.swing.JLabel jCardinalità;
    private javax.swing.JLabel jCodiceA;
    private javax.swing.JLabel jCodiceT;
    private javax.swing.JLabel jDataInserimento;
    private javax.swing.JLabel jDataModifica;
    private javax.swing.JLabel jDescrizione;
    private javax.swing.JLabel jIdAss;
    private javax.swing.JLabel jIdC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jModifyAcc;
    private javax.swing.JLabel jNomeAt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> mDataInserimento;
    private javax.swing.JComboBox<String> mDataModifica;
    private javax.swing.JScrollPane spAttributo;
    private javax.swing.JTextField tCardinalità;
    private javax.swing.JTextField tCodiceA;
    private javax.swing.JTextField tCodiceT;
    private javax.swing.JTextField tDescrizione;
    private javax.swing.JTextField tIdAss;
    private javax.swing.JTextField tIdC;
    private javax.swing.JTextField tModifyAcc;
    private javax.swing.JTextField tNomeAt;
    private javax.swing.JTable tabAttributo;
    // End of variables declaration//GEN-END:variables



   @Override
   protected void getComandoElimina(Connection c)
    throws SQLException {
        String cmd;
        cmd = "delete from " + Database.schema + "." + "Attributo"
               + " where CodiceA=?";
        try {
            Connection conn = Database.getDefaultConnection();
            PreparedStatement st = conn.prepareStatement(cmd);
            st.setString(1, tCodiceA.getText());
            st.executeUpdate();
            } catch (SQLException e) {
             mostraErrori(e);
        }     
   }
}