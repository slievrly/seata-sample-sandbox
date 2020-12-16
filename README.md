## 使用说明

### 目录清单：   
1. [seata-server-1.4.1.zip](https://github.com/slievrly/seata-sample-sandbox/tree/master/seata-server) 为 seata-server 压缩包   
2. seata-sample-sandbox 文件夹为微服务源码工程，包括：storage（库存微服务）、order（订单微服务）和 business（页面交互微服务）   

### 启动步骤：
1. 解压：unzip [seata-server-1.4.1.zip](https://github.com/slievrly/seata-sample-sandbox/tree/master/seata-server)   
2. 启动 seata-server：nohup sh ./seata/bin/seata-server.sh -p 8091 -m file &   
3. 启动微服务：依次启动 storage（库存微服务）、order（订单微服务）和 business（页面交互微服务）   
4. 服务调用：   
  1）实时数据检查页面：通过url 或浏览器可访问 http://localhost:8081/seata/check 订单表和库存表的实时数据。   
  2）服务调用请求：通过url 或浏览器可访问 http://localhost:8081/seata/feign?count=5 其中，参数count 表示扣减库存的数量，当库存数量不足时会触发分布式事务自动回滚，另外可以添加mockException 参数用于主动模拟业务异常，例如：http://localhost:8081/seata/feign?count=5&mockException=true    
  
  每次调用可以刷新数据检查页面用于检查数据表的实时数据。
