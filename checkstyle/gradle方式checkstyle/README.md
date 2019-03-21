
 # 配置说明

    gradle 方式 配置 checkstyle 脚本存放在 scripts 文件夹下

    checkstyle.gradle           grald脚本配置，关键在这里
    huawei_CheckStyle.xml       规则配置
    suppressions.xml            规则配置

 # 怎么应用？

    在 app 的 build.gradle 中加入下面引用
    apply from: '../scripts/checkstyle/checkstyle.gradle'

 # 执行 checkstyle

    ./gradlew clean check

 # 查看报告

    报告存放于：app/build/reports/checkstyle/  文件夹下

 # 常见问题

    gradle 下载不到包的问题，请使用阿里镜像，示例：

    allprojects {
        repositories {
            maven { url "https://maven.aliyun.com/repository/jcenter/" }
            maven { url "https://maven.aliyun.com/repository/gradle-plugin/" }
            maven { url "https://maven.aliyun.com/repository/google/" }
            jcenter()
            google()

        }
    }