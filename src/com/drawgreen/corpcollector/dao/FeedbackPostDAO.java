package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.drawgreen.corpcollector.dto.PostDTO;
import java.sql.CallableStatement;

public class FeedbackPostDAO implements PostDAO{
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private CallableStatement callableStatement = null;
	private ResultSet resultSet = null;
	private int allRowCount;
	// 키워드 검색 결과에 해당하는 연번을 저장할 리스트
	private ArrayList<Integer> boardNums;
	// 키워드 값을 저장할 변수
	private String beforeKeyword;
	
	private FeedbackPostDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/DrawGreen");
			allRowCount = getRowCount("고객후기");
			boardNums = new ArrayList<Integer>();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class InnerInstance_FeedbackDAO {
		private static final FeedbackPostDAO feedbackPostDAO = new FeedbackPostDAO();
	}
	
	public static FeedbackPostDAO getInstance() {
		return InnerInstance_FeedbackDAO.feedbackPostDAO;
	}
	
	// 고객후기 작성
	@Override
	public boolean writePost(String title, String private_Writing, String writer, String writer_id,
			String private_Writer, String content) {
		boolean writeOk = false;
		
		// private Writing이면 쓴 사람과 관리자만 읽을 수 있음
		boolean is_private_writing = false;
		if (private_Writing != null)
			is_private_writing = true;
		
		// private Writer라면 작성자 비공개
		boolean is_private_writer = false;
		if (private_Writer != null)
			is_private_writer = true;
		
		String query = "INSERT INTO 고객후기(id, title, content, 등록일시, 작성자_비공개, 글_비공개) "
					+"VALUES(?,?,?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, writer_id);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, content);
			preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setBoolean(5, is_private_writer);
			preparedStatement.setBoolean(6, is_private_writing);
			
			int resultNum = preparedStatement.executeUpdate();
			if (resultNum > 0)
				writeOk = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return writeOk;
	}
	
	// 고객후기 수정
	@Override
	public boolean updatePost(int board_number, String title, String private_Writing, String private_Writer,
			String content) {
		boolean updateOk = false;
		
		// private Writing이면 쓴 사람과 관리자만 읽을 수 있음
		boolean is_private_writing = false;
		if (private_Writing != null)
			is_private_writing = true;
		
		// private Writer라면 작성자 비공개
		boolean is_private_writer = false;
		if (private_Writer != null)
			is_private_writer = true;
		
		String query = "UPDATE 고객후기 SET title=?, content=?, 작성자_비공개=?, 글_비공개=? WHERE board_id = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, content);
			preparedStatement.setBoolean(3, is_private_writer);
			preparedStatement.setBoolean(4, is_private_writing);
			preparedStatement.setInt(5, board_number);
			
			int resultNum = preparedStatement.executeUpdate();
			if (resultNum > 0)
				updateOk = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return updateOk;
	}
	
	// 고객후기 삭제
	@Override
	public boolean deletePost(int board_number, String writer) {
		// TODO Auto-generated method stub
		
		boolean deleteOk = false;
		String query = "DELETE FROM 고객후기 WHERE board_id = ? AND id = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, board_number);
			preparedStatement.setString(2, writer);
			
			int resultNum = preparedStatement.executeUpdate();
			if (resultNum > 0)
				deleteOk = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return deleteOk;
	}
	
	// 게시글 번호 AUTO_INCREMENT 1부터 시작하도록 설정하는 프로시저 호출
	@Override
	public void resetBoardId() {
		// TODO Auto-generated method stub
		String query = "{ CALL reset_feedback_id() }";
		
		try {
			connection = dataSource.getConnection();
			callableStatement = (CallableStatement) connection.prepareCall(query);
			callableStatement.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
	}
	
	// 전체 테이블 튜플 개수 가져오기
	@Override
	public int getRowCount(String boardName) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		String query = "SELECT count(*) FROM " + boardName;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			rowCount = resultSet.getInt(1);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}

