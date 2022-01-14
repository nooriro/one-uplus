@setlocal
@set "SRC_DIR=%~dp0OneUplus\app\src\main\cpp"
@set "DEST_DIR=%USERPROFILE%\AndroidStudioProjects\OneUplus\app\src\main\cpp"
@echo   SRC_DIR:  "%SRC_DIR%"
@echo   DEST_DIR: "%DEST_DIR%"
@if exist "%DEST_DIR%/" echo Destination directory exists.
@if not exist "%DEST_DIR%/" ( echo Destination directory does not exist. Aborting... & goto END )
:PROMPT
@echo This will reverse sync cpp folder, from SRC_DIR to DEST_DIR.
@set /p ANSWER=Are you sure (Y/N)? 
@if /i "%ANSWER%" neq "Y" ( echo Aborting... & goto END )
@robocopy "%SRC_DIR%" "%DEST_DIR%" /dcopy:dat /mir
:END
@endlocal
