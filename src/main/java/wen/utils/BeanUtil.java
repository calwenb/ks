package wen.utils;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 单例模式 饿汉式
 * 包装javaBen
 * whl
 */
public class BeanUtil {
    private static final BeanUtil beanUtil = new BeanUtil();

    private BeanUtil() {
    }

    public static BeanUtil getInstance() {
        return beanUtil;
    }

    /**
     * 使用Introspector接口,通过反射包装 javaBean
     *
     * @param map
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void packBean(Map<String, String[]> map, Object bean) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            System.out.println(name);
            if (map.containsKey(name)) {
                Method method = pd.getWriteMethod();
                method.invoke(bean, map.get(name)[0]);
            }
        }
    }


}
