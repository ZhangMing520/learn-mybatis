# learn-mybatis

1. configuration 中一些 settings 

| 设置项 | 描述 | 允许值 | 默认值 |
| :------| ------: | :------: | :------: |
| cacheEnabled | 对在此配置文件下的所有cache进行全局性开/关设置 | true\|false |true |
| lazyLoadingEnabled | 全局设置懒加载。如果设为“关”，则所有相关联的额都会被初始化加载 | true\|false |true |
| aggressiveLazyLoading | 当设置为‘开’的时候，懒加载的对象可能被任何懒属性全部加载。否则，每个属性都按需加载。 | true\|false |true |
| multipleResultSetsEnabled | 允许和不允许单条语句返回多个数据集（取决于驱动需求） | true\|false |true |
| useColumnLabel | 使用列标签代替列名称。不同的驱动器有不同的作法。参考一下驱动器文档， 或者用这两个不同的选项进行测试一下。| true\|false |true |
| useGeneratedKeys | 允许JDBC 生成主键。需要驱动器支持。如果设为了true，这个设置将强制使用被生成的主键，有一些驱动器不兼容不过仍然可以执行。 | true\|false |false |
| autoMappingBehavior | 对在此配置文件下的所有cache进行全局性开/关设置 | true\|false |true |
| cacheEnabled |指定MyBatis 是否并且如何来自动映射数据表字段与对象的属性。PARTIAL将只自动映射简单的，没有嵌套的结果。FULL 将自动映射所有复杂的结果。 | NONE,PARTIAL,FULL |PARTIAL |
| defaultExecutorType | 配置和设定执行器，SIMPLE 执行器执行其它语句。REUSE 执行器可能重复使用prepared statements 语句，BATCH执行器可以重复执行语句和批量更新。| SIMPLE,REUSE,BATCH | SIMPLE |
| defaultStatementTimeout | 设置一个时限，以决定让驱动器等待数据库回应的多长时间为超时 | 正整数 |Not Set(null) |

2. java的基本类型和mybatis中类型映射

|别名|映射类型|
|:-- | :--|
| _byte | byte | 
| _long | long| 
| _short | short| 
| _int | int| 
| _integer | int| 
| _double | double| 
| _float | float| 
| _boolean | boolean| 
| string | String| 
| byte | Byte| 
| long | Long| 
| short | Short| 
| int | Integer| 
| integer | Integer| 
| double | Double| 
| float | Float| 
| boolean | Boolean| 
| date| Date | 
| decimal| BigDecimal | 
| bigdecimal| BigDecimal | 
| object| Object | 
| map| Map | 
| hashmap| HashMap | 
| list| List | 
| arraylist| ArrayList | 
| collection| Collection | 
| iterator| Iterator | 

3. 类型句柄（typeHandlers）
>当 Mybatis 对 PreparedStatement 设入一个参数或者从 ResultSet 返回一个值的时候，类型句柄将值转化为相匹配的 JAVA 类型

|类型句柄 | Java 类型| JDBC 类型|
|:--| :--| :--|
|BooleanTypeHandler | Boolean, boolean | 任何与BOOLEAN 兼容的类型|
|ByteTypeHandler | Byte, byte  | 任何与NUMERIC or BYTE 兼容的类型
|ShortTypeHandler | Short, short | 任何与NUMERIC or SHORT INTEGER 兼容的类型|
| IntegerTypeHandler |Integer, int | 任何与NUMERIC or INTEGER 兼容的类型|
|LongTypeHandler |Long, long |任何与NUMERIC or LONG INTEGER 兼容的类型|
|FloatTypeHandler | Float, float | 任何与NUMERIC or FLOAT 兼容的类型 |
|DoubleTypeHandler| Double, double |任何与NUMERIC or DOUBLE 兼容的类型
|BigDecimalTypeHandler| BigDecimal | 任何与NUMERIC or DECIMAL 兼容的类型
|StringTypeHandler| String |CHAR, VARCHAR|
|ClobTypeHandler| String | CLOB, LONGVARCHAR|
|NStringTypeHandler| String | NVARCHAR, NCHAR|
|NClobTypeHandler | String | NCLOB |
|ByteArrayTypeHandler | byte[] | 任何与字节流兼容的类型|
|BlobTypeHandler | byte[] | BLOB, LONGVARBINARY|
|DateTypeHandler | Date (java.util) | TIMESTAMP|
|DateOnlyTypeHandler | Date (java.util) | DATE|
|TimeOnlyTypeHandler | Date (java.util) | TIME|
|SqlTimestampTypeHandler | Timestamp (java.sql)  |TIMESTAMP |
|SqlDateTypeHadler | Date (java.sql) | DATE |
|SqlTimeTypeHandler | Time (java.sql)  |TIME |
|ObjectTypeHandler | Any | 其它未指定的类型 |
|EnumTypeHandler | Enumeration Type | VARCHAR - 任何与string 兼容的类型。存储的是枚举编码，而不是枚举索引 |

