import os.path
import sys

def pathtest():
	filePath = 'D:\\Projects\\Ongoing_Projects\\AIHeath\\NetworkLab\\DLModels\\ImageNetCatsVDogs.h5'
	if os.path.isfile(filePath) is False:
		raise FileNotFoundError("Model not found")
	print("Good to go")


def typetest():
	target_size = (300,300)
	if isinstance(target_size, tuple) is False or len(target_size) is not 2:
		raise TypeError("Input Dimension Mismatch")
	print("ok")

"""
try:
	typetest()
except:
	print(sys.exc_info()[1])"""


import os

print(os.getcwd())