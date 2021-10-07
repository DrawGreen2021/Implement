package com.drawgreen.corpcollector.util;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Pager {
	
	private final static int pageCount = 5;
	private final static int rowCount = 10;
    private static int blockStartNum = 1;
    private static int blockLastNum = 0;
    private static int lastPageNum = 0;
    private static int[] numArray;
    
	public int getBlockStartNum(int page) {
		if (page % pageCount == 1 && page != 1) {
			if (page > blockStartNum)
				setBlockStartNum(blockStartNum + pageCount);
			else if (page < blockStartNum) 
				setBlockStartNum(blockStartNum - pageCount);
		} else if(page == 1) 
			setBlockStartNum(1);
		return blockStartNum;
	}
	public void setBlockStartNum(int blockStartNum) {
		Pager.blockStartNum = blockStartNum;
	}
	public int getBlockLastNum() {
		setBlockLastNum(blockStartNum+pageCount-1);
		return blockLastNum;
	}
	public void setBlockLastNum(int blockLastNum) {
		Pager.blockLastNum = blockLastNum;
	}
	public int getLastPageNum(int allRowCount) {
		if (allRowCount % rowCount == 0) {
			setLastPageNum(allRowCount / rowCount);
		} else {
			setLastPageNum(allRowCount / rowCount + 1);
		}
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		Pager.lastPageNum = lastPageNum;
	}
	public static int getPagecount() {
		return pageCount;
	}
	public static int getRowcount() {
		return rowCount;
	}
	
	public void setNumbers(int page, int rowCount, HttpServletRequest request, HttpServletResponse response) {
		int blockStartNum = getBlockStartNum(page);
		setLastPageNum(rowCount);
		int blockLastNum = getBlockLastNum();
		int lastPageNum = getLastPageNum(rowCount);
		int pageCount = Pager.getPagecount();
		numArray = new int[lastPageNum];
		
		for (int i = blockStartNum; i <= blockLastNum; i++) {
			numArray[i-1] = i;
		}

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("blockStartNum", blockStartNum);
		httpSession.setAttribute("blockLastNum", blockLastNum);
		httpSession.setAttribute("lastPageNum", lastPageNum);
		httpSession.setAttribute("pageCount", pageCount);
		
		System.out.println("numArray: "+Arrays.toString(numArray));
		System.out.println("blockStartNum: "+blockStartNum);
		System.out.println("blockLastNum: "+blockLastNum);
		System.out.println("lastPageNum: "+lastPageNum);
	}
	
}
