# 准备环境信息
CUR=`PWD`
echo 当前工作目录:${CUR}
basepath=$(cd `dirname $0`; pwd)
echo 当前执行的脚本文件的父目录:${basepath}

# jar 文件路径
JAR_NAME=checkstyle-8.17-all.jar
# 规则文件位置
RULE_PATH=./huawei_CheckStyle.xml

# 源代码位置
SRC_PATH=../../app/src/main/java


JAR=${basepath}/${JAR_NAME}
RULE=${basepath}/${RULE_PATH}
SRC=${basepath}/${SRC_PATH}

java -jar ${JAR} -c ${RULE} ${SRC}
