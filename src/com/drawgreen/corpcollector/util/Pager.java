package com.drawgreen.corpcollector.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Pager {
	
	private final static int pageCount = 5;
	private final static int rowCount = 10;
    private static int blockStartNum = 1;
    private static int blockLastNum = 0;
    private static int lastPageNum = 0;
    
	public int getBlockStartNum(int page) {
		if (page % pageCount == 1 && page != 1) {
			int newBlockStartNum = 0;
			
			if (page > blockStartNum) {
				newBlockStartNum 
				= (blockStartNum + pageCount)<=lastPageNum?
						blockStartNum + pageCount:lastPageNum;
				setBlockStartNum(newBlockStartNum);
			}
				
			else if (page < blockStartNum) 
				setBlockStartNum(blockStartNum - pageCount);
		} else if(page == 1) 
			setBlockStartNum(1);
		return blockStartNum;
	}
	public void setBlockStartNum(int blockStartNum) {
		Pager.blockStartNum = blockStartNum;
	}
	public int getBlockLastNum(int page) {
		if (page == lastPageNum) {
			setBlockLastNum(lastPageNum);
			return lastPageNum;
		}
		else {
			int newBlockLsatNum = blockStartNum+pageCount-1;
			if (newBlockLsatNum > lastPageNum) {
				setBlockLastNum(lastPageNum);
				return lastPageNum;
			}
			else {
				setBlockLastNum(blockStartNum+pageCount-1);
				return blockLastNum;
			}
		}
		
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
		int lastPageNum = getLastPageNum(rowCount);
		setLastPageNum(lastPageNum);
		int blockStartNum = getBlockStartNum(page);
		int blockLastNum = getBlockLastNum(page);

		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum);
		request.setAttribute("lastPageNum", lastPageNum);

		System.out.println("blockStartNum: "+blockStartNum);
		System.out.println("blockLastNum: "+blockLastNum);
		System.out.println("lastPageNum: "+lastPageNum);
	}
	
}
