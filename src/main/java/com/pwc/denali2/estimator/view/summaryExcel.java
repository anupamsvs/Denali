package com.pwc.denali2.estimator.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.pwc.denali2.estimator.dao.DetailedSummaryDao;
import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.DetailedSummary;

public class summaryExcel extends AbstractXlsView {

	private CellStyle titleStyle = null;
	private CellStyle subStyle = null;
	private CellStyle currencyStyle = null;
	private CellStyle totalStyle = null;
	private CellStyle simpleStyle = null;
	private DecimalFormat df = new DecimalFormat("#.##");
	private DecimalFormat sf = new DecimalFormat("#.#");
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public CellStyle getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(CellStyle titleStyle) {
		this.titleStyle = titleStyle;
	}

	public CellStyle getSubStyle() {
		return subStyle;
	}

	public void setSubStyle(CellStyle subStyle) {
		this.subStyle = subStyle;
	}

	public CellStyle getCurrencyStyle() {
		return currencyStyle;
	}

	public void setCurrencyStyle(CellStyle currencyStyle) {
		this.currencyStyle = currencyStyle;
	}

	public CellStyle getTotalStyle() {
		return totalStyle;
	}

	public void setTotalStyle(CellStyle totalStyle) {
		this.totalStyle = totalStyle;
	}

