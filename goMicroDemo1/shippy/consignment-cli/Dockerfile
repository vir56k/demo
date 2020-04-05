FROM alpine:latest
RUN mkdir -p /app
WORKDIR /app

# 将当前目录下的货物信息文件 consignment.json 拷贝到 /app 目录下
ADD consignment.json /app/consignment.json
ADD consignment-cli /app/consignment-cli

CMD ["./consignment-cli"]