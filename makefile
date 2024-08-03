#all: src/calculator/*.java
#	javac -d src/classes src/calculator/*.java

.PHONY: clean test

test:# all
	cat run_autograder

clean:
	rm -rf src/classes