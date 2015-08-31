package com.capgemini.starterkit.javafx.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.capgemini.starterkit.javafx.model.ImageSearch;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Controller for the person search screen.
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

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	Button searchButton;

	@FXML
	TableView<File> tableImage;

	@FXML
	TableColumn<File, String> columnNameImage;

	private final ImageSearch model = new ImageSearch();

	private File directory;

	@FXML
	ImageView imageView;

	@FXML
	private void initialize() {
		initializeResultTable();
		columnNameImage.textProperty().bindBidirectional(model.nameProperty());
		tableImage.itemsProperty().bind(model.resultProperty());
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		this.directory = directoryChooser.showDialog(new Stage());
		if (directory != null) {
			FilenameFilter fileNameFilter = new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					if (name.lastIndexOf('.') > 0) {
						int lastIndex = name.lastIndexOf('.');
						String str = name.substring(lastIndex).toLowerCase();
						if (isImage(str)) {
							return true;
						}
					}
					return false;
				}
			};
			model.setResult(new ArrayList<File>(Arrays.asList(directory.listFiles(fileNameFilter))));
			tableImage.getSortOrder().clear();
		}

	}

	private boolean isImage(String str) {
		return str.equals(".jpg") || str.equals(".png") || str.equals(".bmp") || str.equals(".tiff")
				|| str.equals(".swf") || str.equals(".cdr") || str.equals(".gif") || str.equals(".jpeg")
				|| str.equals(".tif") || str.equals(".fmw");
	}

	private void initializeResultTable() {
		columnNameImage.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));

		tableImage.setPlaceholder(new Label(resources.getString("table.emptyText")));

		tableImage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<File>() {

			@Override
			public void changed(ObservableValue<? extends File> observable, File oldValue, File newValue) {
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
