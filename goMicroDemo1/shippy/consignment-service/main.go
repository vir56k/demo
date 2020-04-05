package main

import (
	pb "consignment-service/proto/consignment"
	"context"
	"github.com/micro/go-micro"
	"log"
)

//
// 仓库接口
//
type IRepository interface {
	Create(consignment *pb.Consignment) (*pb.Consignment, error) // 存放新货物
	GetAll() []*pb.Consignment                                   // 获取仓库中所有的货物
}

//
// 我们存放多批货物的仓库，实现了 IRepository 接口
//
type Repository struct {
	consignments []*pb.Consignment
}

func (repo *Repository) Create(consignment *pb.Consignment) (*pb.Consignment, error) {
	repo.consignments = append(repo.consignments, consignment)
	return consignment, nil
}

func (repo *Repository) GetAll() []*pb.Consignment {
    return repo.consignments
}

//
// 定义微服务
//
type service struct {
	repo Repository
}

//
// service 实现 consignment.pb.go 中的 ShippingServiceServer 接口
// 使 service 作为 gRPC 的服务端
//
// 托运新的货物
func (s *service) CreateConsignment(ctx context.Context, req *pb.Consignment, resp *pb.Response) error {
	// 接收承运的货物
	consignment, err := s.repo.Create(req)
	if err != nil {
		log.Println("创建托运物品，失败：",err)
		return err
	}
	*resp = pb.Response{Created: true, Consignment: consignment}
	log.Println("操作完成：",resp)
	return nil
}

// 获取目前所有托运的货物
// func (s *service) GetConsignments(ctx context.Context, req *pb.GetRequest) (*pb.Response, error) {
func (s *service) GetConsignments(ctx context.Context, req *pb.GetRequest, resp *pb.Response) error {
	allConsignments := s.repo.GetAll()
	*resp = pb.Response{Consignments: allConsignments}
	log.Println("读取托运物品:：",resp)
	return nil
}

func main() {
	server := micro.NewService(
		// 必须和 consignment.proto 中的 package 一致
		micro.Name("go.micro.srv.consignment"),
		micro.Version("latest"),
	)

	// 解析命令行参数
	server.Init()
	repo := Repository{}
	pb.RegisterShippingServiceHandler(server.Server(), &service{repo})

	if err := server.Run(); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