4. 插件
> mybatis 允许你在映射语句执行过程中某点上拦截调用

- Executor(update, query, flushStatements, commit, rollback, getTransaction, close,isClosed)
- ParameterHandler (getParameterObject, setParameters)
- ResultSetHandler (handleResultSets, handleOutputParameters)
- StatementHandler (prepare, parameterize, batch, update, query) 

5. 事务管理器
- JDBC 这个类型直接全部使用 JDBC 的提交和回滚功能。它依靠使用连接的数据源来管理事务的作用域
- MANAGED 这个类型什么不做，它从不提交，回滚并且关闭连接。而是让容器来管理事务的全部生命周期（比如spring或者javaEE）

6. 数据源
> 三种类型：UNPOOLED,POOLED,JNDI

- UNPOOLED 每次请求的时候简单的打开和关闭一个连接
  - driver  驱动
  - url   URL路径
  - username  用户名
  - password  密码
  - defaultTransactionsolationLevel  默认事务隔离级别
  - driver.encoding=UTF8（也可以为数据驱动器设置属性。只需要简单去 "diver."开头就行）
- POOLED 避免每次都要连接和生成连接实例和验证
  - poolMaximumActiveConnections 特定时间可同时使用的连接数
  - poolMaximumIdleConnections  特定时间里闲置的连接数
  - poolMaximumCheckoutTime 在连接池强行返回前，一个连接可以进行“检出”的总计时间
  - poolTimeToWait  这是一个底层的设置，给连接一个机会去打印log状态，并重新尝试重新连接，免得长时间的等待
  - poolPingQuery Ping Query 是发送给数据库的Ping 信息，测试数据库连接是否良好和是否准备好了接受请求。默认值是“NO PING QUERY SET”，让大部分数据库都不使用Ping，返回一个友好的错误信息。
  - poolPingEnabled  设置PingQuery 是否可用。如果可用，你可以使用一个最简单的SQL 语句测试一下。默认是：false
  - poolPingConnectionsNotUsedFor 配置poolPingQuery 多长时间可以用。通常匹配数据库连接的超时，避免无谓的ping。默认：0，表示随时允许ping，当然，必须在poolPingEnabled 设为true 的前提下。
  
- JNDI 为了准备和Spring和javaEE一起使用，可以在外部也可以在内部配置这个数据源，然后在JNDI上下文中引用它
  - initial_context 这个属性是被用于上下文从 InitialContext 中（比如：InitialContext.lookup(initial_context)）查找。这个属性是可选，默认InitialContext将会查找data_source属性
  - data_source 数据源实例能搜索到的上下文路径。他会直接查找 initial_context 搜索返回的值，如果 initial_context 没有值，直接使用 InitialContext 查找
  - env.encoding=UTF8 （可以使用以‘env.’属性直接设给InitialContext）

