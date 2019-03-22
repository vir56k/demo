# 准备环境信息
CUR=`PWD`
echo 当前工作目录:${CUR}
basepath=$(cd `dirname $0`; pwd)
echo 当前执行的脚本文件的父目录:${basepath}

FINDBUGS_HOME=$basepath/findbugs-3.0.1


# 项目目录
PROJ_DIR=$(cd ${basepath}; cd ../../; pwd)
echo PROJ_DIR:${PROJ_DIR}


# Usage: findbugs [general options] -textui [command line options...] [jar/zip/class files, directories...]

${FINDBUGS_HOME}/bin/findbugs  -textui -exclude exclude.xml ${PROJ_DIR}/app/build/intermediates/javac
