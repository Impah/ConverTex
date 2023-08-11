package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.json.JSONObject;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConversorMonedasController implements Initializable{

    @FXML
    private TextField cantidad;

    @FXML
    private Button convertirButton;
    
    @FXML
    private AnchorPane footer;

    @FXML
    private AnchorPane header;

    @FXML
    private ImageView imageSwap;

    @FXML
    private TextField monedaConvertida;

    @FXML
    private ComboBox<String> monedaDestino;

    @FXML
    private ComboBox<String> monedaOrigen;

    @FXML
    private Text resultadoLabel;

    @FXML
    private Text resultadoText;
    
    @FXML
    private Text title;
    
    private Map<String, Double> tiposDeCambio;
    

    private void obtenerTiposDeCambio() {
        try {
            URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=96f71e54258a40ed9fcc70edc79515be");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("Ocurrió un error: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }

                scanner.close();
                
                JSONObject json = new JSONObject(informationString.toString());
                System.out.println(json);
                JSONObject rates = json.getJSONObject("rates");

                tiposDeCambio = new HashMap<>();
                tiposDeCambio.put("USD", 1.0); // Tipo de cambio base
                tiposDeCambio.put("EUR", rates.getDouble("EUR"));
                tiposDeCambio.put("GBP", rates.getDouble("GBP"));
                tiposDeCambio.put("ARS", rates.getDouble("ARS"));
                tiposDeCambio.put("KRW", rates.getDouble("KRW"));
                tiposDeCambio.put("JPY", rates.getDouble("JPY"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   

    @FXML
    void convertirMoneda(ActionEvent event) {

    	String origen = monedaOrigen.getValue();
        String destino = monedaDestino.getValue();
        double cantidad = Double.parseDouble(this.cantidad.getText());
        double total = 0;

        // Lógica de conversión aquí
        double tipoDeCambioOrigen = tiposDeCambio.get(origen);
        double tipoDeCambioDestino = tiposDeCambio.get(destino);

        total = cantidad * (tipoDeCambioDestino / tipoDeCambioOrigen);

        monedaConvertida.setText(monedaDestino.getValue()+" $" + total);
    	 
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
            stage.setTitle("ConverT");

            // Cambiar la escena del escenario
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		obtenerTiposDeCambio();
		
		Map<String, Image> monedaImages = new HashMap<>();
	    monedaImages.put("USD", new Image("/image/estados-unidos-de-america.png")); // Ruta a la imagen
	    monedaImages.put("EUR", new Image("/image/union-europea.png"));
	    monedaImages.put("GBP", new Image("/image/reino-unido.png"));
	    monedaImages.put("ARS", new Image("/image/argentina.png"));
	    monedaImages.put("KRW", new Image("/image/corea-del-sur.png"));
	    monedaImages.put("JPY", new Image("/image/japon.png"));
	    
	    
	    monedaOrigen.setCellFactory(param -> new MonedaListCell(monedaImages));
	    monedaOrigen.setButtonCell(new MonedaListCell(monedaImages));
	    
	    monedaDestino.setCellFactory(param -> new MonedaListCell(monedaImages));
	    monedaDestino.setButtonCell(new MonedaListCell(monedaImages));
	    
		monedaOrigen.getItems().addAll("USD", "EUR", "GBP", "ARS", "KRW", "JPY");
        monedaOrigen.setValue("USD");
        
        monedaDestino.getItems().addAll("USD", "EUR", "GBP", "ARS", "KRW", "JPY");
        monedaDestino.setValue("ARS");
		
	}

}
