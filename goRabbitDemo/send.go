package main

import (
	"github.com/streadway/amqp"
	"log"
)

func failOnError(err error, msg string) {
	if err != nil {
		log.Fatalf("%s: %s", msg, err)
	}
}

func main() {
	// 连接 RabbitMQ
	conn, err := amqp.Dial("amqp://admin:admin@dev.com:5672/")
	failOnError(err, "连接失败")
	defer conn.Close()

	// 建立一个 channel ( 其实就是TCP连接 ）
	ch, err := conn.Channel()
	failOnError(err, "打开通道失败")
	defer ch.Close()

	// 创建一个名字叫 "hello" 的队列
	q, err := ch.QueueDeclare(
		"hello", // name
		false,   // durable
		false,   // delete when unused
		false,   // exclusive
		false,   // no-wait
		nil,     // arguments
	)
	failOnError(err, "创建队列失败")

	// 构建一个消息
	body := "Hello World!"
	msg := amqp.Publishing{
		ContentType: "text/plain",
		Body:        []byte(body),
	}

	// 放入队列
	err = ch.Publish(
		"",     // exchange
		q.Name, // routing key
		false,  // mandatory
		false,  // immediate
		msg)
	log.Printf(" [x] Sent %s", body)
	failOnError(err, "Failed to publish a message")
}
