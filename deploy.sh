

#aws ecr get-login-password --region ap-northeast-2 --profile test2 | docker login --username AWS --password-stdin 635507690469.dkr.ecr.ap-northeast-2.amazonaws.com

docker build -t ysquad636277/server .

docker push ysquad636277/server:latest

