package com.darian.beans;

/***
 * 描述人的 POJO 类
 * 贫血模型，没有其他的
 *
 * Setter / Getter 方法
 * 可写方法 （Writable） / 可读方法 (Readable)
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/8  23:31
 */
public class Person {
    // String -> String
    private String name;  // Property
    // String -> Integer
    private Integer age;  // Property

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
