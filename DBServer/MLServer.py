import sys
import numpy as np
import pickle
import mysql.connector

def ChronicKidneyDisease(username):
	ip = 'localhost'
	user = 'root'
	password = '1440'
	dbname = 'aihealth'

	con = mysql.connector.connect(host=ip,user=user, passwd=password,db=dbname)
	statement = "select * from pat_data where username = \"" + username + "\";"

	try:
		cursor = con.cursor()
		cursor.execute(statement)
		ans = cursor.fetchone()
		dataList = []

		for i in range(1, len(ans)):
			dataList.append(float(ans[i]))

		dataList = np.asarray(dataList)
		dataList = np.reshape(dataList, (1, -1))

		filename = 'D:\\Projects\\Ongoing_Projects\\AIHeath\\pythonlab\\RandomForestModel.sav'
		model = pickle.load(open(filename, 'rb'))
		x = model.predict(dataList)[0]
		print(x)
		if str(x) == "0":
			prediction = "Negative"
		else:
			prediction = "Positive"
		return {
			"code": "200",
			"result": prediction
		}


	except Exception as e:
		print(e)
		return {
			"code": "201",
			"error": e
		}

def testPrediction():
	temp_list = [21, 80, 1.02, 1, 0, 1, 1, 0, 0, 121, 36, 1.2, 137.4816667, 4.63277592, 15.4, 44, 7800, 5.2, 1, 3, 1, 0, 0]
	np_input = np.asarray(temp_list)
	np_input = np.reshape(np_input, (1, -1))
	print(np_input)

	filename = 'D:\\Projects\\Ongoing_Projects\\AIHeath\\pythonlab\\RandomForestModel.sav'
	model = pickle.load(open(filename, 'rb'))

	x = model.predict(np_input)[0]
	print(x)