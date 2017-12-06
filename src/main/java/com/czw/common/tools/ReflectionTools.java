package com.czw.common.tools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月5日
 * www.cuizhiwei.com
 */
public class ReflectionTools {
	private static Logger logger = LoggerFactory.getLogger(ReflectionTools.class);
	/**
	 * 通过反射, 获得定义Class时声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class getSuperClassGenricType(Class clazz, int index)
	{

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType))
		{
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		} else
		{
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

			if (index >= params.length || index < 0)
			{
				logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
				return Object.class;
			}
			if (!(params[index] instanceof Class))
			{
				logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
				return Object.class;
			}

			return (Class) params[index];
		}
	}
}
