package org.mybatis.example;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;

/**
 * @author zhangming
 * @date 2019/3/9 20:32
 * <p>
 * 每次 mybatis 为结果对象创建一个新实例，都会用到 ObjectFactory
 * <p>
 * 默认的 ObjectFactory和使用目标类的构造函数创建一个实例毫无区别
 * <p>
 * ObjectFactory 只包含两个方法，一个是构造函数，一个是带参数的构造函数。可以在 ObjectFactory 实例化之后，通过
 * setProperties 方法，在对象工厂中定义属性
 */
public class ObjectFactoryDemo extends DefaultObjectFactory {

    @Override
    public <Blog> Blog create(Class<Blog> type) {
        return super.create(type);
    }

    @Override
    public <Blog> Blog create(Class<Blog> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }
}
