#!/bin/bash

ARTIFACT_PATH="out/artifacts/Client_jar/Client.jar"
JNLP_PATH="Turtles.jnlp"
USER_NAME="turtles"
HOST="elgassia.tk"

scp "${ARTIFACT_PATH}" "${USER_NAME}@${HOST}:jnlp/Client.jar"
scp "${JNLP_PATH}" "${USER_NAME}@${HOST}:jnlp/Client.jnlp"
ssh ${USER_NAME}@${HOST} ./sign.sh
