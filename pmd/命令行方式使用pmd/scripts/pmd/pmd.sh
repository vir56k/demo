# 准备环境信息
CUR=`PWD`
echo 当前工作目录:${CUR}
basepath=$(cd `dirname $0`; pwd)
echo 当前执行的脚本文件的父目录:${basepath}
PMD_HOME=$basepath/pmd-bin-6.12.0
echo PMD_HOME:${PMD_HOME}
PROJ_DIR=$(cd ${basepath}; cd ../../; pwd)
echo PROJ_DIR:${PROJ_DIR}


SRC=${PROJ_DIR}/app/src/main/java
FORMAT=html
RULE=rulesets/java/basic.xml

${PMD_HOME}/bin/run.sh pmd -d ${SRC} -f ${FORMAT} -R ${RULE}