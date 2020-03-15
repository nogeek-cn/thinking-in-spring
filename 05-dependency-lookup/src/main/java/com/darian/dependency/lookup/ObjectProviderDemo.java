package com.darian.dependency.lookup;

import com.darian.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.function.Supplier;

/***
 * 通过 {@link ObjectProvider} 进行依赖查找
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/15  4:57
 */
public class ObjectProviderDemo { // @Configuration 是非必须注解
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);

        applicationContext.refresh();
        lookupByObjectProvider(applicationContext, String.class);
        lookupIfAvailable(applicationContext, User.class, () -> {
            User userCreate = User.createUser();
            userCreate.setName(userCreate.getName() + "--userProvider.getIfAvailable");
            return userCreate;
        });
        lookupByStreamOps(applicationContext, String.class);
        applicationContext.close();
    }

    private static <T> void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext,
                                              Class<T> clazz) {
        ObjectProvider<T> objectProvider = applicationContext.getBeanProvider(clazz);
//        Iterator<T> iterator = objectProvider.iterator();
//        while (iterator.hasNext()) {
//            T t = iterator.next();
//            System.out.println(t);
//        }
        objectProvider.stream().forEach(System.out::println);

    }

    private static <T> void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext,
                                              Class<T> clazz, Supplier<T> tSupplier) {
        ObjectProvider<T> userProvider = applicationContext.getBeanProvider(clazz);
        T t = userProvider.getIfAvailable(tSupplier);
        System.out.println("user--: [" + t + "]");
    }

    @Bean
    @Primary
    public String helloWorld() { // 方法名 就是 Bean 名称
        return "helloWorld";
    }

    @Bean
    public String message() {
        return "message";
    }

    private static <T> void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext
            , Class<T> clazz) {
        ObjectProvider<T> objectProvider = applicationContext.getBeanProvider(clazz);
        System.out.println(objectProvider.getObject());
    }
}
