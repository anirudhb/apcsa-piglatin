.PHONY: *.run

build:
	mkdir -p $@

%.run: %
	java -cp build $<

%:: %.java build
	javac -d build $<