package main.java.com.capgemini.starterkit.javafx.dataprovider.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.capgemini.starterkit.javafx.dataprovider.DataProvider;
import main.java.com.capgemini.starterkit.javafx.dataprovider.data.ImageVO;

/**
 * Provides data. Data is stored from file dictionary.
 */
public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);

	@Override
	public List<ImageVO> searchImage(File directory) {
		List<ImageVO> listFileVO = new ArrayList<>();
		LOG.debug("click searchImage()");

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
			for (File file : directory.listFiles(fileNameFilter)) {
				listFileVO.add(new ImageVO(file));
			}
		}

		return listFileVO;
	}

	/**
	 * Method checks whether the specified string is an extension for the image
	 * @param str
	 * @return boolean
	 */
	private boolean isImage(String str) {
		return str.equals(".jpg") || str.equals(".png") || str.equals(".bmp") || str.equals(".tiff")
				|| str.equals(".gif") || str.equals(".jpeg") || str.equals(".tif");
	}
}
