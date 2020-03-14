package com.darian.domain;

/***
 * 用户类
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/9  14:58
 */
public class User {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(23432L);
        user.setName("darian--User#createUser");
        return user;
    }
}
