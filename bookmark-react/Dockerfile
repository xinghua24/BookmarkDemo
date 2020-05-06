# pull official base image
FROM node:13.12.0-alpine
WORKDIR /app
COPY . ./
RUN npm run-script build
RUN npm install serve -g
EXPOSE 8081
CMD ["serve", "-l", "8081", "build/"]
