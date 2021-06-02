import numpy as np
import pickle

print("Disclaimer")
print("This is an EXPERIMENTAL FEATURE and should be used with CAUTION")
print("Any PREDICTION made is not a SUBSTITUTE for a PROFESSIONAL OPINION")
print("The MODEL used is Random Forest which was trained on 400 instances with 96.2 ACCURACY")

"""
input_list = []
input_list.append(float(input("Enter age: ")))
input_list.append(float(input("Enter blood pressure: ")))
input_list.append(float(input("Enter specific gravity: ")))
input_list.append(float(input("Enter albumin: ")))
input_list.append(float(input("Enter sugar: ")))
input_list.append(float(input("Enter red blood cells: ")))
input_list.append(float(input("Enter pus cell: ")))
input_list.append(float(input("Enter pus cell clumps: ")))
input_list.append(float(input("Enter bacteria: ")))
input_list.append(float(input("Enter blood glucose random: ")))
input_list.append(float(input("Enter blood urea: ")))
input_list.append(float(input("Enter serum creatinine: ")))
input_list.append(float(input("Enter sodium: ")))
input_list.append(float(input("Enter potassium: ")))
input_list.append(float(input("Enter hemoglobin: ")))
input_list.append(float(input("Enter packed cell volume: ")))
input_list.append(float(input("Enter white blood cell count: ")))
input_list.append(float(input("Enter red blood cell count: ")))
input_list.append(float(input("Enter hypertension: ")))
input_list.append(float(input("Enter diabetes mellitus: ")))
input_list.append(float(input("Enter coronary artery disease: ")))
input_list.append(float(input("Enter appetite: ")))
input_list.append(float(input("Enter pedal edema: ")))
"""

input_list = [48, 80, 1.02, 1, 0, 1, 1, 0, 0, 121, 36, 1.2, 137.4816667, 4.63277592, 15.4, 44, 7800, 5.2, 1, 3, 1, 0, 0]
np_input = np.asarray(input_list)
np_input = np.reshape(np_input, (1, -1))

filename = 'RandomForestModel.sav'
model = pickle.load(open(filename, 'rb'))
x = model.predict(np_input)[0]
if x == 1:
    print("The system is suggesting that you hvae chronic kidney disease.")
    print("You probably should go and see a doctor.")
else:
    print("The system is suggesting that you do not hvae chronic kidney disease")
    print("But, you probably should go and see a doctor, if you don't feel well.")