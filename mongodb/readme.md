# mongodb集群 #
## 一. 集群搭建 ##
### 1. 目录结构
	mkdir -p /home/mongo/mongodb/conf
	mkdir -p /home/mongo/mongodb/mongos/log
	mkdir -p /home/mongo/mongodb/config/data
	mkdir -p /home/mongo/mongodb/config/log
	mkdir -p /home/mongo/mongodb/shard1/data
	mkdir -p /home/mongo/mongodb/shard1/log
	mkdir -p /home/mongo/mongodb/shard2/data
	mkdir -p /home/mongo/mongodb/shard2/log
	mkdir -p /home/mongo/mongodb/shard3/data
	mkdir -p /home/mongo/mongodb/shard3/log
### 2. 配置文件 ###
- config server
vi /home/mongo/mongodb/conf/config.conf
```
pidfilepath = /home/mongo/mongodb/config/log/configsrv.pid
dbpath = /home/mongo/mongodb/config/data
logpath = /home/mongo/mongodb/config/log/congigsrv.log
logappend = true
bind_ip = 0.0.0.0
port = 21000
fork = true
#declare this is a config db of a cluster;
configsvr = true
#副本集名称
replSet=configs
#设置最大连接数
maxConns=20000
```
- shard1
vi /home/mongo/mongodb/conf/shard1.conf
```
pidfilepath = /home/mongo/mongodb/shard1/log/shard1.pid
dbpath = /home/mongo/mongodb/shard1/data
logpath = /home/mongo/mongodb/shard1/log/shard1.log
logappend = true
bind_ip = 0.0.0.0
port = 27001
fork = true
#打开web监控
httpinterface=true
rest=true
#副本集名称
replSet=shard1
#declare this is a shard db of a cluster;
shardsvr = true
#设置最大连接数
maxConns=20000
```
- shard2
vi /home/mongo/mongodb/conf/shard2.conf
```
pidfilepath = /home/mongo/mongodb/shard2/log/shard2.pid
dbpath = /home/mongo/mongodb/shard2/data
logpath = /home/mongo/mongodb/shard2/log/shard2.log
logappend = true
bind_ip = 0.0.0.0
port = 27002
fork = true
#打开web监控
httpinterface=true
rest=true
#副本集名称
replSet=shard2
#declare this is a shard db of a cluster;
shardsvr = true
#设置最大连接数
maxConns=20000
```
- shard3
vi /home/mongo/mongodb/conf/shard3.conf
```
pidfilepath = /home/mongo/mongodb/shard3/log/shard3.pid
dbpath = /home/mongo/mongodb/shard3/data
logpath = /home/mongo/mongodb/shard3/log/shard3.log
logappend = true
bind_ip = 0.0.0.0
port = 27003
fork = true
#打开web监控
httpinterface=true
rest=true
#副本集名称
replSet=shard3
#declare this is a shard db of a cluster;
shardsvr = true
#设置最大连接数
maxConns=20000
```
- momgos
vi /home/mongo/mongodb/conf/mongos.conf
```
pidfilepath = /home/mongo/mongodb/mongos/log/mongos.pid
logpath = /home/mongo/mongodb/mongos/log/mongos.log
logappend = true
bind_ip = 0.0.0.0
port = 20000
fork = true
#监听的配置服务器,只能有1个或者3个 configs为配置服务器的副本集名字
configdb = configs/192.168.10.55:21000,192.168.10.126:21000,192.168.10.229:21000
#设置最大连接数
maxConns=20000
```
### 3. 进程启动 ###
- config启动配置
bin/mongod -f ~/mongodb/conf/config.conf
bin/mongo --port 21000
```
config = { _id:"configs", members:[
                   	 {_id:0,host:"192.168.10.55:21000"},
                     {_id:1,host:"192.168.10.126:21000"},
                     {_id:2,host:"192.168.10.229:21000"}
                ]
         }
rs.initiate(configs)
```
- shard1启动配置
bin/mongod -f ~/mongodb/conf/shard1.conf
bin/mongo --port 27001
use admin
```
config = { _id:"shard1", members:[
                   	 {_id:0,host:"192.168.10.55:27001"},
                     {_id:1,host:"192.168.10.126:27001"},
                     {_id:2,host:"192.168.10.229:27001", arbiterOnly: true}
                ]
         }
rs.initiate(config);
```
- shard2启动配置
bin/mongod -f ~/mongodb/conf/shard2.conf
bin/mongo --port 27002
use admin
```
config = { _id:"shard2", members:[
                   	 {_id:0,host:"192.168.10.55:27002", arbiterOnly: true},
                     {_id:1,host:"192.168.10.126:27002"},
                     {_id:2,host:"192.168.10.229:27002"}
                ]
         }
rs.initiate(config);
```
- shard3启动配置
bin/mongod -f ~/mongodb/conf/shard3.conf
bin/mongo --port 27003
use admin
```
config = { _id:"shard3", members:[
                   	 {_id:0,host:"192.168.10.55:27003"},
                     {_id:1,host:"192.168.10.126:27003", arbiterOnly: true},
                     {_id:2,host:"192.168.10.229:27003"}
                ]
         }
rs.initiate(config);
```
- mongos启动配置
bin/mongos -f ~/mongodb/conf/mongos.conf
bin/mongo --port 20000
use  admin
```
sh.addShard("shard1/192.168.10.55:27001,192.168.10.126:27001,192.168.10.229:27001")
sh.addShard("shard1/192.168.10.55:27002,192.168.10.126:27002,192.168.10.229:27002")
sh.addShard("shard1/192.168.10.55:27003,192.168.10.126:27003,192.168.10.229:27003")
sh.status()
```
### 4. 进程关闭 ###
use admin
db.shutdownServer()
db.shutdownServer({force : true})


## 二. 集群运维 ##
### 1. 库表操作 ###
### 2. 状态查看 ###
### 3. 分片策略 ###
db.runCommand( { removeshard:"your_shard_name" } )
db.runCommand({listshards:1})
db.runCommand({"enablesharding":"test"})
db.createCollection("user")
db.runCommand({"shardcollection":"test.user","key":{_id:1}})

db.users.ensureIndex({"username":"hashed"})
sh.shardCollection("app.users",{"username":"hashed"})