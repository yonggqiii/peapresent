ret=0
find ../src -name "*.java" > javasources.txt
java -jar checkstyle-8.19-all.jar -c peapresent_checks.xml @javasources.txt > checkstyleoutput
NUMLINES=$(cat checkstyleoutput | wc -l)
cat checkstyleoutput
if [ $NUMLINES != 2 ]; then
    ret=1
fi
rm javasources.txt
rm checkstyleoutput
exit $ret
