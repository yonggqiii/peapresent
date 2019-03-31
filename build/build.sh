ret=0
cd ../src/
find -name "*.java" > javasources.txt
meow=$(javac @javasources.txt 2>&1)
if [ "$meow" != '' ]; then
    echo "Build failed"
    echo $meow
    ret=1
else
    find -name "*.class" > classsources.txt
    NAME=$(awk '/NAME/ {print $2}' ../build/build.config)
    jar cvfe $NAME App @classsources.txt
    mv $NAME ../
    find . -name '*.class' -exec rm -f {} \;
    rm classsources.txt
fi
rm javasources.txt
cd ../build
exit $ret
