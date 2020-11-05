package main.java.com.capgemini.starterkit.javafx.dataprovider;

import java.io.File;
import java.util.List;

import main.java.com.capgemini.starterkit.javafx.dataprovider.data.ImageVO;
import main.java.com.capgemini.starterkit.javafx.dataprovider.impl.DataProviderImpl;

/**
 * Provides data.
 *
 */
public interface DataProvider {

	/**
	 * Instance of this interface.
	 */
	DataProvider INSTANCE = new DataProviderImpl();


	/**
	 * Method searches for images in a given folder
	 * @param directory
	 * @return List of Object ImageVO
	 */
	List<ImageVO> searchImage(File directory);



}
