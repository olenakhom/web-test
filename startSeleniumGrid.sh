#!/bin/sh
# Navigate to qa-test-framework directory to run script
OS="`uname`"
echo "Starting Selenium Hub..."
sleep 2
java -jar ./selenium/server/selenium-server-standalone-3.14.0.jar -role hub &
sleep 5
echo "Started Selenium Hub on http://localhost:4444"
sleep 2
echo "Starting Selenium Node...in $OS"
case $OS in
    'Darwin')
        java -Dwebdriver.chrome.driver=./selenium/chromedriver/chromedriver_mac64  -jar ./selenium/server/selenium-server-standalone-3.14.0.jar -role node -nodeConfig ./selenium/node.json  &
     ;;
     'Linux')
       java -Dwebdriver.chrome.driver=./selenium/chromedriver/chromedriver_linux64  -jar ./selenium/server/selenium-server-standalone-3.14.0.jar -role node -nodeConfig ./selenium/node.json  &
     ;;
     *) ;;
esac

sleep 5
echo "Started Selenium Node"
echo "To enable running tests on Safari browser write in terminal safaridriver --enable"