	private Map<String, Object> getByValue(List<Map<String, Object>> arr, String value) {
//		Iterator it = arr.entrySet().iterator();
		try{
		for (Map<String, Object>obj : arr) {
			if (obj.get("idkey").toString().equals(value)) {
				return obj;
			}
		}
		}catch(Exception e){
		return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private Double trackMe(String id, String type, String key,
			Map<String, List> arr) {
		Map<String, Object> value = getByValue((List<Map<String, Object>>)arr.get(key), id);
		if (value != null) {
			return (Double) value.get(type);
		} else {
			return 0.00;
		}
	}

	// All styles resides here
	private void initStyles(Workbook workbook) {

		Font boldFont = workbook.createFont();
		boldFont.setColor(IndexedColors.AUTOMATIC.index);
		boldFont.setBold(true);
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
		titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		titleStyle.setWrapText(true);
		titleStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		titleStyle.setFont(boldFont);

		CellStyle subStyle = workbook.createCellStyle();
		subStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		subStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		subStyle.setWrapText(true);
		subStyle.setAlignment(HorizontalAlignment.CENTER);
		subStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		subStyle.setBorderBottom(BorderStyle.THIN);
		subStyle.setBorderTop(BorderStyle.THIN);
		subStyle.setBorderLeft(BorderStyle.THIN);
		subStyle.setBorderRight(BorderStyle.THIN);

		subStyle.setFont(boldFont);

		CellStyle currencyStyle = workbook.createCellStyle();
		DataFormat currency = workbook.createDataFormat();
		currencyStyle.setBorderBottom(BorderStyle.THIN);
		currencyStyle.setBorderTop(BorderStyle.THIN);
		currencyStyle.setBorderLeft(BorderStyle.THIN);
		currencyStyle.setBorderRight(BorderStyle.THIN);
		currencyStyle.setDataFormat(currency.getFormat("$#,#0"));

		CellStyle totalStyle = workbook.createCellStyle();
		totalStyle.setFillForegroundColor(IndexedColors.TAN.index);
		totalStyle.setBorderTop(BorderStyle.THIN);
		totalStyle.setBorderLeft(BorderStyle.THIN);
		totalStyle.setBorderRight(BorderStyle.THIN);
		totalStyle.setBorderBottom(BorderStyle.MEDIUM);
		totalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		totalStyle.setWrapText(true);

		CellStyle simpleStyle = workbook.createCellStyle();
		simpleStyle.setBorderBottom(BorderStyle.THIN);
		simpleStyle.setBorderTop(BorderStyle.THIN);
		simpleStyle.setBorderLeft(BorderStyle.THIN);
		simpleStyle.setBorderRight(BorderStyle.THIN);
		simpleStyle.setWrapText(true);

		this.setSimpleStyle(simpleStyle);
		this.setTotalStyle(totalStyle);
		this.setTitleStyle(titleStyle);
		this.setSubStyle(subStyle);
		this.setCurrencyStyle(currencyStyle);
	}

	// Format Number
	private Double formatNumber(Object str, DecimalFormat ddf) {
		try {
			return Double.parseDouble(ddf.format(Double.parseDouble(str
					.toString())));
		} catch (Exception e) {
			return 0.00;
		}
	}

	private void detailedSummary(Workbook workbook, Map<String, List> dsList) {
		Iterator it = dsList.entrySet().iterator();
		Sheet sheet = workbook.createSheet("Detailed Summary");
		int rowNum = 3;
		// Iterate through Workstream
		while (it.hasNext()) {
			Map.Entry ws = (Map.Entry) it.next();

			// Title
			Row title = sheet.createRow(rowNum - 3);
			title.createCell(0).setCellValue(ws.getKey() + "");
			title.getCell(0).setCellStyle(titleStyle);

			Row header = sheet.createRow(rowNum - 2);
			// header.setRowStyle(subStyle);
			header.createCell(0).setCellValue("");
			header.createCell(1).setCellValue("");
			header.createCell(2).setCellValue("Effort (Hr)");
			header.createCell(4).setCellValue("Override Effort (Hr)");
			header.createCell(6).setCellValue("CSP Price");
			header.createCell(8).setCellValue("RSR Price");
			header.createCell(10).setCellValue("Margin Price");
			// Style Headers
			for (int j = 2; j <= 10; j += 2) {
				try {
					header.getCell(j).setCellStyle(subStyle);
				} catch (Exception e) {
					header.createCell(j).setCellStyle(subStyle);
				}
			}

			// Merge Cells
			sheet.addMergedRegion(new CellRangeAddress((rowNum - 2),
					(rowNum - 2), 2, 3));
			sheet.addMergedRegion(new CellRangeAddress((rowNum - 2),
					(rowNum - 2), 4, 5));
			sheet.addMergedRegion(new CellRangeAddress((rowNum - 2),
					(rowNum - 2), 6, 7));
			sheet.addMergedRegion(new CellRangeAddress((rowNum - 2),
					(rowNum - 2), 8, 9));
			sheet.addMergedRegion(new CellRangeAddress((rowNum - 2),
					(rowNum - 2), 10, 11));

			// Second header
			Row header2 = sheet.createRow(rowNum - 1);
			header2.createCell(0).setCellValue("Prototype");
			header2.createCell(1).setCellValue("Activity");
			header2.createCell(2).setCellValue("Onsite");
			header2.createCell(3).setCellValue("DDC");
			header2.createCell(4).setCellValue("Onsite");
			header2.createCell(5).setCellValue("DDC");
			header2.createCell(6).setCellValue("Onsite");
			header2.createCell(7).setCellValue("DDC");
			header2.createCell(8).setCellValue("Onsite");
			header2.createCell(9).setCellValue("DDC");
			header2.createCell(10).setCellValue("Onsite");
			header2.createCell(11).setCellValue("DDC");
			// Style Headers
			for (int j = 0; j <= 11; j++) {
				header2.getCell(j).setCellStyle(subStyle);
				workbook.getSheetAt(0).autoSizeColumn(j);
			}

			List<Map<String, Object>> summaryList = (List<Map<String, Object>>) ws
					.getValue();
			int start = rowNum;
			// for each workstream get values
			for (Map<String, Object> ds : summaryList) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(ds.get("prototype") + "");
				row.createCell(1).setCellValue(ds.get("activity") + "");

				row.createCell(2).setCellValue(
						this.formatNumber(ds.get("effort_onsite"), sf));
				row.createCell(3).setCellValue(
						this.formatNumber(ds.get("effort_ddc"), sf));
				row.createCell(4)
						.setCellValue(
								this.formatNumber(
										ds.get("effort_onsite_override"), sf));
				row.createCell(5).setCellValue(
						this.formatNumber(ds.get("effort_ddc_override"), sf));
				row.createCell(6).setCellValue(
						this.formatNumber(ds.get("csp_onsite"), df));
				row.createCell(7).setCellValue(
						this.formatNumber(ds.get("csp_ddc"), sf));
				row.createCell(8).setCellValue(
						this.formatNumber(ds.get("rsr_onsite"), df));
				row.createCell(9).setCellValue(
						this.formatNumber(ds.get("rsr_ddc"), df));
				row.createCell(10).setCellValue(
						this.formatNumber(ds.get("margin_onsite"), df));
				row.createCell(11).setCellValue(
						this.formatNumber(ds.get("margin_ddc"), df));
				// Style Headers
				for (int j = 0; j <= 6; j++) {
					row.getCell(j).setCellStyle(simpleStyle);
				}
				// Style Headers
				for (int j = 6; j <= 11; j++) {
					row.getCell(j).setCellStyle(currencyStyle);
				}
			}

			Row Totalrow = sheet.createRow(rowNum++);
			Totalrow.createCell(0).setCellValue("");
			Totalrow.createCell(1).setCellValue("Total");
			sheet.addMergedRegion(new CellRangeAddress((rowNum), (rowNum), 0, 1));

			// Create Total row,adding excel Formulae Ex.SUM(C1:C12)
			Totalrow.createCell(2).setCellFormula(
					"SUM(C" + (start + 1) + ":C" + (rowNum - 1) + ")");
			Totalrow.createCell(3).setCellFormula(
					"SUM(D" + (start + 1) + ":D" + (rowNum - 1) + ")");
			Totalrow.createCell(4).setCellFormula(
					"SUM(E" + (start + 1) + ":E" + (rowNum - 1) + ")");
			Totalrow.createCell(5).setCellFormula(
					"SUM(F" + (start + 1) + ":F" + (rowNum - 1) + ")");
			Totalrow.createCell(6).setCellFormula(
					"SUM(G" + (start + 1) + ":G" + (rowNum - 1) + ")");
			Totalrow.createCell(7).setCellFormula(
					"SUM(H" + (start + 1) + ":H" + (rowNum - 1) + ")");
			Totalrow.createCell(8).setCellFormula(
					"SUM(I" + (start + 1) + ":I" + (rowNum - 1) + ")");
			Totalrow.createCell(9).setCellFormula(
					"SUM(J" + (start + 1) + ":J" + (rowNum - 1) + ")");
			Totalrow.createCell(10).setCellFormula(
					"SUM(K" + (start + 1) + ":K" + (rowNum - 1) + ")");
			Totalrow.createCell(11).setCellFormula(
					"SUM(L" + (start + 1) + ":L" + (rowNum - 1) + ")");
			// Style totals
			for (int j = 0; j <= 11; j++) {
				Totalrow.getCell(j).setCellStyle(totalStyle);
			}

			rowNum = rowNum + 5;
		}
	}

	@SuppressWarnings("unchecked")
	private void detailedSummaryEffort(Workbook workbook,
			Map<String, List> dsList, Map<String, Object> effortGroup,
			List workstream, List protoype) {
		Iterator it = dsList.entrySet().iterator();
		// Iterator itGroup = effortGroup.entrySet().iterator();
		Sheet sheet = workbook.createSheet("Detailed Summary Effort");
		int rowNum = 3;
		boolean first = true;
		// Iterate through Workstream

		// while (it.hasNext()) {
		// Map.Entry ws = (Map.Entry) it.next();

	

		if (first) {
			// P1-Assessment P2-Design & Config P3-Refine & Validate P4-Test &
			// Deploy Post Go Live Total Revenue Total Hours
			// Activity Group Onsite DDC Onsite DDC Onsite DDC Onsite DDC Onsite
			// DDC Onsite DDC Total Revenue Onsite DDC Total Hour
			Row header = sheet.createRow(rowNum - 2);
			header.createCell(0).setCellValue("");
			header.getCell(0).setCellStyle(subStyle);
			Row header2 = sheet.createRow(rowNum - 1);
			header2.createCell(0).setCellValue("Activity Group");
			workbook.getSheetAt(1).autoSizeColumn(0);
			int j = 1;
			for (int i = 1; i <= protoype.size(); i++) {

				Map<String, Object> pr = (Map<String, Object>) protoype
						.get(i - 1);
				header.createCell((j)).setCellValue(pr.get("code") + "");
				header.getCell(j).setCellStyle(subStyle);
				header.createCell((j + 1)).setCellValue(pr.get("code") + "");
				header.getCell((j + 1)).setCellStyle(subStyle);
				sheet.addMergedRegion(new CellRangeAddress((rowNum - 2),
						(rowNum - 2), j, (j + 1)));
				workbook.getSheetAt(1).autoSizeColumn(j);
				workbook.getSheetAt(1).autoSizeColumn(j + 2);
				header2.createCell((j)).setCellValue("Onsite");				
				header2.createCell((j + 1)).setCellValue("DDC");
				//Style
				header2.getCell((j)).setCellStyle(subStyle);
				header2.getCell((j + 1)).setCellStyle(subStyle);
				j = j + 2;
			}
//			header.createCell((j++)).setCellValue("Total Revenue");
//			header.createCell((j++)).setCellValue("Total Revenue");
//			header.createCell((j++)).setCellValue("Total Revenue");
//			header2.createCell((j++)).setCellValue("Onsite");
//			header2.createCell((j++)).setCellValue("DDC");
//			header2.createCell((j++)).setCellValue("Total Revenue");
//			
//			header.createCell((j++)).setCellValue("Total Hours");
//			header.createCell((j++)).setCellValue("Total Hours");
//			header.createCell((j++)).setCellValue("Total Hours");	
//			header2.createCell((j++)).setCellValue("Onsite");
//			header2.createCell((j++)).setCellValue("DDC");
//			header2.createCell((j++)).setCellValue("Total Revenue");

		}
		// For each activity group
		for (int i = 0; i < workstream.size(); i++) {
			
			
			Map<String, Object> work = (Map<String, Object>) workstream.get(i);
			
			// Title
			Row title = sheet.createRow(first?(rowNum - 3):rowNum++);
			title.createCell(0).setCellValue(work.get("code") + "");
			title.getCell(0).setCellStyle(titleStyle);
			
			int rowStart = rowNum;
			List<Map<String, Object>> itemList = (List<Map<String, Object>>) effortGroup
					.get(work.get("id") + "");
			for (Map<String, Object> item : itemList) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(item.get("activity_group") + "");
				row.getCell(0).setCellStyle(simpleStyle);
				int j = 1;
				// get all proto vals
				for (int i1 = 1; i1 <= protoype.size(); i1++) {

					Map<String, Object> pr = (Map<String, Object>) protoype
							.get(i1 - 1);
					String idKey = work.get("id") + "_" + pr.get("id") + "_"
							+ item.get("activity_group");
					try{
					row.createCell(j).setCellValue(this.trackMe(idKey, "onsite", work.get("code")+"",dsList ));
					row.createCell(j+1).setCellValue(this.trackMe(idKey, "ddc", work.get("code")+"",dsList ));
					//Style
					row.getCell((j)).setCellStyle(currencyStyle);
					row.getCell((j + 1)).setCellStyle(currencyStyle);
					}catch(Exception e){
						
						row.createCell(j).setCellValue(0);
						//Style
						row.getCell((j)).setCellStyle(currencyStyle);
					}
					j = j + 2;
				}
				
			}
			//Total row
			int j = 1;
			Row rowTotal = sheet.createRow(rowNum++);
			rowTotal.createCell(0).setCellValue("Total");
			rowTotal.getCell(0).setCellStyle(totalStyle);
			for (int i1 = 1; i1 <= protoype.size(); i1++) {
				char charAlphaOnsite = alphabet.charAt(j);
				char charAlphaDDC = alphabet.charAt(j+1);
				rowTotal.createCell(j).setCellFormula("SUM("+charAlphaOnsite+rowStart+":"+charAlphaOnsite+(rowNum-1)+")");
				rowTotal.createCell(j+1).setCellFormula("SUM("+charAlphaDDC+rowStart+":"+charAlphaDDC+(rowNum-1)+")");
				//Style
				rowTotal.getCell((j)).setCellStyle(totalStyle);
				rowTotal.getCell((j + 1)).setCellStyle(totalStyle);
				j = j + 2;
			}
			int end = j;
//			rowTotal.createCell(j+1).setCellFormula("SUM("+alphabet.charAt(0)+rowNum+":"+alphabet.charAt(end-1)+(rowNum)+")");
//			rowTotal.createCell(j+2).setCellFormula("SUM("+alphabet.charAt(0)+rowNum+":"+alphabet.charAt(end-1)+(rowNum)+")");
			rowNum = rowNum + 3;
		}

		first = false;
		// }
	}

