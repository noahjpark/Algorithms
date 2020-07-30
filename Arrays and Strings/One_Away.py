'''

There are three types of edits that can be performed on strings:
insert a character, remove a character, or replace a character. 
Given two strings, write a function to check if they are one
edit (or zero edits) away.

'''

def oneAway(str1, str2):
	if(len(str1) == len(str2)):
		return checkEqualLength(str1, str2)
	elif(len(str1) - len(str2) == 1):
		return checkNonEqualLength(str2, str1)
	elif(len(str2) - len(str1) == 1):
		return checkNonEqualLength(str1, str2)
	else:
		return False
	
def checkNonEqualLength(str1, str2):
	i = 0
	j = 0
	while i < len(str1) and j < len(str2):
		if str1[i] != str2[j]:
			if i != j:
				return False
			j += 1
		else:
			i += 1
			j += 1
	return True


def checkEqualLength(str1, str2):
	numDifference = 0
	for i in range(0, len(str1)):
		if str1[i] != str2[i]:
			numDifference += 1
	return numDifference <= 1

def main():
	str1 = "pale"
	str2 = "ple"
	str3 = "pales"
	str4 = "bale"
	str5 = "bake"
	print(oneAway(str1, str2))
	print(oneAway(str3, str1))
	print(oneAway(str1, str4))
	print(oneAway(str1, str5))

if __name__ == '__main__':
	main()