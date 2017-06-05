#!/usr/bin/python
# Yi An - CS265 Assignment 2
# 05/10/2017
# Description: Find bus and trolly stops nearby


import sys
import urllib
import philly_loc
import json
import math

url = 'http://www3.septa.org/hackathon/Stops/?req1=23'

# Modify the input argument and Get the correct nums of output
if len(sys.argv) < 2:
	outputNum = 5
else:
	outputNum = int(sys.argv[1][2:])# Convert string to int

# Generate a location
pos = philly_loc.getLoc() 
latitude = pos[0]
longitude = pos[1]

# Grab data from web
sock = urllib.urlopen(url)
doc = sock.read()
sock.close()

# Parse string to json
data = json.loads(doc)

# Update each dic in the list with a new attribute distance
# stopid as the key and distance as the value
for stopInformation in data:
	dist = math.hypot(stopInformation["lat"] - latitude, stopInformation["lng"] - longitude)
	stopInformation["distance"] = dist

sortedList = sorted(data, key=lambda k: k['distance'])

# Print out the required numbers of stops
for i in range(outputNum):
	_distance = sortedList[i]["distance"]
	_stopname = sortedList[i]["stopname"]
	_lat = sortedList[i]["lat"]
	_lng = sortedList[i]["lng"]
	print "Distance: %f,StopName: %s,Coordinates: ( %f , %f)" % (_distance, _stopname, _lat, _lng)

