package com.tz.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * oracle连接测试类
 * @author Administrator
 *
 */
public class DbConnectionTest{
	
	/*
	 * 连接Oracle数据库的示例代码
	 */
	public static void testOracleConnection(){
	    Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet result = null;// 创建一个结果集对象
	    try{
	        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
	        System.out.println("开始尝试连接Oracle数据库！");
	        String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";// 127.0.0.1是本机地址，orcl是认数据库名
	        String user = "scott";// 用户名,系统默认的账户名
	        String password = "tiger";// 你安装时选设置的密码
	        con = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println("连接Oracle数据库成功！");
	        String sql = "SELECT * FROM EMP WHERE DEPTNO=?";// 预编译语句，“？”代表参数
	        pre = con.prepareStatement(sql);// 实例化预编译语句
	        pre.setInt(1, 10);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
	        result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
	        while (result.next())
	            // 当结果集不为空时
	            System.out.println("empno:" + result.getInt("empno") + "姓名:"
	                    + result.getString("ename"));
	    }catch (Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
	            // 注意关闭的顺序，最后使用的最先关闭
	            if (result != null)
	            		result.close();
	            if (pre != null)
	            		pre.close();
	            if (con != null)
	            		con.close();
	            System.out.println("数据库连接已关闭！");
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }
	}
	
	
/*	
	 * 连接Mysql数据库的示例代码
	 
	public static void testMysqlConnection(){
	    Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet result = null;// 创建一个结果集对象
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver"); //加载mysql驱动
	        System.out.println("开始尝试连接Mysql数据库！");
 	        String	url = "jdbc:mysql://localhost/test?user=root&password=123456" +
 	        						"&useUnicode=true&&characterEncoding=gbk";
	        String user = "root";// 用户名,系统默认的账户名
	        String password = "123456";// 你安装时选设置的密码
	        con = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println("连接Mysql成功！");
	        String sql = "SELECT * FROM M_USER WHERE USER_DEPTNO=?";// 预编译语句，“？”代表参数
	        pre = con.prepareStatement(sql);// 实例化预编译语句
	        pre.setInt(1, 10);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
	        result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
	        while (result.next())
	            // 当结果集不为空时
	            System.out.println("USER_ID:" + result.getInt("USER_ID") + "姓名:"
	                    + result.getString("USER_NAME"));
	    }catch (Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
	            // 注意关闭的顺序，最后使用的最先关闭
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println("数据库连接已关闭！");
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }
	}
	
	
	 * 通过properties文件连接Oracle数据库的示例代码
	 
	public static void testOracleConnectionByPro() throws IOException{
		*//**
         * 读取src根目录下文件的配置文件 
         * jdbc.properties位于src目录
         *//*
        InputStream in = DbConnectionTest.class.getClassLoader()
                .getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();
        if(in!=null)
        	pro.load(in);
        
	    Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet result = null;// 创建一个结果集对象
	    try{
 	        Class.forName(pro.getProperty("jdbc.driverClass"));
	        System.out.println("开始尝试连接Oracle数据库！");
	        String url = pro.getProperty("jdbc.url");
	        String user = pro.getProperty("jdbc.user");
	        String password = pro.getProperty("jdbc.password");
	        con = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println("连接Oracle数据库成功！");
	        String sql = "SELECT * FROM EMP WHERE DEPTNO=?";// 预编译语句，“？”代表参数
	        pre = con.prepareStatement(sql);// 实例化预编译语句
	        pre.setInt(1, 10);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
	        result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
	        while (result.next())
	            // 当结果集不为空时
	            System.out.println("empno:" + result.getInt("empno") + "姓名:"
	                    + result.getString("ename"));
	    }catch (Exception e){
	        e.printStackTrace();
	    }finally{
	        try{
	            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
	            // 注意关闭的顺序，最后使用的最先关闭
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println("数据库连接已关闭！");
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }
	}*/
	
	public static void main(String[] args){
		DbConnectionTest.testOracleConnection();
		//DbConnectionTest.testMysqlConnection();
		/*try {
			DbConnectionTest.testOracleConnectionByPro();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
}