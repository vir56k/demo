package main

import (
	context "context"
	"fmt"
	"google.golang.org/grpc"
	"google.golang.org/grpc/examples/helloworld/helloworld"
	pb "grpcdemo/helloworld"
	"log"
	"net"
)

type myServer struct {
	helloworld.UnimplementedGreeterServer
}

// 端口号
const (
	port = ":50051"
)

/**
实现接口
*/
func (*myServer) SayHello(ctx context.Context, req *pb.HelloRequest) (*pb.HelloReply, error) {
	log.Printf("Received: %v", req.GetName())

	reply := pb.HelloReply{Message: "Hello " + req.GetName()}
	return &reply, nil
}

func main() {
	// 准备一个 tcp 的监听器
	listener1, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("出现异常: %v", err)
	}
	// 构建一个 grpc 服务
	server1 := grpc.NewServer()
	pb.RegisterGreeterServer(server1, &myServer{})

	// 启动服务
	fmt.Printf("服务启动 %v\n", port)
	err = server1.Serve(listener1)
	if err != nil {
		log.Fatalf("启动server失败: %v", err)
	}
}
