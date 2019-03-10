package org.mybatis.example;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author zhangming
 * @date 2019/3/9 20:43
 * <p>
 * 此插件可以在执行器上拦截所有“update”方法的调用，这里的执行器，是一个映射语句内部对象的深架构的执行器
 */
@Intercepts(
        {
                @Signature(type = Executor.class,
                        method = "update",
                        args = {MappedStatement.class, Object.class})
        }
)
public class PluginDemo implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }
}
