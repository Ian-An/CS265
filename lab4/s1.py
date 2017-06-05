#!/usr/bin/python



import sys

if len(sys.argv) > 1:
	 filename = sys.argv[1]
else:
	 print "Errors"
	 sys.exit()

f = file( filename, "r" )
l = f.readline() 
while l :
	l = l.strip(' \t\n')
	s = l.split()# return a list
	length = len(s)
	i = 0
	total = 0
	while i < length :
		if i == 0 :
			name = s[i]
		else :
			total += int(s[i])	
		i+=1		
        avg = total / (length - 1)
	print '{0:10}  {1}'.format(name, avg)
	l = f.readline()
