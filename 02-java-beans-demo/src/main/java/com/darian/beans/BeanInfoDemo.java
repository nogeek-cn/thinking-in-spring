package com.darian.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/***
 * {@link java.beans.BeanInfo} 示例
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/8  23:34
 */
public class BeanInfoDemo {
    // Bean 自省会有异常
    public static void main(String[] args) throws IntrospectionException {
        // BeanDescriptor 基本的描述
        // EventSetDescriptor 事件的描述
        // PropertyDescriptor 字段的描述

        // stop class
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    // PropertyDescriptor 允许添加属性编辑器 PropertyEditor
                    // GUI -> text(String) -> PropertyType
                    // name-> String
                    // age -> Integer
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String propertyName = propertyDescriptor.getName();
                    if ("age".equals(propertyName)) {
                        // 为 age 属性添加 添加 PropertyEditor
                        // String -> Integer
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        // 给某个  Bean创建 propertyEditor
//                        propertyDescriptor.createPropertyEditor();
                    }
                });

        // 我们通常和 GUI 结合去做，输入 String， 转化成其他的类型

    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }

}
