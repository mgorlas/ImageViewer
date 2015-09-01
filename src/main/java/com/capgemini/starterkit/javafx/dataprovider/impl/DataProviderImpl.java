package com.capgemini.starterkit.javafx.dataprovider.impl;

import java.io.File;
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

	@Override
	public List<ImageVO> toFileVO(File[] listFiles) {
		List<ImageVO> listFileVO = new ArrayList<>();
		for (File file : listFiles) {
			listFileVO.add(new ImageVO(file));
		}
		return listFileVO;
	}

}
