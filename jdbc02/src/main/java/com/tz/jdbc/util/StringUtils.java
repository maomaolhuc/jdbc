/**
 *
 */
package com.tz.jdbc.util;

import java.util.UUID;

/**
 * UUID生成器
 * @author 南天
 *
 * @Date 2017年4月18日
 */
public class StringUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" UUID : " + StringUtils.getUUID());
	}

	public static String getUUID(){
		UUID uid = UUID.randomUUID();
		return uid.toString().replace("-", "").trim();
	}

}
