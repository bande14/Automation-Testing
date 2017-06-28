call run.bat Script30544_OpenPrivacyPErmisionPage "adb:SM-G900F" ./ False
::call run.bat Script25_PE_ChangePIN_Wallet "adb:GT-I9305" ./ False
pause
::call run.bat Script11_AddCard_MD_Proxy "adb:GT-I9305" ./ False
::call run.bat tests.mwallet.Script25_PE_ChangePIN_Wallet "adb:GT-I9305" global-local MC ./ False
::pause
::CutOver
::call run.bat tests.mwallet.Script21_PE_check_WalletVersion_Wallet "adb:SM-G900F(1)" global-local MC ./ False
::call run.bat Script11_AddCard_MD_Proxy "adb:SM-G900F" global-local MC ./ False One87
::call run.bat tests.mwallet.Script47_Proxycard_i_check_carddetails_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script16_EditCard_Nickname_Proxy "adb:SM-G900F(1)" global-local MC ./ False Tokyo
::call run.bat tests.mwallet.Script17_EditCard_Image_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script18_EditCard_Nickname_By_SP_Proxy "adb:SM-G900F(1)" global-local MC ./ False One909
::call run.bat tests.mwallet.Script19_EditCard_Image_By_SP_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script_39_ProxyCardPinRules_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script26_PE_back_relaunch_Wallet "adb:SM-G900F(1)" global-local MC ./ False One87
::call run.bat tests.mwallet.Script25_PE_ChangePIN_Wallet "adb:SM-G900F(1)" global-local MC ./ False 
::call run.bat tests.mwallet.Script25_PE_ChangePIN_Wallet "adb:SM-G900F(1)" local-global MC ./ False 
::call run.bat tests.mwallet.Script22_PE_PIN_disable_Wallet "adb:SM-G900F(1)" global-local PAYPAL ./ False One95
::call run.bat tests.mwallet.Script28_PD_Launch_SP_Wait_5_Back_Wallet "adb:SM-G900F(1)" global-local PAYPAL ./ False One95
::call run.bat tests.mwallet.Script23_PD_PIN_enable_Wallet "adb:SM-G900F(1)" global-local PAYPAL ./ False One95
::call run.bat tests.mwallet.Script41_Proxycard_PIN_Settings_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script41_Proxycard_PIN_Settings_Proxy "adb:SM-G900F(1)" local-global PAYPAL ./ False One87
::call run.bat tests.mwallet.Script48_PE_TryChangePIN_Different_PINs_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script42_Proxycard_Forgot_PIN_Call_Proxy "adb:SM-G900F(1)" global-local Visa ./ False Alok23
::call run.bat tests.mwallet.Script50_Proxycard_Forgot_PIN_Email_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script45_Proxycard_Payment_Mode_A_M_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script_38_MakePayment_Cancelled_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script_36_Timeout_MakePaymentManualMode_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script_37_Retry_TimeOutMakePayment_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script46_Proxycard_Payment_Mode_M_A_Proxy "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script27_PE_Launch_SP_Wait_5_Back_Wallet "adb:SM-G900F(1)" global-local MC ./ False One87
::call run.bat tests.mwallet.Script24_PE_home_relaunch_Wallet "adb:SM-G900F(1)" global-local MC ./ False One95
::call run.bat tests.mwallet.Script43_PE_Settings_Forgot_PIN_Call_Wallet "adb:SM-G900F(1)" global-local Visa ./ False Alok42
::call run.bat tests.mwallet.Script44_PE_Wallet_Update_Wallet "adb:SM-G900F(1)" global-local MC ./ False One95
::call run.bat tests.mwallet.Script51_PE_Wallet_Forgot_PIN_atLaunch_Wallet "adb:SM-G900F(1)" global-local MC ./ False One95
::call run.bat tests.mwallet.Script15_DeleteCard_Proxy "adb:SM-G900F(1)" global-local MC ./ False One95
::call run.bat tests.mwallet.Script29411_ChangePaymentMode_AutomaticToManual_NoNetwork "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script29411_ChangePaymentMode_AutomaticToManual_NoNetwork "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script29412_ChangePaymentMode_ManualToAutomatic "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Scripts29415_ChangePaymentMode_AutomaticToManual "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29416_ChangePaymentMode_AutomaticToManual_Inactive "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29417_ChangePaymentMode_ManualToAutomatic_Inactive "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29414_ChangePaymentMode_AutomaticToManual_ForgotPin "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29430_PayPallCustomerCareCallUs "adb:SM-G900F(1)" global-local PAYPAL ./ False One87....Need to check dialer part
call run.bat tests.mwallet.Script29431_CustomerCareScreenVerification "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29432_PayPallCustomerCareEmail "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script29433_CSIEmailNotSetup "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script29434_EmailNotSetupCallCS "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script29436_PayPallChangePinNotNetwork "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29437_ForgotPinSetting "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29439_PayPallSuccesiveNumbePinNotAllowed "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29440_PayPallForgetPasswrdViaChangePasswrd "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29441_ChangeCardPINErrorScenario "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29442_Abandon_Change_Card_PIN "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29735_ChangePin "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29739_PayPallIdenticalPinFormatNotAccepted "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29740_PayPallChangePinRepeatedPainNotAccepted "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script29741_PayPallRecentYearAsPin "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
call run.bat tests.mwallet.Script12_AddSamePaymentCard_Proxy "adb:SM-G900F(1)" global-local MC ./ False One95
call run.bat tests.mwallet.Script13_AddIncorrectCard_Proxy "adb:SM-G900F(1)" global-local VISA ./ False One95


