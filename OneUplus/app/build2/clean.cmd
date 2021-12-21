@pushd "%~dp0"
@if exist class/ ( rd /s /q class )
@if not exist class/ ( del class 2>nul )
@if exist classesdex/ ( rd /s /q classesdex )
@if not exist classesdex/ ( del classesdex 2>nul )
@popd
