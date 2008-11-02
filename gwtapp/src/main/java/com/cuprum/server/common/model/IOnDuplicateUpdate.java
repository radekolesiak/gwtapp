package com.cuprum.server.common.model;


public interface IOnDuplicateUpdate<T> {
	T find();
	T update(T object);
	T create();
}
