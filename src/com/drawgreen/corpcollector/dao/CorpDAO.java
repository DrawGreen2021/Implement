package com.drawgreen.corpcollector.dao;

import java.util.ArrayList;

import com.drawgreen.corpcollector.dto.CorpDTO;

public interface CorpDAO {
	int getAllRowCount();
	void setAllRowCount(int allRowCount);
	ArrayList<? extends CorpDTO> getCorpList(int page);
	int getRowCount(String corpType);
	ArrayList<? extends CorpDTO> getCorpList(String keyword, int page);
	ArrayList<Integer> getSerialNumQuery(String keyword, ArrayList<Integer> serialNums);
	int getRowCount_byKeyword();
	ArrayList<Integer> getSerialNums();
}
