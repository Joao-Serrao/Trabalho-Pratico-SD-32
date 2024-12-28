#!/bin/bash


# Run the first JAR file in a new terminal
gnome-terminal -- bash -c "java -jar ServerStart.jar; exec bash"

# Run the second JAR file in a new terminal
gnome-terminal -- bash -c "java -jar ClientStart.jar; exec bash"

echo "Press Enter after you have provided the input to the program... (Consider that 10 Clients will be added! and Expect Port is 12345!"
read
echo "Resuming script!"

# Run the client with the expect script
gnome-terminal -- bash -c "./Tests/TestAux.exp; exec bash"

sleep 1

gnome-terminal -- bash -c "./Tests/TestAux1.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux2.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux3.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux4.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux1.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux2.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux2.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux4.exp; exec bash"

gnome-terminal -- bash -c "./Tests/TestAux3.exp; exec bash"