::call run.bat tests.mwallet.Script_35_UC4_done_Add_First_card_NonNFC_Showcard "adb:SM-G900F(1)" global-local Visa ./ False Alok23
::call run.bat tests.mwallet.Script_MicroCardBlockCard "adb:SM-G900F(1)" text2 MC ./ False Alok23
::call run.bat tests.mwallet.Script_MDBubbleCardVerification "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.UD_Script_2_DeviceSwap_NFC_to_NFC "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script_UserNavigatedToPersonalInfoMDCard "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script_MicroCardEditCardWithWrongDetail "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script_AddMdCard_VerificatioLater "adb:SM-G900F(1)" global-local MC ./ False Alok23
::call run.bat tests.mwallet.Script29438_PayPallWrongPinCardBlocked "adb:SM-G900F(1)" global-local Visa ./ False Alok23
::call run.bat tests.mwallet.Script13_AddIncorrectCard_Proxy "adb:SM-G900F(1)" global-local Visa ./ False Alok23

::FreshSim
::call run.bat tests.mwallet.Script_1_SetupWallet_UC4_Wallet "adb:SM-G900F(1)" global-local Visa ./ False Alok23
::call run.bat tests.mwallet.Script_3_UC4_done_Relaunch_Wallet "adb:SM-G900F(1)" global-local Visa ./ False Alok23
::call run.bat tests.mwallet.Script_80_TeaserNFC "adb:SM-G900F(1)" global-local Visa ./ False Alok23
::call run.bat tests.mwallet.Script_81_Teaser_NonNFC "adb:SM-G900F(1)" global-local Visa ./ False Alok23


::call run.bat tests.mwallet.Script_32_RestoreShowCard_PluginInstalled_Showcard "adb:SM-G900F(1)" global-local Visa ./ False Alok23
::call run.bat tests.mwallet.Script_34_AddShowCard_PluginInstalled_Showcard "adb:SM-G900F(1)" global-local Visa ./ False Alok23
::call run.bat tests.mwallet.Script_33_Delete_LoyaltyCard_Showcard "adb:SM-G900F(1)" global-local Visa ./ False Alok23


::call run.bat tests.mwallet.Script_AddMdCard_VerificatioLater "adb:SM-G900F(1)" global-local MC ./ False
::call run.bat tests.mwallet.Script61_PaypalAddCard "adb:GT-I9305_Wallet" global-local PAYPAL ./ False One95

::call run.bat tests.mwallet.Script21_PE_check_WalletVersion_Wallet "adb:GT-I9305_Wallet" global-local MC ./ False

::call run.bat tests.mwallet.Script14_AddCard_3DS_Proxy "adb:SM-G900F(1)" global-local VISA ./ False One95

::call run.bat tests.mwallet.Script25_PE_ChangePIN_Wallet "adb:SM-G900F(1)" global-local MC ./ False 
::call run.bat tests.mwallet.Script25_PE_ChangePIN_Wallet "adb:SM-G900F(1)" local-global MC ./ False 
::call run.bat tests.mwallet.Script21_PE_check_WalletVersion_Wallet "adb:SM-G900F(1)" global-local MC ./ False 
::call run.bat tests.mwallet.Script43_PE_Settings_Forgot_PIN_Call_Wallet "adb:SM-G900F(1)" global-local Visa ./ False Alok42
::call run.bat tests.mwallet.Script15_DeleteCard_Proxy "adb:SM-G900F(1)" global-local MC ./ False One95

::call run.bat tests.mwallet.Script61_PaypalAddCard "adb:SM-G900F(1)" global-local PAYPAL ./ False One87

::call run.bat tests.mwallet.Script29801_ValidateDataFieldFormat_ViaSettings "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script41_Proxycard_PIN_Settings_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script42_Proxycard_Forgot_PIN_Call_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script43_PE_Settings_Forgot_PIN_Call_Wallet "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script44_PE_Wallet_Update_Wallet "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script45_Proxycard_Payment_Mode_A_M_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script46_Proxycard_Payment_Mode_M_A_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script47_Proxycard_i_check_carddetails_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script48_PE_TryChangePIN_Different_PINs_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87

:: Needed client that has 'Whats new'......
::call run.bat tests.mwallet.Script49_PE_WhatsNew_Wallet "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script50_Proxycard_Forgot_PIN_Email_Proxy "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script51_PE_Wallet_Forgot_PIN_atLaunch_Wallet "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script51_PE_Wallet_Forgot_PIN_atLaunch_Wallet "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script26163_MWalletEditToolTip "adb:SM-G900F(1)" global-local PAYPAL ./ False One87
::call run.bat tests.mwallet.Script_AddMdCard_VerificatioLater "adb:SM-G900F(1)" global-local VISA ./ False One87
::call run.bat tests.mwallet.Script_MicroCardEditCardWithWrongDetail "adb:SM-G900F(1)" global-local VISA ./ False One87
::call run.bat tests.mwallet.Script_82_CheckOffersTAB "adb:SM-G900F(1)" global-local VISA ./ False One87
::call run.bat tests.mwallet.Script_83_LoyaliyCardIntegration "adb:SM-G900F(1)" global-local VISA ./ False One87
::call run.bat tests.mwallet.Script00_ClearAppData_General "adb:SM-G900F(1)" global-local VISA ./ False One87
::call run.bat tests.mwallet.Script13_AddIncorrectCard_Proxy "adb:SM-G900F(1)" global-local VISA ./ False One87
