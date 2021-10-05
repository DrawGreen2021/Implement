package com.drawgreen.corpcollector.dao;

import java.util.ArrayList;

public interface CorpDAO {
	public <E> ArrayList<E> getCorpList(String keyword);
	public <E> ArrayList<E> getCorpList();
}
