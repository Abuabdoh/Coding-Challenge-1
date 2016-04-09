#!/usr/bin/env bash

# example of the run script for running the average_degree calculation

# I'll execute my programs, with the input directory tweet_input and output the files in the directory tweet_output
#Please note if you use Linux or UNIX you should change the character ';' to ':' in the below link
java -cp ".;src/json-simple-1.1.1.jar" src.average_degree tweet_input/tweets.txt tweet_output/output.txt
