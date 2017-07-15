package com.wind.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wind.entity.Case;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	/**
	 * ����Excel�ļ��е�����
	 * 
	 * @param excelFile
	 *            ��ȡ�ļ�����
	 * @param rowNum
	 *            �ӵڼ��п�ʼ���������һ�б�ͷ��ӵڶ��п�ʼ��
	 * @return ���������б�
	 */
	public static Sheet readExcel(File excelFile, int line) {
		// ����һ��list �����洢��ȡ������

		Workbook rwb = null;
		Cell cell = null;
		Sheet sheet = null;
		// ����������
		InputStream stream = null;
		try {
			stream = new FileInputStream(excelFile);
			// ��ȡExcel�ļ�����
			rwb = Workbook.getWorkbook(stream);
			// ��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��
			sheet = rwb.getSheet(line - 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ����ֵ����
		return sheet;
	}

	/**
	 * ��ȡExcel�ļ�������
	 * 
	 * @param excelFile�ļ�·��
	 * @param line
	 *            ��ʾ��Excel�еڼ���sheet��ʼ��
	 * @param col
	 *            ��ʾ�ӵڼ��п�ʼ��
	 * @return
	 */
	public static XSSFSheet read2007Excel(File excelFile, int line) {

		// ����һ��list �����洢��ȡ������

		XSSFWorkbook rwb = null;
		XSSFSheet sheet = null;
		// ����������
		InputStream stream = null;
		try {
			stream = new FileInputStream(excelFile);
			// ��ȡExcel�ļ�����
			rwb = new XSSFWorkbook(stream);
			// ��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��
			sheet = rwb.getSheetAt(line - 1);
			// ����(��ͷ��Ŀ¼����Ҫ����1��ʼ)
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ����ֵ����
		return sheet;
	}

	public static ArrayList<Case> write2003Case(Sheet sheet, int rowNum) {
		ArrayList<Case> cases = new ArrayList<Case>();
		// ����(��ͷ��Ŀ¼����Ҫ����1��ʼ)
		for (int i = rowNum - 1; i < sheet.getRows(); i++) {
			Case c = new Case();
			String str[] = new String[sheet.getColumns()];
			for (int j = 0; j < sheet.getColumns(); j++) {

				Cell name = sheet.getCell(j, rowNum - 2);
				System.out.println(name.getContents());
				if (name.getContents().toString().contains("����") && sheet.getCell(j, i) != null) {
					c.setClassification(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("ģ��") && sheet.getCell(j, i) != null) {
					c.setCaseName(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("������") && sheet.getCell(j, i) != null) {
					c.setTestItem(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("�������") && sheet.getCell(j, i) != null) {
					c.setNum(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("��Ҫ�ȼ�") && sheet.getCell(j, i) != null) {
					c.setLevel(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("��������")
						|| name.getContents().toString().contains("��������") && sheet.getCell(j, i) != null) {
					c.setDescription(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("Ԥ������") && sheet.getCell(j, i) != null) {
					c.setEnvironment(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("���Բ���") && sheet.getCell(j, i) != null) {
					c.setStep(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("Ԥ�ڽ��") && sheet.getCell(j, i) != null) {
					c.setExpectResult(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("���Խ��") && sheet.getCell(j, i) != null) {
					c.setTestResult(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("��ע") && sheet.getCell(j, i) != null) {
					c.setRemark(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("����") && sheet.getCell(j, i) != null) {
					c.setAdvidceTime(sheet.getCell(j, i).getContents());
				}
			}
			if (c.getNum() != null) {
				cases.add(c);
			}
		}
		return cases;
	}

	/**
	 * ����ȡ�������ݴ��뵽����������
	 * 
	 * @param sheet
	 * @param line
	 * @param col
	 * @return
	 */
	public static ArrayList<Case> write2007Case(XSSFSheet sheet, int col) {
		ArrayList<Case> cases = new ArrayList<Case>();
		XSSFRow row;
		for (int i = col - 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			Case c = new Case();
			row = sheet.getRow(i);
			for (int m = 0; m < row.getPhysicalNumberOfCells(); m++) {
				XSSFCell name = sheet.getRow(col - 2).getCell(m);
				if (name.toString().contains("����") && row.getCell(m) != null) {
					c.setClassification(row.getCell(m).toString());
				}
				if (name.toString().contains("ģ��") && row.getCell(m) != null) {
					c.setCaseName(row.getCell(m).toString());
				}
				if (name.toString().contains("������") && row.getCell(m) != null) {
					c.setTestItem(row.getCell(m).toString());
				}
				if (name.toString().contains("�������") && row.getCell(m) != null) {
					c.setNum(row.getCell(m).toString());
				}
				if (name.toString().contains("��Ҫ�ȼ�") && row.getCell(m) != null) {
					c.setLevel(row.getCell(m).toString());
				}
				if (name.toString().contains("��������") || name.toString().contains("��������") && row.getCell(m) != null) {
					c.setDescription(row.getCell(m).toString());
				}
				if (name.toString().contains("Ԥ������") && row.getCell(m) != null) {
					c.setEnvironment(row.getCell(m).toString());
				}
				if (name.toString().contains("���Բ���") && row.getCell(m) != null) {
					c.setStep(row.getCell(m).toString());
				}
				if (name.toString().contains("Ԥ�ڽ��") && row.getCell(m) != null) {
					c.setExpectResult(row.getCell(m).toString());
				}
				if (name.toString().contains("���Խ��") && row.getCell(m) != null) {
					c.setTestResult(row.getCell(m).toString());
				}
				if (name.toString().contains("��ע") && row.getCell(m) != null) {
					c.setRemark(row.getCell(m).toString());
				}
				if (name.toString().contains("����") && row.getCell(m) != null) {
					c.setAdvidceTime(row.getCell(m).toString());
				}
			}
			if (c.getNum() != null) {
				System.out.println("saaaaaaa:" + c.getNum());
				cases.add(c);
			}
		}
		return cases;
	}

	public static List<String[]> write2007(XSSFSheet sheet, int col) {
		XSSFRow row;
		List<String[]> list = new ArrayList<String[]>();
		for (int i = col - 1; i < sheet.getPhysicalNumberOfRows(); i++) {

			row = sheet.getRow(i);
			String str[] = new String[row.getPhysicalNumberOfCells()];
			for (int m = 0; m < row.getPhysicalNumberOfCells(); m++) {
				XSSFCell name = sheet.getRow(col - 2).getCell(m);
				if (row.getCell(m) != null) {
					str[m] = name + ":" + row.getCell(m).toString();
				} else {
					str[m] = name + ":";
				}
			}
			list.add(str);
		}

		return list;
	}

	public static List<String[]> write2003(Sheet sheet, int rowNum) {
		// String
		// str[]={"�׶�","�汾","����ʱ��","������","����","����","����","����","��ȱ","����","����","��ʼ","����","��ע"};
		List<String[]> list = new ArrayList<String[]>();
		// ����(��ͷ��Ŀ¼����Ҫ����1��ʼ)
		for (int i = rowNum - 1; i < sheet.getRows(); i++) {
			String str[] = new String[sheet.getColumns()];
			for (int j = 0; j < sheet.getColumns(); j++) {

				Cell name = sheet.getCell(j, rowNum - 2);
				if (sheet.getCell(j, i) != null) {
					str[j] = name.getContents() + ":" + sheet.getCell(j, i).getContents();
				} else {
					str[j] = name.getContents() + ":";
				}
			}
			list.add(str);
		}

		return list;
	}

	public static void main(String[] args) {
		// ArrayList<Case> list=read2007Excel(new File("D:\\TP����.xlsx"));
		//
		// System.out.println("������ţ�"+list.get(0).getNum());
		// System.out.println("ģ��"+list.get(0).getCaseName());
		// System.out.println("��������"+list.get(0).getDescription());
		// System.out.println("����"+list.get(0).getClassification());
		// System.out.println("Ԥ������"+list.get(0).getEnvironment());
		// System.out.println("Ԥ�ڽ����"+list.get(0).getExpectResult());
		// System.out.println("���Բ��裺"+list.get(0).getStep());
		List<String[]> strs = write2003(
				readExcel(new File("C:\\Users\\huangmingliang\\Desktop\\����ͻ�����ģ��_20170509192944.xls"), 1), 2);
		for (String[] str : strs) {
			for (String s : str) {
				System.out.print(s + "-");
			}
			System.out.println();
		}
	}

}
