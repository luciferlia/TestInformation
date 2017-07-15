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
	 * 解析Excel文件中的内容
	 * 
	 * @param excelFile
	 *            读取文件对象
	 * @param rowNum
	 *            从第几行开始读，如果有一行表头则从第二行开始读
	 * @return 返回数组列表
	 */
	public static Sheet readExcel(File excelFile, int line) {
		// 创建一个list 用来存储读取的内容

		Workbook rwb = null;
		Cell cell = null;
		Sheet sheet = null;
		// 创建输入流
		InputStream stream = null;
		try {
			stream = new FileInputStream(excelFile);
			// 获取Excel文件对象
			rwb = Workbook.getWorkbook(stream);
			// 获取文件的指定工作表 默认的第一个
			sheet = rwb.getSheet(line - 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 返回值集合
		return sheet;
	}

	/**
	 * 读取Excel文件中内容
	 * 
	 * @param excelFile文件路径
	 * @param line
	 *            表示从Excel中第几个sheet开始读
	 * @param col
	 *            表示从第几行开始读
	 * @return
	 */
	public static XSSFSheet read2007Excel(File excelFile, int line) {

		// 创建一个list 用来存储读取的内容

		XSSFWorkbook rwb = null;
		XSSFSheet sheet = null;
		// 创建输入流
		InputStream stream = null;
		try {
			stream = new FileInputStream(excelFile);
			// 获取Excel文件对象
			rwb = new XSSFWorkbook(stream);
			// 获取文件的指定工作表 默认的第一个
			sheet = rwb.getSheetAt(line - 1);
			// 行数(表头的目录不需要，从1开始)
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 返回值集合
		return sheet;
	}

	public static ArrayList<Case> write2003Case(Sheet sheet, int rowNum) {
		ArrayList<Case> cases = new ArrayList<Case>();
		// 行数(表头的目录不需要，从1开始)
		for (int i = rowNum - 1; i < sheet.getRows(); i++) {
			Case c = new Case();
			String str[] = new String[sheet.getColumns()];
			for (int j = 0; j < sheet.getColumns(); j++) {

				Cell name = sheet.getCell(j, rowNum - 2);
				System.out.println(name.getContents());
				if (name.getContents().toString().contains("分类") && sheet.getCell(j, i) != null) {
					c.setClassification(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("模块") && sheet.getCell(j, i) != null) {
					c.setCaseName(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("测试项") && sheet.getCell(j, i) != null) {
					c.setTestItem(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("用例编号") && sheet.getCell(j, i) != null) {
					c.setNum(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("重要等级") && sheet.getCell(j, i) != null) {
					c.setLevel(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("测试内容")
						|| name.getContents().toString().contains("用例描述") && sheet.getCell(j, i) != null) {
					c.setDescription(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("预置条件") && sheet.getCell(j, i) != null) {
					c.setEnvironment(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("测试步骤") && sheet.getCell(j, i) != null) {
					c.setStep(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("预期结果") && sheet.getCell(j, i) != null) {
					c.setExpectResult(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("测试结果") && sheet.getCell(j, i) != null) {
					c.setTestResult(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("备注") && sheet.getCell(j, i) != null) {
					c.setRemark(sheet.getCell(j, i).getContents());
				}
				if (name.getContents().toString().contains("建议") && sheet.getCell(j, i) != null) {
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
	 * 将读取到的内容存入到用例对象中
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
				if (name.toString().contains("分类") && row.getCell(m) != null) {
					c.setClassification(row.getCell(m).toString());
				}
				if (name.toString().contains("模块") && row.getCell(m) != null) {
					c.setCaseName(row.getCell(m).toString());
				}
				if (name.toString().contains("测试项") && row.getCell(m) != null) {
					c.setTestItem(row.getCell(m).toString());
				}
				if (name.toString().contains("用例编号") && row.getCell(m) != null) {
					c.setNum(row.getCell(m).toString());
				}
				if (name.toString().contains("重要等级") && row.getCell(m) != null) {
					c.setLevel(row.getCell(m).toString());
				}
				if (name.toString().contains("测试内容") || name.toString().contains("用例描述") && row.getCell(m) != null) {
					c.setDescription(row.getCell(m).toString());
				}
				if (name.toString().contains("预置条件") && row.getCell(m) != null) {
					c.setEnvironment(row.getCell(m).toString());
				}
				if (name.toString().contains("测试步骤") && row.getCell(m) != null) {
					c.setStep(row.getCell(m).toString());
				}
				if (name.toString().contains("预期结果") && row.getCell(m) != null) {
					c.setExpectResult(row.getCell(m).toString());
				}
				if (name.toString().contains("测试结果") && row.getCell(m) != null) {
					c.setTestResult(row.getCell(m).toString());
				}
				if (name.toString().contains("备注") && row.getCell(m) != null) {
					c.setRemark(row.getCell(m).toString());
				}
				if (name.toString().contains("建议") && row.getCell(m) != null) {
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
		// str[]={"阶段","版本","发布时间","测试项","内容","用例","人力","人数","需缺","需求","复用","开始","结束","备注"};
		List<String[]> list = new ArrayList<String[]>();
		// 行数(表头的目录不需要，从1开始)
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
		// ArrayList<Case> list=read2007Excel(new File("D:\\TP用例.xlsx"));
		//
		// System.out.println("用例编号："+list.get(0).getNum());
		// System.out.println("模块"+list.get(0).getCaseName());
		// System.out.println("用例描述"+list.get(0).getDescription());
		// System.out.println("类型"+list.get(0).getClassification());
		// System.out.println("预置条件"+list.get(0).getEnvironment());
		// System.out.println("预期结果："+list.get(0).getExpectResult());
		// System.out.println("测试步骤："+list.get(0).getStep());
		List<String[]> strs = write2003(
				readExcel(new File("C:\\Users\\huangmingliang\\Desktop\\联想客户策略模板_20170509192944.xls"), 1), 2);
		for (String[] str : strs) {
			for (String s : str) {
				System.out.print(s + "-");
			}
			System.out.println();
		}
	}

}
