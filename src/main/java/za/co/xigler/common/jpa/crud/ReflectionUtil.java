package za.co.xigler.common.jpa.crud;

import java.lang.reflect.ParameterizedType;

public class ReflectionUtil {

	public static <T> Class<T> retrieveParameterizedType(Class<?> clazz) {
		@SuppressWarnings("unchecked")
		Class<T> parameterizedType = ((Class<T>) ((ParameterizedType) 
		  clazz.getGenericSuperclass()).getActualTypeArguments()[0]);
		
		return parameterizedType;
	}

}
