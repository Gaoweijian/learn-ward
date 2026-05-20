# 汉字启蒙系统 - 儿童教育平台

一个面向4-6岁儿童的卡片式学习系统，支持汉字、数字、算数、字母、单词五个模块的学习。

## 功能特性

### 儿童学习端
- 五大模块：汉字、数字、算数、字母、单词
- 卡片翻转学习模式
- 10-20题可选学习数量
- 进度追踪和得分统计
- 明亮友好的UI设计

### 家长管理端
- 五个模块的增删改查
- 学习记录查看
- 统计数据概览

## 技术栈

- **后端**: Spring Boot 2.7 + MySQL 8.0 + Spring Data JPA
- **前端**: 原生 HTML/CSS/ES6 JavaScript（无框架）
- **安全**: Spring Security + BCrypt密码加密

## 项目结构

```
demo/
├── src/main/java/com/example/demo/
│   ├── HanziLearningApplication.java    # 启动类
│   ├── config/
│   │   ├── SecurityConfig.java          # 安全配置
│   │   ├── WebConfig.java                # Web配置
│   │   └── DataInitializer.java          # 数据初始化
│   ├── controller/
│   │   ├── HanziController.java          # 汉字CRUD
│   │   ├── NumberController.java         # 数字CRUD
│   │   ├── ArithmeticController.java     # 算数CRUD
│   │   ├── LetterController.java        # 字母CRUD
│   │   ├── WordController.java           # 单词CRUD
│   │   ├── StudyController.java          # 学习API
│   │   └── AuthController.java           # 认证API
│   ├── service/                          # 业务逻辑层
│   ├── repository/                       # 数据访问层
│   └── entity/                           # 实体类
├── src/main/resources/
│   ├── static/
│   │   ├── index.html                    # 儿童学习端
│   │   └── admin.html                    # 管理后台
│   └── application.yml                   # 配置文件
└── sql/
    └── init_database.sql                 # 数据库初始化脚本
```

## 快速开始

### 1. 环境要求

- JDK 11+
- MySQL 8.0+
- Maven 3.6+

### 2. 数据库配置

1. 创建数据库并导入数据：

```bash
mysql -u root -p < sql/init_database.sql
```

或手动执行 `sql/init_database.sql` 中的SQL语句。

2. 修改 `src/main/resources/application.yml` 中的数据库连接配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hanzi_learning
    username: root
    password: your_password
```

### 3. 启动项目

```bash
# 进入项目目录
cd demo

# 使用 Maven 启动
mvn spring-boot:run

# 或打包后运行
mvn clean package
java -jar target/demo-1.0.0.jar
```

### 4. 访问应用

- 儿童学习端: http://localhost:8080/
- 管理后台: http://localhost:8080/admin

## 默认账号

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 管理员 | admin | admin123 |
| 儿童 | child1 | 123456 |
| 儿童 | child2 | 123456 |

## API接口

### 儿童学习端

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | `/api/study/{moduleType}?limit=10` | 获取随机题目 |
| POST | `/api/study/record` | 保存学习记录 |
| GET | `/api/study/history/{userId}` | 获取学习历史 |
| GET | `/api/study/records` | 获取所有学习记录(管理员) |

### 管理端CRUD

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | `/api/admin/hanzi` | 获取所有汉字 |
| POST | `/api/admin/hanzi` | 添加汉字 |
| PUT | `/api/admin/hanzi/{id}` | 更新汉字 |
| DELETE | `/api/admin/hanzi/{id}` | 删除汉字 |

其他模块（number, arithmetic, letter, word）接口同理。

### 认证接口

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | `/api/auth/login` | 登录 |
| POST | `/api/auth/register` | 注册 |
| GET | `/api/auth/children` | 获取儿童用户列表 |
| GET | `/api/auth/users` | 获取所有用户 |

## 数据库表结构

| 表名 | 描述 |
|------|------|
| user | 用户表（管理员/儿童） |
| hanzi | 汉字库 |
| number | 数字库 |
| arithmetic | 算数库 |
| letter | 字母库 |
| word | 单词库 |
| learning_record | 学习记录 |

## 模块说明

### 模块类型值 (moduleType)

- `hanzi` - 汉字
- `number` - 数字
- `arithmetic` - 算数
- `letter` - 字母
- `word` - 单词

## 开发说明

### 密码说明

默认用户密码已使用BCrypt加密存储在新项目中。如果是全新项目，启动时会自动初始化默认用户。

### CORS配置

后端已配置允许所有来源的跨域请求，便于开发和测试。生产环境请根据需要调整 `SecurityConfig.java` 中的CORS配置。

### 静态资源

前端HTML文件位于 `src/main/resources/static/` 目录，Spring Boot会自动提供静态资源服务。

## 扩展建议

1. **添加更多内容**: 在各模块表中插入更多数据
2. **图片支持**: 修改实体类添加 `imageUrl` 字段，存储图片URL
3. **音频支持**: 在单词表中添加 `audioUrl` 字段，支持发音
4. **用户管理**: 完善管理端用户CRUD功能
5. **数据统计**: 添加更详细的学习数据分析

## 许可证

MIT License
