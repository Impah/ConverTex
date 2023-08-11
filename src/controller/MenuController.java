package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



public class MenuController {

	
	
	@FXML
    private AnchorPane mainPane;
	@FXML
    private void irConversorMonedas(ActionEvent event) {
        // Cargar la interfaz de conversión de monedas
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/conversor_monedas.fxml"));
            Parent root = loader.load();

            // Obtener el escenario actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cambiar el título del escenario
            stage.setTitle("Conversor de Monedas");

            // Cambiar la escena del escenario
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@FXML
    private void irConversorTemperatura(ActionEvent event) {
        // Cargar la interfaz de conversión de monedas
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/conversor_temperatura.fxml"));
            Parent root = loader.load();

            // Obtener el escenario actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cambiar el título del escenario
            stage.setTitle("Conversor de Temperatura");

            // Cambiar la escena del escenario
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
