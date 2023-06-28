import random,string,time,datetime

def billingName():
    letters = string.ascii_lowercase
    BillingName = "Test,"+''.join((random.choice(letters) for i in range(5)))+"."
    return BillingName

def telephone():
    randomInt=random.randint(1000, 9999)
    seqTelephoneNo="403450"+str(randomInt)
    return seqTelephoneNo

def requestDate():
    Requested_Date = datetime.datetime.today().strftime ('%Y%m%d')
    return str(Requested_Date)

def dueDate():
    NextDay_Date = datetime.datetime.today() + datetime.timedelta(days=1)
    Due_Date= NextDay_Date.strftime ('%Y%m%d')
    return str(Due_Date)