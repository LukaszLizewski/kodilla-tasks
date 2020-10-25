call runcrud.bat
if "%ERRORLEVEL%" == "0" goto url
echo.
echo GRADLEW BUILD has errors - breaking work
goto runcrud_fail

:url
start chrome.exe http://localhost:8080/crud/v1/task/getTasks
goto end

:runcrud_fail
echo.
echo There were errors in runcrud.bat

:end
echo.
echo Tasks have been shown.