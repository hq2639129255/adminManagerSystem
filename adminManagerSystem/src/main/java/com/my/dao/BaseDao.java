package com.my.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.my.utils.JDBCutil;

public abstract class BaseDao<T> {
	private  Class<T> clazz;

	{
		//获取当前BaseDAO的子类继承的父类中的泛型
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) genericSuperclass;

		Type[] typeArguments = paramType.getActualTypeArguments();//获取了父类的泛型参数
		clazz = (Class<T>) typeArguments[0];//泛型的第一个参数

	}


	//通用增删改操作
	public int update(Connection con,String sql,Object... args) throws SQLException{

		PreparedStatement pre=null;

		pre=con.prepareStatement(sql);
		for(int i=0;args!=null&&i<args.length;i++){
			pre.setObject(i+1, args[i]);
		}

		int rows=pre.executeUpdate();
		JDBCutil.close(null,  pre, null);
		return rows;
	}

	//针对多个表的查询返回当个对象的写法1
	public <T>  T getInstence(Connection con,String sql,Object...args ) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		T obj=null;

		PreparedStatement pre=null;
		ResultSet re=null;
		pre=con.prepareStatement(sql);
		for(int i=0;args!=null&&i<args.length;i++){
			pre.setObject(i+1, args[i]);

		}
		re=pre.executeQuery();
		ResultSetMetaData remeta = re.getMetaData();
		int columnCount=remeta.getColumnCount();
		if(re.next()){
			obj=(T) clazz.newInstance();
			for(int i=0;i<columnCount;i++){
				String columnName=remeta.getColumnLabel(i+1);
				Object value=re.getObject(columnName);
				Field of=clazz.getDeclaredField(columnName);
				of.setAccessible(true);
				of.set(obj, value);
			}
		}
		JDBCutil.close(null,pre,re);

		return obj;
	}


//返回多个对象的通用查询操作

	public <T> List<T> getListInstence(Connection   con,String sql,Object...args) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		List<T> data=null;

		PreparedStatement	pre=null;
		ResultSet re=null;
		pre=con.prepareStatement(sql);
		for(int i=0;args!=null&& i<args.length;i++){
			pre.setObject(i+1, args[i]);



		}
		re=	pre.executeQuery();
		if(re.next()){
			ResultSetMetaData remeta = re.getMetaData();
			int columnCount = remeta.getColumnCount();
			data=new ArrayList<T>();
			re.beforeFirst();
			while(re.next()){
				T obj=(T) clazz.newInstance();
				for(int i=0;i<columnCount;i++){
					String columnName=remeta.getColumnLabel(i+1);
					Object value=re.getObject(columnName);
					Field of=clazz.getDeclaredField(columnName);
					of.setAccessible(true);
					of.set(obj, value);

				}

				data.add(obj);

			}
		}
		JDBCutil.close(null,pre,re);
		return data;
	}


	//返回单个值的通用操作
	public <E>  E getValue(Connection con,String sql,Object...args) throws SQLException{
		PreparedStatement pre=null;
		 E value=null;
		pre=con.prepareStatement(sql);
		ResultSet re=null;
		for(int i=0;args!=null&&i<args.length;i++){
			pre.setObject(i+1, args[i]);



		}
		re=pre.executeQuery();

		if(re.next()){

			value=(E) re.getObject(1);
		}
		JDBCutil.close(null,pre,re);

		return value;

	}
	//返回多个对象的通用查询操作

	public ResultSet  getListResultSet(Connection   con,String sql,Object...args) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{

		PreparedStatement	pre=null;
		ResultSet re=null;
		pre=con.prepareStatement(sql);
		for(int i=0;args!=null&& i<args.length;i++){
			pre.setObject(i+1, args[i]);
		}
		re=	pre.executeQuery();


		return re;
	}

}
