package com.drawgreen.corpcollector.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.drawgreen.corpcollector.dto.CorpDTO;
import com.drawgreen.corpcollector.dto.RecentSearchDTO;

public interface CorpDAO {
	int getRowCount(String corpType); // 전체 테이블(키워드x) 튜플 개수 가져오기
	int getAllRowCount(); // 전체 테이블 튜플 개수를 저장한 변수 값 반환(getter)
	void setAllRowCount(int allRowCount); // 전체 테이블 튜플 개수 설정
	ArrayList<? extends CorpDTO> getCorpList(int page); // 키워드 없을 때 기업리스트 가져오기
	
	// -----------------------------------------------------------------------------
	
	// 키워드 있을 때 기업리스트 가져오기
	ArrayList<? extends CorpDTO> getCorpList(String keyword, int page); 
	// 검색 키워드가 존재하는 행의 연번 알아오기
	ArrayList<Integer> getSerialNumQuery(String keyword, ArrayList<Integer> serialNums);
	int getRowCount_byKeyword(); // 키워드가 있을 때 전체 튜플 개수 가져오기
	ArrayList<Integer> getSerialNums(); // 키워드가 있을 때 저장한 연번 리스트 반환(getter)
	
	// -----------------------------------------------------------------------------
	
	// 상세 기업 페이지에서 관련 정보를 가져올 때, 해당 레코드 정보 반환
	LinkedHashMap<String, Object> getInfo(int serial_num);
	// 최근 검색 기업과 연관된 정보 가져오기
	ArrayList<RecentSearchDTO> getRecentRecords(String user_id);
}
