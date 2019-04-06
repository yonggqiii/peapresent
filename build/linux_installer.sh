cd ../
mvn package
cat ./build/run.sh ./target/peapresent.jar > peapresent
chmod +x peapresent
sudo mv peapresent /usr/bin

