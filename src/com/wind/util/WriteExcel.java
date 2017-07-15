package com.wind.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteExcel {
	WritableWorkbook workbook;
	WritableSheet sheet;
	String path, sheetName;
	OutputStream os;
	private WritableCellFormat format1, format2, format3, format4;
	private WritableFont font1, font2, font3;

	/**
	 * 初始化工作表
	 * 
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名
	 * @return
	 */

	public WritableSheet createSheet(String filePath, String fileName) {
		this.path = filePath;
		this.sheetName = fileName;
		try {
			os = new FileOutputStream(path);
			workbook = Workbook.createWorkbook(new File(path));
			sheet = workbook.createSheet(sheetName, 0);
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;

	}

	/**
	 * 初始化工作表
	 * 
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名
	 * @return
	 */

	public WritableSheet createSheet(WritableWorkbook workbook, String fileName, int num) {
		this.sheetName = fileName;
		try {
			sheet = workbook.createSheet(sheetName, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;

	}

	/**
	 * 创建工作空间
	 * 
	 * @param path
	 * @return
	 */
	public WritableWorkbook createWorkbook(String path) {
		try {
			os = new FileOutputStream(path);
			workbook = Workbook.createWorkbook(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return workbook;

	}

	/**
	 * 初始化工作表
	 * 
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名
	 * @return
	 */

	public WritableSheet createSheet(OutputStream outputStream, String fileName) {
		// this.path=filePath;
		this.sheetName = fileName;
		try {
			os = new FileOutputStream(path);
			workbook = Workbook.createWorkbook(outputStream);
			sheet = workbook.createSheet(sheetName, 0);
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;

	}

	public void setColumnSize(int num, int size) {
		sheet.setColumnView(num, size);

	}

	public void setColumnSize(WritableSheet sheet, int num, int size) {
		sheet.setColumnView(num, size);

	}

	public void init() {
		try {

			font1 = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD, false);
			font2 = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD, false);
			font3 = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD, false);
			format1 = new WritableCellFormat(font1);
			// format1.setBackground(Colour.PALE_BLUE);
			format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			format1.setAlignment(Alignment.CENTRE);
			format1.setVerticalAlignment(VerticalAlignment.CENTRE);
			format1.setBackground(Colour.ICE_BLUE);
			format2 = new WritableCellFormat(font2);
			format2.setAlignment(Alignment.CENTRE);
			format2.setBorder(Border.ALL, BorderLineStyle.THIN);
			format2.setVerticalAlignment(VerticalAlignment.CENTRE);
			format2.setWrap(true);
			format4 = new WritableCellFormat(font2);
			format4.setAlignment(Alignment.LEFT);
			format4.setBorder(Border.ALL, BorderLineStyle.THIN);
			format4.setVerticalAlignment(VerticalAlignment.CENTRE);
			format4.setWrap(true);
			font3.setColour(Colour.RED);
			format3 = new WritableCellFormat(font3);
			format3.setBorder(Border.ALL, BorderLineStyle.THIN);
			format3.setAlignment(Alignment.CENTRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入一行数据
	 * 
	 * @param sheet
	 *            工作表
	 * @param col
	 *            从哪一列开始
	 * @param row
	 *            行号
	 * @param data
	 *            数据
	 * @param format
	 *            风格
	 */

	public void insertRowData(WritableSheet sheet, int col, int row, ArrayList<String> data,
			WritableCellFormat format) {
		try {
			Label label;
			for (int i = 0; i < data.size(); i++) {
				label = new Label(i + col, row, data.get(i), format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将数据写入到Excel表格中
	 * 
	 * @param data
	 * @param savefilePath
	 * @param sheetName
	 * @throws WriteException
	 * @throws RowsExceededException
	 */

	public void writeExcel(ArrayList<ArrayList<String>> data, String savefilePath, String sheetName)
			throws RowsExceededException {
		init();
		sheet = createSheet(savefilePath, sheetName);
		for (int i = 0; i < 5; i++) {
			setColumnSize(i, 13);
		}
		for (int i = 5; i < 9; i++) {
			setColumnSize(i, 25);
		}
		for (int i = 9; i < 11; i++) {
			setColumnSize(i, 13);
		}
		setColumnSize(11, 25);
		insertRowData(sheet, 0, 0, data.get(0), format1);
		sheet.setRowView(0, 500);// 标题--设置行高
		ArrayList<String> list = null;
		for (int i = 1; i < data.size(); i++) {
			insertRowData(sheet, 0, i, data.get(i), format2);
		}
		// 根据内容自动设置列宽
		/*
		 * CellView cellView = new CellView(); cellView.setAutosize(true); //
		 * 设置自动大小 for (int j = 0; j < data.size(); j++) { sheet.setColumnView(j,
		 * cellView);// 根据内容自动设置列宽 }
		 */

		try {
			workbook.write();
			workbook.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 将数据写入表格中，并初始化单元格大小
	 * 
	 * @param data
	 * @param savefilePath
	 * @param sheetName
	 * @throws RowsExceededException
	 */
	public void writeExcel2(ArrayList<ArrayList<String>> data, String savefilePath, String sheetName)
			throws RowsExceededException {
		init();
		sheet = createSheet(savefilePath, sheetName);
		// 设置宽度
		for (int i = 0; i < 4; i++) {
			setColumnSize(i, 13);
		}
		setColumnSize(4, 40);
		for (int i = 5; i < 20; i++) {
			setColumnSize(i, 13);
		}
		insertRowData(sheet, 0, 0, data.get(0), format1);
		sheet.setRowView(0, 500);// 标题--设置行高
		ArrayList<String> list = null;
		for (int i = 1; i < data.size(); i++) {
			insertRowData(sheet, 0, i, data.get(i), format2);
		}
		// 根据内容自动设置列宽
		/*
		 * CellView cellView = new CellView(); cellView.setAutosize(true); //
		 * 设置自动大小 for (int j = 0; j < data.size(); j++) { sheet.setColumnView(j,
		 * cellView);// 根据内容自动设置列宽 }
		 */
		try {
			workbook.write();
			workbook.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void writeExcel(ArrayList<ArrayList<String>> data, OutputStream outputStream, String sheetName2)
			throws RowsExceededException {
		init();
		sheet = createSheet(outputStream, sheetName);
		for (int i = 0; i < 20; i++) {
			setColumnSize(i, 20);
		}
		insertRowData(sheet, 0, 0, data.get(0), format1);
		sheet.setRowView(0, 500);// 标题--设置行高
		ArrayList<String> list = null;
		for (int i = 1; i < data.size(); i++) {
			insertRowData(sheet, 0, i, data.get(i), format2);
		}
		// 根据内容自动设置列宽
		/*
		 * CellView cellView = new CellView(); cellView.setAutosize(true); //
		 * 设置自动大小 for (int j = 0; j < data.size(); j++) { sheet.setColumnView(j,
		 * cellView);// 根据内容自动设置列宽 }
		 */
		try {
			workbook.write();
			workbook.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void writeExcel(WritableWorkbook workbook, WritableSheet sheet, ArrayList<ArrayList<String>> data)
			throws RowsExceededException {
		init();
		for (int i = 0; i < 20; i++) {
			setColumnSize(sheet, i, 20);
		}
		insertRowData(sheet, 0, 0, data.get(0), format1);
		sheet.setRowView(0, 500);// 标题--设置行高
		ArrayList<String> list = null;
		for (int i = 1; i < data.size(); i++) {
			insertRowData(sheet, 0, i, data.get(i), format2);
		}
		// 根据内容自动设置列宽
		/*
		 * CellView cellView = new CellView(); cellView.setAutosize(true); //
		 * 设置自动大小 for (int j = 0; j < data.size(); j++) { sheet.setColumnView(j,
		 * cellView);// 根据内容自动设置列宽 }
		 */
	}

	public void close() {
		try {
			workbook.write();
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
