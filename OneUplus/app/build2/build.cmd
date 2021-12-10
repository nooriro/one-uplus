:: JDK: Android Studio 4.0.2 (October 6, 2020) built-in JDK (javac 1.8.0_242-release)
:: https://developer.android.com/studio/archive
:: - Method 1: Download installer and install it
:: - Method 2: Download zip/tar.gz and extract jre folder, and modify JAVA_HOME variable below
:: 
:: 89dc11470c0892553ce0ce1f5c3a0389117f00a7  sdk/android-30.jar
:: https://drive.google.com/drive/folders/17oMwQ0xBcSGn159mgbqxcXXEcneUmnph
:: 3ac913b600589dba649abe56d70889cb797bbb1a  lib/protobuf-javalite-3.19.1.jar
:: https://search.maven.org/artifact/com.google.protobuf/protobuf-javalite
:: ec7e281caa8aad15793fc4c227cfb91df0c2b60e  d8.jar
:: https://dl.google.com/android/repository/91936d4ee3ccc839f0addd53c9ebf087b1e39251.build-tools_r30.0.3-windows.zip
:: https://dl.google.com/android/repository/build-tools_r30.0.3-linux.zip
:: 
@pushd "%~dp0"
@setlocal
:: @set ANDROID_HOME=%LOCALAPPDATA%\Android\Sdk
@set JAVA_HOME=%ProgramFiles%\Android\Android Studio\jre
@set PATH=%JAVA_HOME%\bin;%PATH%
@if exist class/ ( del /f /s /q class >nul & for /d %%i in (class\*) do @rd /s /q "%%i" )
@if not exist class/ ( del class 2>nul & mkdir class )
@javac -g ^
    -bootclasspath sdk\android-30.jar ^
    -classpath lib\protobuf-javalite-3.19.1.jar ^
    -d class ^
    -encoding UTF-8 ^
    ..\src\main\java\*.java ..\src\main\java\com\nooriro\oneuplus\proto\googlecarrier\*.java
@if exist classesdex/ ( del /f /s /q classesdex >nul & for /d %%i in (classesdex\*) do @rd /s /q "%%i" )
@if not exist classesdex/ ( del classesdex 2>nul & mkdir classesdex )
:: "%ANDROID_HOME%\build-tools\30.0.3\lib\d8.jar"
@java -cp d8.jar com.android.tools.r8.R8 ^
    --debug ^
    --output classesdex ^
    --lib sdk\android-30.jar ^
    --min-api 19 ^
    --pg-conf rules.txt ^
    class\*.class class\com\nooriro\oneuplus\proto\googlecarrier\*.class lib\protobuf-javalite-3.19.1.jar
@endlocal
@popd
