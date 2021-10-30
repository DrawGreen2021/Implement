package com.drawgreen.corpcollector.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.drawgreen.corpcollector.dto.PostDTO;

public interface PostDAO {
	boolean writePost(String title, String private_Writing, String writer, String writer_id, String private_Writer, String content);
	boolean updatePost(int board_number, String title, String private_Writing, String private_Writer, String content);
	int getRowCount(String boardName);
	ArrayList<PostDTO> getPostList(int page);
	ArrayList<PostDTO> getPostList(String keyword, int page);
	ArrayList<Integer> setBoardNums(String keyword, ArrayList<Integer> serialNums);
	int getAllRowCount();
	int getRowCount_byKeyword();
	HashMap<String, Object> getPost(int board_num);
	void updateHits(int board_num);
	boolean isAccessible(int board_num);
	boolean isWriter(String user_id, int board_id);
}
