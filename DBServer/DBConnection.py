import mysql.connector

class DBConnection:
	def __init__(self, ip, user, password, dbname):
		self.conn = mysql.connector.connect(host=ip,user=user, passwd=password,db=dbname)

	def LoginRetrive(self, loginType, username):
		statement = "SELECT password FROM " +  loginType + " WHERE username = \"" + username + "\";"
		try:
			self.cursor = self.conn.cursor()
			self.cursor.execute(statement)

			ans = self.cursor.fetchone()
			return ans[0]

		except Exception as e:
			raise e

		finally:
			self.cursor.close()
			self.conn.close()

	def query(self, loginType, username):
		statement = "select * from " + loginType + " where username = \"" + username + "\";"

		print(statement)

		self.result = []

		try:
			self.cursor = self.conn.cursor()
			self.cursor.execute(statement)

			row = self.cursor.fetchone()

			while row is not None:
				self.result.append(row)
				row = self.cursor.fetchone()
			return self.result

		except Exception as e:
			ans = "Database Error" + str(e)
			self.result.append(ans)
			return self.result

		finally:
			self.cursor.close()
			self.conn.close()
