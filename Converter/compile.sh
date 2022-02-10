#!/bin/bash

# create directory for compilation process
mkdir -p compile/de/jonas/informatik/converter

# create manifest
touch compile/manifest.txt

# define main-class
echo 'Main-Class: de/jonas/informatik/converter/Converter' >> compile/manifest.txt

# copy background image into compile directory
cp src/main/resources/background.jpg compile/

# switch to java directory
cd src/main/java

# copy all java source files into compile directory
cp -R de/jonas/informatik/converter/ ../../../compile/de/jonas/informatik

# switch back to compile directory
cd ../../../compile

# compile classes
javac de/jonas/informatik/converter/Converter.java

# create jar file
jar cfm Converter.jar manifest.txt de/jonas/informatik/converter/* background.jpg

# cleanup compile directory
rm -R de/ manifest.txt background.jpg

# print success message
echo 'The compile process from the Converter-Module was successful! You can find the Jar-File in the compile folder :D'
