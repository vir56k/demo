build:
	# 同步类库
	GOOS=linux GOARCH=amd64 go build
	# 指定构建
	docker build -t consignment-cli .

run:
	# 准备启动
	docker run -e MICRO_REGISTRY=mdns consignment-cli

clean:
	docker image rm -f consignment-cli
