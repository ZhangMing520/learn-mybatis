package org.mybatis.example;

import org.apache.ibatis.annotations.Select;

/**
 * @author zhangming
 * @date 2019/3/9 11:25
 * <p>
 * 可以设置简单的语句，与此对应的也会查找 mapper.xml，两者不能重复声明 sql 语句
 */
public interface BlogMapper {

    //    @Select("select * from blog where id=#{id}")
    Blog selectBlog(int id);

}
