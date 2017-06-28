dim FSO,File,workFolder 


call ant compile > temp_reports\1.txt

msgbox Wscript.Arguments.Count
msgbox Wscript.Arguments(0)
msgbox Wscript.Arguments(1)

