#all: src/calculator/*.java
#	javac -d src/classes src/calculator/*.java

.PHONY: clean test

test:# all
	ls -A -d src

clean:
	rm -rf src/classes