		return rowCount;
	}
	
	// 키워드가 없을 때 공지사항 글 목록 얻어오기
	@Override
	public ArrayList<PostDTO> getPostList(int page) {
		ArrayList<PostDTO> postList = new ArrayList<PostDTO>();
		String query = "SELECT f.*, m.nickname FROM 고객후기 f, members m WHERE m.id = f.id "
				+ "ORDER BY f.등록일시 DESC LIMIT ?, ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, (page-1)*pageRowCount);
			preparedStatement.setInt(2, pageRowCount);
			
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next())
				return null;
			do {
				int board_number = resultSet.getInt("board_id");
				String writer_id = resultSet.getString("id");
				String writer_name = resultSet.getString("nickname");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				Timestamp registration_date = resultSet.getTimestamp("등록일시");
				int hits = resultSet.getInt("조회수");
				boolean is_private_writing = resultSet.getBoolean("글_비공개");
				boolean is_private_writer = resultSet.getBoolean("작성자_비공개");
				
				PostDTO post = new PostDTO(board_number, writer_id, writer_name, title, content, registration_date, hits, is_private_writing, is_private_writer);
				postList.add(post);
			} while (resultSet.next());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return postList;
	}
	
	// 키워드가 있을 때 고객후기 글 목록 얻어오기
	@Override
	public ArrayList<PostDTO> getPostList(String keyword, int page) {
		ArrayList<PostDTO> postList = new ArrayList<PostDTO>();
		
		// 검색어가 달라졌으면 해당 게시글 번호 다시 select
		if (beforeKeyword == null) {
			beforeKeyword = keyword;
			boardNums = setBoardNums(keyword, boardNums);
		} else if (!beforeKeyword.equals(keyword)) {
			boardNums.clear();
			boardNums = setBoardNums(keyword, boardNums);
		}

		if (boardNums.size() == 0) {
			return null;
		}
		
		String query = "SELECT f.*, m.nickname FROM 고객후기 f, members m " 
				+ "WHERE m.id = f.id AND board_id IN(";
		// 0~9, 10~19 ...
		int startNum = page * pageRowCount - pageRowCount;
		int lastNum = page * pageRowCount;

		StringBuilder builder = new StringBuilder(query);

		builder.append(boardNums.get(startNum));
		for (int i = startNum+1; i < lastNum && i < boardNums.size(); i++) {
			builder.append(",");
			builder.append(boardNums.get(i));	
		}
		builder.append(") ORDER BY f.등록일시 DESC");
		query = builder.toString();
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);

			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int board_number = resultSet.getInt("board_id");
				String writer_id = resultSet.getString("id");
				String writer_name = resultSet.getString("nickname");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				Timestamp registration_date = resultSet.getTimestamp("등록일시");
				int hits = resultSet.getInt("조회수");
				boolean is_private_writing = resultSet.getBoolean("글_비공개");
				boolean is_private_writer = resultSet.getBoolean("작성자_비공개");
				
				PostDTO post = new PostDTO(board_number, writer_id, writer_name, title, content, registration_date, hits, is_private_writing, is_private_writer);
				postList.add(post);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		
		return postList;
	}
	
	// 검색 키워드가 존재하는 행의 게시글 번호 알아오기
	@Override
	public ArrayList<Integer> setBoardNums(String keyword, ArrayList<Integer> boardNums) {
		// TODO Auto-generated method stub
		// 키워드 공백으로 분리
		StringTokenizer tokenizer = new StringTokenizer(keyword);

		String query = "SELECT board_id FROM feedback_view " + "WHERE text REGEXP('";
		StringBuffer buffer = new StringBuffer(query);
		buffer.append(tokenizer.nextToken() + "'");
		while (tokenizer.hasMoreTokens()) {
			buffer.append("|'");
			buffer.append(tokenizer.nextToken() + "'");
		}
		buffer.append(")");
		query = buffer.toString();

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				boardNums.add(resultSet.getInt("board_id"));

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closing();
		}

		return boardNums;
	}

	@Override
	public int getAllRowCount() {
		// TODO Auto-generated method stub
		return allRowCount;
	}
	
	public void setAllRowCount(int allRowCount) {
		this.allRowCount = allRowCount;
	}

	@Override
	public int getRowCount_byKeyword() {
		// TODO Auto-generated method stub
		return boardNums.size();
	}
	
	// 게시글을 볼 때, 해당 레코드 정보 반환
	@Override
	public HashMap<String, Object> getPost(int board_num) {
		// TODO Auto-generated method stub
		HashMap<String, Object> feedbackPost = new HashMap<String, Object>();
		
		String query = "SELECT f.*, m.nickname FROM 고객후기 f, members m WHERE board_id = ? AND m.id = f.id";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, board_num);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			feedbackPost.put("board_number", resultSet.getInt("board_id"));
			feedbackPost.put("writer_id", resultSet.getString("id"));
			feedbackPost.put("writer_name", resultSet.getString("nickname"));
			feedbackPost.put("title", resultSet.getString("title"));
			feedbackPost.put("content", resultSet.getString("content"));
			feedbackPost.put("registration_date", resultSet.getString("등록일시"));
			feedbackPost.put("hits", resultSet.getInt("조회수"));
			feedbackPost.put("is_private_writing", resultSet.getBoolean("글_비공개"));
			feedbackPost.put("is_private_writer", resultSet.getBoolean("작성자_비공개"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return feedbackPost;
	}
	
	// 조회수 1 증가
	@Override
	public void updateHits(int board_num) {
		String query = "UPDATE 고객후기 SET 조회수 = 조회수+1 WHERE board_id = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, board_num);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
	}
	
	// 글 비공개 설정 확인
	@Override
	public boolean isAccessible(int board_num) {
		String query = "SELECT IF(글_비공개=0, 'true', 'false') AS result FROM 고객후기 "
				+ "WHERE board_id = ?";
		
		boolean isAccessible = true;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, board_num);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String result = resultSet.getString(1);
			isAccessible = Boolean.parseBoolean(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return isAccessible;
	}
	
	// 현재 사용자가 접근하려는 게시글의 작성자인지 확인
	@Override
	public boolean isWriter(String user_id, int board_id) {
		boolean isWriter = false;
		String query = "SELECT IF(count(*)=1, 'true', 'false') AS result FROM 고객후기 "
				+ "WHERE id = ? AND board_id = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, board_id);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			isWriter = Boolean.parseBoolean(resultSet.getString(1));

		} catch (Exception e) {
			// TODO: handle exception
			isWriter = false;
		} finally {
			closing();
		}
		
		return isWriter;
	}
	
	// 마이페이지 - 내가 쓴 글에 표시할 고객후기 게시글 목록 가져오기
	public ArrayList<PostDTO> getPostList_forMyPage(String user_id, int page) {
		// TODO Auto-generated method stub
		
		ArrayList<PostDTO> postList = new ArrayList<PostDTO>();
		
		String query = "SELECT f.*, m.nickname FROM 고객후기 f, members m "
				+ "WHERE f.id = ? AND f.id = m.id ORDER BY f.등록일시 DESC LIMIT ?, ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, (page-1)*pageRowCount);
			preparedStatement.setInt(3, pageRowCount);
			
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next())
				return null;
			do {
				int board_number = resultSet.getInt("board_id");
				String writer_id = resultSet.getString("id");
				String writer_name = resultSet.getString("nickname");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				Timestamp registration_date = resultSet.getTimestamp("등록일시");
				int hits = resultSet.getInt("조회수");
				boolean is_private_writing = resultSet.getBoolean("글_비공개");
				boolean is_private_writer = resultSet.getBoolean("작성자_비공개");
				
				PostDTO post = new PostDTO(board_number, writer_id, writer_name, title, content, registration_date, hits, is_private_writing, is_private_writer);
				postList.add(post);
			} while (resultSet.next());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return postList;
	}
	
	// 사용자가 작성한 고객후기 개수 가져오기
	public int getRowCount_forMyPage(String user_id) {
		// TODO Auto-generated method stub
		String query = "SELECT count(*) FROM 고객후기 WHERE id = ?";
		int rowcount = 0;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			rowcount = resultSet.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return rowcount;
	}
	
	// 마이페이지 - 내가 쓴 글에서 선택한 게시글 모두 삭제
	public void deleteMyFeedback(int[] board_numbers) {
		String query = "DELETE FROM 고객후기 WHERE board_id IN(";
		StringBuilder builder = new StringBuilder(query);
		
		builder.append(board_numbers[0]);
		for (int i = 1; i < board_numbers.length; i++) {
			builder.append(","+board_numbers[i]);
		}
		builder.append(")");
		query = builder.toString();
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
	}

	@Override
	public void closing() {
		try {
			if (connection != null) connection.close();
			if (preparedStatement != null) preparedStatement.close();
			if (resultSet!=null) resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
