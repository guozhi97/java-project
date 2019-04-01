package com.ctgu.core.dao;

import java.util.List;

import com.ctgu.lss.common.ResultOrder;

/**
 * DAO父类接口
 *
 * @version 1.0,2013-8-31
 * @author Wymann
 * @param <T> 实体模型
 */
public interface IBaseDao<K,V> {
	
		/**
		 * 执行数据的插入操作
		 * 
		 * @param vo 要插入的数据对象
		 * @return 插入成功返回true，如果存在同样的记录或插入失败，返回false
		 * @throws Exception 出现异常，交由调用处处理
		 */
		public boolean doInsert(V vo) throws Exception;

		/**
		 * 执行更新数据操作
		 * 
		 * @param vo
		 *            要更新的对象包装
		 * @return 如果更新成功，返回true，如果要更新的数据id不存在或者更新失败，返回false
		 * @throws Exception
		 *             出现异常，交由调用处处理
		 */
		public boolean doUpdate(V vo) throws Exception;

		/**
		 * 执行删除数据操作
		 * 
		 * @param id
		 *            要删除数据的id值
		 * @return 如果删除成功，返回true，如果数据不存在或者删除失败，返回false
		 * @throws Exception
		 *             出现异常，交由调用处处理
		 */
		public boolean doDelete(K id) throws Exception;

		/**
		 * 执行对象查找操作
		 * 
		 * @param id
		 *            要查找的对象的id
		 * @return 如果查找到指定id的对象，则返回此对象，如果未找到，返回null
		 * @throws Exception
		 *             出现异常，交由调用处处理
		 */
		public V findById(K id) throws Exception;

		/**
		 * 执行查询所有数据操作
		 * 
		 * @return 返回所有对象的List数据集合，List长度为0，表示无数据
		 * @throws Exception
		 *             出现异常，交由调用处处理
		 */
		public List<V> findAll() throws Exception;

		/**
		 * 执行模糊查询，并按照分页返回
		 * @param column	查询条件的列名
		 * @param keyWord   进行模糊查询的关键词，如果关键词为空，则返回所有数据
		 * @param currentPage  当前分页
		 * @param pageSize  分页的每页数据元素个数
		 * @return  返回多个对象的List数据集合，List长度为0，表示无数据
		 * @throws Exception  出现异常，交由调用处处理
		 */
		
		public List<V> findAllPaging(String column, String keyWord,
				int currentPage, int pageSize) throws Exception;
		
		/**
		 * 根据条件，查询返回记录
		 * @param v  待查询的对象，封装了查询条件（为空的部分不纳入查询条件内）
		 * @return  返回所有满足条件的记录
		 * @throws Exception  出现异常，交由调用处处理
		 */
		public List<ResultOrder> findByCondition(String condition)throws Exception; 

		/**
		 * 获取模糊查询数据的记录条数
		 * @param column  查询条件的列名
		 * @param keyWord  进行模糊查询的关键词，如果关键词为空，则查询所有数据
		 * @return  返回查询到数据记录的条数，如果无数据，返回0
		 * @throws Exception  出现异常，交由调用处处理
		 */
		public int getAllCount(String column,String keyWord) throws Exception;
}