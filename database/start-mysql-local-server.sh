####################################
# for development only
# run mysql  server on port 3306
# username: root
# password 3306
#
# run phpmyadmin at http://localhost:7000/
####################################

#!/bin/bash
# Create network
docker network create bookmark-network

# Create mysql container
docker run  -d --name=mysql-dev \
--network=bookmark-network \
-e MYSQL_ROOT_PASSWORD=password \
-p 3306:3306 \
mysql:8

# Create phpmyadmin container
docker run -d --name phpmyadmin  \
--network=bookmark-network \
-e PMA_HOSTS=mysql-dev \
-e PMA_PORT=3306 \
-e PMA_USER=root \
-e PMA_PASSWORD=password \
-p 7000:80 \
phpmyadmin/phpmyadmin