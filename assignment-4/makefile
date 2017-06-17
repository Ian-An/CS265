PAGER=less

.PHONY: build clean view

build : cddb.py
	./cddb.py -h
	./cddb.py -l
	./cddb.py -d
	./cddb.py -a
view : cddb.py
	$(PAGER) cddb.py
