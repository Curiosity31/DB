/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database2019;
import javax.swing.JComboBox;
import java.time.Year;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.sql.Date;
/**
 * Classe per scegliere una data usando 3 {@link JComboBox}.
 * 
 */
public class SelettoreData {
   private final JComboBox GIORNO;
   private final JComboBox MESE;
   private final JComboBox ANNO;
   private final Year INIZIO;
   private final Year FINE;
   private boolean feb29; //solo bisestili
   
   /**
    * Costruice un'istanza in base ai parametri forniti.
    * 
    * @param giorno una JComboBox per la selezione del GIORNO
    * @param mese una JComboBox per la selezione del MESE
    * @param anno una JComboBox per la selezione dell'ANNO
    * @param inizio il primo (pi&uacute; lontano) ANNO selezionabile
    * @param fine l'ultimo ANNO selezionabile
    */
   public SelettoreData(JComboBox giorno, JComboBox mese,
           JComboBox anno, Year inizio, Year fine) {
      GIORNO = giorno;
      MESE = mese;
      ANNO = anno;
      INIZIO = inizio;
      FINE = fine;
      feb29 = false;
      riempiAnno();
   }
   
   /**
    * Attiva o disattiva i 3 JComboBox
    * 
    * @param mode true per attivare, false per disattivare
    */
   public void setEnabled(boolean mode) {
      GIORNO.setEnabled(mode);
      MESE.setEnabled(mode);
      ANNO.setEnabled(mode);
   }
   
   /**
    * Riempie la JComboBox degli anni, eventualmente solo con
    * anni bisestili.
    */
   final public void riempiAnno() {
      int curr; //memorizza l'anno precedentemente selezionato
      try {
         curr = Integer.parseInt(ANNO.getSelectedItem().toString());
      } catch (NumberFormatException e) {
         curr = 0;
      }
      ANNO.removeAllItems();
      int primo = INIZIO.getValue();
      int ultimo = FINE.getValue();
      int passo = 1;
      if (feb29) {
         primo--; primo += 4 - (primo % 4);
         passo = 4;
      }
      for (int i = primo; i <= ultimo; i += passo) {
         ANNO.addItem(i);
      }
      if (feb29) { //regole calendario gregoriano
         for (int i = 1700; i <= ultimo; i += 100) {
            if (i % 400 != 0) ANNO.removeItem(i);
         }
      }
      ANNO.insertItemAt("(nessuno)",0);
      ANNO.setSelectedItem(curr - (curr % passo));
      if (ANNO.getSelectedIndex() == -1) {
         ANNO.setSelectedIndex(0);
      }
   }
   
   /**
    * Ricalcola i mesi e gli anni possibili in base al GIORNO
    * selezionato.
    */
   public void cambiatoGiorno() {
      int indice = GIORNO.getSelectedIndex();
      if (indice == -1) { //l'utente ha scritto qualcosa non in [1..31]
         GIORNO.setSelectedItem(31);
         indice = 30;
      }
      int numMesi = MESE.getItemCount();
      if (feb29) {
         feb29 = false;
         riempiAnno();
      }
      if (numMesi == 12) {
         if (indice == 28 && MESE.getSelectedIndex() == 1 && !feb29) {
            feb29 = true;
            riempiAnno();
         }
         if (indice > 28) MESE.removeItemAt(1); //feb
      }
      if (numMesi > 8 && indice == 30) {
         MESE.removeItemAt(2); //apr
         MESE.removeItemAt(3); //giu
         MESE.removeItemAt(5); //set
         MESE.removeItemAt(6); //nov
      } else {
         if (numMesi < 12 && indice < 29) {
            MESE.insertItemAt("febbraio", 1);
         }
         if (numMesi < 8 && indice < 30) {
            int i = (indice == 29) ? 1 : 0;
            MESE.insertItemAt("aprile", 3-i);
            MESE.insertItemAt("giugno", 5-i);
            MESE.insertItemAt("settembre", 8-i);
            MESE.insertItemAt("novembre", 10-i);
         }
      }
   }
   
   /**
    * Controlla se la data inserita &egrave; un 29 febbraio e
    * modifica gli anni possibili di conseguenza.
    */
   public void cambiatoMese() {
      int indice = MESE.getSelectedIndex();
      if (feb29) {
         feb29 = false;
         riempiAnno();
      } else if (indice == 1 && MESE.getItemCount() == 12
              && GIORNO.getSelectedIndex() == 28) {
         feb29 = true;
         riempiAnno();
      }
   }
   
   /**
    * Restituisce la data corrispondente ai valori selezionati.
    * 
    * @return data selezionata, come oggetto LocalDate
    */
   public LocalDate getDate() {
      if (ANNO.getSelectedIndex() == 0) return null; //(nessuno)
      int year = Integer.parseInt(ANNO.getSelectedItem().toString());
      int month = MESE.getSelectedIndex() + 1;
      int numMesi = MESE.getItemCount();
      if (numMesi < 12) {
         if (month > 1) month++;
         if (numMesi == 7) {
            switch (month) {
               case 4: //mag
                  month++;
                  break;
               case 5: case 6: //lug, ago
                  month += 2;
                  break;
               case 7: //ott
                  month += 3;
                  break;
               case 8: //dic
                  month += 4;
               default:
            }
         }
      }
      return LocalDate.of(year, month, GIORNO.getSelectedIndex()+1);
   }
   
   /**
    * Imposta una data specifica nelle 3 JComboBox.
    * 
    * @param date data da impostare, come {@link LocalDate}
    */
   public void setDate(LocalDate date) {
      if (date == null) {
         // = "1 gennaio (nessuno)"
         GIORNO.setSelectedIndex(0);
         MESE.setSelectedIndex(0);
         ANNO.setSelectedIndex(0);
      } else{
         GIORNO.setSelectedIndex(date.getDayOfMonth()-1);
         MESE.setSelectedItem(date.getMonth().getDisplayName(TextStyle.FULL,
                 Locale.ITALIAN));
         ANNO.setSelectedItem(date.getYear());
      }
   }

   /**
    * Imposta una data specifica nelle 3 JComboBox.
    * 
    * @param date data da impostare, come {@link Date}
    */
   public void setDate(Date date) {
      if (date == null) {
         LocalDate d = null;
         setDate(d);
      }
      else setDate(date.toLocalDate());
   }
}
