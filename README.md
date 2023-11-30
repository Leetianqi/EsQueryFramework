# EsQueryFramework

该框架为es的查询框架
使用的es相关版本为7.15.2
设计原因:因为es的查询语句较为复杂，大多数情况都是使用硬编码的方法，这样会导致扩展性和可靠性很差；
该框架通过注解和反射的方式构建es的查询语句；

注： 反选的含义：
即如果当前订阅中对车辆的选配数据进行反选，则匹配该订阅的车辆可以没有这个选配或者是这个选配值的其他数据；
举个例子：车辆的卡钳分为：红色卡钳，橙色卡钳；当订阅反选红色卡钳时，则表明该订阅希望匹配的车辆没有红色卡钳，即车辆可以没有卡钳或者不是红色卡钳（可以是橙色卡钳）

为了实现反选的功能，也需要对反选的属性值进行特殊处理，即如果反选了红色卡钳（卡钳的FeatureCode为F000001），则其属性值为0，这个在构建es索引文档时也是有讲究的

# ESDocField.java

该注解主要用于ES查询的对象，通过对对象属性添加注解，在执行com.es.config.builder.EsQueryBuilder.wrapSubscriptionQueryBuilder
中把ES查询对象转换成ES的查询语句；

# wrapSubscriptionQueryBuilder

下面对wrapSubscriptionQueryBuilder方法进行解析：
其入参T param代表：ES查询的对象（也就是被ESDocField注解修饰）
其入参BoolQueryBuilder boolQueryBuilder用于处理解析后的es语句


对象上注解的属性均影响了解析的流程