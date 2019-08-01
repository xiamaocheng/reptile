package log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JsonUtils{
    public static SerializeConfig dateSerialize(String format)
    {
        SerializeConfig mapping = new SerializeConfig();
        mapping.put(Date.class, new SimpleDateFormatSerializer(format));

        return mapping;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map<String, Object> object2Map(Object object)
    {
        Map<String, Object> returnMap = new HashMap<String, Object>();

        if (object == null) {
            return returnMap;
        }

        if(object instanceof Map) {
            return (Map) object;
        }

        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(object.getClass());
        } catch (IntrospectionException e1) {
            return returnMap;
        }

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            try {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(object, new Object[0]);
                    if (result != null)
                        returnMap.put(propertyName, result);
                }
            } catch (NullPointerException e) {
            } catch (Exception e) {
                break;
            }
        }

        return returnMap;
    }

    public static String toJSONString(Object object, String dateFormat)
    {
        return JSON.toJSONString(object, dateSerialize(dateFormat), new SerializerFeature[0]);
    }
}