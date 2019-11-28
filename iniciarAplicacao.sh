#!/bin/bash

cd src/
javac -d . protocolo/*.java cliente/*.java servidor/*.java
nohup java servidor.TCPServer &
java cliente.InicioCliente

PIDSERVER=$(pgrep -f TCPServer)
echo "Matando processo TCPServer..."
echo "$PIDSERVER"
RESULT=$(kill $PIDSERVER)