7. Sql 映射XML文件仅仅只有一些初级的元素：
- cache  配置给定模式的缓存
- cache-ref 从别的模式中引用一个缓存
- resultMap  这是最复杂而却强大的一个元素了，它描述如何从结果集中加载对象
- sql 一个可以被其他语句复用的SQL 块
- insert  映射INSERT 语句
- update  映射UPDATE 语句
- delete  映射DELETE 语句
- select  映射SELECT 语句

```xml
<select id="selectPerson"
        parameterType="int"
        resultType="hashmap"
        resultMap="personResultMap"
        flushCache="false"
        useCache="true"
        timeout="10000"
        fetchSize="256"
        statementType="PREPARED"
        resultSetType="FORWARD_ONLY">
        
</select>
```

|属性|描述|
|:--|:--:|
| id | 在这个模式下唯一的标识符，可被其它语句引用 |
| parameterType | 传给此语句的参数的完整类名或别名 |
| resultType | 语句返回值类型的整类名或别名。注意，如果是集合，那么这里填写的是集合的项的整类名或别名，而不是集合本身的类名。resultType 与resultMap 不能并用 | 
| resultMap | 引用的外部resultMap 名。结果集映射是MyBatis 中最强 大的特性，也非常好理解。许多复杂的映射都可以轻松解决。resultType 与resultMap 不能并用 | 
| flushCache | 如果设为true，则会在每次语句调用的时候就会清空缓存。select 语句默认设为false | 
| useCache | 如果设为true，则语句的结果集将被缓存，select 语句默认设为false | 
| timeout | 设置驱动器在抛出异常前等待回应的最长时间，默认为不值，由驱动器自己决定 | 
| fetchSize | 设置一个值后，驱动器会在结果集数目达到此数值后，激发返回，默认为不值，由驱动器自己决定 | 
| statementType | STATEMENT，PREPARED 或CALLABLE 中的任意一个， 这就告诉MyBatis 分别使用Statement，PreparedStatement或者CallableStatement。默认：PREPARED | 
| resultSetType | FORWARD_ONLY 、SCROLL_SENSITIVE 、 SCROLL_INSENSITIVE 三个中的任意一个。默认是不设置，由驱动器决定| 

```xml
<insert id="insertAuthor"
        parameterType="domain.blog.Author"
        flushCache="true"
        statementType="PREPARED"
        keyProperty=""
        useGeneratedKeys=""
        timeout="20000">
</insert>
```
```xml
<update id="insertAuthor"
        parameterType="domain.blog.Author"
        flushCache="true"
        statementType="PREPARED"
        timeout="20000">
</update>
```
```xml
<delete
    id="insertAuthor"
    parameterType="domain.blog.Author"
    flushCache="true"
    statementType="PREPARED"
    timeout="20000">
    
</delete>
```

|属性|描述|
|:--|:--:|
|useGeneratedKeys| （ 仅限insert ） 告诉MyBatis 使用JDBC 的getGeneratedKeys 方法来获取数据库自己生成的主键（MySQL、SQLSERVER 等关系型数据库会有自动生成的字段）。默认：false |
|keyProperty| （ 仅限insert ） 标识一个将要被MyBatis 设置进 getGeneratedKeys 的key 所返回的值，或者为insert 语句 使用一个selectKey 子元素。|

```xml
<!-- 支持自动生成主键 -->
<insert id="insertAuthor" parameterType="domain.blog.Author" useGeneratedKeys="true" keyProperty="id">
    insert into Author(username,password,email,bio)
    values (#{username},#{password},#{email},#{bio})
</insert>
```
```xml
<insert id="insertAuthor" parameterType="domain.blog.Author" >
    <selectKey keyProperty="id" resultType="int" order="BEFORE">
    select CAST(RAND()*1000000  AS DECIMAL ) from dual
    </selectKey>

    insert into Author(id,username,password,email,bio)
    values (#{id},#{username},#{password},#{email},#{bio})
</insert>
```

```xml
<selectKey keyProperty="id"
           resultType="int"
           order="BEFORE"
           statementType="PREPARED" >

</selectKey>
```

