package main.java.com.capgemini.starterkit.javafx.dataprovider.data;

import java.io.File;

import javafx.scene.image.Image;

/**
 * Image data.
 */
public class ImageVO {

	private File file;

	public ImageVO(File file) {
		this.file = file;
	}

	public String getName() {
		return file.getName();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return file.getName();
	}

}
