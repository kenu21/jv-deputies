FROM openjdk:11-oracle
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENV LANG en_US.UTF-8
ENTRYPOINT ["java","-cp","app:app/lib/*","com.statistic.deputies.DeputiesApplication"]