|属性| 描述 |
|:-: |:--:|
| keyProperty | selectKey 语句生成结果需要设给的目的列 |
|resultType| 结果类型，MyBatis 通常可以自己检测到，但这并不影响给它一个确实的设定。MyBatis 允许使用基本的数据类型，也包括String 类型。|
| order | 可以设成BEFORE 或者AFTER，如果设为BEFORE，那它会先选择主键，然后设置keyProperty，再执行insert语句；如果设为AFTER，它就先运行insert 语句再运行selectKey 语句，通常是insert 语句中内部调用数据库（像Oracle）内嵌的序列机制。 |
|statementType| 像上面的那样， MyBatis 支持STATEMENT，PREPARED和CALLABLE 的语句形式， 对应Statement ，PreparedStatement 和CallableStatement 响应 |

8. resultMap 专题
```xml
<resultMap id="detailedBlogResultMap" type="Blog">
    <constructor>
        <idArg column="blog_id" javaType="int"></idArg>
    </constructor>
    <result property="title" column="blog_title"/>
    
    <association property="author" column="blog_author_id" javaType="Author">
        <id property="id" column="author_id"/>
        <result property="username" column="author_username"/>
        <result property="password" column="author_password"/>
        <result property="email" column="author_email"/>
        <result property="bio" column="author_bio"/>
        <result property="favouriteSection" column="author_favourite_section"/>
    </association>

    <collection property="posts" ofType="Post">
        <id property="id" column="post_id"/>
        <result property="subject" column="post_subject" />
        <association property="author" column="post_author_id" javaType="Author"/>
        
        <collection property="comments" column="post_id" ofType="Comment">
            <id property="id" column="comment_id"></id>
        </collection>
        
        <collection property="tags" column="post_id" ofType="Tag">
            <id property="id" column="tag_id"/>
        </collection>
        
        <discriminator javaType="int" column="draft">
            <case value="1" resultType="DraftPost"/>
        </discriminator>
    </collection>
</resultMap>
```

- constructor  用来将结果反射给一个实例化好的类的构造器
  - idArg  ID 参数；将结果集标记为ID，以方便全局调用
  - arg 反射到构造器的通常字段
- id  ID 结果，将结果集标记为ID，以方便全局调用
- result  反射到JavaBean 属性的普通字段 
- association  复杂类型的结合；多个结果合成的类型（一对一）
  - nested result mappings – 几resultMap 自身嵌套关联，也可以引用到一个其它上
- collection   复杂类型集合a collection of complex types
  - nested result mappings – resultMap 的集合，也可以引用到一个其它上
- discriminator  使用一个结果值以决定使用哪个resultMap
  - case 基本一些值的结果映射的case 情形
    - nested result mappings –一个case 情形本身就是一个结果映射，因此也可以包括一些相同的元素，也可以引用一个外部resultMap

9. mybatis 支持的JDBC类型（JdbcType 枚举）
- BIT 
- FLOAT 
- CHAR
- TIMESTAMP
- OTHER
- UNDEFINED
- TINYINT
- REAL
- VARCHAR
- BINARY
- BLOB
- NVARCHAR
- SMALLINT
- DOUBLE
- LONGVARCHAR
- VARBINARY
- CLOB
- NCHAR
- INTEGER
- NUMERIC
- DATE
- LONGVARBINARY
- BOOLEAN
- NCLOB
- BIGINT
- DECIMAL
- TIME
- NULL
- CURSOR

10. SQL 映射文件中加入简单的一行 <cache/>
- 所有在映射文件里的select 语句都将被缓存
- 所有在映射文件里insert,update 和delete 语句会清空缓存。
- 缓存使用“最近很少使用”算法来回收
   - LRU - 最近最少使用法：移出最近较长周期内都没有被使用的对象。
   - FIFI- 先进先出：移出队列里较早的对象
   - SOFT - 软引用：基于软引用规则，使用垃圾回收机制来移出对象
   - WEAK - 弱引用：基于弱引用规则，使用垃圾回收机制来强制性地移出对象
   - 默认值是LRU。
    
