::cd %3
cd C:\Wallet\Eclipse\mwallet-e2e-tests\
::call ant compile
java -Ddevice.name=%2 -Ddata.index=%4  -cp ".\lib\*";.\bin org.junit.runner.JUnitCore %1
::java -Ddevice.name=%2 -Ddata.index=%3 -Dproxycardtype=%4 -Dgetdevicelogmandatory=%6 -Deditnickname=%7 -cp ".\lib\*";.\classes org.junit.runner.JUnitCore %1
::pause

