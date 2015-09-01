package com.capgemini.starterkit.javafx.dataprovider.data;

import java.io.File;
import java.time.LocalDate;

import javafx.scene.image.Image;

/**
 * Image data.
 */
public class ImageVO {

	private File file;
	private String name;

	public ImageVO(File file) {
		this.file = file;
		this.name = file.getName();
	}

	public String getName(){
		return name;
	}
	public Image getImage(){
		return new Image(toString());
	}

	@Override
	public String toString() {
		return "file:" + file.toString();
	}

}
