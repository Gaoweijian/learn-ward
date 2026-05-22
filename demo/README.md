# 儿童汉字启蒙学习系统

一个面向4-8岁儿童的卡片式学习平台，支持汉字、数字、算数、字母、单词五个模块的趣味学习。

![版本](https://img.shields.io/badge/version-1.0.0-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green)
![License](https://img.shields.io/badge/license-MIT-orange)

## 功能特性

### 儿童学习端 (`/`)
| 功能 | 说明 |
|------|------|
| 五大模块 | 汉字、数字、算数、字母、单词 |
| 卡片翻转学习 | 点击卡片翻转查看答案 |
| 选题模式 | 随机选题 / 自定义选题 |
| 题库浏览 | 分页浏览所有题目，支持搜索筛选 |
| 进度追踪 | 实时显示当前进度和得分 |
| 语音朗读 | TTS技术自动朗读发音 |
| 深色模式 | 护眼深色主题一键切换 |
| 错题本 | 自动收集答错题目，随时复习 |
| 复习提醒 | 每日定时提醒复习错题 |
| 汉字笔画动画 | 观看汉字书写顺序动画 |
| 多语言支持 | 中文/English 一键切换 |
| PWA支持 | 可安装到桌面，离线使用 |
| 键盘快捷键 | A/B键快速作答，方向键翻题 |

### 汉字卡片 (`/hanzi-cards.html`)
- 网格视图 / 单字大卡视图切换
- 汉字、拼音搜索
- 分页导航 + 页码跳转
- 点击朗读发音
- 详情弹窗展示
- 多语言界面切换

### 家长管理端 (`/admin`)
- 五个模块完整 CRUD 管理
- 学习记录查看与统计
- 数据统计概览仪表盘
- 搜索过滤与分页

## 技术栈

| 层级 | 技术 |
|------|------|
| 后端 | Spring Boot 2.7 + MySQL 8.0 |
| ORM | Spring Data JPA + Hibernate |
| 安全 | Spring Security + BCrypt |
| 前端 | 原生 HTML/CSS/JavaScript (ES6) |
| 构建 | Gradle |
| 其他 | PWA (Service Worker + Web App Manifest) |

## 项目结构

```
learn-ward/
├── demo/
│   ├── src/main/java/com/example/demo/
│   │   ├── HanziLearningApplication.java    # 启动类
│   │   ├── config/
│   │   │   ├── SecurityConfig.java          # 安全配置
│   │   │   ├── WebConfig.java               # Web配置
│   │   │   └── DataInitializer.java         # 数据初始化
│   │   ├── controller/
│   │   │   ├── HanziController.java         # 汉字 CRUD
│   │   │   ├── NumberController.java        # 数字 CRUD
│   │   │   ├── ArithmeticController.java    # 算数 CRUD
│   │   │   ├── LetterController.java        # 字母 CRUD
│   │   │   ├── WordController.java          # 单词 CRUD
│   │   │   ├── StudyController.java         # 学习 API
│   │   │   └── AuthController.java         # 认证 API
│   │   ├── service/                        # 业务逻辑层
│   │   ├── repository/                     # 数据访问层
│   │   └── entity/                         # 实体类
│   ├── src/main/resources/
│   │   ├── static/
│   │   │   ├── index.html                  # 儿童学习端
│   │   │   ├── hanzi-cards.html            # 汉字卡片浏览
│   │   │   ├── admin.html                  # 管理后台
│   │   │   ├── manifest.json               # PWA 配置
│   │   │   └── sw.js                       # Service Worker
│   │   └── application.yml                 # 应用配置
│   └── sql/
│       └── init_database.sql               # 数据库初始化脚本
└── README.md
```

## 快速开始

### 1. 环境要求
- JDK 11+
- MySQL 8.0+
- Gradle 7+

### 2. 数据库配置

```bash
# 创建数据库并导入数据
mysql -u root -p < sql/init_database.sql
```

或修改 `application.yml` 中的连接配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hanzi_learning
    username: root
    password: your_password
```

### 3. 启动项目

```bash
cd demo

# 使用 Gradle 启动
./gradlew bootRun

# 或打包后运行
./gradlew build
java -jar build/libs/demo-1.0.0.jar
```

### 4. 访问应用
- 儿童学习端: http://localhost:8080/
- 汉字卡片: http://localhost:8080/hanzi-cards.html
- 管理后台: http://localhost:8080/admin

## 默认账号

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 管理员 | admin | admin123 |
| 儿童用户 | child1 | 123456 |
| 儿童用户 | child2 | 123456 |

## API 接口

### 学习相关

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | `/api/study/{moduleType}?limit=10` | 获取随机题目 |
| GET | `/api/study/library/{moduleType}` | 获取题库列表 |
| POST | `/api/study/custom` | 自定义选题 |
| POST | `/api/study/record` | 保存学习记录 |
| GET | `/api/study/history/{userId}` | 获取学习历史 |
| GET | `/api/study/records` | 获取所有学习记录 |

### 管理端 CRUD

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | `/api/admin/{module}` | 获取所有数据 |
| POST | `/api/admin/{module}` | 新增数据 |
| PUT | `/api/admin/{module}/{id}` | 更新数据 |
| DELETE | `/api/admin/{module}/{id}` | 删除数据 |
| GET | `/api/admin/{module}/cards` | 分页获取（卡片页用） |

> 模块类型 (module): `hanzi`, `number`, `arithmetic`, `letter`, `word`

### 认证相关

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | `/api/auth/login` | 用户登录 |
| POST | `/api/auth/register` | 用户注册 |
| GET | `/api/auth/children` | 获取儿童用户列表 |
| GET | `/api/auth/users` | 获取所有用户 |

## 数据库表结构

| 表名 | 描述 |
|------|------|
| `user` | 用户表（管理员/儿童） |
| `hanzi` | 汉字库（汉字、拼音、释义、组词） |
| `number` | 数字库（数字值、英文名） |
| `arithmetic` | 算数库（题目、答案） |
| `letter` | 字母库（字母、示例单词） |
| `word` | 单词库（单词、释义） |
| `learning_record` | 学习记录表 |

## 扩展建议

1. **更多学习模块**: 添加古诗词、拼音、成语等模块
2. **游戏化系统**: 成就徽章、积分排行榜、学习打卡
3. **✅ 笔画动画**: 展示汉字书写顺序（已完成）
4. **个性化**: 多套皮肤主题、头像系统
5. **数据分析**: 周报/月报生成、薄弱点分析
6. **家长控制**: 学习时间限制、内容过滤
7. **✅ 多语言支持**: 中英文界面切换（已完成）

## 开发说明

- 密码使用 BCrypt 加密存储
- CORS 已配置允许所有来源（开发环境）
- 静态资源由 Spring Boot 自动提供服务
- JPA ddl-auto 设置为 update，初次启动自动建表

## 许可证

MIT License
