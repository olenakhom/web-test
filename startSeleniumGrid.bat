echo "Starting Selenium Hub..."
call java -jar "./selenium/server/selenium-server-standalone-3.14.0.jar" -role hub -host 127.0.0.1 &
echo "Started Selenium Hub on http://localhost:4444"
echo "Starting Selenium Node..."

call java "-Dwebdriver.chrome.driver=./selenium/chromedriver/chromedriver_win32.exe"  -jar "./selenium/server/selenium-server-standalone-3.14.0.jar" -role node -port 5556 -hub http://localhost:4444/grid/register -maxSession 10 -browser browserName=chrome,maxInstances=10 -host 127.0.0.1  &

echo "Started Selenium Node"