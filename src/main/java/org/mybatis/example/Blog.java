package org.mybatis.example;

/**
 * @author zhangming
 * @date 2019/3/9 11:33
 */
public class Blog {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                '}';
    }
}
