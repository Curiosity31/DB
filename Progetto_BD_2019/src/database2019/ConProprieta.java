/*
 * ConProprieta.java
 */
package database2019;

/**
 * Interfaccia generica che permette di impostare un valore per determinate
 * propriet&agrave;.
 */
public interface ConProprieta {
   /**
    * Imposta un valore per una data propriet&agrave;.
    * 
    * @param proprieta stringa con il nome della propriet&agrave;
    * @param valore stringa con il valore associato
    */
   public void setProprieta(String proprieta, String valore);
}
