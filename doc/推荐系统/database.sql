-- 推荐系统sql
-- 用户日志表
drop table if exists user_log;
CREATE TABLE user_log (
	id INT (11) AUTO_INCREMENT COMMENT '自增id(主键)',
	user_id INT (11) COMMENT '用户ID',
	log_type INT (3) COMMENT '日志类型',
	log_value VARCHAR (1024) COMMENT '日志内容',
	log_detail VARCHAR (128) COMMENT '日志详情',
	log_time LONG COMMENT '日志时间戳',
	PRIMARY KEY (id)
)COMMENT = '用户日志表,记录所有用户操作日志';
