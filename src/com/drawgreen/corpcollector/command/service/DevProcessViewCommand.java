package com.drawgreen.corpcollector.command.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;

import net.coobird.thumbnailator.Thumbnails;

public class DevProcessViewCommand implements Command{
	private int UIdesignImgCount = 12;
	private int FlowChartImgCount = 9;
	private String realPath;
	private String uploadPath;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		realPath = request.getSession().getServletContext().getRealPath("/");
		uploadPath = realPath+"images/thumbnails/";
		
		// UI 설계 이미지 썸네일 생성
		String[] UIdesign = new String[UIdesignImgCount];
		for (int i = 0; i < UIdesign.length; i++) {
			UIdesign[i] = "UI images/image"+(i+1)+".PNG";
			createThumbnail(UIdesign[i], 0.2);
		}
		request.setAttribute("UIdesignImgCount", UIdesignImgCount);
		
		// 플로우 차트 이미지 썸네일 생성
		String[] flowChart = new String[FlowChartImgCount];
		for (int i = 0; i < flowChart.length; i++) {
			flowChart[i] = "flow charts/image"+(i+1)+".png";
			createThumbnail(flowChart[i], 0.25);
		}
		request.setAttribute("FlowChartImgCount", FlowChartImgCount);
		
		// 데이터베이스 설계 이미지 썸네일 생성
		createThumbnail("database_design.png", 0.3);
		
	}
	
	public void createThumbnail(String imgName, double scale) {
		String imgFilePath = realPath+"images/"+imgName;
		File imgFile = new File(imgFilePath); // 원본
		
		File thumFile = new File(uploadPath+imgName); // 썸네일
		
		try {
			Thumbnails.of(imgFile).scale(scale).toFile(thumFile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
