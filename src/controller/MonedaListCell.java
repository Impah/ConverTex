package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

import javafx.scene.control.ListCell;

public class MonedaListCell extends ListCell<String> {
	
    private final Map<String, Image> monedaImages;
    private final ImageView imageView = new ImageView();
    
    public MonedaListCell(Map<String, Image> monedaImages) {
        this.monedaImages = monedaImages;
        this.imageView.setFitWidth(35);
        this.imageView.setFitHeight(35);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null && !empty) {
            setText(item);
            imageView.setImage(monedaImages.get(item));
            setGraphic(imageView);
        } else {
            setText(null);
            setGraphic(null);
        }
    }
}
