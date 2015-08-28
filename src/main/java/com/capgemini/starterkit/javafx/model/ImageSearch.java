package com.capgemini.starterkit.javafx.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Data displayed image on screen.
 *
 * @author Marzena
 */
public class ImageSearch {

	private final StringProperty name = new SimpleStringProperty();
	private final ListProperty<File> result = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));


	public final String getName() {
		return name.get();
	}

	public final void setName(String value) {
		name.set(value);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public final List<File> getResult() {
		return result.get();
	}

	public final void setResult(List<File> value) {
		result.setAll(value);
	}

	public ListProperty<File> resultProperty() {
		return result;
	}
	@Override
	public String toString() {
		return "Image Search [name=" + name + ", result=" + result + "]";
	}
}
