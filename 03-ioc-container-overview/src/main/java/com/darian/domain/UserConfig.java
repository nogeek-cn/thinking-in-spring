package com.darian.domain;

import com.darian.enums.City;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/20  5:25
 */
@Configuration
public class UserConfig {
    @Bean
    public User user() {
        User user = new User();
        user.setId(23432L);
        user.setName("darian--UserConfig#user");
        user.setCity(City.BEI_JING);
        user.setConfigFileLocation(new ClassPathResource("META-INF/lookup.xml"));
        List<City> lifeCities = new ArrayList<>();
        lifeCities.add(City.BEI_JING);
        lifeCities.add(City.HANG_ZHOU);
        user.setLifeCities(lifeCities);
        user.setWorkCities(new City[]{City.BEI_JING, City.SHANG_HAI});
        return user;
    }

    @Bean
    @Primary
    public SuperUser superUser() {
        SuperUser superUser = new SuperUser();
        superUser.setId(23432L);
        superUser.setName("darian--UserConfig#user");
        superUser.setCity(City.BEI_JING);
        superUser.setConfigFileLocation(new ClassPathResource("META-INF/lookup.xml"));
        List<City> lifeCities = new ArrayList<>();
        lifeCities.add(City.BEI_JING);
        lifeCities.add(City.HANG_ZHOU);
        superUser.setLifeCities(lifeCities);
        superUser.setWorkCities(new City[]{City.BEI_JING, City.SHANG_HAI});
        superUser.setAddress("xxxxxxxx");
        return superUser;
    }
}
