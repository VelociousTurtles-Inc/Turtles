#!/bin/bash

ARTIFACT_PATH="out/artifacts/Server_jar/Server.jar"
USER_NAME="turtles"
HOST="elgassia.tk"

scp "${ARTIFACT_PATH}" "${USER_NAME}@${HOST}:server/Server.jar"
ssh ${USER_NAME}@${HOST} ./restart_server.sh
