FROM alpine:latest AS zulu_base
RUN apk add --no-cache wget && \
  wget -P /etc/apk/keys/ https://cdn.azul.com/public_keys/alpine-signing@azul.com-5d5dc44c.rsa.pub && \
  echo "https://repos.azul.com/zulu/alpine" | tee -a /etc/apk/repositories && \
  apk add zulu17-jre-headless --no-cache && \
  apk del --no-cache wget

ENV USER=docker
ENV UID=10001
ENV GID=10001

WORKDIR /app
RUN addgroup -g ${GID} docker && \
    adduser \
    --disabled-password \
    --gecos "" \
    --home "$(pwd)" \
    --ingroup "$USER" \
    --no-create-home \
    --uid "$UID" \
    "$USER"
USER docker
COPY ./build/libs/*.jar /app/app.jar
COPY ./docker-entrypoint.sh /app/docker-entrypoint.sh
EXPOSE 8080/tcp
EXPOSE 9090/tcp
ENTRYPOINT [ "/app/docker-entrypoint.sh" ]