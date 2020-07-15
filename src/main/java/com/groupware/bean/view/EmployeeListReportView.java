package com.groupware.bean.view;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.web.servlet.view.AbstractView;

import com.groupware.request.SearchCriteria;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class EmployeeListReportView extends AbstractView{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String type = (String)model.get("type");
		SearchCriteria cri = (SearchCriteria)model.get("cri");
		
		String jrFileName = "employee_list.jrxml";
		
		//1. 파라미터 추가
		Map<String,Object> params = new HashMap<String,Object>();
		
		Map<String, String> searchType = new HashMap<String,String>();
		searchType.put("i", "id");
		searchType.put("n", "name");
		searchType.put("e", "email");
		searchType.put("t", "phone");
		String query = "select * from employee";
		
		if(cri.getSearchType()!=null) {
			query += " where "+searchType.get(cri.getSearchType())+" like '%'||'"+cri.getKeyword()+"'||'%'";
		}
		System.out.println("query:"+query);
		params.put("query",query);
		
		//2. jrxml 템플릿 결정/로딩
		InputStream reportStream = this.getClass().getResourceAsStream("/com/groupware/jrxml/" + jrFileName);
		
		//3. 템플릿을 JasperDesign으로 변환
		JasperDesign jd = JRXmlLoader.load(reportStream);
		
		//4. jrxml 컴파일
		JasperReport jr = JasperCompileManager.compileReport(jd);
		// 2~4 까지가 결과적으로 compile..?
		
		//5. JasperPrint 생성
		JasperPrint jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());
		
		//6. outputstream 생성
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		//7. exporter() 실행
		export(type, jp, response, baos);
		
		//8. response 이용한 전송
		write(response, baos);
	}
	
	public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
	public static final String MEDIA_TYPE_PDF="application/pdf";
	public static final String EXTENSION_TYPE_EXCEL="xls";
	public static final String EXTENSION_TYPE_PDF="pdf";
	
	public void export(String type, JasperPrint jp, HttpServletResponse response,
			ByteArrayOutputStream baos) throws RuntimeException{
		if(type.equalsIgnoreCase(EXTENSION_TYPE_EXCEL)) {
			exportXls(jp, baos);
			
			String fileName = "MemberList.xls";
			response.setHeader("Content-Disposition", "inline; filename="+fileName);
			response.setContentType(MEDIA_TYPE_EXCEL);
			response.setContentLength(baos.size());
		}
		else if(type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
			exportPdf(jp, baos);
			
			String fileName = "MemberList.pdf";
			response.setHeader("Content-Disposition", "inline; filename="+fileName);
			response.setContentType(MEDIA_TYPE_PDF);
			response.setContentLength(baos.size());
		}else {
			throw new RuntimeException("No type set for type "+type);
		}
	}
	private void write(HttpServletResponse response, ByteArrayOutputStream baos) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			baos.writeTo(outputStream);
			outputStream.flush();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void exportXls(JasperPrint jp, ByteArrayOutputStream baos) {
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(true);
		exporter.setConfiguration(configuration);
		try {
			exporter.exportReport();
		}catch(JRException e) {
			throw new RuntimeException(e);
		}
	}
	public void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {
		JRPdfExporter exporter = new JRPdfExporter();
		
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		
		SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
		exporter.setConfiguration(configuration);
		
		try {
			exporter.exportReport();
		}catch(JRException e) {
			throw new RuntimeException(e);
		}
	}
	
}
