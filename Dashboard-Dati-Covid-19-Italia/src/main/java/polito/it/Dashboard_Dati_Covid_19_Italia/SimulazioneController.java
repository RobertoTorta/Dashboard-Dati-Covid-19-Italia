package polito.it.Dashboard_Dati_Covid_19_Italia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import polito.it.Dashboard_Dati_Covid_19_Italia.model.Analisi;
import polito.it.Dashboard_Dati_Covid_19_Italia.model.Simulatore;
import javafx.scene.control.TextField;

public class SimulazioneController {

	Analisi model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAvviaSimulazione;

    @FXML
    private Slider sldProbabilitaContagioIniziale;

    @FXML
    private Slider sldProbabilitaDecessoIniziale;

    @FXML
    private Slider sldProbabilitaGuarigioneIniziale;

    @FXML
    private Slider sldGiorniDaAsintomatico;

    @FXML
    private Slider sldContagiIniziali;

    @FXML
    private CheckBox cBoxTrattamenti;

    @FXML
    private CheckBox cBoxOspedali;

    @FXML
    private CheckBox cboxDistanziamento;

    @FXML
    private Slider sldSettimane;

    @FXML
    private TextArea txtResult;

    @FXML
    private PieChart graficoResult;

    @FXML
    private Button bottoneTornaAnalisi;
    
    @FXML
    void simula(ActionEvent event) {
    	txtResult.clear();
    	double trattamentoPlasma=0.0;
    	double ospedaliSaturi=0.0;
    	double distanziamento=0.0;
    	int settimanaDiFine;
       	int contagiatiIniziali;
       	int giorniDaAsintomatico;
       	double probabilitaContagioIniziale;
       	double probabilitaDecessoIniziale;
       	double probabilitaGuarigioneIniziale;
       	    	
    	contagiatiIniziali = (int) this.sldContagiIniziali.getValue();
       	settimanaDiFine = (int) this.sldSettimane.getValue();
       	giorniDaAsintomatico = (int) this.sldGiorniDaAsintomatico.getValue();
       	probabilitaContagioIniziale = (double) this.sldProbabilitaContagioIniziale.getValue();
       	probabilitaDecessoIniziale = (double) this.sldProbabilitaDecessoIniziale.getValue();
       	probabilitaGuarigioneIniziale = (double) this.sldProbabilitaGuarigioneIniziale.getValue();
    	
       	if(this.cBoxTrattamenti.isSelected()) {
       		trattamentoPlasma=0.2;
       	}
       	if(this.cboxDistanziamento.isSelected()) {
       		distanziamento=0.1;
       	}
       	if(this.cBoxOspedali.isSelected()) {
       		ospedaliSaturi=0.2;
       	}
       	
       	Simulatore sim= new Simulatore(contagiatiIniziali, settimanaDiFine, distanziamento, trattamentoPlasma, ospedaliSaturi,
       									giorniDaAsintomatico,probabilitaContagioIniziale,probabilitaDecessoIniziale,
       									probabilitaGuarigioneIniziale);
    	sim.init();
    	sim.run();

    	this.txtResult.appendText("Sono state contagiate: "+sim.getTotaleContagiati());
    	this.txtResult.appendText("\nSono morte: "+sim.getTotaleMorti());
    	this.txtResult.appendText("\nSono guarite: "+sim.getTotaleGuariti());
    	
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Totale Casi = "+sim.getTotaleContagiati(), sim.getTotaleContagiati()),
				new PieChart.Data("Deceduti = "+sim.getTotaleMorti(), sim.getTotaleMorti()),
				new PieChart.Data("Guariti = "+sim.getTotaleGuariti(), sim.getTotaleGuariti()));
		graficoResult.setData(pieChartData);
    	

    }

    @FXML
    void tornaAnalisi(ActionEvent event) throws IOException {
    	Stage stage = null;
        BorderPane root = null;
        stage = (Stage) bottoneTornaAnalisi.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ScenaAnalisi.fxml"));
        root = loader.load();
        AnalisiDatiController controller=loader.getController();
        Analisi model=new Analisi(); 
        controller.setModel(model);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void initialize() {
        assert btnAvviaSimulazione != null : "fx:id=\"btnAvviaSimulazione\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert sldProbabilitaContagioIniziale != null : "fx:id=\"sldProbabilitaContagioIniziale\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert sldProbabilitaDecessoIniziale != null : "fx:id=\"sldProbabilitaDecessoIniziale\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert sldProbabilitaGuarigioneIniziale != null : "fx:id=\"sldProbabilitaGuarigioneIniziale\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert sldGiorniDaAsintomatico != null : "fx:id=\"sldGiorniDaAsintomatico\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert sldContagiIniziali != null : "fx:id=\"txtContagiIniziali\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert cBoxTrattamenti != null : "fx:id=\"cBoxTrattamenti\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert cBoxOspedali != null : "fx:id=\"cBoxOspedali\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert cboxDistanziamento != null : "fx:id=\"cboxDistanziamento\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert sldSettimane != null : "fx:id=\"sldSettimane\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert graficoResult != null : "fx:id=\"graficoResult\" was not injected: check your FXML file 'Scene2.fxml'.";
        assert bottoneTornaAnalisi != null : "fx:id=\"bottoneTornaAnalisi\" was not injected: check your FXML file 'Scene2.fxml'.";

    }
    
    public void setModel(Analisi model) {
		this.model=model; 
		
	}
}
