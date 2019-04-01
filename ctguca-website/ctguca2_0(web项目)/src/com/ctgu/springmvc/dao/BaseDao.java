package com.ctgu.springmvc.dao;

import java.util.Collection;

public interface BaseDao<K,V> {
	//���һ��ʵ��
	public boolean insert(V v);
	//ɾ��һ��ʵ��
	public boolean remove(K k);
	//�޸�һ��ʵ�� 
	public boolean update(K k,V v);
	//��ѯ���м�¼
	public Collection<V> getAll();
}
