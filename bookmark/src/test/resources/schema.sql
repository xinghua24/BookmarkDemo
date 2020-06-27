CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `provider` varchar(255) NOT NULL,
  `provider_id` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `bookmark_list` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `user_id` int
);

CREATE TABLE `bookmark` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `list_id` int
);

ALTER TABLE `bookmark_list` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `bookmark` ADD FOREIGN KEY (`list_id`) REFERENCES `bookmark_list` (`id`);
