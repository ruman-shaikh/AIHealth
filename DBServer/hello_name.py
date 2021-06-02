from flask import Flask
from flask import jsonify
from verifyLogin import *
from DBPatientList import *
from MLServer import ChronicKidneyDisease

app = Flask(__name__)

#URL: http://192.168.0.114:5000/hello/world/
#URL: http://192.168.0.114:5000/silas.stone@scorp.com/stone52/
"""
#@app.route("/username/password/", methods=['GET'])
@app.route("/<string:username>/<string:password>/", methods=['GET'])
def running(username, password):
	loginType = "doc_log"
	code = verifyLogin(loginType, username, password)
	response = {}

	if (code == "200"):
		response = retiveUsrData(username)
	elif (code == "201"):
		response = {
			"code": code,
			"error": "Incorrect username/password"
		}
	else:
		response = {
			"code": code,
			"error": "Database error"
		}

	print(response)
	return jsonify(response)
"""

#http://192.168.0.112:5000/doc_log/silas.stone@scorp.com/stone52/
@app.route("/doc_log/<string:username>/<string:password>/", methods=['GET'])
def docLog(username, password):
	loginType = "doc_log"
	code = verifyLogin(loginType, username, password)
	response = {}

	if (code == "200"):
		response = retiveDocData(username)
	elif (code == "201"):
		response = {
			"code": code,
			"error": "Incorrect username/password"
		}
	else:
		response = {
			"code": code,
			"error": "Database error"
		}

	print(response)
	return jsonify(response)

#http://192.168.0.112:5000/pat_log/ruman@scorp.com/ruman18/
@app.route("/pat_log/<string:username>/<string:password>/", methods=['GET'])
def patLog(username, password):
	loginType = "pat_log"
	code = verifyLogin(loginType, username, password)
	response = {}

	if (code == "200"):
		response = retivePatData(username)
	elif (code == "201"):
		response = {
			"code": code,
			"error": "Incorrect username/password"
		}
	else:
		response = {
			"code": code,
			"error": "Database error"
		}

	print(response)
	return jsonify(response)

@app.route("/doc/patlist/<string:username>/", methods=['GET'])
def running(username):
	response = retrivePatList(username)
	print(response)
	return jsonify(response)

@app.route("/pat_prediction/ckd/<string:username>/", methods=['GET'])
def ckdPrediction(username):
	response = ChronicKidneyDisease(username)
	print(response)
	return jsonify(response)

# start flask app
app.run(host="0.0.0.0", port=5000)