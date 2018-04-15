# Sample Dockerfile for use with the Docker file mode
FROM ${base}
MAINTAINER Sander Puts

ENV SAMPLE_BUILD_MODE=dockerfile
LABEL PROJECT_NAME=KAFFLOW-ixa-pipe-parse \
      PROJECT=${project.artifactId}

#Get and extract models
RUN wget http://ixa2.si.ehu.es/ixa-pipes/models/parse-models.tgz && \
    tar xvzf parse-models.tgz && \
    rm parse-models.tgz

# In maven/ the files as specified in the <assembly> section is stored
# and need to be added manually
COPY maven/ /maven/

CMD java -jar /maven/${project.artifactId}.jar