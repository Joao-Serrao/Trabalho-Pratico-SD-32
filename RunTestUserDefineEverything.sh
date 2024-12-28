#!/bin/bash

# Run the first JAR file in a new terminal
gnome-terminal -- bash -c "java -jar ServerStart.jar; exec bash"



echo "Press Enter after you have provided the input to the program... (Consider that 10 Clients will be added! and Expect Port is 12345!)"
read
echo "Resuming script!"

# Ask the user how many TestAux programs to run
echo "Enter the number of TestAux programs to run (e.g., 5, 10, etc.):"
read count

# Validate user input (ensure it's a positive number)
if ! [[ "$count" =~ ^[0-9]+$ ]]; then
    echo "Invalid input. Please enter a positive number."
    exit 1
fi

# Run the first TestAux program (TestAux1)
gnome-terminal -- bash -c "./Tests/TestAux.exp; exec bash"
sleep 1

# Run the requested number of TestAux programs (from TestAux1 to TestAux4)
for i in $(seq 2 $count); do
    # Calculate the TestAux number (1 through 4, repeating after 4)
    test_number=$(( (i - 1) % 4 + 1 ))

    # Open the corresponding TestAux script
    gnome-terminal -- bash -c "./Tests/TestAux$test_number.exp; exec bash"
done

