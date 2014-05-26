#!/bin/bash

ARTIFACT_PATH="out/artifacts/Client_jar/Client.jar"
HOST="elgassia.tk"
PORT=8080

java -jar "${ARTIFACT_PATH}" ${HOST} ${PORT}
