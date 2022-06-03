package com.tz.framework.jdbc;

import com.tz.framework.jdbc.bean.Emp;
import oracle.jdbc.internal.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProAndFuncTest {
	/**
	 * 获取序列
	 * com.tz.framework.jdbc
	 * 方法名：getSequence
	 * 创建人：huayan
	 * 时间：2018年6月7日-下午9:28:49
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException long
	 * @exception
	 * @since  1.0.0
	 */
	public static long getSequence() throws ClassNotFoundException, SQLException {
		long seq = 0;
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		con = Dbutils.getConnection();
		String sql = "SELECT SEQ_ID.NEXTVAL FROM DUAL";   //下一个序列值SEQ_ID.NEXTVAL
        pre = con.prepareStatement(sql)	;
        result = pre.executeQuery();
        while(result.next()) {  //当结果不为空的时候
        	seq = result.getLong(1);
        	System.out.println();
        }
        Dbutils.closeConnection(con, pre, result);
		return seq;
	}
	
	/**
	 * 调用存储过程
	 * com.tz.framework.jdbc
	 * 方法名：getProcedureMsg
	 * 创建人：xq
	 * 时间：2018年6月7日-下午9:51:17
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException List<Emp>
	 * @exception
	 * @since  1.0.0
	 */
	public static List<Emp> getProcedureMsg() throws ClassNotFoundException, SQLException{
		Connection con = null;
        CallableStatement cstm = null;  //创建一个调用函数和存储过程的预编译方法
        ResultSet result = null;
		int deptNo =10;
		List<Emp> empList = new ArrayList<Emp>();
		con = Dbutils.getConnection();
		
		
		String sql = "{call MYPACKAGE2.QUERY_EMP(?,?)}";  
		
		cstm = con.prepareCall(sql);  //预编译
		cstm.setInt(1, deptNo);
		//注册一个输出参数， 且输出参数的类型为游标  OracleTypes.CURSOR
		cstm.registerOutParameter(2, OracleTypes.CURSOR);  
		cstm.execute();  //执行查询
		result=(ResultSet)cstm.getObject(2);   //获取返回的数据对象
		Emp emp;
		while(result.next()) {
			emp =new Emp();
			emp.setEmpno(result.getInt("empno"));
			emp.setEname(result.getString("ename"));
			emp.setSal(result.getDouble("sal"));
			emp.setDeptno(result.getInt("deptno"));
			empList.add(emp);
		}
		Dbutils.closeConnection(con, cstm, result);
		return empList;
	}
	
	/**
	 * 访问函数
	 * com.tz.framework.jdbc
	 * 方法名：getFunctionMsg
	 * 创建人：xq
	 * 时间：2018年6月7日-下午10:14:30
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException Double
	 * @exception
	 * @since  1.0.0
	 */
	public static Double getFunctionMsg() throws ClassNotFoundException, SQLException {
		double returnArea = 0;
		Connection con = null;
        CallableStatement cstm = null;  //创建一个调用函数和存储过程的预编译方法
        int radius = 5;
        con = Dbutils.getConnection();
        String sql = "{? = call MYPACKAGE.GET_AREA(?)}";   
        cstm = con.prepareCall(sql);
        //注册输出参数，且输出参数类型为数值型
        cstm.registerOutParameter(1, OracleTypes.NUMBER);  
        cstm.setInt(2, radius);
        cstm.execute();
        returnArea = cstm.getDouble(1);
        Dbutils.closeConnection(con, cstm, null);
        return returnArea;
	}
	
	public static void main(String[] args) {
		try {
			//getSequence();
			/*List<Emp> empList =  getProcedureMsg();
			for(Emp emp :empList) {
				System.out.println(emp.getEname());
			}*/
             System.out.println(getFunctionMsg());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
