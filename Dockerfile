FROM centos:latest

RUN sed -i 's/mirrorlist/#mirrorlist/g' /etc/yum.repos.d/CentOS-*.repo && \
    sed -i 's|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g' /etc/yum.repos.d/CentOS-*.repo

RUN yum update -y && \
    yum install -y java-17-openjdk && \
    yum clean all

RUN groupadd appgroup && useradd -m -g appgroup appuser

WORKDIR /opt

COPY ./target/demo.jar /opt/demo.jar

USER appuser

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/opt/demo.jar" ]
