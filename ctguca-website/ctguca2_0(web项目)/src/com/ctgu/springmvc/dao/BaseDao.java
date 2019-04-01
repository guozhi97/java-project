package com.ctgu.springmvc.dao;

import java.util.Collection;

public interface BaseDao<K,V> {
	//添加一个实体
	public boolean insert(V v);
	//删除一个实体
	public boolean remove(K k);
	//修改一个实体 
	public boolean update(K k,V v);
	//查询所有记录
	public Collection<V> getAll();
}
