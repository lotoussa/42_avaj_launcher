rm -Rf com/
rm -Rf simulation.txt
javac -d . src/com/avaj/**/*.java
java com.avaj.Tower.Main scenario.txt
cat simulation.txt
