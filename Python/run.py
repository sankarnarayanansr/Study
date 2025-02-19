def findOne(i):
    return i

def myFunc(n):
    for i in range(0,n):
        yield findOne(n)


for val in myFunc(10):
    print(val)
