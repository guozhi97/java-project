package com.ctgu.lss.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BaseConst {

	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE = "scancode_push";
	public static final String MESSAGE_TEMPLATE = "TEMPLATESENDJOBFINISH";
	// --------------------------------------------session常量-----------------------------------
	public static final String USERSESSION = "USERSESSION";

	public static final String MODULETYPE = "ModuleType"; // 保存模块类型

	public static final String CHARTTYPE = "ChartType"; // 图表类型

	public static final String HISTORYURL = "historyUrl"; // 历史路由

	public static String path;
	/**
	 * 求索人APP端请求类型编码
	 */
	// 登录请求时，发送的数据中method字段的值
	public static final int LOGIN = 1;
	// 查询取件信息时，发送的数据中method字段的值
	public static final int PICK_QUERY = 2;
	// 确认取件时时，发送的数据中method字段的值
	public static final int PICK_CONFIRM = 3;
	// 查询派件信息时，发送的数据中method字段的值
	public static final int DELIVERY_QUERY = 4;
	// 确认派件时，发送的数据中method字段的值
	public static final int DELIVERY_CONFIRM = 5;
	// 获取用户信息，发送的数据中method字段的值
	public static final int GET_USERINFO = 6;
	// 获取用户信息，发送的数据中method字段的值
	public static final int GET_PICDELV_DATA = 7;

	/**
	 * 新版求索人APP端请求类型编码
	 */
	// 求索人APP端注册请求时，发送的数据中method字段的值
	public static final int S_REGISTER = 0;
	// 求索人APP端登录请求时，发送的数据中method字段的值
	public static final int S_LOGIN = 1;
	// 登录请求时，发送的数据中method字段的值
	public static final int S_ORDER_QUERY = 2;
	// 登录请求时，发送的数据中method字段的值
	public static final int S_TAKE_ORDER = 3;
	// 查询取件信息时，发送的数据中method字段的值
	public static final int S_PICK_QUERY = 4;
	// 确认取件时时，发送的数据中method字段的值
	public static final int S_PICK_CONFIRM = 5;
	// 查询派件信息时，发送的数据中method字段的值
	public static final int S_DELIVERY_QUERY = 6;
	// 确认派件时，发送的数据中method字段的值
	public static final int S_DELIVERY_CONFIRM = 7;

	// 聚合数据应用APP的应用KEY
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	public static final String TARGET_URL = "http://v.juhe.cn/sms/send";
	public static final String METHOD = "GET";
	public static final String APPKEY = "28b0d0df66a3b06bd8f36d064ee429cb";

	// 支付方式编码设定
	public static final int CASHPAY = -1;
	public static final int WXPAY = 0;
	public static final int ALIPAY = 1;

	/**
	 * 验证码到cookie
	 */
	public static final String authcode = "authCode";

	/**
	 * 生成登陆认证cookie
	 */
	public static final String authmark = "authmark";

	/**
	 * 用户名到cookie
	 */
	public static final String userName = "userName";

	/**
	 * #IDEA加密秘钥
	 */
	public static final String securityKey = "ttJw6Oc4NEtwPf8CbmwLNQ==";// #IDEA加密秘钥

	/**
	 * #cookie最大时间 3600 * 24 * 15 = 12960000 秒 = 15 天
	 */
	public static final int maxAge = 12960000;

	// --------------------------------------------文件目录-----------------------------------

	public static final String UPLOAD_FILE = "uploadFiles"; // 上传文件根目录

	// --------------------------------------------字典常量常量-----------------------------------

	/**
	 * 合同分类
	 */
	public static final String DIC_CONTRACT_TYPE = "dic_contract_type";

	/**
	 * 合同状态
	 */
	public static final String DIC_CONTRACT_STATE = "dic_contract_state";

	/**
	 * 归档状态
	 */
	public static final String DIC_ARCHIVE_STATE = "dic_archive_state";

	/**
	 * 收付款类型
	 */
	public static final String DIC_FUND_TYPE = "dic_fund_type";

	/**
	 * 模块类型
	 */
	public static final String DIC_MODULE_TYPE = "dic_module_type";

	/**
	 * 分页常量
	 */
	public static final String DIC_PAGE_SIZE = "dic_page_size";

	// ----------------------------------------查询条件起始年份--------------------------------------------------

	// -----------------AJAX请求返回参数---------------------------------------
	/**
	 * respone报错信息
	 */
	public static final int AJAX_999 = 999;

	/**
	 * 没有权限
	 */
	public static final int AJAX_998 = 998;

	public BaseConst() {
		path = getPath();
	}

	public String getPath() {
		String sysPath = getClass().getClassLoader().getResource("/").getPath();
		sysPath = sysPath.substring(0, sysPath.lastIndexOf("classes"));
		System.out.println("-------------------"+sysPath+"----------------");
		return sysPath;
	}

	/**
	 * 根据编码分解出序号
	 * 
	 * @param contractCode
	 * @return
	 */
	public static int getSortCode(String contractCode) {
		String[] str = contractCode.split("-");
		if (str.length >= 3) {
			return Integer.valueOf(str[3].toString());
		}
		return -1;
	}

	/**
	 * 根据编码分解出年份
	 * 
	 * @param contractCode
	 * @return
	 */
	public static String getYear(String contractCode) {
		String[] str = contractCode.split("-");
		if (str.length > 3) {
			return str[2].toString();
		}
		return "";
	}

	public static void main(String[] args) {
		// String regEx="[a-zA-Z]*-[a-zA-Z]*-[0-9]*-";
		// Pattern pat=Pattern.compile(regEx);
		// Matcher match=pat.matcher("SS-JJZB-2014-01");
		// String s=match.replaceAll("");
		BaseConst b = new BaseConst();
		System.out.println(BaseConst.path);

	}
}
