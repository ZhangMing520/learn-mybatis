package org.mybatis.example;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @author zhangming
 * @date 2019/3/9 11:14
 *
 * SqlSessionFactoryBuilder 创建好 SqlSessionFactory 后就不需要保留而来，最好作用域在方法体内
 *
 * SqlSessionFactory 在应用过程中始终存在  作用域是 Application，使用 google guice或者 spring 管理成单例
 *
 * SqlSession 每个线程都有自己的SqlSession实例 不能被共享 也不是线程安全的，作用域是Request域或者方法域
 *
 * Mapper 作用域等同于 SqlSession，保持 Mapper 在方法体作用域内部
 */
public class SqlSessionFactoryDemo {

    private final static Logger logger = LoggerFactory.getLogger(SqlSessionFactoryDemo.class);

    public static void main(String[] args) throws IOException {
        String resource = "Configuration.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sessionFactory.openSession();

        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlog(101);
        logger.info("{}", blog);
    }
}
