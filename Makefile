all: run

clean:
	rm -f src/*.class out/App.jar

App.jar: out/parcs.jar src/*.java
	@javac -cp out/parcs.jar src/*.java
	@jar cf out/App.jar -C src .
	@rm -f src/*.class

run: App.jar
	@cd out && java -cp 'parcs.jar:App.jar' App
