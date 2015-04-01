all: dir java

dir:
	mkdir -p -- "build"

java:
	javac com/simonsen/watermark/Watermark.java -d build/

clean:
	rm -r build/
