
# y | docker image prune -a

aws ecr get-login-password --region ap-northeast-2 --profile youngsquad | docker login --username AWS --password-stdin 657353526320.dkr.ecr.ap-northeast-2.amazonaws.com

docker build -t youngsquad .

docker tag youngsquad:latest 657353526320.dkr.ecr.ap-northeast-2.amazonaws.com/youngsquad-server:latest

docker push 657353526320.dkr.ecr.ap-northeast-2.amazonaws.com/youngsquad-server:latest
