-- 推荐系统sql
-- 用户日志表
DROP TABLE
IF EXISTS ai_user_log;

CREATE TABLE ai_user_log (
	id INT (11) AUTO_INCREMENT COMMENT '自增id(主键)',
	user_id INT (11) COMMENT '用户ID',
	log_type INT (3) COMMENT '日志类型',
	log_value VARCHAR (1024) COMMENT '日志内容',
	log_detail VARCHAR (128) COMMENT '日志详情',
	log_time BIGINT COMMENT '日志时间戳',
	PRIMARY KEY (id)
) COMMENT = '用户日志表,记录所有用户操作日志';

-- 文章评论表
DROP TABLE
IF EXISTS ai_article_comment;

CREATE TABLE ai_article_comment (
	id INT (11) AUTO_INCREMENT COMMENT '自增id(主键)',
	article_id INT (11) COMMENT '文章id',
	user_id INT (11) COMMENT '用户id',
	ref_id INT (11) COMMENT '回复评论id',
	comment_content VARCHAR (512) COMMENT '评论内容',
	like_count INT (11) COMMENT '点赞数',
	dis_like_count INT (11) COMMENT '点踩数',
	comment_time BIGINT COMMENT '评论时间戳',
	PRIMARY KEY (id)
) COMMENT = '文章评论表,记录文章评论';

CREATE INDEX idx_comment_article ON ai_article_comment (article_id);

CREATE INDEX idx_comment_user ON ai_article_comment (user_id);

-- 用户标签表
DROP TABLE
IF EXISTS ai_user_tag;

CREATE TABLE ai_user_tag (
	id INT (11) AUTO_INCREMENT COMMENT '自增id(主键)',
	user_id INT (11) COMMENT '用户id',
	tag_id INT (11) COMMENT '标签id',
	tag_ratio INT COMMENT '标签评分',
	tag_time BIGINT COMMENT '标签时间',
	PRIMARY KEY (id)
) COMMENT = '用户标签表';

CREATE UNIQUE INDEX uni_user_tag ON ai_user_tag (user_id, tag_id);

CREATE INDEX idx_tag_user ON ai_user_tag (tag_id);

-- 文章标签表
DROP TABLE
IF EXISTS ai_article_tag;

CREATE TABLE ai_article_tag (
	id INT (11) AUTO_INCREMENT COMMENT '自增id(主键)',
	article_id INT (11) COMMENT '用户id',
	tag_id INT (11) COMMENT '标签id',
	tag_ratio INT COMMENT '标签评分',
	tag_time BIGINT COMMENT '标签时间',
	PRIMARY KEY (id)
) COMMENT = '用户标签表';

CREATE UNIQUE INDEX uni_article_tag ON ai_article_tag (article_id, tag_id);

CREATE INDEX idx_tag_article ON ai_user_tag (tag_id);

-- 标签表
DROP TABLE
IF EXISTS ai_tag_content;

CREATE TABLE ai_tag_content (
	id INT (11) AUTO_INCREMENT COMMENT '自增id(主键)',
	content VARCHAR (512) COMMENT '标签内容',
	PRIMARY KEY (id)
) COMMENT = '标签表';

CREATE UNIQUE INDEX uni_tag_content ON ai_tag_content (content);

-- 文章表
DROP TABLE
IF EXISTS ai_article;

CREATE TABLE ai_article (
	id INT (11) AUTO_INCREMENT COMMENT '自增id(主键)',
	info TEXT COMMENT '内容',
	content TEXT COMMENT '格式化内容',
	TITLE VARCHAR (256),
	pic VARCHAR (1024),
	vedio VARCHAR (256),
	url VARCHAR (256),
	PRIMARY KEY (id)
) COMMENT = '文章表';

