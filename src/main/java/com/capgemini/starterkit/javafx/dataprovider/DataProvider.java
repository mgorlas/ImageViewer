package com.capgemini.starterkit.javafx.dataprovider;

import java.io.File;
import java.util.List;

import com.capgemini.starterkit.javafx.dataprovider.data.ImageVO;
import com.capgemini.starterkit.javafx.dataprovider.impl.DataProviderImpl;

/**
 * Provides data.
 *
 */
public interface DataProvider {

	/**
	 * Instance of this interface.
	 */
	DataProvider INSTANCE = new DataProviderImpl();

	List<ImageVO> searchImage(File directory);



}
