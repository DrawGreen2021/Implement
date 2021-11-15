package com.drawgreen.corpcollector.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.drawgreen.corpcollector.dto.PostDTO;

public interface PostDAO {
	// 게시글 작성
	boolean writePost(String title, String private_Writing, String writer, String writer_id, String private_Writer, String content);
	// 게시글 수정
	boolean updatePost(int board_number, String title, String private_Writing, String private_Writer, String content);
	boolean deletePost(int board_number, String writer); // 게시글 삭제
	void resetBoardId(); // 게시글 번호 AUTO_INCREMENT 1부터 시작하도록 설정하는 프로시저 호출
	
	// -----------------------------------------------------------------------------
	
	int getRowCount(String boardName); // 전체 테이블(키워드x) 튜플 개수 가져오기
	int getAllRowCount(); // 전체 테이블 튜플 개수를 저장한 변수 값 반환(getter)
	void setAllRowCount(int allRowCount); // 전체 테이블 튜플 개수 설정
	ArrayList<PostDTO> getPostList(int page); // 키워드 없을 때 게시글 리스트 가져오기
	
	// -----------------------------------------------------------------------------
	
	// 키워드 있을 때 게시글 리스트 가져오기
	ArrayList<PostDTO> getPostList(String keyword, int page);
	// 검색 키워드가 존재하는 행의 게시글 번호 알아오기
	ArrayList<Integer> setBoardNums(String keyword, ArrayList<Integer> serialNums);
	int getRowCount_byKeyword(); // 키워드가 있을 때 전체 튜플 개수 가져오기
	
	// -----------------------------------------------------------------------------
	
	HashMap<String, Object> getPost(int board_num); // 게시글을 볼 때, 해당 레코드 정보 반환
	void updateHits(int board_num); // 조회수 1 증가
	boolean isAccessible(int board_num); // 글 비공개 설정 확인
	// 현재 사용자가 접근하려는 게시글의 작성자인지 확인
	boolean isWriter(String user_id, int board_id); 
	
	// -----------------------------------------------------------------------------
	
	// 자원 해제
	void closing();
}
