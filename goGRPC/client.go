package main

import (
	"context"
	"fmt"
	"google.golang.org/grpc"
	pb "grpcdemo/helloworld"
	"log"
	"time"
)

// 服务地址
const (
	address     = "localhost:50051"
	defaultName = "world"
)

func main() {
	conn, err := grpc.Dial(address, grpc.WithInsecure(), grpc.WithBlock())
	if err != nil {
		log.Fatalf("连接失败: %v", err)
	}
	defer conn.Close()
	// 获得一个调用方法 的stub
	client := pb.NewGreeterClient(conn)
	// 准备一个超时处理
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	//  执行 调用
	fmt.Println("发起调用")
	reply, err := client.SayHello(ctx, &pb.HelloRequest{Name: "zhang3"})
	if err != nil {
		log.Fatalf("调用失败: %v", err)
	}
	log.Printf("结果: %s", reply.GetMessage())
}
