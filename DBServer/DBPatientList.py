import mysql.connector

def retrivePatList(username):
	ip = 'localhost'
	user = 'root'
	password = '1440'
	dbname = 'aihealth'

	con = mysql.connector.connect(host=ip,user=user, passwd=password,db=dbname)

	d_username = "silas.stone@scorp.com"

	statement = "select * from pat where username in (select pun from doc_pat where dun = \"" + username + "\");"
	#print(statement)

	try:
		cursor = con.cursor()
		cursor.execute(statement)

		ans = cursor.fetchone()
		result = []
		count = 0

		while ans is not None:
			result.append(ans)
			ans = cursor.fetchone()
			count += 1
		
		#print(result, count)

		if(count < 1):
			response = {
				"code": "201",
				"message": "No patient registered yet",
				"size": count,
				"patientList": []
			}

			return response

		else:
			response = {
				"code": "200",
				"message": "Successful Retrieval",
				"size": count,
				"patientList": []
			}

			for row in result:
				ele = {
					"username": row[0],
					"lname": row[1],
					"fname": row[2],
					"age": row[3],
					"ph_no": row[4]
				}
				response["patientList"].append(ele)
			
			return response

	except Exception as e:
		response = {
			"code": "202",
			"message": e,
			"size": count,
			"patientList": []
		}