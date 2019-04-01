package com.ctgu.lss.common;

import java.util.List;
import java.util.Map;

public interface IBaseDAO<K,V> {
	
	public boolean doInsert(V v) throws Exception;
	public boolean doUpdate(V v) throws Exception;
	public V findById(K k) throws Exception;
	public List<V> findAll() throws Exception;
	public boolean doDelete(K k) throws Exception;
	public int getAllCount() throws Exception;
	public List<V> findByConditions(Map<String, Object> condition) throws Exception;
	public int getCountByConditions(Map<String, Object> condition) throws Exception;

}
