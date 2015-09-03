package com.capgemini.starterkit.javafx.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.capgemini.starterkit.javafx.dataprovider.DataProvider;
import com.capgemini.starterkit.javafx.dataprovider.data.ImageVO;
import com.capgemini.starterkit.javafx.model.ImageSearch;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Controller for the image search screen.
 * <p>
 * The JavaFX runtime will inject corresponding objects in the @FXML annotated
 * fields. The @FXML annotated methods will be called by JavaFX runtime at
 * specific points in time.
 * </p>
 *
 * @author Marzena
 */
public class ImageSearchController {

	private static final Logger LOG = Logger.getLogger(ImageSearchController.class);
	private final DataProvider dataProvider = DataProvider.INSTANCE;
	private final ImageSearch model = new ImageSearch();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	Button searchButton;

	@FXML
	ImageView imageView;

	@FXML
	ListView<ImageVO> imageListView;

	@FXML
	private void initialize() {
		initializeResultTable();
		imageListView.itemsProperty().bind(model.resultProperty());
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		/*
		 * REV: okno wyboru katalogu powinno byc modalne w stosunku do glownego okna
		 * showDialog(primaryStage)
		 */
		File directory = directoryChooser.showDialog(new Stage());
		model.setResult(dataProvider.searchImage(directory));
	}

	private void initializeResultTable() {
		imageListView.setPlaceholder(new Label(resources.getString("table.emptyText")));
		imageListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageVO>() {

			@Override
			public void changed(ObservableValue<? extends ImageVO> observable, ImageVO oldValue, ImageVO newValue) {
				LOG.debug(newValue + " selected");

				Task<Void> imageTask = new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						Image img = newValue.getImage();
						LOG.debug(img);
						imageView.setImage(img);
						imageView.autosize();
						return null;
					}
				};
				new Thread(imageTask).start();
			}
		});
	}

}
