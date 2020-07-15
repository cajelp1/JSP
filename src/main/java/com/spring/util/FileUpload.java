package com.spring.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import com.spring.exception.FileSizeOverflowException;
import com.spring.exception.MultipartFileEmptyException;


public class FileUpload {
	
	//경로 유동
	public static String uploadFile(MultipartFile multi, String uploadPath) throws Exception{
		
		//저장 경로..확인 및 생성
		String savePath=calcPath(uploadPath); // \년\월\일
		
		//중복파일명 해결..
		UUID uid=UUID.randomUUID();
		String originalName = multi.getOriginalFilename();
		String saveName=uid.toString().replace("-", "")+"$$"+originalName;
		
		//파일 저장
		File target=new File(uploadPath+savePath,saveName);	
		multi.transferTo(target);
		
		//썸네일 이미지/파일...
		String thumbFileName=null;
		String formatName=originalName.substring(originalName.lastIndexOf(".")+1);
		
		
		if(MediaUtils.getMediaType(formatName)!=null){
			//썸네일 형태로 보여주기
			// file.seperator -> /년/월/일/s_uuid$$originalName.format
			thumbFileName=makeThumbnail(uploadPath,savePath,saveName); 
		}else {
			//텍스트 형태로 보여주기
			// file.seperator -> /년/월/일/uuid$$originalName.format
			thumbFileName=makeIcon(uploadPath,savePath,saveName);
		}
		
		return thumbFileName;		
	}
	
	
	//경로 고정
	public static File saveFile(MultipartFile multi, HttpServletRequest request)
			throws MultipartFileEmptyException, FileSizeOverflowException,
					IOException{
		
		if (multi.isEmpty()) {
			throw new MultipartFileEmptyException();
		}
		
		if (multi.getSize() > 1024 * 1024 * 5) {
			throw new FileSizeOverflowException();
		}
		
		/* 파일저장폴더설정. 실제 META-INF, WEB-INF가 생성되는 폴더로. */
		String uploadPath =
				request.getServletContext().getRealPath("resources/upload");
		
		System.out.println("uploadpath : "+uploadPath);
		
		
		/* 파일명 중복방지 */
		String fileName = UUID.randomUUID().toString().replace("-", "") 
					+ "$$" + multi.getOriginalFilename();
		
		/* 파일 경로확인 및 생성 */
		File file = new File(uploadPath, fileName);
		
		/*파일 경로 생성 */
		if (!file.exists()) {
			file.mkdirs();
		}
		
		/* 파일저장 */
		multi.transferTo(file);
		
		return file;
	}
	
	public static String calcPath(String uploadPath)throws Exception{
		Calendar cal=Calendar.getInstance();
		
		String yearPath=File.separator+cal.get(Calendar.YEAR);
		String monthPath=yearPath+File.separator+
				new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath=monthPath+File.separator+
				new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		File file = new File(uploadPath+datePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		return datePath;
	} 
	
	//썸네일 형태
	public static String makeThumbnail(String uploadPath,
									   String path,
									   String fileName)throws Exception{
		BufferedImage sourceImg=
				ImageIO.read(new File(uploadPath+path,fileName));
		
		BufferedImage destImg=Scalr.resize(sourceImg, 
							  Scalr.Method.AUTOMATIC,
							  Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName=uploadPath+path+File.separator+"s_"+fileName;
		File newFile=new File(thumbnailName);
		String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		ImageIO.write(destImg, formatName.toUpperCase(),newFile);
		
		return thumbnailName.substring(uploadPath.length()).
				replace(File.separatorChar,'/');
	}
	
	//아이콘 형태
	public static String makeIcon(String uploadPath, String path, String fileName) throws Exception{
		String iconName = uploadPath+path+File.separator+fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/'); //잉.. char type?
	}
	
	
	
}
