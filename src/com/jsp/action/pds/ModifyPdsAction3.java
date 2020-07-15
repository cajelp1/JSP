package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class ModifyPdsAction3 implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/modify_success";
		
		try {
			PdsVO pdsAfterModify = fileModify(request);
			//pdsService.modify(pdsAfterModify);
		} catch (Exception e) {
			// 4. 결과에 따른 화면 결정.
			url = "pds/modify_fail";
			e.printStackTrace();
		}
		
		return url;
	}
	
	//업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;	//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;		//40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;	//50MB. filesize보다 커야 한다.
	
	private PdsVO fileModify(HttpServletRequest request) throws Exception {
		
		
		// 업로드를 위한 upload 환경설정 적용
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 저장을 위한 threshold memory 적용
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 위치 결정
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 업로드 파일의 크기 적용
		upload.setFileSizeMax(MAX_FILE_SIZE);
		// 업로드 request 크기 적용
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//실제 저장경로를 설정
		String uploadPath = GetUploadPath.getUploadPathToday("pds.upload");
		
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		List<FileItem> formItems;
		
		String title = null; //request.getParameter("title");
		String writer = null; //request.getParameter("writer");
		String content = null; //request.getParameter("content");
		int pno = -1;
		PdsVO pdsAfterModify = new PdsVO(); //수정 완료된 pds값을 담을 vo
		
		
		
		try {
			formItems = upload.parseRequest(request);
			
			
			
			List<AttachVO> newFileList = new ArrayList<AttachVO>(); //업데이트된 attach값을 담을 list
			List<Integer> intList = new ArrayList<Integer>(); //삭제 파일 ano를 담을 변수
			
			
			
			for(FileItem item : formItems) {
				// 1.1 필드
				if(item.isFormField()) {
					
					switch(item.getFieldName()) {
					case "deleteFile":
						intList.add(Integer.parseInt(item.getString("utf-8")));
						System.out.println("deleteFile을 감지했습니다");
						break;
					case "title":
						title = item.getString("utf-8");
						break;
					case "writer":
						writer = item.getString("utf-8");
						break;
					case "content":
						content = item.getString("utf-8");
						break;
					case "pno":
						pno = Integer.parseInt(item.getString("utf-8"));
						break;
					}
					
				}else {//1.2 파일
					
					//summernote의 files를 제외함
					if(!item.getFieldName().equals("uploadFile")) continue;
					
					String fileName = new File(item.getName()).getName();
					fileName = MakeFileName.toUUIDFileName(fileName, "$$");
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					
					//1.5 파일 저장
					//local HDD에 저장
					try {
						//item.write(storeFile);
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}

					//AttachVO 생성
					AttachVO attach = new AttachVO();
					//DB에 저장할 attach에 file 내용 추가
					attach.setFileName(fileName);
					attach.setUploadPath(uploadPath);
					attach.setFiletype(fileName.substring(fileName.lastIndexOf(".")+1));
					
					//List<AttachVO>에 추가
					newFileList.add(attach);
					
					System.out.println("upload file : "+ attach);
				}
			}
			
			
			//pno를 통해 원래 pds의 list를 가져온다
			PdsVO pds = pdsService.getPds(pno);
			List<AttachVO> attachList = pds.getAttachList();
			
			//for문으로 delete들을 삭제
			if(attachList.size()>0) {
			for(int i = attachList.size()-1; i >= 0; i--) {
				for(int j = 0; j < intList.size(); j++) {
					System.out.println("attachList : "+attachList.get(i).getAno());
					System.out.println("intList : "+intList.get(j));
					if(attachList.get(i).getAno() == intList.get(j)) {
						
						//해당파일 실제로 삭제
						String path = attachList.get(i).getUploadPath();
						String name = attachList.get(i).getFileName();
						File target = new File(path+File.separator+name);
						try {
							//target.delete();
						}catch(Exception e) {
							e.printStackTrace();
							throw e;
						}
						System.out.println(path+File.separator+name);
						
						//그리고 리스트에서 삭제..? 뀨아아ㅏㅇ
						attachList.remove(i);
					}
				}
			}
			}
			//두 list를 합친다
			for(AttachVO vo : attachList) {
				newFileList.add(vo);
			}
			
			
			pdsAfterModify.setAttachList(newFileList);
			pdsAfterModify.setWriter(writer);
			pdsAfterModify.setContent(content);
			pdsAfterModify.setTitle(title);
			
			for(AttachVO vo: newFileList) {
				System.out.println("현재 아이템은"+vo.getFileName());
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pdsAfterModify;
	}
	
	
}
