FROM frolvlad/alpine-oraclejre8:8.202.08-slim
MAINTAINER Vincent
USER root
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

#安装agent jar
#RUN wget -c https://cos-qiye-1318760806.cos.ap-beijing.myqcloud.com/software/ot-agent-release.tar \
#    && tar xvf ot-agent-release.tar
WORKDIR /app
ADD ./op-facade-impl/target/op-facade-impl-*.jar /app/app.jar

# JAVA_OPTS 环境变量的值为部署组的 JVM 启动参数，在运行时 bash 替换。使用 exec 以使 Java 程序可以接收 SIGTERM 信号。
# 考虑到容器场景对于内存的要求，建议添加-Xshare:off选项关闭CDS功能
# TODO: 添加-Xshare:off选项关闭CDS功能
ENTRYPOINT ["java","-jar","/app/app.jar"]