	private void allSummaryEffort(Workbook workbook, Map<String, Object> dsList) {
		Iterator it = dsList.entrySet().iterator();
		Sheet sheet = workbook.createSheet("Summary Efforts");
		int rowNum = 3;
		// Iterate through Workstream
		while (it.hasNext()) {
			Map.Entry ws = (Map.Entry) it.next();

			Map<String, List> table = (Map<String, List>) ws.getValue();
			Iterator it2 = table.entrySet().iterator();
			// Title
			Row title = sheet.createRow(rowNum - 3);
			title.createCell(0).setCellValue(ws.getKey() + "");
			title.getCell(0).setCellStyle(titleStyle);

			// WorkStream Prototype Effort(Hr) CSP Price($) RSR Price($)
			// Margin($) Rate/Hour($)
			// Onsite DDC Onsite DDC Onsite DDC Onsite DDC Onsite DDC Total
			Row header = sheet.createRow(rowNum - 2);

			header.createCell(0).setCellValue("");
			header.createCell(1).setCellValue("");

			header.createCell(2).setCellValue("Effort(Hr)");
			header.createCell(3).setCellValue("Effort(Hr)");

			header.createCell(4).setCellValue("CSP Price($)");
			header.createCell(5).setCellValue("CSP Price($)");

			header.createCell(6).setCellValue("RSR Price($)");
			header.createCell(7).setCellValue("RSR Price($)");

			header.createCell(8).setCellValue("Margin($)");
			header.createCell(9).setCellValue("Margin($)");

			header.createCell(10).setCellValue("Rate/Hour($)");
			header.createCell(11).setCellValue("Rate/Hour($)");
			header.createCell(12).setCellValue("Rate/Hour($)");
			// Style totals
			for (int i = 0; i <= 12; i++) {
				header.getCell(i).setCellStyle(subStyle);
				workbook.getSheetAt(2).autoSizeColumn(i);
			}

			Row header2 = sheet.createRow(rowNum - 1);
			header2.createCell(0).setCellValue("Workstream");
			header2.createCell(1).setCellValue("Prototype");

			header2.createCell(2).setCellValue("Onsite");
			header2.createCell(3).setCellValue("DDC");

			header2.createCell(4).setCellValue("Onsite");
			header2.createCell(5).setCellValue("DDC");

			header2.createCell(6).setCellValue("Onsite");
			header2.createCell(7).setCellValue("DDC");

			header2.createCell(8).setCellValue("Onsite");
			header2.createCell(9).setCellValue("DDC");

			header2.createCell(10).setCellValue("Onsite");
			header2.createCell(11).setCellValue("DDC");
			header2.createCell(12).setCellValue("Total");
			// Style totals
			for (int i = 0; i <= 12; i++) {
				header2.getCell(i).setCellStyle(subStyle);
			}
			while (it2.hasNext()) {

				Map.Entry ws2 = (Map.Entry) it2.next();

				try {
					ArrayList<Map<String, Object>> summaryList = (ArrayList<Map<String, Object>>) ws2
							.getValue();
					int startRow = rowNum;
					for (Map<String, Object> values : summaryList) {
						Row row = sheet.createRow(rowNum++);
						if (values.get("workstream") != null) {
							row.createCell(0).setCellValue(
									values.get("workstream") + "");
							row.createCell(1).setCellValue(
									values.get("prototype") + "");
							row.createCell(2).setCellValue(
									this.formatNumber(
											values.get("sumEffortWorkstream"),
											this.sf));
							row.createCell(3)
									.setCellValue(
											this.formatNumber(
													values.get("sumEffortDDC"),
													this.sf));
							row.createCell(4)
									.setCellValue(
											this.formatNumber(
													values.get("sumCSPRates"),
													this.df));
							row.createCell(5).setCellValue(
									this.formatNumber(
											values.get("sumCSPRatesDDC"),
											this.df));
							row.createCell(6)
									.setCellValue(
											this.formatNumber(
													values.get("sumRsrRates"),
													this.df));
							row.createCell(7).setCellValue(
									this.formatNumber(
											values.get("sumRsrRatesDDC"),
											this.df));
							row.createCell(8).setCellValue(
									this.formatNumber(
											values.get("marginWorkstream"),
											this.df));
							row.createCell(9).setCellValue(
									this.formatNumber(values.get("marginDDc"),
											this.df));
							row.createCell(10)
									.setCellValue(
											this.formatNumber(
													values.get("RateHrOnsite"),
													this.df));
							row.createCell(11).setCellValue(
									this.formatNumber(values.get("RateHrDDC"),
											this.df));
							row.createCell(12)
									.setCellValue(
											this.formatNumber(
													values.get("RateHrTotal"),
													this.df));
							// Style currency
							for (int j = 0; j <= 4; j++) {
								row.getCell(j).setCellStyle(simpleStyle);
							}
							for (int j = 4; j <= 12; j++) {
								row.getCell(j).setCellStyle(currencyStyle);
							}
						} else {
							row.createCell(0).setCellValue("");
							row.createCell(1).setCellValue("Total");
							row.createCell(2).setCellValue(
									this.formatNumber(values
											.get("totalEffortWorkstreamRates"),
											this.sf));
							row.createCell(3).setCellValue(
									this.formatNumber(
											values.get("totalEffortDDCRates"),
											this.sf));
							row.createCell(4).setCellValue(
									this.formatNumber(values
											.get("totalSumCspWorkstreamRates"),
											this.df));
							row.createCell(5).setCellValue(
									this.formatNumber(
											values.get("totalSumCspDDCRates"),
											this.df));
							row.createCell(6).setCellValue(
									this.formatNumber(values
											.get("totalSumRsrWorkstreamRates"),
											this.df));
							row.createCell(7).setCellValue(
									this.formatNumber(
											values.get("totalSumRsrDDCRates"),
											this.df));
							row.createCell(8).setCellValue(
									this.formatNumber(values
											.get("totalSumMarginWorkstream"),
											this.df));
							row.createCell(9).setCellValue(
									this.formatNumber(
											values.get("totalSumMarginDDC"),
											this.df));
							row.createCell(10).setCellValue("");
							row.createCell(11).setCellValue("");
							row.createCell(12).setCellValue("");
							// Style totals
							for (int j = 0; j <= 12; j++) {
								row.getCell(j).setCellStyle(totalStyle);
							}
						}

					}
					sheet.addMergedRegion(new CellRangeAddress((startRow),
							(rowNum - 1), 0, 0));

				} catch (Exception e) {
					continue;
				}

			}
			rowNum = rowNum + 5;
		}

		Map<String, Object> grndtotalObj = (Map<String, Object>) dsList
				.get("TotalSummaryEfforts");
		Map<String, Object> grndtotal = (Map<String, Object>) grndtotalObj
				.get("TotalSummaryEfforts");

		// Add Grand total
		Row row = sheet.createRow((rowNum + 1));
		row.createCell(0).setCellValue("Grand Total");
		row.createCell(1).setCellValue("Effort (Hr)");

		row.createCell(2).setCellValue("CSP Total");
		row.createCell(3).setCellValue("RSR Total");

		row.createCell(4).setCellValue("Margin Total");
		row.createCell(5).setCellValue("Rate/Hour");
		for (int j = 0; j <= 5; j++) {
			row.getCell(j).setCellStyle(subStyle);
		}

		Row grandrow = sheet.createRow((rowNum + 2));
		grandrow.createCell(0).setCellValue("Grand Total");
		grandrow.createCell(1).setCellValue(
				this.formatNumber(grndtotal.get("GrandTotalEfforts"), this.df));
		grandrow.createCell(2).setCellValue(
				this.formatNumber(grndtotal.get("GrandTotalCSP"), this.sf));
		grandrow.createCell(3).setCellValue(
				this.formatNumber(grndtotal.get("GrandTotalRSR"), this.sf));
		grandrow.createCell(4).setCellValue(
				this.formatNumber(grndtotal.get("GrandTotalMargin"), this.sf));
		grandrow.createCell(5).setCellValue(
				this.formatNumber(grndtotal.get("GrandRatePerHr"), this.sf));
		row.getCell(0).setCellStyle(simpleStyle);
		for (int j = 1; j <= 5; j++) {
			row.getCell(j).setCellStyle(currencyStyle);
		}
		try {
			sheet.addMergedRegion(new CellRangeAddress(((rowNum + 2)),
					((rowNum + 2)), 0, 0));
		} catch (Exception e) {
		}

	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition",
				"attachment;filename=\"Summary.xls\"");

		// initialize styles
		initStyles(workbook);

		// Get model from controller(project management)
		Map<String, Object> Object = (Map<String, Object>) model
				.get("DetailedSummary");

		// Generate Detailed Summary
		Map<String, List> dsList = (Map<String, List>) Object.get("detailed");
		detailedSummary(workbook, dsList);

		Map<String, List> effort = (Map<String, List>) Object.get("effort");
		Map<String, Object> effortGroup = (Map<String, Object>) Object
				.get("effortGroup");
		List workstream = (List) Object.get("workstream");
		List prototype = (List) Object.get("prototype");

		detailedSummaryEffort(workbook, effort, effortGroup, workstream,
				prototype);

		Map<String, Object> sumeffort = (Map<String, Object>) Object
				.get("sumeffort");
		allSummaryEffort(workbook, sumeffort);

		// Write all to output stream
		workbook.write(response.getOutputStream());
	}

	public CellStyle getSimpleStyle() {
		return simpleStyle;
	}

	public void setSimpleStyle(CellStyle simpleStyle) {
		this.simpleStyle = simpleStyle;
	}
}