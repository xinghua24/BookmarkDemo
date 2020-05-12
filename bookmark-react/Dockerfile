# pull official base image
FROM node:13.12.0-alpine
WORKDIR /app
COPY . ./
RUN npm install
RUN npm run-script build
RUN npm install serve -g
EXPOSE 8081
ENTRYPOINT ["serve", "-l", "8081", "build/"]
