#!/bin/bash
for f in `ls`; do
if [[ -f $f ]]; then 
  line=$(wc -l $f|cut -d " " -f1)
  word=$(wc -w $f|cut -d " " -f1)
  echo "$f $line $word"
fi
done
