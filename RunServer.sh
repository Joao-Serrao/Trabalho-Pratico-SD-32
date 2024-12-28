#!/bin/bash

# Run the first JAR file in a new terminal
gnome-terminal -- bash -c "java -jar ServerStart.jar; exec bash"


