#!/bin/bash

CLIENT_PATH="out/artifacts/Client/Client.jar"
SERVER_PATH="out/artifacts/Server_jar/Server.jar"
DEST_DIR="deploy"
USER_NAME="turtles"
HOST="elgassia.tk"

scp "${CLIENT_PATH}" "${USER_NAME}@${HOST}:${DEST_DIR}/Client.jar"

scp "${SERVER_PATH}" "${USER_NAME}@${HOST}:server/Server.jar"
ssh ${USER_NAME}@${HOST} ./restart_server.sh
