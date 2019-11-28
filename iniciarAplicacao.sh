#!/bin/bash

cd src/
javac -d . protocolo/*.java cliente/*.java servidor/*.java
nohup java servidor.TCPServer &
java cliente.InicioCliente
