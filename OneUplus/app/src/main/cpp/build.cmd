:: Android NDK R21 (21.0.6113669)
:: - Windows: https://dl.google.com/android/repository/android-ndk-r21-windows-x86_64.zip
:: - Linux:   https://dl.google.com/android/repository/android-ndk-r21-linux-x86_64.zip
:: @set "NDK_ROOT=%USERPROFILE%\Desktop\android-ndk-r21"
:: @set "NDK_ROOT=%USERPROFILE%\AppData\Local\Android\Sdk\ndk\21.0.6113669"
@set "NDK_ROOT=%LOCALAPPDATA%\Android\Sdk\ndk\21.0.6113669"
@pushd "%~dp0"
@setlocal enabledelayedexpansion
@cmd /c "%NDK_ROOT%\ndk-build" -j %NUMBER_OF_PROCESSORS%
@echo %~dp0
@for /f "delims=" %%F in ('dir /s /b /a-d libs') do @(
  REM See: https://stackoverflow.com/questions/10220675/convert-absolute-path-to-relative-path-in-batch-file
  REM See: https://stackoverflow.com/questions/49849881/cmd-setlocal-enabledelayedexpansion-inside-a-for-loop-not-working-as-id-like
  REM See: https://ss64.com/nt/syntax-replace.html
  set "A=%%F"
  set "F=!A:%~dp0=!"
  REM The variable F is a relative path of "%%F" based at the "%~dp0" directory.
  REM Use "!F!" to expand F. Do not use "%F%".
  echo !F!
  elf-cleaner "!F!"
  "%NDK_ROOT%\toolchains\llvm\prebuilt\windows-x86_64\bin\llvm-objcopy" --strip-sections "!F!"
@REM   "%NDK_ROOT%\toolchains\llvm\prebuilt\windows-x86_64\bin\llvm-readelf" -lS "!F!" 
)
@REM @"%NDK_ROOT%\toolchains\llvm\prebuilt\windows-x86_64\bin\llvm-readelf" -lS libs\arm64-v8a\dtf
@endlocal
@popd
