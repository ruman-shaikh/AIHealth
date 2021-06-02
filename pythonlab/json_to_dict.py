import json
import requests

appDict = {'name': 'Ruman'}
app_json = json.dumps(appDict)
print(app_json)

addr = 'http://192.168.0.108:5000'
test_url = addr + '/hello'

response = requests.post(test_url, data=app_json)
print(type(response.json()))
print(response.json()['greeting'])