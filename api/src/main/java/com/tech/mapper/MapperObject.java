package com.tech.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class MapperObject {

	private static Mapper mapper = new DozerBeanMapper();

	public static <S, D> D parse(S src, Class<D> des) {
		return mapper.map(src, des);
	}

	public static <S, D> List<D> parse(List<S> src, Class<D> des) {
		List<D> objects = new ArrayList<>();
		for (Object o : src) {
			objects.add(mapper.map(o, des));
		}
		return objects;
	}

}