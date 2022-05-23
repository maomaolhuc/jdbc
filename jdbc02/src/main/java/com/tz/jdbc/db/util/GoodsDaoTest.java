/**
 *
 */
package com.tz.jdbc.db.util;

import com.tz.bean.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：普通的封装了数据库连接和关闭的对表Goods增删改查
 * @author 南天
 *
 * @Date 2017年4月18日
 */
public class GoodsDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoodsDaoTest dao = new GoodsDaoTest();
		//测试一
//		List<Goods> list = dao.getGoodsList();
//		for(Goods g : list){
//			System.out.println("  " + g.getGoodsName());
//		}
		Goods goods = new Goods();
		goods.setId(2);
		goods.setGoodsName("桔子");
		goods.setNum(890);
		goods.setRemark("测试修改");
		dao.updateGoods(goods);
	}

	/**
	 * Goods表的删除方法
	 */
	public int delGoods(int goodsId) {
		int rows = 0;
		Connection con = null; // 一个数据库连接
		PreparedStatement pre = null; // 预编译对象
		ResultSet result = null; // 结果集
		try {
			con = DbUtil.getConnection();
			String deleteSql = "delete from goods where id = ?";
			pre = con.prepareStatement(deleteSql);
			pre.setInt(1, goodsId);
			rows = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(con, pre, result);
		}
		return rows;

	}

	/**
	 * 修改方法
	 */
	public int updateGoods(Goods goods){
		int rows = 0;
		Connection conn = null; // 一个数据库连接
		PreparedStatement pre = null; // 预编译对象
		ResultSet rs = null; // 结果集

		try {
			conn = DbUtil.getConnection();
			String updatesql = "update goods set goods_num=?, remark=? where id=?";
			pre = conn.prepareStatement(updatesql);
			pre.setInt(1, goods.getNum());
			pre.setString(2, goods.getRemark());
			pre.setInt(3, goods.getId());
			rows = pre.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtil.closeConnection(conn, pre, rs);
		}

		return rows;
	}

	/**
	 * 查询方法
	 * @return
	 */
	public List<Goods> getGoodsList(){
		Connection conn = null; // 一个数据库连接
		PreparedStatement pre = null; // 预编译对象
		ResultSet rs = null; // 结果集
		List<Goods> list = new ArrayList<Goods>();
		try{
			conn = DbUtil.getConnection();
			String sql = "select * from goods";
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			Goods goods;
			while (rs.next()) {
				goods = new Goods();
				goods.setId(rs.getInt(1));
				goods.setGoodsName(rs.getString(2));
				goods.setNum(rs.getInt(3));
				goods.setRemark(rs.getString(4));
				list.add(goods);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbUtil.closeConnection(conn, pre, rs);
		}
		return list;
	}

}
