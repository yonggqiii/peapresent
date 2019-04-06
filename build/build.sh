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
    jar cvfe $NAME.jar App @classsources.txt
    find . -name '*.class' -exec rm -f {} \;
    rm classsources.txt
    cat ../build/run.sh $NAME.jar > $NAME
    chmod +x $NAME
    rm $NAME.jar
    sudo mv $NAME /usr/bin
fi
rm javasources.txt
cd ../build
exit $ret
