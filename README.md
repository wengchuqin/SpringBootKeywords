## 常用的操作h2语句

### 查看url队列
```
(select count(*) from tb_visit where HAS_VISITED = true) union (select count(*) from tb_visit where HAS_VISITED = false)
```

## application.properties详解

## entranceUrl.data
指定入口url
