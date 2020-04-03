package main

import (
	"fmt"
	"github.com/golang/protobuf/proto"
	example "gopbdemo/pb"
	"log"
)

func main() {
	// 一个实体
	obj := &example.Test{
		Label: proto.String("hello"),
		Type:  proto.Int32(17),
		Reps:  []int64{1, 2, 3},
	}
	// 序列化成字节
	data, err := proto.Marshal(obj)
	if err != nil {
		log.Fatal("序列化时发生错误: ", err)
	}
	fmt.Println("完成序列化，长度=", len(data))

	newObj := example.Test{}
	err = proto.Unmarshal(data, &newObj)
	if err != nil {
		log.Fatal("反序列化时发生错误: ", err)
	}
	fmt.Println("结果：", newObj.GetLabel(),newObj.GetType(),newObj.GetReps())

}
