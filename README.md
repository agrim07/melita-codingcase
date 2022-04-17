## Pre-requisites

Step 1: Start the Messaging Servers
In a fresh terminal window, go to the root folder of the project and issue the following command.

You’ll need “Docker” to be installed and running on your system for this script to work properly as it requires docker-compose.

./start-servers.sh

This script will start RabitMQ and stream the log output from both to the terminal window (unless you exit with Ctrl-C). The servers do not stop when you press Ctrl-C - they’ll keep running in the background. Once started these servers will all be available to applications running on your computer.
