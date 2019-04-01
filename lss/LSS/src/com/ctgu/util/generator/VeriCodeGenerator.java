package com.ctgu.util.generator;

/**
 * @author bibek_lee
 * @function generate random verification code
 * @version 1.0
 * @category tools
 * @Date 2015-12-27
 */

public class VeriCodeGenerator {
	private static final String STR_NUM = "0123456789";
	private static final String STR_ALPH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//如果可能，考虑去除容易混淆的字符，如1、I、L、O、0这几个
//	private static final String STR_NUM_ALPH = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	private static final String STR_NUM_ALPH = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
	public static final int NUMBER = 0;
	public static final int ALPHA = 1;
	public static final int NUM_ALPH = 2;
	
	private static final int CODE_LEN = 5;

	/**
	 * 产生固定长度为codeLen位数字的随机码
	 * @return 生成的codeLen位随机数字码
	 */
	public static String generateCode(int codeType, int codeLen) {
		String code = new String();
		String strTable = null;
		char c;

		switch (codeType) {
		case NUMBER:
			strTable = STR_NUM;
			break;
		case ALPHA:
			strTable = STR_ALPH;
			break;
		case NUM_ALPH:
			strTable = STR_NUM_ALPH;
			break;
		default:
			;

		}
		for (int i = 0; i < codeLen; i++) {
			c = strTable.charAt((int) (strTable.length() * Math.random()));
			code = code + c;
		}
		return code;
	}

	/**
	 * 生成自定义字符的随机码的函数（可以用来生产字符串的字符自己定义）
	 * 
	 * @param strTb
	 *            随机码中可以包含的字符的集合
	 * @param codeLen
	 *            要生成的随机码的长度
	 * @return 返回生成随机码
	 */
	public static String generateCode(String strTb, int codeLen) {
		String code = new String();
		int t_len = strTb.length();
		char c;

		for (int i = 0; i < codeLen; i++) {
			c = strTb.charAt((int) (t_len * Math.random()));
			code = code + c;
		}
		return code;
	}

	public static void main(String[] args) {

		int i = 0;
		while (i < 2) {
			System.out.println(VeriCodeGenerator.generateCode(
					VeriCodeGenerator.NUMBER, VeriCodeGenerator.CODE_LEN));
			i++;
		}
	}

}
