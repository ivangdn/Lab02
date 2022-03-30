/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.alien.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Dictionary dictionary = new Dictionary();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="btnTranslate"
    private Button btnTranslate; // Value injected by FXMLLoader

    @FXML // fx:id="lblTesto"
    private TextField lblTesto; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doReset(ActionEvent event) {
    	lblTesto.clear();
    	txtResult.clear();
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	txtResult.clear();
    	
    	String riga = lblTesto.getText().toLowerCase();
    	
    	// Controllo sull'input
    	if(riga==null || riga.equals("")) {
    		txtResult.setText("Inserire una o due parole.");
    		return;
    	}
    	
    	StringTokenizer st = new StringTokenizer(riga, " ");
    	if(!st.hasMoreTokens() || st.countTokens()>2) {
    		txtResult.setText("Inserire una o due parole.");
    		return;
    	}
    	
    	// Estraggo la prima parola
    	String alienWord = st.nextToken();
    	if(st.hasMoreTokens()) {
    		// Devo inserire parola e traduzione nel dizionario
    		// Estraggo la seconda parola
    		String translation = st.nextToken();
    		
    		if(!alienWord.matches("[a-zA-Z]*") && !translation.matches("[a-zA-Z]*")) {
    			txtResult.setText("Inserire solo caratteri alfabetici.");
        		return;
    		}
    		
    		// Aggiungo parola aliena e traduzione nel dizionario
    		this.dictionary.addWord(alienWord, translation);
    		txtResult.setText("La parola \""+alienWord+"\" con traduzione \""+translation+"\" è stata inserita nel dizionario.");
    		
    	} else {
    		// Devo tradurre la parola aliena
    		if(!alienWord.matches("[a-zA-Z]*")) {
    			txtResult.setText("Inserire solo caratteri alfabetici.");
        		return;
    		}
    		
    		String translation = this.dictionary.translate(alienWord);
    		if(translation!=null) {
    			txtResult.setText(translation);
    		} else {
    			txtResult.setText("La parola cercata non esiste nel dizionario.");
    			return;
    		}
    		
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTesto != null : "fx:id=\"lblTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
