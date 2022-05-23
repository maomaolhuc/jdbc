/**
 *
 */
package com.tz.jdbc.db.util;

import com.tz.bean.Goods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：Goods表增删改查
 * @author 南天
 *
 * @Date 2017年4月18日
 */
public class GoodsDaoTemplateTest extends TzJdbcTemplate{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoodsDaoTemplateTest dao = new GoodsDaoTemplateTest();
//
		List<Goods> list = dao.getGoodsList();
		for(Goods g : list){
			System.out.println("id : " + g.getId() + "| GoodsName : " + g.getGoodsName() +
					" | Num : " + g.getNum() +" | Remark : " + g.getRemark());
		}


//		Goods goods = new Goods();
//		goods.setId(6);
//		goods.setGoodsName("水蜜桃");
//		goods.setNum(6);
//		goods.setRemark("测试");
//		dao.delGoods(6);
//


	}

	/**
	 * Goods表的增加方法
	 */
	public int addGoods(Goods goods){
		String sql =  "insert into goods(id,goods_name,goods_num,remark) values(?,?,?,?)";
		Object[] params = new Object[]{goods.getId(), goods.getGoodsName(), goods.getNum(), goods.getRemark()};
		return this.updateTemplate(sql, params);
	}

	/**
	 * Goods表的删除方法
	 */
	public int delGoods(int goodsId){
		String sql =  "delete from goods where id = ?";
		Object[] params = new Object[]{goodsId};
		return this.updateTemplate(sql, params);
	}

	/**
	 * Goods表的修改方法
	 */
	public int updateGoods(Goods goods){
		String sql =  "update goods set goods_num =?, remark=? where id =?";
		Object[] params = new Object[]{goods.getNum(), goods.getRemark(), goods.getId()};
		return this.updateTemplate(sql, params);
	}

	/**
	 * 利用接口的实现来讲结果集赋给对象的值移到dao层实现
	 * T 是一个抽象的javabean,实体类
	 * 然后通过接口实现类，实现T到Goods的转变
	 */
	public List<Goods> getGoodsList(){
		List<Goods> list = new ArrayList<Goods>();
		String sql = "select * from goods";
		Object[] params = new Object[]{};
		list = this.queryForList(new RowMapper<Goods>(){

			@Override
			public Goods mappingRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goods_name"));
				goods.setNum(rs.getInt("goods_num"));
				goods.setRemark(rs.getString("remark"));
				return goods;
			}

		}, sql, params);

		return list;
	}

}
