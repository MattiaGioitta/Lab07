/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Poweroutages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;
	ObservableList nerc = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> choiceNerc;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnAnalysis;

    @FXML
    private TextArea txtResult;

    @FXML
    void doWorstCaseAnalysis(ActionEvent event) {
    	this.txtResult.clear();
    	
    	try{Nerc nercScelto = (Nerc) this.choiceNerc.getValue();
    	int years = Integer.parseInt(this.txtYears.getText());
    	int hours=Integer.parseInt(this.txtHours.getText());
    	List<Poweroutages> soluzione = this.model.getAnalysis(nercScelto.getValue(),years,hours);
    	if(soluzione==null) {
    		this.txtResult.appendText("Soluzione non trovata");
    		return;
    	}
    	int numPersone = this.model.calcolaPersone(soluzione);
    	this.txtResult.appendText("Numero persone coinvolte: "+numPersone+"\n");
    	for(Poweroutages p : soluzione) {
    		this.txtResult.appendText(p.toString()+"\n");
    	} 
    	}catch(NumberFormatException n) {
    		this.txtResult.appendText("Errore nel formato delle scelte");
    	}
    	

    }

    @FXML
    void initialize() {
        assert choiceNerc != null : "fx:id=\"choiceNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalysis != null : "fx:id=\"btnAnalysis\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    private void loadData() {
    	List<Nerc> l = this.model.getNercList();
    	this.nerc.addAll(l);
    	this.choiceNerc.setItems(nerc);
    }
	public void setModel(Model model) {
		this.model= model;
		this.loadData();
		
	}
}
