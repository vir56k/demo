package main

import (
	pb "consignment-cli/proto/consignment"
	"context"
	"encoding/json"
	"errors"
	"fmt"
	microclient "github.com/micro/go-micro/client"
	"github.com/micro/go-micro/config/cmd"
	"io/ioutil"
	"log"
	"os"
)

const (
	ADDRESS           = "localhost:50051"
	DEFAULT_INFO_FILE = "consignment.json"
)

// 读取 consignment.json 中记录的货物信息
func parseFile(fileName string) (*pb.Consignment, error) {
	data, err := ioutil.ReadFile(fileName)
	if err != nil {
		return nil, err
	}
	var consignment *pb.Consignment
	err = json.Unmarshal(data, &consignment)
	if err != nil {
		return nil, errors.New("consignment.json file content error")
	}
	return consignment, nil
}

func main() {
	fmt.Println("客户端启动....")
	cmd.Init()
	// 创建微服务的客户端，简化了手动 Dial 连接服务端的步骤
	client := pb.NewShippingServiceClient("go.micro.srv.consignment", microclient.DefaultClient)

	// 在命令行中指定新的货物信息 json 文件
	infoFile := DEFAULT_INFO_FILE
	if len(os.Args) > 1 {
		infoFile = os.Args[1]
	}

	// 解析货物信息
	consignment, err := parseFile(infoFile)
	if err != nil {
		log.Fatalf("parse info file error: %v", err)
	}

	// 调用 RPC
	// 将货物存储到我们自己的仓库里
	resp, err := client.CreateConsignment(context.Background(), consignment)
	if err != nil {
		log.Fatalf("创建托运物信息 error: %v", err)
	}

	// 新货物是否托运成功
	log.Println("## 新货物是否托运成功:", resp, "err=", err)
	log.Printf("  属性信息%v,%v", resp.Created, resp.Consignment)

	// 列出目前所有托运的货物
	resp, err = client.GetConsignments(context.Background(), &pb.GetRequest{})
	if err != nil {
		log.Fatalf("failed to list consignments: %v", err)
	}
	log.Println("## 列出目前所有托运的货物:", resp, "err=", err)
	log.Printf("  属性信息%v", resp.Consignments)
	for _, c := range resp.Consignments {
		log.Printf("%+v", c)
	}
}
