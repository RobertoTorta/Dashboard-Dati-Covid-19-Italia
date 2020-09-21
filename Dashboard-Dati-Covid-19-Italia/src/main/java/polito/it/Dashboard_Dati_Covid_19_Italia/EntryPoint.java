package polito.it.Dashboard_Dati_Covid_19_Italia;

import javafx.application.Application;
import static javafx.application.Application.launch;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import polito.it.Dashboard_Dati_Covid_19_Italia.model.Analisi;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ScenaAnalisi.fxml"));
    	Parent root = loader.load();
        Scene scene = new Scene(root);
         
        Analisi model = new Analisi();
        AnalisiDatiController controller = loader.getController();
        controller.setModel(model);
        
        stage.setTitle("Analisi Covid");
        stage.setScene(scene);
        stage.show();
    }
    

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
