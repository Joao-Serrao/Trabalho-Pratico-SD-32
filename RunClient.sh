#!/bin/bash

# Run the second JAR file in a new terminal
gnome-terminal -- bash -c "java -jar ClientStart.jar; exec bash"

