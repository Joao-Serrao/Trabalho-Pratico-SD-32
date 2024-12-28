#!/bin/bash

# Run the server
gnome-terminal -- bash -c "./Tests/TestAuxS.exp; exec bash"

# Wait for the server to start
sleep 1

# Run the client with the expect script
gnome-terminal -- bash -c "./Tests/TestAux.exp; exec bash"

sleep 1

gnome-terminal -- bash -c "./Tests/TestAux1.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux2.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux3.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux4.exp; exec bash"
