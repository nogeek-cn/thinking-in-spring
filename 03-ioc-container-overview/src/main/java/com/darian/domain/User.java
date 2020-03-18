package com.darian.domain;

import com.darian.enums.City;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

/***
 * 用户类
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/9  14:58
 */
public class User {
    private Long id;
    private String name;
    private City city;
    private City[] workCities;
    private List<City> lifeCities;
    private Resource configFileLocation;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", configurationLocation='" + configFileLocation + '\'' +
                ", workCities='" + Arrays.toString(workCities) + '\'' +
                ", lifeCities='" + lifeCities + '\'' +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(23432L);
        user.setName("darian--User#createUser");
        return user;
    }
}
