all: src/calculator/*.java
	javac -d src/classes src/calculator/*.java

.PHONY: clean test

test: all
	cd ~
	ls

clean:
	rm -rf src/classes