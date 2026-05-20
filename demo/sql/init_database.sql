-- ===============================================
-- 汉字启蒙系统数据库初始化脚本
-- 数据库名: hanzi_learning
-- ===============================================

CREATE DATABASE IF NOT EXISTS hanzi_learning 
DEFAULT CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE hanzi_learning;

-- ===============================================
-- 1. 用户表
-- ===============================================
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码（加密存储）',
    `role` VARCHAR(20) NOT NULL DEFAULT 'child' COMMENT '角色：admin-管理员，child-儿童',
    `nickname` VARCHAR(50) DEFAULT NULL COMMENT '昵称',
    `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入默认用户
INSERT INTO `user` (username, password, role, nickname) VALUES 
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'admin', '管理员'),
('child1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'child', '小明'),
('child2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'child', '小红');

-- ===============================================
-- 2. 汉字库
-- ===============================================
DROP TABLE IF EXISTS `hanzi`;
CREATE TABLE `hanzi` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '汉字ID',
    `hanzi` VARCHAR(10) NOT NULL COMMENT '汉字',
    `pinyin` VARCHAR(50) NOT NULL COMMENT '拼音',
    `meaning` VARCHAR(255) NOT NULL COMMENT '含义解释',
    `image_url` VARCHAR(255) DEFAULT NULL COMMENT '配图URL或emoji',
    `example_word` VARCHAR(50) DEFAULT NULL COMMENT '例词',
    `stroke_count` INT DEFAULT 1 COMMENT '笔画数',
    `difficulty` INT DEFAULT 1 COMMENT '难度等级：1-简单，2-中等，3-困难',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='汉字库';

INSERT INTO `hanzi` (hanzi, pinyin, meaning, image_url, example_word, stroke_count, difficulty) VALUES
('日', 'rì', '太阳，白天', '☀️', '生日', 4, 1),
('月', 'yuè', '月亮，月份', '🌙', '月亮', 4, 1),
('水', 'shuǐ', '液态的水', '💧', '喝水', 4, 1),
('火', 'huǒ', '燃烧产生的光和热', '🔥', '大火', 4, 1),
('山', 'shān', '高耸的地形', '⛰️', '高山', 3, 1),
('大', 'dà', '体积、面积、数量大', '👨‍👩‍👧', '大人', 3, 1),
('小', 'xiǎo', '体积、面积、数量小', '🐜', '小孩', 3, 1),
('人', 'rén', '人类，自己或他人', '👤', '人们', 2, 1),
('天', 'tiān', '天空，日子', '🌈', '今天', 4, 1),
('上', 'shàng', '位置在高处', '⬆️', '上学', 3, 1),
('下', 'xià', '位置在低处', '⬇️', '下雨', 3, 1),
('中', 'zhōng', '中间，中国', '🏮', '中国', 4, 2),
('口', 'kǒu', '嘴巴，入口', '👄', '入口', 3, 1),
('目', 'mù', '眼睛', '👀', '目光', 5, 2),
('手', 'shǒu', '人的上肢', '✋', '握手', 4, 1),
('足', 'zú', '脚，足够', '🦶', '足球', 7, 3),
('马', 'mǎ', '一种动物', '🐴', '马车', 3, 1),
('鸟', 'niǎo', '会飞的动物', '🐦', '小鸟', 5, 2),
('鱼', 'yú', '水生动物', '🐟', '金鱼', 8, 2),
('花', 'huā', '植物的繁殖器官', '🌸', '花朵', 7, 2);

-- ===============================================
-- 3. 数字库
-- ===============================================
DROP TABLE IF EXISTS `number`;
CREATE TABLE `number` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '数字ID',
    `number_value` INT NOT NULL UNIQUE COMMENT '数字值（0-20）',
    `english_name` VARCHAR(20) NOT NULL COMMENT '英文名称',
    `chinese_reading` VARCHAR(20) NOT NULL COMMENT '中文读法',
    `image_url` VARCHAR(255) DEFAULT NULL COMMENT '配图URL或emoji',
    `color` VARCHAR(20) DEFAULT NULL COMMENT '代表颜色',
    `difficulty` INT DEFAULT 1 COMMENT '难度等级',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数字库';

INSERT INTO `number` (number_value, english_name, chinese_reading, image_url, color, difficulty) VALUES
(0, 'zero', '零', '0️⃣', '灰色', 1),
(1, 'one', '一', '1️⃣', '红色', 1),
(2, 'two', '二', '2️⃣', '橙色', 1),
(3, 'three', '三', '3️⃣', '黄色', 1),
(4, 'four', '四', '4️⃣', '绿色', 1),
(5, 'five', '五', '5️⃣', '青色', 1),
(6, 'six', '六', '6️⃣', '蓝色', 1),
(7, 'seven', '七', '7️⃣', '紫色', 1),
(8, 'eight', '八', '8️⃣', '粉色', 1),
(9, 'nine', '九', '9️⃣', '金色', 1),
(10, 'ten', '十', '🔟', '彩虹', 1),
(11, 'eleven', '十一', '1️⃣1️⃣', '蓝色', 2),
(12, 'twelve', '十二', '1️⃣2️⃣', '绿色', 2),
(13, 'thirteen', '十三', '1️⃣3️⃣', '黄色', 2),
(14, 'fourteen', '十四', '1️⃣4️⃣', '橙色', 2),
(15, 'fifteen', '十五', '1️⃣5️⃣', '红色', 2),
(16, 'sixteen', '十六', '1️⃣6️⃣', '紫色', 2),
(17, 'seventeen', '十七', '1️⃣7️⃣', '粉色', 2),
(18, 'eighteen', '十八', '1️⃣8️⃣', '青色', 2),
(19, 'nineteen', '十九', '1️⃣9️⃣', '金色', 2),
(20, 'twenty', '二十', '2️⃣0️⃣', '彩虹', 2);

-- ===============================================
-- 4. 算数库
-- ===============================================
DROP TABLE IF EXISTS `arithmetic`;
CREATE TABLE `arithmetic` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '算数ID',
    `question` VARCHAR(50) NOT NULL COMMENT '题目（如2+3）',
    `answer` INT NOT NULL COMMENT '答案',
    `operator` VARCHAR(10) NOT NULL COMMENT '运算符：add/sub/mul/div',
    `operand1` INT NOT NULL COMMENT '第一个操作数',
    `operand2` INT NOT NULL COMMENT '第二个操作数',
    `difficulty` INT DEFAULT 1 COMMENT '难度等级：1-10以内加减法，2-20以内加减法，3-乘法',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='算数库';

INSERT INTO `arithmetic` (question, answer, operator, operand1, operand2, difficulty) VALUES
('1 + 1', 2, 'add', 1, 1, 1),
('1 + 2', 3, 'add', 1, 2, 1),
('2 + 1', 3, 'add', 2, 1, 1),
('1 + 3', 4, 'add', 1, 3, 1),
('2 + 2', 4, 'add', 2, 2, 1),
('1 + 4', 5, 'add', 1, 4, 1),
('2 + 3', 5, 'add', 2, 3, 1),
('3 + 2', 5, 'add', 3, 2, 1),
('1 + 5', 6, 'add', 1, 5, 1),
('2 + 4', 6, 'add', 2, 4, 1),
('3 + 3', 6, 'add', 3, 3, 1),
('1 + 6', 7, 'add', 1, 6, 1),
('2 + 5', 7, 'add', 2, 5, 1),
('3 + 4', 7, 'add', 3, 4, 1),
('4 + 3', 7, 'add', 4, 3, 1),
('1 + 7', 8, 'add', 1, 7, 1),
('2 + 6', 8, 'add', 2, 6, 1),
('3 + 5', 8, 'add', 3, 5, 1),
('4 + 4', 8, 'add', 4, 4, 1),
('1 + 8', 9, 'add', 1, 8, 1),
('2 + 7', 9, 'add', 2, 7, 1),
('3 + 6', 9, 'add', 3, 6, 1),
('4 + 5', 9, 'add', 4, 5, 1),
('5 + 4', 9, 'add', 5, 4, 1),
('1 + 9', 10, 'add', 1, 9, 1),
('2 + 8', 10, 'add', 2, 8, 1),
('3 + 7', 10, 'add', 3, 7, 1),
('4 + 6', 10, 'add', 4, 6, 1),
('5 + 5', 10, 'add', 5, 5, 1),
('5 - 1', 4, 'sub', 5, 1, 1),
('6 - 1', 5, 'sub', 6, 1, 1),
('6 - 2', 4, 'sub', 6, 2, 1),
('7 - 1', 6, 'sub', 7, 1, 1),
('7 - 2', 5, 'sub', 7, 2, 1),
('7 - 3', 4, 'sub', 7, 3, 1),
('8 - 1', 7, 'sub', 8, 1, 1),
('8 - 2', 6, 'sub', 8, 2, 1),
('8 - 3', 5, 'sub', 8, 3, 1),
('8 - 4', 4, 'sub', 8, 4, 1),
('9 - 1', 8, 'sub', 9, 1, 1),
('9 - 2', 7, 'sub', 9, 2, 1),
('9 - 3', 6, 'sub', 9, 3, 1),
('9 - 4', 5, 'sub', 9, 4, 1),
('9 - 5', 4, 'sub', 9, 5, 1),
('10 - 1', 9, 'sub', 10, 1, 1),
('10 - 2', 8, 'sub', 10, 2, 1),
('10 - 3', 7, 'sub', 10, 3, 1),
('10 - 4', 6, 'sub', 10, 4, 1),
('10 - 5', 5, 'sub', 10, 5, 1),
('10 - 6', 4, 'sub', 10, 6, 1),
('10 - 7', 3, 'sub', 10, 7, 1),
('10 - 8', 2, 'sub', 10, 8, 1),
('10 - 9', 1, 'sub', 10, 9, 1);

-- ===============================================
-- 5. 字母库
-- ===============================================
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '字母ID',
    `letter_upper` CHAR(1) NOT NULL COMMENT '大写字母',
    `letter_lower` CHAR(1) NOT NULL COMMENT '小写字母',
    `word_example` VARCHAR(50) NOT NULL COMMENT '例词',
    `word_meaning` VARCHAR(100) DEFAULT NULL COMMENT '例词含义',
    `image_url` VARCHAR(255) DEFAULT NULL COMMENT '配图URL或emoji',
    `phonetic` VARCHAR(20) DEFAULT NULL COMMENT '音标',
    `difficulty` INT DEFAULT 1 COMMENT '难度等级',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字母库';

INSERT INTO `letter` (letter_upper, letter_lower, word_example, word_meaning, image_url, phonetic, difficulty) VALUES
('A', 'a', 'Apple', '苹果', '🍎', '/æ/', 1),
('B', 'b', 'Ball', '球', '⚽', '/b/', 1),
('C', 'c', 'Cat', '猫', '🐱', '/k/', 1),
('D', 'd', 'Dog', '狗', '🐕', '/d/', 1),
('E', 'e', 'Egg', '鸡蛋', '🥚', '/e/', 1),
('F', 'f', 'Fish', '鱼', '🐟', '/f/', 1),
('G', 'g', 'Grape', '葡萄', '🍇', '/g/', 1),
('H', 'h', 'Hat', '帽子', '🎩', '/h/', 1),
('I', 'i', 'Ice cream', '冰淇淋', '🍦', '/aɪ/', 1),
('J', 'j', 'Juice', '果汁', '🧃', '/dʒ/', 1),
('K', 'k', 'Kite', '风筝', '🪁', '/k/', 1),
('L', 'l', 'Lion', '狮子', '🦁', '/l/', 1),
('M', 'm', 'Moon', '月亮', '🌙', '/m/', 1),
('N', 'n', 'Nest', '鸟巢', '🪺', '/n/', 1),
('O', 'o', 'Orange', '橙子', '🍊', '/əʊ/', 1),
('P', 'p', 'Pig', '猪', '🐷', '/p/', 1),
('Q', 'q', 'Queen', '女王', '👸', '/kjuː/', 1),
('R', 'r', 'Rainbow', '彩虹', '🌈', '/r/', 1),
('S', 's', 'Sun', '太阳', '☀️', '/s/', 1),
('T', 't', 'Tree', '树', '🌳', '/t/', 1),
('U', 'u', 'Umbrella', '雨伞', '☂️', '/ʌ/', 1),
('V', 'v', 'Violin', '小提琴', '🎻', '/v/', 2),
('W', 'w', 'Water', '水', '💧', '/w/', 1),
('X', 'x', 'Xylophone', '木琴', '🎵', '/ks/', 2),
('Y', 'y', 'Yellow', '黄色', '🟡', '/j/', 1),
('Z', 'z', 'Zoo', '动物园', '🦓', '/z/', 1);

-- ===============================================
-- 6. 单词库
-- ===============================================
DROP TABLE IF EXISTS `word`;
CREATE TABLE `word` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '单词ID',
    `word` VARCHAR(50) NOT NULL COMMENT '单词',
    `phonetic` VARCHAR(50) DEFAULT NULL COMMENT '音标',
    `meaning` VARCHAR(255) NOT NULL COMMENT '中文含义',
    `image_url` VARCHAR(255) DEFAULT NULL COMMENT '配图URL或emoji',
    `category` VARCHAR(20) NOT NULL COMMENT '分类：动物/颜色/水果/食物/自然/其他',
    `audio_url` VARCHAR(255) DEFAULT NULL COMMENT '发音音频URL',
    `difficulty` INT DEFAULT 1 COMMENT '难度等级：1-简单，2-中等，3-较难',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='单词库';

INSERT INTO `word` (word, phonetic, meaning, image_url, category, difficulty) VALUES
('dog', '/dɒɡ/', '狗', '🐕', '动物', 1),
('cat', '/kæt/', '猫', '🐱', '动物', 1),
('bird', '/bɜːd/', '鸟', '🐦', '动物', 1),
('fish', '/fɪʃ/', '鱼', '🐟', '动物', 1),
('rabbit', '/ˈræbɪt/', '兔子', '🐰', '动物', 1),
('bear', '/beə/', '熊', '🐻', '动物', 1),
('panda', '/ˈpændə/', '熊猫', '🐼', '动物', 1),
('monkey', '/ˈmʌŋki/', '猴子', '🐵', '动物', 1),
('elephant', '/ˈelɪfənt/', '大象', '🐘', '动物', 2),
('tiger', '/ˈtaɪɡə/', '老虎', '🐯', '动物', 2),
('red', '/red/', '红色', '🔴', '颜色', 1),
('blue', '/bluː/', '蓝色', '🔵', '颜色', 1),
('yellow', '/ˈjeloʊ/', '黄色', '🟡', '颜色', 1),
('green', '/ɡriːn/', '绿色', '🟢', '颜色', 1),
('pink', '/pɪŋk/', '粉色', '🩷', '颜色', 1),
('purple', '/ˈpɜːrpl/', '紫色', '🟣', '颜色', 2),
('orange', '/ˈɔːrɪndʒ/', '橙色', '🟠', '颜色', 1),
('black', '/blæk/', '黑色', '⚫', '颜色', 1),
('white', '/waɪt/', '白色', '⚪', '颜色', 1),
('apple', '/ˈæpl/', '苹果', '🍎', '水果', 1),
('banana', '/bəˈnænə/', '香蕉', '🍌', '水果', 1),
('orange', '/ˈɔːrɪndʒ/', '橙子', '🍊', '水果', 1),
('grape', '/ɡreɪp/', '葡萄', '🍇', '水果', 1),
('watermelon', '/ˈwɔːtərmelən/', '西瓜', '🍉', '水果', 2),
('strawberry', '/ˈstrɔːbəri/', '草莓', '🍓', '水果', 2),
('peach', '/piːtʃ/', '桃子', '🍑', '水果', 2),
('pineapple', '/ˈpaɪnæpl/', '菠萝', '🍍', '水果', 2),
('sun', '/sʌn/', '太阳', '☀️', '自然', 1),
('moon', '/muːn/', '月亮', '🌙', '自然', 1),
('star', '/stɑːr/', '星星', '⭐', '自然', 1),
('cloud', '/klaʊd/', '云', '☁️', '自然', 1),
('rain', '/reɪn/', '雨', '🌧️', '自然', 1),
('snow', '/snoʊ/', '雪', '❄️', '自然', 1),
('flower', '/ˈflaʊər/', '花', '🌸', '自然', 1),
('tree', '/triː/', '树', '🌳', '自然', 1),
('rainbow', '/ˈreɪnboʊ/', '彩虹', '🌈', '自然', 2),
('house', '/haʊs/', '房子', '🏠', '其他', 1),
('car', '/kɑːr/', '汽车', '🚗', '其他', 1),
('book', '/bʊk/', '书', '📚', '其他', 1),
('ball', '/bɔːl/', '球', '⚽', '其他', 1);

-- ===============================================
-- 7. 学习记录表
-- ===============================================
DROP TABLE IF EXISTS `learning_record`;
CREATE TABLE `learning_record` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '记录ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `module_type` VARCHAR(20) NOT NULL COMMENT '模块类型：hanzi/number/arithmetic/letter/word',
    `question_ids` JSON NOT NULL COMMENT '本次学习的题目ID列表（JSON数组）',
    `answers` JSON NOT NULL COMMENT '答题结果（JSON数组，true/false）',
    `correct_count` INT DEFAULT 0 COMMENT '正确数量',
    `total_count` INT DEFAULT 0 COMMENT '总题目数',
    `score` INT DEFAULT 0 COMMENT '得分百分比',
    `duration` INT DEFAULT 0 COMMENT '学习时长（秒）',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '学习时间',
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学习记录表';

-- 插入一些测试学习记录
INSERT INTO `learning_record` (user_id, module_type, question_ids, answers, correct_count, total_count, score) VALUES
(2, 'hanzi', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]', '[true, true, false, true, true, true, false, true, true, true]', 8, 10, 80),
(2, 'number', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]', '[true, true, true, true, true, true, true, true, true, true]', 10, 10, 100),
(3, 'letter', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]', '[true, true, true, true, false, true, true, false, true, true]', 8, 10, 80);

-- ===============================================
-- 创建索引优化查询性能
-- ===============================================
CREATE INDEX idx_user_role ON user(role);
CREATE INDEX idx_hanzi_difficulty ON hanzi(difficulty);
CREATE INDEX idx_number_value ON number(number_value);
CREATE INDEX idx_arithmetic_difficulty ON arithmetic(difficulty);
CREATE INDEX idx_letter_category ON letter(letter_upper);
CREATE INDEX idx_word_category ON word(category);
CREATE INDEX idx_learning_record_user ON learning_record(user_id);
CREATE INDEX idx_learning_record_module ON learning_record(module_type);
CREATE INDEX idx_learning_record_created ON learning_record(created_at);

-- ===============================================
-- 完成提示
-- ===============================================
SELECT '数据库初始化完成！共创建7张表，示例数据已插入。' AS Status;
