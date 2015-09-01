package com.capgemini.starterkit.javafx.dataprovider.data;

import java.io.File;

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
	public void setName(String name){
		this.name = name;
	}
	public File getFile(){
		return file;
	}
	public void setFile(File file){
		this.file = file;
	}

	public Image getImage(){
		return new Image("file:" + file.toString());
	}

	@Override
	public String toString() {
		return name;
	}

}
