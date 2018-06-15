package com.scm.services.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bulls.scm.common.vo.StudentVO;

public class ExportUtil {

	enum FileType {
		XLS,TXT,PDF
	}
	private static int rownum =0;
	private static int headerColIndex;
	
	public static void exporttoXLS(List<StudentVO> students) {
		if(students == null)
			return;
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet =workBook.createSheet();
		String[] headers = {"Session,MIS_ITI_Code","State_Registration_Number","Appliction_Form_Number","Admission_Date","Trainee_Name","Mobile_Number","Email_ID","Date_Of_Birth","Gender","Category","Horizontal_Category","Father_Guardian_Name","Mother_Name","Admission_Given_in_Category","Trainee_Type","Trade","Shift","Unit","Minority_Category","UID_Number","Highest_Qualification","Is_Trainee_Dual_Mode","Remarks"};
		rownum = 0;
		HSSFRow headerRow = sheet.createRow(rownum);
		rownum++;
		headerColIndex = 0;
		Arrays.asList(headers).forEach(header -> {
			headerRow.createCell(headerColIndex).setCellValue(header);
			headerColIndex++;
		});
		
		students.forEach(student ->{
			HSSFRow row = sheet.createRow(rownum);
			row.createCell(0).setCellValue(student.getAcademicYear().getName());
			row.createCell(1).setCellValue(student.getAdmissionNo());
			row.createCell(2).setCellValue(student.getRegistrationNo());
			row.createCell(3).setCellValue(student.getAdmissionNo());
			row.createCell(4).setCellValue(student.getAdmissionDate());
			row.createCell(5).setCellValue(student.getName());
			row.createCell(6).setCellValue(student.getMobileNo()!=null?student.getMobileNo().toString():null);
			row.createCell(7).setCellValue(student.getEmail());
			row.createCell(8).setCellValue(student.getDob());
			row.createCell(9).setCellValue(student.getGender());
			row.createCell(10).setCellValue(student.getCaste().getName());
			row.createCell(11).setCellValue(student.getCaste().getName());
			row.createCell(12).setCellValue(student.getFatherName());
			row.createCell(13).setCellValue(student.getMotherName());
			row.createCell(14).setCellValue(student.getCaste().getName());
			row.createCell(15).setCellValue(student.getEmail());
			row.createCell(16).setCellValue(student.getType().getName());
			row.createCell(17).setCellValue(student.getTrade().getName());
			row.createCell(18).setCellValue(student.getShift());
			row.createCell(19).setCellValue(student.getUnit());
			row.createCell(20).setCellValue(student.getUID_Number());
			row.createCell(21).setCellValue(student.getHighestQualification());
			row.createCell(22).setCellValue(student.getDualMode());
			row.createCell(23).setCellValue(student.getIdentificationMarks());
			rownum++;
		});
		writeToFile(workBook);
	}

	public static void writeToFile(HSSFWorkbook workBook) {
		String file = "D:\\COE\\studentdetails.xls";
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
}
