cd ../src/
find -name "*.java" > javasources.txt
javac @javasources.txt
find -name "*.class" > classsources.txt
NAME=$(awk '/NAME/ {print $2}' ../build/build.config)
jar cvfe $NAME App @classsources.txt
mv $NAME ../
find . -name '*.class' -exec rm -f {} \;
rm javasources.txt
rm classsources.txt
cd ../build
