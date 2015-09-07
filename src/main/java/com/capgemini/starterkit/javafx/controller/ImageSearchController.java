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
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

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
	AnchorPane panel;

	/**
	 * The JavaFX runtime calls this method after the FXML file loaded.
	 * <p>
	 * The @FXML annotated fields are initialized at this point.
	 * </p>
	 * <p>
	 * NOTE: The method name must be {@code initialize}.
	 * </p>
	 */
	@FXML
	private void initialize() {
		initializeResultTable();
		/*
		 * Bind controls properties to model properties.
		 */
		imageListView.itemsProperty().bind(model.resultProperty());
	}

	/**
	 * The method used to select a folder and load the images to the list
	 */
	@FXML
	public void searchButtonAction(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File directory = directoryChooser.showDialog(panel.getScene().getWindow());
		model.setResult(dataProvider.searchImage(directory));
	}


	/**
	 * The method used to initialize the table
	 */
	private void initializeResultTable() {
		imageListView.setPlaceholder(new Label(resources.getString("table.emptyText")));
		/*
		 * When table row gets selected display given image.
		 */
		imageListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageVO>() {

			@Override
			public void changed(ObservableValue<? extends ImageVO> observable, ImageVO oldValue, ImageVO newValue) {
				LOG.debug(newValue + " selected");

				Task<Void> imageTask = new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						Image img = new Image("file:" + newValue.toString());
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
