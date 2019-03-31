ret=0
cd ./build/
if ! ./build.sh; then
    ret=1
fi
exit $ret
