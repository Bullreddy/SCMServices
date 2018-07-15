package com.scm.services.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.MediaType;

import com.bulls.scm.common.vo.StudentVO;
import com.sun.media.jfxmedia.logging.Logger;

public class ExportUtil {
	
	private static List<Field> fields = null;
	static{
		fields = new ArrayList<>();
		fields.add(new Field("Session", new String(), Boolean.TRUE));
		fields.add(new Field("MIS_ITI_Code", new String(), Boolean.TRUE));
		fields.add(new Field("State_Registration_Number", new String(), Boolean.FALSE));
		fields.add(new Field("Appliction_Form_Number", new String(), Boolean.TRUE));
		fields.add(new Field("Admission_Date", new Date(), Boolean.TRUE));
		fields.add(new Field("Trainee_Name", new String(), Boolean.TRUE));
		fields.add(new Field("Mobile_Number", new String(), Boolean.FALSE));
		fields.add(new Field("Email_ID", new String(), Boolean.FALSE));
		fields.add(new Field("Date_Of_Birth", new Date(), Boolean.TRUE));
		fields.add(new Field("Gender", new String(), Boolean.TRUE));
		fields.add(new Field("Category", new String(), Boolean.TRUE));
		fields.add(new Field("Horizontal_Category", new String(), Boolean.TRUE));
		fields.add(new Field("Father_Guardian_Name", new String(), Boolean.TRUE));
		fields.add(new Field("Mother_Name", new String(), Boolean.TRUE));
		fields.add(new Field("Admission_Given_in_Category", new String(), Boolean.TRUE));
		fields.add(new Field("Trainee_Type", new String(), Boolean.TRUE));
		fields.add(new Field("Trade", new String(), Boolean.TRUE));
		fields.add(new Field("Shift", new String(), Boolean.TRUE));
		fields.add(new Field("Unit", new String(), Boolean.TRUE));
		fields.add(new Field("Minority_Category", new String(), Boolean.TRUE));
		fields.add(new Field("UID_Number", new String(), Boolean.TRUE));
		fields.add(new Field("Highest_Qualification", new String(), Boolean.FALSE));
		fields.add(new Field("Is_Trainee_Dual_Mode", new String(), Boolean.FALSE));
		fields.add(new Field("Remarks", new String(), Boolean.FALSE));
	}

	enum FileType {
		XLS,TXT,PDF
	}
	private static int rownum =0;
	private static int headerColIndex;
	
	public static HSSFWorkbook exporttoXLS(List<StudentVO> students, HttpServletResponse response) {
		if(students == null)
			return null;
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet =workBook.createSheet();
		rownum = 0;
		HSSFRow headerRow = sheet.createRow(rownum);
		rownum++;
		headerColIndex = 0;
		fields.forEach(field -> {
			headerRow.createCell(headerColIndex).setCellValue(getHeaderName(field));
			setHeaderProperties(workBook,headerRow.getCell(headerColIndex),field);
			headerColIndex++;
		});
		
		students.forEach(student ->{
			HSSFRow row = sheet.createRow(rownum);
			row.createCell(0).setCellValue(student.getAcademicYear().getName());
			//row.createCell(1).setCellValue();
			//row.createCell(2).setCellValue(student.getRegistrationNo());
			row.createCell(3).setCellValue(student.getAdmissionNo());
			
			row.createCell(4).setCellType(getCellTypeAndSetProperties(student.getAdmissionDate(), workBook, row.getCell(4)));
			row.getCell(4).setCellValue(student.getAdmissionDate()!=null?student.getAdmissionDate().toString():"");
			
			row.createCell(5).setCellValue(student.getName());
			row.createCell(6).setCellValue(student.getMobileNo()!=null?student.getMobileNo().toString():null);
			row.createCell(7).setCellValue(student.getEmail());
			
			row.createCell(8).setCellType(getCellTypeAndSetProperties(student.getAdmissionDate(), workBook, row.getCell(8)));
			System.out.println("welcome"+student.getDob());
			row.getCell(8).setCellValue(student.getDob().toString());
			
			row.createCell(9).setCellValue(student.getGender());
			row.createCell(10).setCellValue(student.getCaste().getName());
			row.createCell(11).setCellValue(student.getCategory());
			row.createCell(12).setCellValue(student.getFatherName());
			row.createCell(13).setCellValue(student.getMotherName());
			row.createCell(14).setCellValue(student.getCaste().getName());
			row.createCell(15).setCellValue(student.getType().getName());
			row.createCell(16).setCellValue(student.getTrade().getName());
			row.createCell(17).setCellValue(student.getShift());
			row.createCell(18).setCellValue(student.getUnit());
			row.createCell(19).setCellValue("NA");
			row.createCell(20).setCellValue(student.getUID_Number());
			row.createCell(21).setCellValue(student.getHighestQualification());
			row.createCell(22).setCellValue(student.getDualMode());
			row.createCell(23).setCellValue(student.getIdentificationMarks());
			rownum++;
		});
		return workBook;
		//downloadFile(workBook,response);
	}

	private static CellType getCellTypeAndSetProperties(Object dataType,HSSFWorkbook wb,Cell cell) {
		if(dataType instanceof java.lang.String) {
			return CellType.STRING;
		}else if(dataType instanceof Date) {
			 CellStyle cellStyle = wb.createCellStyle();
			 CreationHelper createHelper = wb.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("dd-MMM-yy");
			 cellStyle.setDataFormat(dateFormat);
			 cell.setCellStyle(cellStyle);
			 return CellType.STRING;
		}else {
			 return CellType.STRING;
		}
	}
	private static void downloadFile(HSSFWorkbook workBook, HttpServletResponse response) {
		
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"testExcel.xls\"");
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		   // response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		    
			workBook.write(response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeToFile(HSSFWorkbook workBook) {
		String file = "E:\\studentdetails.xls";
        try{
            FileOutputStream fos = new FileOutputStream(file);
            workBook.write(fos);
            fos.flush();
            fos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Invalid directory or file not found");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error occurred while writting excel file to directory");
        }
	}
	
	private static String getHeaderName(Field field) {
		if(field.isMandatory())
			return field.getFieldName().concat("*");
		return field.getFieldName();
	}
	
	private static void setHeaderProperties(HSSFWorkbook wb, Cell row, Field field) {
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(field.isMandatory()?IndexedColors.YELLOW.getIndex():IndexedColors.WHITE.getIndex());

	}
}
