@pushd "%~dp0"
@if exist obj/ ( rd /s /q obj )
@if not exist obj/ ( del obj 2>nul )
@if exist libs/ ( rd /s /q libs )
@if not exist libs/ ( del libs 2>nul )
@popd
