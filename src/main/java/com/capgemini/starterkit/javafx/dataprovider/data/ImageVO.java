package main.java.com.capgemini.starterkit.javafx.dataprovider.data;

import java.io.File;

import javafx.scene.image.Image;

/**
 * Image data.
 */
public class ImageVO {

	private File file;
	/*
	 * REV: File.getName() zwraca nazwe pliku
	 */
	private String name;

	public ImageVO(File file) {
		this.file = file;
	}

	public String getName() {
		return file.getName();
	}

	public File getFile() {
		return file;
	}

	public Image getImage(){
		/*
		 * REV: klasa Image nalezy do wartswy GUI, klasa ImageVO do warstwy danych
		 * warstwa danych nie ma pojecia o warstwie GUI
		 */
		return new Image("file:" + file.toString());
  }
  
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return file.getName();
	}

}
