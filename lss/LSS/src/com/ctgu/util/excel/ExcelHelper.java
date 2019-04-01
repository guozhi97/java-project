package com.ctgu.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.bean.BeanException;
import jodd.bean.BeanUtil;
import jodd.datetime.JDateTime;
import jodd.util.StringUtil;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Excel助手类
 *
 * @createTime: 2012-4-19 上午10:14:46
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @changesSum: 
 * 
 */
public class ExcelHelper {
	private static ExcelHelper helper = null;
	
	private ExcelHelper() {
		
	}
	
	public static synchronized ExcelHelper getInstanse() {
		if(helper == null) {
			helper = new ExcelHelper();
		}
		return helper;
	}
	
	/**
	 * 将Excel文件导入到list对象
	 * @param head	文件头信息
	 * @param file	导入的数据源
	 * @param cls	保存当前数据的对象
	 * @return
	 * @return List
	 * 2012-4-19 下午01:17:48
	 */
	public List importToObjectList(ExcelHead head, File file, Class cls) {
		List contents = null;
		FileInputStream fis;
		// 根据excel生成list类型的数据
		List<List> rows;
		try {
			fis = new FileInputStream(file);
			rows = excelFileConvertToList(fis);
			
			// 删除头信息
			for (int i = 0; i < head.getRowCount(); i++) {
				rows.remove(0);
			}
			
			// 将表结构转换成Map
			Map<Integer, ExcelColumn> excelHeadMap = convertExcelHeadToMap(head.getColumns());
			// 构建为对象
			contents = buildDataObject(excelHeadMap, head.getColumnsConvertMap(), rows, cls);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
		return contents;
	}
	
	/**
	 * 将Excel文件导入到list对象
	 * @param head	文件头信息
	 * @param file	导入的数据源
	 * @param cls	保存当前数据的对象
	 * @return
	 * @return List
	 * 2012-4-19 下午01:17:48
	 */
	public List importToObjectList(ExcelHead head, InputStream ips, Class cls) {
		List contents = null;
		// 根据excel生成list类型的数据
		List<List> rows;
		try {
			rows = excelFileConvertToList(ips);
			
			// 删除头信息
			if(!rows.isEmpty() && rows.size()>0){
				rows.remove(0);
			}
			
			// 将表结构转换成Map
			Map<Integer, ExcelColumn> excelHeadMap = convertExcelHeadToMap(head.getColumns());
			// 构建为对象
			contents = buildDataObject(excelHeadMap, head.getColumnsConvertMap(), rows, cls);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return contents;
  	}
	
	/**
	 * 导出数据至Excel文件
	 * @param excelColumns	报表头信息
	 * @param excelHeadConvertMap	需要对数据进行特殊转换的列
	 * @param modelFile  模板Excel文件
	 * @param outputFile 导出文件
	 * @param dataList	导入excel报表的数据来源
	 * @return void
	 * 2012-4-19 上午10:04:30
	 */
	public static void exportExcelFile(ExcelHead head, File modelFile, File outputFile, List dataList) {
		// 读取导出excel模板
	    InputStream inp = null;
	    Workbook wb = null;
		try {
			//inp = new FileInputStream(modelFile);
			//wb = WorkbookFactory.create(inp);
			wb=new HSSFWorkbook();
			Sheet sheet = wb.getSheetAt(0);
			// 生成导出数据
			buildExcelData(wb, head, dataList);
			
			// 导出到文件中
		    FileOutputStream fileOut = new FileOutputStream(outputFile);
		    wb.write(fileOut);
		    fileOut.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}  catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 将报表结构转换成Map
	 * @param excelColumns
	 * @return void
	 * 2012-4-18 下午01:31:12
	 */
	public static Map<Integer, ExcelColumn> convertExcelHeadToMap(List<ExcelColumn> excelColumns) {
		Map<Integer, ExcelColumn> excelHeadMap = new HashMap<Integer, ExcelColumn>();
		for (ExcelColumn excelColumn : excelColumns) {
			if(StringUtil.isEmpty(excelColumn.getFieldName())) {
				continue;
			} else {
				excelHeadMap.put(excelColumn.getIndex(), excelColumn);
			}
		}
		return excelHeadMap;
	}
	
	/**
	 * 生成导出至Excel文件的数据
	 * @param sheet	工作区间
	 * @param excelColumns	excel表头
	 * @param excelHeadMap	excel表头对应实体属性
	 * @param excelHeadConvertMap	需要对数据进行特殊转换的列
	 * @param dataList		导入excel报表的数据来源
	 * @return void
	 * 2012-4-19 上午09:36:37
	 */
	public static void buildExcelData(Workbook wb, ExcelHead head, List dataList) {
		List<ExcelColumn> excelColumns = head.getColumns(); 
		Map<String, Map> excelHeadConvertMap = head.getColumnsConvertMap();
		
		// 将表结构转换成Map
		Map<Integer, ExcelColumn> excelHeadMap = convertExcelHeadToMap(excelColumns);
		
		// 从第几行开始插入数据
	    int startRow = head.getRowCount();
	    int order = 1;
	    String fieldName="";
	    int column=head.getColumns().size();
	    Sheet sheet=wb.createSheet();
	    sheet.setDefaultRowHeightInPoints((short)1*30);
	    
		//合并单元格,设置标题
		sheet.addMergedRegion(new CellRangeAddress(0, (short)startRow, 0, (short)(column-1)));
		Row row=sheet.createRow(0);
		Cell cell=row.createCell(0);
		cell.setCellValue(head.getTitle());
		
		//设置标题样式
		CellStyle titleStyle=head.getTitleStyle();
		cell.setCellStyle(titleStyle);
		
		//设置表头
		startRow++;
		row=sheet.createRow(startRow);
		CellStyle tableTitleStyle=head.getClumnStyle();
		for(int i=0;i<excelColumns.size();i++){
			sheet.setColumnWidth(i, (short)excelColumns.get(i).getWidth()*256);
			cell=row.createCell(i);
			cell.setCellValue(excelColumns.get(i).getFieldDispName());
			cell.setCellStyle(tableTitleStyle);
		}

	    Object valueObject=null;
	    CellStyle cellStyle=head.getCellStyle();
	    cellStyle.setWrapText(true);
	    for (Object obj : dataList) {
	    	startRow++;
	    	row = sheet.createRow(startRow);
	    	for (int j = 0; j < excelColumns.size(); j++) {
	    		cell = row.createCell(j);
	    		cell.setCellType(excelColumns.get(j).getType());
	    		fieldName = excelHeadMap.get(j).getFieldName();
	    		if(fieldName != null) {
	    			valueObject = BeanUtil.getProperty(obj, fieldName);
	    			
	    			// 如果存在需要转换的字段信息，则进行转换
	    			if(excelHeadConvertMap != null && excelHeadConvertMap.get(fieldName) != null) {
	    				valueObject = excelHeadConvertMap.get(fieldName).get(valueObject);
	    			}
	    			
	    			if(valueObject == null) {
	    				cell.setCellValue("");
	    			} else if (valueObject instanceof Integer) {
	    				cell.setCellValue((Integer)valueObject);
					}else if(valueObject instanceof Double){
						cell.setCellValue((Double)valueObject);
					} else if (valueObject instanceof String) {
						cell.setCellValue((String)valueObject);
					} else if (valueObject instanceof Date) {
						cell.setCellValue(new JDateTime((Date)valueObject).toString("YYYY-MM-DD"));
					} else {
						cell.setCellValue(valueObject.toString());
					}
	    		} else {
	    			cell.setCellValue(order++);
	    		}
	    		cell.setCellStyle(cellStyle);
			}
	    	//重新计算行高
	    	calcAndSetRowHeigt(row);
		}
	}
	
	/**
	 * 设置列表表头信息
	 * @param sheet:工作薄对象
	 * @param head:列表信心
	 * @param startRow：工作薄起始行
	 */
	public static int setHeadColumn(Sheet sheet,ExcelHead head,int startRow){
		List<ExcelColumn> excelColumns = head.getColumns(); 
		
		//设置表头
		startRow++;
		Row row=sheet.createRow(startRow);
		Cell cell=null;
		CellStyle tableTitleStyle=head.getClumnStyle();
		for(int i=0;i<excelColumns.size();i++){
			sheet.setColumnWidth(i, (short)excelColumns.get(i).getWidth()*256);
			cell=row.createCell(i);
			cell.setCellValue(excelColumns.get(i).getFieldDispName());
			cell.setCellStyle(tableTitleStyle);
		}
		return startRow;
	}
	
	/**
	 * 读取列表行信息
	 * @param sheet：当前工作薄
	 * @param head：表格头
	 * @param startRow：起始行
	 * @param dataList：列表数据
	 */
	public static int readCell(Sheet sheet,ExcelHead head,int startRow,List dataList){
		List<ExcelColumn> excelColumns = head.getColumns(); 
		Map<String, Map> excelHeadConvertMap = head.getColumnsConvertMap(); 
		// 将表结构转换成Map
		Map<Integer, ExcelColumn> excelHeadMap = convertExcelHeadToMap(excelColumns);
	    Object valueObject=null;
	    CellStyle cellStyle=head.getCellStyle();
	    cellStyle.setWrapText(true);
	    Row row =null;
	    Cell cell=null;
	    int order = 1;
	    String fieldName="";
	    for (Object obj : dataList) {
	    	startRow++;
	    	row = sheet.createRow(startRow);
	    	for (int j = 0; j < excelColumns.size(); j++) {
	    		cell = row.createCell(j);
	    		cell.setCellType(excelColumns.get(j).getType());
	    		fieldName = excelHeadMap.get(j).getFieldName();
	    		if(fieldName != null) {
	    			valueObject = BeanUtil.getProperty(obj, fieldName);
	    			
	    			// 如果存在需要转换的字段信息，则进行转换
	    			if(excelHeadConvertMap != null && excelHeadConvertMap.get(fieldName) != null) {
	    				valueObject = excelHeadConvertMap.get(fieldName).get(valueObject);
	    			}
	    			
	    			if(valueObject == null) {
	    				cell.setCellValue("");
	    			} else if (valueObject instanceof Integer) {
	    				cell.setCellValue((Integer)valueObject);
					}else if(valueObject instanceof Double){
						cell.setCellValue((Double)valueObject);
					} else if (valueObject instanceof String) {
						cell.setCellValue((String)valueObject);
					} else if (valueObject instanceof Date) {
						cell.setCellValue(new JDateTime((Date)valueObject).toString("YYYY-MM-DD"));
					} else {
						cell.setCellValue(valueObject.toString());
					}
	    		} else {
	    			cell.setCellValue(order++);
	    		}
	    		cell.setCellStyle(cellStyle);
			}
		}
	    return startRow;
	}
	
	/**
	 * 将Excel文件内容转换为List对象
	 * @param fis	excel文件
	 * @return	List<List> list存放形式的内容
	 * @throws IOException
	 * @return List<List>
	 * 2012-4-18 上午11:37:17
	 */
	public List<List> excelFileConvertToList(InputStream fis) throws Exception {
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sheet = wb.getSheetAt(0);
		
		List<List> rows = new ArrayList<List>();
		Object obj = null;
		Cell cell=null;
		int i=0;
		for (Row row : sheet) {
			if(null==row || isBlankRow(row)){
				continue;
			}
			List<Object> cells = new ArrayList<Object>();
			for(i=0;i<row.getLastCellNum();i++){
				cell=row.getCell(i);
				try{
		            switch (cell.getCellType()) {
			            case Cell.CELL_TYPE_NUMERIC: //0.数字日期类型
			            	if (DateUtil.isCellDateFormatted(cell)) {
			            		obj = new JDateTime(cell.getDateCellValue());
			            	} else {
			            		obj = cell.getNumericCellValue();
			            	}
			            	break;
		                case Cell.CELL_TYPE_STRING://1.字符串类型
		                    obj = cell.getRichStringCellValue().getString().trim();
		                    break;
		                case Cell.CELL_TYPE_FORMULA://2.公式类型
		                	obj = cell.getNumericCellValue();
		                	break;
		                case Cell.CELL_TYPE_BLANK://3.空白类型
		                	obj=null;
		                	break;
		                case Cell.CELL_TYPE_BOOLEAN://4.布尔类型
		                    obj = cell.getBooleanCellValue();
		                    break;
		                case Cell.CELL_TYPE_ERROR://error
		                	obj=null;
		                	break;
		                default:
		                	obj = null;
		                	break;
		            }
				} catch(Exception ex){
					obj=null;
				}
				cells.add(obj);
			}
			rows.add(cells);
	    }
		return rows;
	}
	
	/**
	 * 根据Excel生成数据对象
	 * @param excelHeadMap 表头信息
	 * @param excelHeadConvertMap 需要特殊转换的单元
	 * @param rows
	 * @param cls 
	 * @return void
	 * 2012-4-18 上午11:39:43
	 */
	public List buildDataObject(Map<Integer, ExcelColumn> excelHeadMap, Map<String, Map> excelHeadConvertMap, List<List> rows, Class cls) {
		List contents = new ArrayList();
		int num=0;
		for (List<Object> list : rows) {
			num++;
			// 如果当前第一列中无数据,则忽略当前行的数据
			if(list == null || list.get(0) == null) {
				break;
			}
				// 当前行的数据放入map中,生成<fieldName, value>的形式
				Map<String, Object> rowMap = rowListToMap(excelHeadMap, excelHeadConvertMap, list);
				
				// 将当前行转换成对应的对象
				Object obj = null;
				try {
					obj = cls.newInstance();
					BeanUtil.populateBean(obj, rowMap);
					contents.add(obj);
				} catch (InstantiationException ex) {
					throw new RuntimeException("第"+num+"行实例化异常");
				} catch (IllegalAccessException ex) {
					throw new RuntimeException("第"+num+"行实例化反射异常");
				} catch(BeanException ex){
					throw new RuntimeException("第"+num+"行数据格式转化错误，原因："+ex.getCause().getMessage());
				} catch(NumberFormatException ex){
					throw new RuntimeException("第"+num+"行数据格式转化错误，原因："+ex.getMessage());
				} catch(Exception ex){
					throw new RuntimeException("第"+num+"行数据格式不正确，原因："+ex.getCause().getMessage());
				}
		}
		return contents;
	}
	
	/**
	 * 将行转行成map,生成<fieldName, value>的形式
	 * @param excelHeadMap 表头信息
	 * @param excelHeadConvertMap excelHeadConvertMap
	 * @param list
	 * @return
	 * @return Map<String,Object>
	 * 2012-4-18 下午01:48:41
	 */
	public Map<String, Object> rowListToMap(Map<Integer, ExcelColumn> excelHeadMap, Map<String, Map> excelHeadConvertMap, List list) {
		Map<String, Object> rowMap = new HashMap<String, Object>();
		String fieldName="";
		String fieldDispName="";
		boolean isNull=true;
		for(int i = 0; i < list.size(); i++) {
			fieldName =  excelHeadMap.get(i).getFieldName();
			fieldDispName=excelHeadMap.get(i).getFieldDispName();
			isNull=excelHeadMap.get(i).isNull();
			// 存在所定义的列
			if(fieldName != null) {
				Object value = list.get(i);
				if(!isNull){//该字段不为空
					if(null==value || value==""){
						throw new RuntimeException("第"+(i+1)+"列’"+fieldDispName+"‘不能为空，请检查！");
					}
				}
				if(excelHeadConvertMap != null && excelHeadConvertMap.get(fieldName) != null) {
					value = excelHeadConvertMap.get(fieldName).get(value);
				}
				rowMap.put(fieldName, value);
			}
		}
		return rowMap;
	}
	
	/**
	 * 判断整行是否为空,如果为空，返回真，说明
	 * 1.如果excel本来行没数据，那么row==null
	 * 2.如果excel本来行有数据，通过DEL删除了行数据，那么row不为空，必须判断每个单元格都为空
	 * @param row
	 * @return
	 */
	public boolean isBlankRow(Row row){
		if(null!=row){
			int startNum=row.getFirstCellNum();
			int lastNum=row.getLastCellNum();
			int num=0;
			Cell cell=null;
			for(int i=startNum;i<lastNum;i++){
				cell=row.getCell(i,HSSFRow.RETURN_BLANK_AS_NULL);
				if(cell==null){
					num++;
				}
			}
			if(num>0 && num==(lastNum-startNum)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据行内容重新计算行高
	 * @param row
	 */
	public static void calcAndSetRowHeigt(Row sourceRow) {
		//原行高
		double maxHeight = sourceRow.getHeight();
		for (int cellIndex = sourceRow.getFirstCellNum(); cellIndex <= sourceRow.getPhysicalNumberOfCells(); cellIndex++) {
			Cell sourceCell = sourceRow.getCell(cellIndex);
			//单元格的内容
			String cellContent = getCellContentAsString(sourceCell);
			if(null == cellContent || "".equals(cellContent)){
				continue;
			}
			//单元格的宽高及单元格信息
			Map<String, Object> cellInfoMap = getCellInfo(sourceCell);
			Integer cellWidth = (Integer)cellInfoMap.get("width");
			Integer cellHeight = (Integer)cellInfoMap.get("height");
			if(cellHeight > maxHeight){
				maxHeight = cellHeight;
			}
			CellStyle cellStyle = sourceCell.getCellStyle();
			Font font = sourceRow.getSheet().getWorkbook().getFontAt(cellStyle.getFontIndex());
			//字体的高度
			short fontHeight = font.getFontHeight();
			
			//cell内容字符串总宽度
			double cellContentWidth = cellContent.getBytes().length * 2 * 256;
			
	        //字符串需要的行数 不做四舍五入之类的操作
	        double stringNeedsRows =(double)cellContentWidth / cellWidth;
	        //小于一行补足一行
	        if(stringNeedsRows < 1.0){
	        	stringNeedsRows = 1.0;
	        }
	        
	        //需要的高度 			(Math.floor(stringNeedsRows) - 1) * 40 为两行之间空白高度
	        double stringNeedsHeight = (double)fontHeight * stringNeedsRows-(Math.floor(stringNeedsRows) - 1) * 40;
	        //需要重设行高
	        if(stringNeedsHeight > maxHeight){
	        	maxHeight = stringNeedsHeight;
	        	//超过原行高三倍 则为5倍 实际应用中可做参数配置
	    		if(maxHeight/cellHeight > 5){
	    			maxHeight = 5 * cellHeight;
	    		}
	    		//最后取天花板防止高度不够
	    		maxHeight = Math.ceil(maxHeight);
	    		//重新设置行高 同时处理多行合并单元格的情况
	    		Boolean isPartOfRowsRegion = (Boolean)cellInfoMap.get("isPartOfRowsRegion");
	    		if(isPartOfRowsRegion){
	    			Integer firstRow = (Integer)cellInfoMap.get("firstRow");
	    			Integer lastRow = (Integer)cellInfoMap.get("lastRow");
	    			//平均每行需要增加的行高
	    			double addHeight = (maxHeight - cellHeight)/(lastRow - firstRow + 1);
	    			for (int i = firstRow; i <= lastRow; i++) {
	    				double rowsRegionHeight =sourceRow.getSheet().getRow(i).getHeight() + addHeight;
	    				sourceRow.getSheet().getRow(i).setHeight((short)rowsRegionHeight);
	    			}
	    		}else{
	    			sourceRow.setHeight((short)maxHeight);
	    		}
	        }
	        System.out.println("字体高度 : " + fontHeight + ",    字符串宽度 : " + cellContentWidth + ",    字符串需要的行数 : " + stringNeedsRows + ",   需要的高度 : " + stringNeedsHeight + ",   现在的行高 : " + maxHeight+"单元格的内容 : " + cellContent);
		}
	}
	
	/**
	 * 解析一个单元格得到数据
	 * @param cell
	 * @return
	 */
	private static String getCellContentAsString(Cell cell) {
		if(null == cell){
			return "";
		}
		String result = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			String s = String.valueOf(cell.getNumericCellValue());
			if (s != null) {
				if (s.endsWith(".0")) {
					s = s.substring(0, s.length() - 2);
				}
			}
			result = s;
			break;
		case Cell.CELL_TYPE_STRING:
			result = String.valueOf(cell.getStringCellValue()).trim();
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			break;
		default:
			break;
		}
		return result;
	}
	
	/**
     * 获取单元格及合并单元格的宽度
     * @param cell
     * @return
     */
    private static Map<String, Object> getCellInfo(Cell cell) {
    	Sheet sheet = cell.getSheet();
    	int rowIndex = cell.getRowIndex();
    	int columnIndex = cell.getColumnIndex();
    	
    	boolean isPartOfRegion = false;
    	int firstColumn = 0;
    	int lastColumn = 0;
    	int firstRow = 0;
    	int lastRow = 0;
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			firstColumn = ca.getFirstColumn();
			lastColumn = ca.getLastColumn();
			firstRow = ca.getFirstRow();
			lastRow = ca.getLastRow();
			if (rowIndex >= firstRow && rowIndex <= lastRow) {
				if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
					isPartOfRegion = true;
					break;
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Integer width = 0;
		Integer height = 0;
		boolean isPartOfRowsRegion = false;
		if(isPartOfRegion){
			for (int i = firstColumn; i <= lastColumn; i++) {
				width += sheet.getColumnWidth(i);
			}
			for (int i = firstRow; i <= lastRow; i++) {
				height += sheet.getRow(i).getHeight();
			}
			if(lastRow > firstRow){
				isPartOfRowsRegion = true;
			}
		}else{
			width = sheet.getColumnWidth(columnIndex);
			height += cell.getRow().getHeight();
		}
		map.put("isPartOfRowsRegion", isPartOfRowsRegion);
		map.put("firstRow", firstRow);
		map.put("lastRow", lastRow);
		map.put("width", width);
		map.put("height", height);
		return map;
	}
	
	/**
	 * 标题样式
	 * @param wb
	 * @param fontSize
	 * @return
	 */
	public static CellStyle titleStyle(Workbook wb,short fontSize){
		CellStyle style=wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//水平居中
		style.setAlignment(CellStyle.ALIGN_CENTER);//垂直
		style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
		style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		style.setBorderRight(HSSFCellStyle.BORDER_NONE);
		style.setBorderTop(HSSFCellStyle.BORDER_NONE);
		style.setWrapText(true);
		Font titleFont=wb.createFont();//设置字体
		titleFont.setColor(HSSFColor.BLUE.index);//颜色
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints(fontSize);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体增粗
		style.setFont(titleFont);
		return style;
	}
	
	/**
	 * 设置常用表格标题样式
	 * @param wb
	 * @return
	 */
	public static CellStyle commonTitleStyle(Workbook wb,short fontSize){
		CellStyle style=wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//水平居中
		style.setAlignment(CellStyle.ALIGN_CENTER);//垂直
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setWrapText(true);
		Font titleFont=wb.createFont();//设置字体
		titleFont.setColor(HSSFColor.BLUE.index);//颜色
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints(fontSize);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体增粗
		style.setFont(titleFont);
		return style;
	}
	
	/**
	 * 设置常用表格列表
	 * @param wb
	 * @return
	 */
	public static CellStyle commonClumnStyle(Workbook wb,short fontSize){
		CellStyle style=wb.createCellStyle();
		//设置背景色需要先设置填充样式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setShrinkToFit(true);
		style.setWrapText(true);
		Font tableTitleFont=wb.createFont();
		tableTitleFont.setFontName("宋体");
		tableTitleFont.setFontHeightInPoints(fontSize);
		tableTitleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(tableTitleFont);
		return style;
	}
	
	/**
	 * 设置常用表格单元格样式
	 * @return
	 */
	public static CellStyle commonCellStyle(Workbook wb,short fontSize){
		CellStyle style=wb.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setShrinkToFit(true);
		style.setWrapText(true);
		Font cellFont=wb.createFont();
		cellFont.setFontName("宋体");
		cellFont.setFontHeightInPoints(fontSize);
		style.setFont(cellFont);
		return style;
	}
}
