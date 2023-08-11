package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConversorTemperaturaController implements Initializable {

	@FXML
    private Text byImpah;

    @FXML
    private TextField cantidad;

    @FXML
    private Text cantidadText;

    @FXML
    private Button convertirButton;

    @FXML
    private ImageView logoTemperatura;

    @FXML
    private Text resultadoText;

    @FXML
    private TextField temperaturaConvertida;

    @FXML
    private Text temperaturaDestino;

    @FXML
    private Text temperaturaEntrada;

    @FXML
    private ComboBox<String> temperaturaFinal;

    @FXML
    private ComboBox<String> temperaturaOrigen;

    @FXML
    private Text title;

    private double convertirTemperatura(double temperatura, String unidadOrigen, String unidadDestino) {
        if (unidadOrigen.equals(unidadDestino)) {
            return temperatura;
        }

        if (unidadOrigen.equals("Celsius")) {
            if (unidadDestino.equals("Fahrenheit")) {
                return (temperatura * 9 / 5) + 32;
            } else if (unidadDestino.equals("Kelvin")) {
                return temperatura + 273.15;
            }
        } else if (unidadOrigen.equals("Fahrenheit")) {
            if (unidadDestino.equals("Celsius")) {
                return (temperatura - 32) * 5 / 9;
            } else if (unidadDestino.equals("Kelvin")) {
                return (temperatura + 459.67) * 5 / 9;
            }
        } else if (unidadOrigen.equals("Kelvin")) {
            if (unidadDestino.equals("Celsius")) {
                return temperatura - 273.15;
            } else if (unidadDestino.equals("Fahrenheit")) {
                return (temperatura * 9 / 5) - 459.67;
            }
        }

        return temperatura;
    }

    @FXML
    void convertirTemperatura(ActionEvent event) {
        String unidadOrigen = temperaturaOrigen.getValue();
        String unidadDestino = temperaturaFinal.getValue();
        double temperatura = Double.parseDouble(this.cantidad.getText());

        double temperaturaConvertidaValor = convertirTemperatura(temperatura, unidadOrigen, unidadDestino);
        temperaturaConvertida.setText(unidadDestino + ": " + temperaturaConvertidaValor);
    }
    
    @FXML
    private void volverHome(ActionEvent event) {
        // Cargar la interfaz de conversión de monedas
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
            Parent root = loader.load();

            // Obtener el escenario actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Cambiar el título del escenario
            stage.setTitle("ConverTex");

            // Cambiar la escena del escenario
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Llenar los ComboBox con las unidades de temperatura
        temperaturaOrigen.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        temperaturaOrigen.setValue("Celsius");

        temperaturaFinal.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        temperaturaFinal.setValue("Fahrenheit"); // Cambiar a la unidad de destino por defecto
    }
}
