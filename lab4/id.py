#!/usr/bin/python


import sys

if len(sys.argv) > 1:
         filename = sys.argv[1]
else:
         print "Errors"
         sys.exit()

f = file( filename, "r" )
l = f.readline()
dict = {}
while l :
        l = l.strip(' \t\n')
	s = l.split(" ", 1)
	id = int(s[0])
	dict[id] = s[1]
	l = f.readline()

for key in sorted(dict):
	print '{0:10}  {1}'.format(key, dict[key])
