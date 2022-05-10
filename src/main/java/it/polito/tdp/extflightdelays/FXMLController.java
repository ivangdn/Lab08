/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Rotta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	txtResult.clear();
    	
    	int distanzaMin;
    	try {
    		distanzaMin = Integer.parseInt(distanzaMinima.getText());
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire una distanza minima numerica");
    		return;
    	}
    	
    	this.model.creaGrafo(distanzaMin);
    	int numVertici = this.model.numeroVertici();
    	int numArchi = this.model.numeroArchi();
    	List<Rotta> rotte = this.model.getRotte();
    	
    	txtResult.appendText("Numero di vertici del grafo: "+numVertici+"\n");
    	txtResult.appendText("Numero di archi del grafo: "+numArchi+"\n");
    	txtResult.appendText("Rotte calcolate:\n");
    	for(Rotta rotta : rotte) {
    		txtResult.appendText(rotta+"\n");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
