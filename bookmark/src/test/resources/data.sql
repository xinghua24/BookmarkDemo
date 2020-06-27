INSERT INTO `user`(`id`, `name`, `provider`, `provider_id`, `created_at`) VALUES (1, 'James Smith', 'github', 'xinghua24', NOW());

INSERT INTO `bookmark_list`(`id`, `name`, `user_id`) VALUES (1, 'default',1);

INSERT INTO `bookmark`(`name`, `url`, `list_id`) VALUES ('Google', 'http://google.com', 1);
INSERT INTO `bookmark`(`name`, `url`, `list_id`) VALUES ('Youtube', 'https://www.youtube.com/', 1);
INSERT INTO `bookmark`(`name`, `url`, `list_id`) VALUES ('Amazon', 'http://amazon.com', 1);
INSERT INTO `bookmark`(`name`, `url`, `list_id`) VALUES ('Netflix', 'http://netflix.com', 1);