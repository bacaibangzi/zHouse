
function installRoot()

	Dim sPKCS7
	Dim n

	sPKCS7=""

	sPKCS7=sPKCS7 & "-----BEGIN CERTIFICATE-----" & vbNewLine
	sPKCS7=sPKCS7 & "MIID+QYJKoZIhvcNAQcCoIID6jCCA+YCAQExADALBgkqhkiG9w0BBwGgggPOMIID" & vbNewLine
	sPKCS7=sPKCS7 & "yjCCArKgAwIBAgIQBD3kyoUdXoZEV0V88DBsHzANBgkqhkiG9w0BAQUFADAjMSEw" & vbNewLine
	sPKCS7=sPKCS7 & "HwYDVQQDDBhXRULmiZPljbDmjqfku7bkuqTmtYHnvqQwHhcNMDgwMTAxMTIwNzQz" & vbNewLine
	sPKCS7=sPKCS7 & "WhcNMzUwNTE4MTIxNjQzWjAjMSEwHwYDVQQDDBhXRULmiZPljbDmjqfku7bkuqTm" & vbNewLine
	sPKCS7=sPKCS7 & "tYHnvqQwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDSEqqyjWzFwm9M" & vbNewLine
	sPKCS7=sPKCS7 & "k4bi0hhbJZBpo79E8wgCinFmvH/xGyabns3l3qNaJSSqxs2WKtCl3kX/xLQtYMvk" & vbNewLine
	sPKCS7=sPKCS7 & "1avgmGU1BgDSwJ/0znSmuIiyEoiwjlUDzNWXq0XZ1hREI/y01kjWdmv6Gszpqo/A" & vbNewLine
	sPKCS7=sPKCS7 & "Ofpecs91GaVXjVoJnPTD3T1Cm9CTKme5vdGK1Gd7J9yhCrjYoZBVej0sL6KVfzak" & vbNewLine
	sPKCS7=sPKCS7 & "G7DdnrQF5xSyD6HlbazEkqZYNmVlctOvM36hZzaca+kEBrCKuFtIdXjNxm7Y5jdu" & vbNewLine
	sPKCS7=sPKCS7 & "eLFPX7fzwBoGlJt5P24st51938mMvGlCwxT1ywzwDDqzvwu/E9G3b9Ve5gsV3p62" & vbNewLine
	sPKCS7=sPKCS7 & "ZsGvz4gxAgMBAAGjgfkwgfYwCwYDVR0PBAQDAgGGMA8GA1UdEwEB/wQFMAMBAf8w" & vbNewLine
	sPKCS7=sPKCS7 & "HQYDVR0OBBYEFNdnMzNjJevi1Z7X1ddD0IZduXqcMIGkBgNVHR8EgZwwgZkwgZag" & vbNewLine
	sPKCS7=sPKCS7 & "gZOggZCGRWh0dHA6Ly93ZWJwcmludC9DZXJ0RW5yb2xsL1dFQiE2MjUzITUzNzAh" & vbNewLine
	sPKCS7=sPKCS7 & "NjNhNyE0ZWY2ITRlYTQhNmQ0MSE3ZmE0LmNybIZHZmlsZTovL1xcd2VicHJpbnRc" & vbNewLine
	sPKCS7=sPKCS7 & "Q2VydEVucm9sbFxXRUIhNjI1MyE1MzcwITYzYTchNGVmNiE0ZWE0ITZkNDEhN2Zh" & vbNewLine
	sPKCS7=sPKCS7 & "NC5jcmwwEAYJKwYBBAGCNxUBBAMCAQAwDQYJKoZIhvcNAQEFBQADggEBAGZKR20y" & vbNewLine
	sPKCS7=sPKCS7 & "8jzYkXSTbxmJiCj8t8n311sAhPUFFPcZqui/jVZ2d+k1BrIJKMk6w1FlhVn5WdDV" & vbNewLine
	sPKCS7=sPKCS7 & "OlBaXnZicFMUqemiVNGdrlSVe+lP3EPQSjCgGHbYS+KQNAZ4Lx6DqXVrJoj/75mk" & vbNewLine
	sPKCS7=sPKCS7 & "0cBDE0EJdICNlunza8wDQqGxdISezi+/fLacLb1ChA9i0HO+blOjyIxttJGmPXVh" & vbNewLine
	sPKCS7=sPKCS7 & "Qs3xyoDWp24dKYS+slzoyhhmjwg8gilSCXyvo/Zf8K6rxOr3yDBeAOZvHSJrJVF3" & vbNewLine
	sPKCS7=sPKCS7 & "IpuXNmB6y83M7CUAwF3mVLfiDtelDoB+BxW8pKPJazZ56UWefbaLoTzU1/d6vJDK" & vbNewLine
	sPKCS7=sPKCS7 & "FZn3iNmDJvJL7FgxAA==" & vbNewLine
	sPKCS7=sPKCS7 & "-----END CERTIFICATE-----" & vbNewLine

	if haveInstalled("E8E632A6 37392D9C F1A1C60A A38AC8F5 25EB6DD6") = 0 then
		n = XEnroll.InstallPKCS7Ex(sPKCS7)
		if n  = 0 then
			Msgbox "没有安装根证书，请手动下载根证书，并安装！"
		end if
	end if

end function


function haveInstalled(str)

	Dim storeName
	Dim thumbprint
	Dim thumbprint0
	
	thumbprint0 = str
	thumbprint0 = Replace(thumbprint0, " ", "")
	thumbprint0 = UCase(thumbprint0)

	Set st = CreateObject("CAPICOM.Store")
	
	'get store name 
	storeName =  "root"

	'open store
	st.Open 2, storeName, 0

	haveInstalled = 0

	For i = 1 to st.Certificates.Count
		thumbprint = st.Certificates(i).thumbprint
		thumbprint = Replace(thumbprint, " ", "")
		thumbprint = UCase(thumbprint)
		if thumbprint = thumbprint0 then
			haveInstalled = 1
			Exit For
		end if
	Next
end function