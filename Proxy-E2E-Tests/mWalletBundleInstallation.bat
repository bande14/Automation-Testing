cd C:\Wallet\Eclipse\mwallet-e2e-tests\

java -Ddevice.name="adb:D2403" -Ddata.index=1 -Dversion.name="4.13.200" -Dgetdevicelogmandatory=false -DbundleFileName="Corpay_c_agile_4.13.200_in-log-wifi_off-intro_off-preprod.zip.bundle.zip" -DUserDirectory="C:\\Wallet\\Eclipse\\mwallet-e2e-tests\\" -cp ".\lib\*";".\bin"; org.junit.runner.JUnitCore tests.mwallet.MwalletBundleInstallation
pause



