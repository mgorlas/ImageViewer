package com.capgemini.starterkit.javafx.dataprovider.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.starterkit.javafx.dataprovider.DataProvider;
import com.capgemini.starterkit.javafx.dataprovider.data.ImageVO;

/**
 * Provides data. Data is stored locally in class.
 */
public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);
	/*
	 * REV: wynik wyszukiwania nie powinien byc przechowywany jako pole w klasie
	 */
	private List<ImageVO> listFileVO = new ArrayList<>();

	@Override
	public List<ImageVO> searchImage(File directory) {
		LOG.debug("click searchImage()");
		listFileVO.clear();

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

	private boolean isImage(String str) {
		/*
		 * REV: czy JavaFX jest w stanie wyswietlic pliki SWF, CDR i FMW?
		 */
		return str.equals(".jpg") || str.equals(".png") || str.equals(".bmp") || str.equals(".tiff")
				|| str.equals(".swf") || str.equals(".cdr") || str.equals(".gif") || str.equals(".jpeg")
				|| str.equals(".tif") || str.equals(".fmw");
	}
}
