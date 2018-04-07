## 常用的操作h2语句

### 查看url队列
```
(select count(*) from tb_visit where HAS_VISITED = true) union (select count(*) from tb_visit where HAS_VISITED = false)
```

## application.properties详解

## entranceUrl.data
指定入口url

## ansj_seg分词工具文档
https://github.com/NLPchina/ansj_seg/wiki


## 功能
- 后台自动抓取知网的摘要和对应的关键词
- 摘要列表
- 摘要详情（显示已经计算好的各种结果）
- 算法比较
  - 计算关键词
  - 对比性能 
  
  
## 模块设计
### SystemController
负责开启与关闭爬虫

### SummaryController
- 分页 Page<Summary> findByPage(int number, int size)
- 查看详情 Summary findOne(@PathVariable(value="id") Long id)


### AnalyzationController
- 查看分析结果
- 重新计算关键词
计算时，首先删除之前的计算结果。计算时，能够查看进度。

## 数据库表
