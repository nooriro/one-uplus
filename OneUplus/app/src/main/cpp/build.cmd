:: Android NDK R21 (21.0.6113669)
:: - Windows: https://dl.google.com/android/repository/android-ndk-r21-windows-x86_64.zip
:: - Linux:   https://dl.google.com/android/repository/android-ndk-r21-linux-x86_64.zip
:: @set "NDK_ROOT=%USERPROFILE%\Desktop\android-ndk-r21"
@set "NDK_ROOT=%LOCALAPPDATA%\Android\Sdk\ndk\21.0.6113669"
@pushd "%~dp0"
@cmd /c "%NDK_ROOT%\ndk-build" -j %NUMBER_OF_PROCESSORS%
@for /f "delims=" %%F in ('dir /s /b /a-d libs') do @( elf-cleaner "%%F" & "%NDK_ROOT%\toolchains\llvm\prebuilt\windows-x86_64\bin\llvm-objcopy" --strip-sections "%%F" )
@popd
