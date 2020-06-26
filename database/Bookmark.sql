CREATE TABLE `User` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `provider` varchar(255),
  `provider_id` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `Folder` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `user_id` int
);

CREATE TABLE `Bookmark` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `url` varchar(255),
  `access` varchar(255),
  `description` varchar(255),
  `folder_id` int
);

ALTER TABLE `Folder` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

ALTER TABLE `Bookmark` ADD FOREIGN KEY (`folder_id`) REFERENCES `Folder` (`id`);
