package com.pwc.denali2.estimator.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class ExcelExport {

	static Workbook wb = null;
	static CellStyle s1 = null;
	static CellStyle s2 = null;
	static final int DetailsSheetRow1 = 8;
	static final int DetailsSheetColumn1 = 3;
	static final int DetailsSheetRow2 = 11;
	static final int DetailsSheetColumn2 = 8;
	static final int DetailsSheetRow3 = 19;
	static final int DetailsSheetColumn3 = 2;
	static final int DetailsSheetRow4 = 22;
	static final int DetailsSheetColumn4 = 5;

	static final int ModulesSheetRow1 = 11;
	static final int ModulesSheetColumn1 = 1;

	static final int ConversionsSheetRow1 = 9;
	static final int ConversionsSheetColumn1 = 1;

	static final int OtherRICESheetRow1 = 11;
	static final int OtherRICESheetColumn1 = 1;

	static final int PwCResponsibilitySheetRow1 = 9;
	static final int PwCResponsibilitySheetColumn1 = 1;

	static final int AdditionalServicesSheetRow1 = 9;
	static final int AdditionalServicesSheetColumn1 = 1;

	public static void exportExcelByModel(String codedFileName, String url, Object ls, HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("application/vnd.ms-excel");
		OutputStream fOut = null;
		try {
			if (isIE(request)) {
				response.setHeader("content-disposition",
						"attachment;filename=" + URLEncoder.encode(codedFileName, "UTF-8") + ".xlsm");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
				response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xlsm");
			}

			Workbook workbook = createSheetInUserModel2FileByTemplate(url, ls);
			workbook.setForceFormulaRecalculation(true);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}

	public static Workbook createSheetInUserModel2FileByTemplate(String url, Object ls) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		try {
			wb = getCloneWorkBook(url);
			s1 = wb.getSheet("Details").getRow(19).getCell(1).getCellStyle();
			s2 = wb.getSheet("Details").getRow(19).getCell(2).getCellStyle();

			
			 wb.getSheet("Modules").shiftRows(14,wb.getSheet("Modules").getLastRowNum(), 1, true, false);
			 Row row = wb.getSheet("Modules").createRow(14);
			setCellValue(row, 5, "Yes", wb, s2);
			// wb.getSheet("Modules").setForceFormulaRecalculation(true);
			wb.setForceFormulaRecalculation(true);
			
			setDetailsSheet(ls);
			setModulesSheet(ls);
			setConversionsSheet(ls);
			setOtherRICESheet(ls);
			setPwCResponsibilitySheet(ls);
			setAdditionalServicesSheet(ls);
			// for (IVRCount lv : ls) {
			// int cellNum = 1;
			// wb.getSheet("Modules").shiftRows(index,
			// wb.getSheet("Modules").getLastRowNum(), 1, true, false);
			// row = wb.getSheet("Modules").createRow(index);
			// setCellValue(row, cellNum++, lv.getBusinessProcess(), wb, s1);
			// setCellValue(row, cellNum++, lv.getModules(), wb, s1);
			// setCellValue(row, cellNum++, lv.getAbbrev(), wb, s1);
			// setCellValue(row, cellNum++, lv.getDdcSupported(), wb, s1);
			// setCellValue(row, cellNum++, lv.getInScope(), wb, s2);
			// setCellValue(row, cellNum++, lv.getCmplexityFactor(), wb, s2);
			// setCellValue(row, cellNum++, lv.getOfRequirements(), wb, s2);
			// setCellValue(row, cellNum++, lv.getOfGaps(), wb, s2);
			// index++;
			// }

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return wb;
	}

	private static void setDetailsSheet(Object data) throws Exception {
		Sheet sheet = wb.getSheet("Details");
		int rowNum = DetailsSheetRow1;
		int colNum = DetailsSheetColumn1;
		setCellValue(sheet.getRow(rowNum++), colNum, "Client Name", wb, null);
		setCellValue(sheet.getRow(rowNum++), colNum, "Engagement Name", wb, null);
		setCellValue(sheet.getRow(rowNum++), colNum, "Engagement Leader", wb, null);
		setCellValue(sheet.getRow(rowNum++), colNum, "Planned Project Start Date", wb, null);
		setCellValue(sheet.getRow(rowNum++), colNum, "Client Sector", wb, null);
		setCellValue(sheet.getRow(rowNum++), colNum, "Estimation Requestor", wb, null);
		setCellValue(sheet.getRow(rowNum++), colNum, "Estimation Requested Date", wb, null);
		setCellValue(sheet.getRow(rowNum++), colNum, "Comments (if any)", wb, null);
		
		rowNum = DetailsSheetRow2;
		for(int i=0;i<7;i++){
			colNum = DetailsSheetColumn2;
			setCellValue(sheet.getRow(rowNum), colNum++, "Workstream", wb, null);
			setCellValue(sheet.getRow(rowNum), colNum++, "1", wb, null);
			setCellValue(sheet.getRow(rowNum), colNum++, "1", wb, null);
			setCellValue(sheet.getRow(rowNum), colNum++, "1", wb, null);
			setCellValue(sheet.getRow(rowNum), colNum++, "1", wb, null);
			setCellValue(sheet.getRow(rowNum), colNum++, "1", wb, null);
			setCellValue(sheet.getRow(rowNum), colNum++, "1", wb, null);
			setCellValue(sheet.getRow(rowNum), colNum++, "1", wb, null);
			setCellValue(sheet.getRow(rowNum), colNum++, "1", wb, null);
			rowNum++;
		}
		
		rowNum = DetailsSheetRow3;
		colNum = DetailsSheetColumn3;
		setCellValue(sheet.getRow(rowNum), colNum, "1", wb, null);
		
		rowNum = DetailsSheetRow4;
		colNum = DetailsSheetColumn4;
		setCellValue(sheet.getRow(rowNum++), colNum, "1", wb, null);
		setCellValue(sheet.getRow(rowNum), colNum, "1", wb, null);

	}
	
	private static void setModulesSheet(Object data) throws Exception {
		Sheet sheet = wb.getSheet("Modules");
		int rowNum = ModulesSheetRow1;
		int colNum = ModulesSheetColumn1;

		for(int i=0;i<7;i++){
			colNum = ModulesSheetColumn1;
			sheet.shiftRows(rowNum,sheet.getLastRowNum(), 1, true, false);
			setCellValue(sheet.getRow(rowNum), colNum++, "Business Process", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Modules", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Abbrev", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "DDC Supported?", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "In Scope", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Complexity", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "# of Requirements", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "# of Gaps", wb, s2);
			rowNum++;
		}
	}
	
	private static void setConversionsSheet(Object data) throws Exception {
		Sheet sheet = wb.getSheet("Conversions");
		int rowNum = ConversionsSheetRow1;
		int colNum = ConversionsSheetColumn1;

		//master data
		for(int i=0;i<7;i++){
			colNum = ConversionsSheetColumn1;
			sheet.shiftRows(rowNum,sheet.getLastRowNum(), 1, true, false);
			setCellValue(sheet.getRow(rowNum), colNum++, "Data Type", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Business Process", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Module", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Abbreviation", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Conversion", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Defaulted with module in Scope", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Override Flag", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Final Scope", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Client Complexity", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Sources", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Volume", wb, s2);
			rowNum++;
		}
		//Transaction Data
		rowNum+=2;
		for(int i=0;i<7;i++){
			colNum = ModulesSheetColumn1;
			sheet.shiftRows(rowNum,sheet.getLastRowNum(), 1, true, false);
			setCellValue(sheet.getRow(rowNum), colNum++, "Data Type", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Business Process", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Module", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Abbreviation", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Conversion", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Defaulted with module in Scope", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Override Flag", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Final Scope", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Client Complexity", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Sources", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Volume", wb, s2);
			rowNum++;
		}
		//
		rowNum+=5;
		for(int i=0;i<7;i++){
			colNum = ModulesSheetColumn1;
			sheet.shiftRows(rowNum,sheet.getLastRowNum(), 1, true, false);
			setCellValue(sheet.getRow(rowNum), colNum++, "Data Type", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Business Process", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Module", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Abbreviation", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Conversion", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Final Scope", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Client Complexity", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Sources", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Volume", wb, s2);
			rowNum++;
		}
		
	}
	
	private static void setOtherRICESheet(Object data) throws Exception {
		Sheet sheet = wb.getSheet("Other RICE");
		int rowNum = OtherRICESheetRow1;
		int colNum = OtherRICESheetColumn1;

		for(int i=0;i<7;i++){
			colNum = OtherRICESheetColumn1;
			sheet.shiftRows(rowNum,sheet.getLastRowNum(), 1, true, false);
			setCellValue(sheet.getRow(rowNum), colNum++, "RICE Type", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "PaaS Apps Type", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Module", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "RICE Name", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Complexity", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Source System", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Target System", wb, s2);
			rowNum++;
		}
	}
	
	private static void setPwCResponsibilitySheet(Object data) throws Exception {
		Sheet sheet = wb.getSheet("PwC Responsibility");
		int rowNum = PwCResponsibilitySheetRow1;
		int colNum = PwCResponsibilitySheetColumn1;

		for(int i=0;i<7;i++){
			colNum = PwCResponsibilitySheetColumn1;
			sheet.shiftRows(rowNum,sheet.getLastRowNum(), 1, true, false);
			setCellValue(sheet.getRow(rowNum), colNum++, "Workstream", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Prototype", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Activity", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "PwC Responsibility", wb, s2);
			rowNum++;
		}
	}
	
	private static void setAdditionalServicesSheet(Object data) throws Exception {
		Sheet sheet = wb.getSheet("Additional Services");
		int rowNum = AdditionalServicesSheetRow1;
		int colNum = AdditionalServicesSheetColumn1;

		for(int i=0;i<7;i++){
			colNum = AdditionalServicesSheetColumn1;
			sheet.shiftRows(rowNum,sheet.getLastRowNum(), 1, true, false);
			setCellValue(sheet.getRow(rowNum), colNum++, "Workstream", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Service Owner", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Activity", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "P1-Assessment", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "P2-Design & Config", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "P3-Refine & Validate", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "P4-Test & Deploy", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Post Go-Live Support", wb, s2);
			setCellValue(sheet.getRow(rowNum), colNum++, "Total Effort", wb, s1);
			setCellValue(sheet.getRow(rowNum), colNum++, "Units", wb, s1);
			rowNum++;
		}
	}

	private static void setCellValue(Row row, int cellNum, String value, Workbook wb, CellStyle s) throws Exception {
		if (!StringUtils.isEmpty(value)) {
			RichTextString Rtext = wb instanceof HSSFWorkbook ? new HSSFRichTextString(value)
					: new XSSFRichTextString(value);
			createListCells(row, cellNum, Rtext, s);
		}
	}

	private static void createListCells(Row row, int cellNum, RichTextString Rtext, CellStyle s) throws Exception {
		Cell cell = null;
		if (s != null) {
			cell = row.createCell(cellNum);
			cell.setCellStyle(s);

		} else {
			cell = row.getCell(cellNum);
		}
		cell.setCellValue(Rtext);
	}

	private static Workbook getCloneWorkBook(String url) throws Exception {
		Workbook wb = null;
		String path = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		path = path.replace("WEB-INF/classes/", "");
		path = path.replace("file:/", "") + url;
		FileInputStream fileis = new FileInputStream(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = fileis.read(buffer)) > -1) {
			baos.write(buffer, 0, len);
		}
		baos.flush();
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		wb = WorkbookFactory.create(is);
		baos.close();
		fileis.close();
		is.close();
		return wb;

	}

	// if IE
	private static boolean isIE(HttpServletRequest request) {
		return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0
				|| request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0) ? true : false;
	}

}