- 缓存不会被设定的时间所清空
- 每个缓存可以存储1024 个列表或对象的引用（不管查询出来的结果是什么）。
- 缓存将作为“读/写”缓存，意味着获取的对象不是共享的且对调用者是安全的。不会有其它的调用者或线程潜在修改。
```xml
<!-- 60s  -->

<cache 
    eviction="FIFO"
    flushInterval="60000"
    size="512"
    readOnly="true"
>

</cache>
```

11. 动态sql
- if
```xml
<if test=" title != null">
  and title like #{title}
</if>
```
- choose(when,otherwise)
```xml
<!-- 类似 switch  只是想从多个选项中选择一个-->
<choose>
    <when test="title != null">
      and title like #{title}
    </when>
    <otherwise>
      and featured=1
    </otherwise>
</choose>
```
- trim(where,set)
```xml
<!-- 无条件时候去除where，有条件时候去除 and或者or开头 -->
<where></where>

<trim prefix="WHERE" prefixOverrides="AND | OR "></trim>

<!-- update 去掉 set，和多余","-->
<set></set>

<trim prefix="SET" suffixOverrides=",">
```
- foreach 
```xml
<!-- 你可以传入一个List 实例或者一个数组给MyBatis 作为一个参数对象。如果你这么做，MyBatis会自动将它包装成一个Map，使用它的名称做为key。List 实例将使用“list”做为键，数组实例以“array” 作为键。 -->

<foreach item="item" index="index" collection="list"
open="(" separator="," close=")">
#{item}
</foreach>
```



# 注意事项
- 一个 SqlSessionFactory 实例对应一个数据库
- 如果传递了一个空值，那么 jdbcType 对于所有jdbc允许为空的列是必须的。需要研究一下 PreparedStatement.setNull() 
```
#{property,javaType=int,jdbcType=NUMERIC}

<!-- 自定义 类型处理 -->
#{age,javaType=int,jdbcType=NUMERIC,typeHandler=MyTypeHandler}

<!-- numericScale 决定相关的小数位多长 -->
#{height,javaType=double,jdbcType=NUMERIC,numericScale=2}
```
- mode 属性允许你设定IN，OUT 或者INOUT 参数，如果参数是OUT 或者INOUT，那实际的参数值可能会有变动，就你正在调用一个输出参数。如果mode=OUT 或者mode=INOUT ，并且jdbcType=CURSOR，你需要指定一个resultMap 映射结果集给这个参数类型。注意这里的javaType 类型是可选的，如果为空而jdbcType 是CURSOR 的话，则会自动地设给ResultSet。
```
#{department,
mode=OUT,
jdbcType=CURSOR,
javaType=ResultSet,
resultMap=departmentResultMap}

<!-- STRUCT 类型，使用 OUT模式时候 必须要告诉语句类型名称 -->
#{middleInitial, mode=OUT,
jdbcType=STRUCT,
jdbcTypeName=MY_TYPE,
resultMap=departmentResultMap } 
```
- 使用#{}语法会促使MyBatis 生成PreparedStatement 属性并且使用PreparedStatement 的参数（=?）来安全的设置值（会被''包裹）。想直接未更改的字符串代入到SQL 语句中。 ${columnName}

- N+1问题。association 使用 select 嵌套。要被用来加载复杂类型的其它映射语句的ID。从指定列属性中返回的值，将作为参数设置给目标select 语句。注意：在处理组合键时，你可以使用column="{prop1=col1,prop2=col2}" 这样的语法，设置多个列名传入到嵌套语句。
  - 你执行一个单条语句去获取一个列表记录。（“+1”）
  - 对每一条记录，再去执行一个select 语句去加载每一条记录的详细（“N”）
```xml
<resultMap id="blogResult" type="Blog">
    <association property="author" column="blog_author_id" javaType="Author" select="selectAuthor" />
</resultMap>

<select id="selectBlog" parameterType="int" resultType="Blog">
  select * from blog where id=#{id}
</select>

<select id="selectAuthor" parameterType="int" resultType="Author">
    select * from author where id=#{id}
</select>
```
