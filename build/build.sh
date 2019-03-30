cd ../src/
find -name "*.java" > javasources.txt
javac @javasources.txt
find -name "*.class" > classsources.txt
jar cvfe app.jar App @classsources.txt
mv app.jar ../build/
find . -name '*.class' -exec rm -f {} \;
rm javasources.txt
rm classsources.txt
cd ../build
