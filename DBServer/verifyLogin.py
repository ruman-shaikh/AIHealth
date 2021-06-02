from DBConnection import *


def verifyLogin(loginType, username, enteredPasswd):	
	try:
		db = DBConnection('localhost', 'root', '1440', 'aihealth')
		result = db.LoginRetrive(loginType, username)

		if (result == enteredPasswd):
			return "200"

		return "201"
	except:
		return "202"

def retiveDocData(username):
	try:
		loginType = "doc"

		db = DBConnection('localhost', 'root', '1440', 'aihealth')
		ans = db.query(loginType, username)[0]

		result = {
			"code": "200",
			"lanme": ans[1],
			"fname": ans[2],
			"age": ans[3],
			"address": ans[4],
			"specialization": ans[5],
			"ph_no": ans[6],
			"institute_id": ans[7]
		}

		return result
	except Exception as e:
		print(e)
		return {"code": "202", "error": "User Data Retrival Error error"}

def retivePatData(username):
	try:
		loginType = "pat"

		db = DBConnection('localhost', 'root', '1440', 'aihealth')
		ans = db.query(loginType, username)[0]

		result = {
			"code": "200",
			"lanme": ans[1],
			"fname": ans[2],
			"age": ans[3],
			"ph_no": ans[4]
		}

		return result
	except Exception as e:
		print(e)
		return {"code": "202", "error": "User Data Retrival Error error"}

def retiveDocData(username):
	try:
		loginType = "doc"

		db = DBConnection('localhost', 'root', '1440', 'aihealth')
		ans = db.query(loginType, username)[0]

		result = {
			"code": "200",
			"lanme": ans[1],
			"fname": ans[2],
			"age": ans[3],
			"address": ans[4],
			"specialization": ans[5],
			"ph_no": ans[6],
			"institute_id": ans[7]
		}

		return result
	except Exception as e:
		print(e)
		return {"code": "202", "error": "User Data Retrival Error error"}

#print(retiveUsrData("silas.stone@scorp.com"))
#print(verifyLogin("doc_log", "silas.stone@scorp.com", "stone52"))