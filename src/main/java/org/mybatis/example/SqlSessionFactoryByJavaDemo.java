package org.mybatis.example;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 * @author zhangming
 * @date 2019/3/9 11:23
 * <p>
 * java方式配置
 */
public class SqlSessionFactoryByJavaDemo {

    public static void main(String[] args) {
        DataSource dataSource = null;
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        final String ENV = "development";
        Environment environment = new Environment(ENV, transactionFactory, dataSource);

        Configuration configuration = new Configuration(environment);
        // 加载完BlogMapper映射类中的sql，会加载BlogMapper.xml
        configuration.addMapper(BlogMapper.class);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }
}
