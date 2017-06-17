#!/usr/bin/python
# Yi An - CS265 Assignment 4
# 06/08/2017
# Description: Command-line utility for managing the CD database. 
# Usage information will be provided in README.

import sys
import os
import collections
import shutil

# List albums information
def list_albums_of_one_artist(albums_of_one_artist,db):
  j = 0
  for album in albums_of_one_artist[1]:
    name = album["album_name"]
    year = album["year"]
    print j,"Album Name: ",name,'Album Year:',year
    j+=1
  album_number = raw_input("Choose an album by the index number or return to preivious menu by entering a: ")
  if album_number == "a":
    list_artists(db)
  elif 0 <= int(album_number) <  len(albums_of_one_artist[1]):
    album = albums_of_one_artist[1][int(album_number)]
    name = album["album_name"]
    tracks = album["tracks"]
    year = album["year"]
    print "Album Name: ",name
    print "Album Year: ",year
    for track in tracks:
      print track
    print "\n"
    menu_command = raw_input("Return to preivious menu by entering a: ")
    if menu_command == "a":
      list_albums_of_one_artist(albums_of_one_artist,db)
    else:
      print "INVALID INPUT. SYSTEM OUT"
      sys.exit()
  else:
    print "INVALID INPUT"
    list_albums_of_one_artist(albums_of_one_artist,db)
# List all all artists information
def list_artists(db):
  i = 0
  for artist in db.keys():
    print i,"Artist Name: ",artist
    i+=1
  artist_number = raw_input("Choose an artist by the index number or enter q to quit: ")
  if artist_number == "q":
    print("Quit")
    sys.exit()
  elif 0 <= int(artist_number) < len(db):
    list_albums_of_one_artist(db.items()[int(artist_number)],db)
  else:
    print "INVALID INPUT"
    list_artists(db)

# Update data base
def update_database(db):
  with open('tmp.db', 'w') as f:
    for artist in db:
      for album in db[artist]:
        album_name = album["album_name"]
        year = album["year"]
        f.write(artist+'\n')
        f.write(year+" "+album_name+"\n")
        for track in album["tracks"]:
          f.write(track+"\n")
        f.write('\n')
  # Move the file to env var direcotry
  directory = os.environ['CDDB']
  shutil.move('tmp.db', directory)

# Delete a certain album
def list_albums_of_one_artist_to_delete(albums_of_one_artist,db,artist_index):
  j = 0
  for album in albums_of_one_artist[1]:
    name = album["album_name"]
    year = album["year"]
    print j,"Album Name: ",name,'Album Year:',year
    j+=1
  album_number = raw_input("Choose an ablum to delete by the index number or enter a to the previous menu: ")
  if album_number == "a":
    delete_album(db)
  elif 0 <= int(album_number) < len(albums_of_one_artist[1]):
    # Delete an album and Update the dicitonary
    key = db.keys()[artist_index]
    value = albums_of_one_artist[1]
    del value[int(album_number)]
    if not value:
      del db[key]
    else:
      db[key] = value
    # Update the falt database file
    update_database(db)
  else:
    print "INVALID INPUT"
    list_albums_of_one_artist_to_delete(albums_of_one_artist,db)
def delete_album(db):
  # List artists
  i = 0
  for artist in db.keys():
    print i,"Artist Name: ",artist
    i+=1
  artist_number = raw_input("Choose an artist to delete by the index number or enter q to quit: ")
  if artist_number == "q":
    print("Quit")
    sys.exit()
  elif 0 <= int(artist_number) < len(db):
    list_albums_of_one_artist_to_delete(db.items()[int(artist_number)],db,int(artist_number))
  else:
    print "INVALID INPUT"
    delete_album(db)

def add_album(db):
  artist_name = raw_input("Enter the artist name:")
  album_name = raw_input("Enter the album name:")

  # Check if the album name exists
  if artist_name in db:
    if any(d['album_name'] == album_name for d in db[artist_name]):
      print("Album already exists. Please try again")
      sys.exit()
    else:
      year = raw_input("Enter the the year of this album:")
      print "Enter the track list with space and -"
      print "For instance: -Track1 -Track2 -Track3"
      album_tracks = raw_input("Enter the tracks here:")
      tracks = album_tracks.split()
      one_album_structure = {"album_name":album_name,"year":year,"tracks":tracks}
      album_info = db[artist_name]
      album_info.append(one_album_structure)
      db[artist_name] = album_info
  else:
    year = raw_input("Enter the the year of this album:")
    print "Enter the track list with space and -"
    print "For instance: -Track1 -Track2 -Track3"
    album_tracks = raw_input("Enter the tracks here:")
    tracks = album_tracks.split()
    one_album_structure = {"album_name":album_name,"year":year,"tracks":tracks}
    db[artist_name] = [one_album_structure]
  update_database(db)

def show_usage(db):
  print "USAGE:-l for album, -d for delete album,-a for add album."
  sys.exit()

def dict_db():
  # Open the env variable
  directory = os.environ['CDDB']
  # Read the database by line
  # Separate it to different lists by blank lines
  with open(directory,'r') as f:
    all_album_info =[] 
    one_album_info = []
    for line in f:
      if(line != "\n"):
        one_album_info.append(line.replace("\n",""))
      else:
        all_album_info.append(one_album_info)
        one_album_info = []
    all_album_info.append(one_album_info)
  
  # Store information into a dictionary data structure with artists' names as keys
  db = {}
  for album in all_album_info:
    if album:
      artist = album[0]
      year = album[1][0:4]
      album_name = album[1][5:]
      tracks = album[2:]
      one_album_structure = {"album_name":album_name,"year":year,"tracks":tracks}
      if artist in db:
        album_info = db[artist]
        album_info.append(one_album_structure)
        sorted_album_info = sorted(album_info, key=lambda k: k['year']) 
        db[artist] = sorted_album_info
      else:
        db[artist] = [one_album_structure]
  return collections.OrderedDict(sorted(db.items()))
# main function
def main(argv):
    if len(argv) < 2:
      print "NO INPUT ARGUMENT!"
      print "USAGE:-l for album, -d for delete album,-a for add album."
      sys.exit()
    else:
      # Define a map to invoke each function based on the command-line argument
      options = {"l" : list_artists,"d" : delete_album,"a" : add_album,"h" : show_usage}
      option = argv[1][1:]
      if option in options:
        # Store the album information in a dictionary
        db = dict_db()
        options[option](db)
      else:
        print "UNEXPECTED INPUT ARGUMENT!"
        print "USAGE:-l for album, -d for delete album,-a for add album."
        sys.exit()

if __name__ == "__main__":
    main(sys.argv)