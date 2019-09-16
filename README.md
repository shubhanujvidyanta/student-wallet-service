# student-wallet-service


API services:

POST  localhost:8080/student/register/add

Request: 

{"firstName":"Shubhanuj",
	"lastName":"Vidyanta",
	"email":"shubhanuj1@gmail.com",
	"password":"qwerty1234",
	"country":"INDIA",
	"phoneNumber":9911111111
}

Response: 

{
    "registerStudentSuccess": true,
    "Student": {
        "id": 5,
        "firstName": "Shubhanuj",
        "lastName": "Vidyanta",
        "email": "shubhanuj1@gmail.com",
        "encryptedPassword": "PlFMxJuHZj5wVdAyHeub7g==",
        "passwordEncrypted": true,
        "country": "INDIA",
        "phoneNumber": 9911111111,
        "wallet": null,
        "addresses": null,
        "createdAt": 1568614441653,
        "updatedAt": 1568614441653
    }
}



POST   localhost:8080/student/login/

Request: 

{"email":"shubhanuj@gmail.com",
"password":"qwerty1234"}


Response:

{
    "loginSuccess": true,
    "viewName": "MyAccountView",
    "student": {
        "id": 2,
        "firstName": "Shubhanuj",
        "lastName": "Vidyanta",
        "email": "shubhanuj@gmail.com",
        "encryptedPassword": "PlFMxJuHZj5wVdAyHeub7g==",
        "passwordEncrypted": true,
        "country": "INDIA",
        "phoneNumber": 7829673467,
        "wallet": {
            "id": 1,
            "balance": 0.00,
            "onHold": 0.00,
            "availableBalance": 0.00,
            "currency": "INR",
            "status": 1,
            "createdAt": 1568206163000,
            "updatedAt": 1568206163000,
            "studentId": 2
        },
        "addresses": [],
        "createdAt": 1568205306000,
        "updatedAt": 1568206163000
    }
}


POST localhost:8080/address/add

Request: 
{
	"isDefault":"true",
	"studentId":1,
	"firstName":"Shubhanuj",
	"lastName":"Vidyanta",
	"address1":"#14, Sri Venkateshwara Nilayam",
	"address2":"9th Cross, Kuvempu Rd.",
	"area": "Vignana Nagar",
	"pincode":560075,
	"city": "Bangalore",
	"country":"India",
	"phone1":7829673467
}

Response:
{
    "Address": {
        "id": 4,
        "isDefault": "true",
        "studentId": 1,
        "firstName": "Shubhanuj",
        "lastName": "Vidyanta",
        "address1": "#14, Sri Venkateshwara Nilayam",
        "address2": "9th Cross, Kuvempu Rd.",
        "address3": null,
        "phone1": 7829673467,
        "phone2": null,
        "pincode": 560075,
        "area": "Vignana Nagar",
        "city": "Bangalore",
        "country": "India",
        "nickName": null,
        "createdAt": 1568203823719,
        "updatedAt": 1568203823719
    }
}


GET localhost:8080/student/getByEmail/shubhanuj@gmail.com

Response:
{
    "Student": {
        "id": 2,
        "firstName": "Shubhanuj",
        "lastName": "Vidyanta",
        "email": "shubhanuj@gmail.com",
        "encryptedPassword": "rN/5gRjLTs6D40SbE37auw==",
        "passwordEncrypted": true,
        "country": "INDIA",
        "phoneNumber": 7829673467,
        "wallet": {
            "id": 1,
            "balance": 0.00,
            "onHold": 0.00,
            "availableBalance": 0.00,
            "currency": "INR",
            "status": 1,
            "createdAt": 1568206163000,
            "updatedAt": 1568206163000,
            "studentId": 2
        },
        "addresses": [],
        "createdAt": 1568205306000,
        "updatedAt": 1568206163000
    }
}


GET localhost:8080/student/getById/2

Response
{
    "Student": {
        "id": 2,
        "firstName": "Shubhanuj",
        "lastName": "Vidyanta",
        "email": "shubhanuj@gmail.com",
        "encryptedPassword": "PlFMxJuHZj5wVdAyHeub7g==",
        "passwordEncrypted": true,
        "country": "INDIA",
        "phoneNumber": 7829673467,
        "wallet": {
            "id": 1,
            "balance": 0.00,
            "onHold": 0.00,
            "availableBalance": 0.00,
            "currency": "INR",
            "status": 1,
            "createdAt": 1568206163000,
            "updatedAt": 1568206163000,
            "studentId": 2
        },
        "addresses": [],
        "createdAt": 1568205306000,
        "updatedAt": 1568206163000
    }
}


POST localhost:8080/student/createWallet/3

Error Response:
{
    "WalletStatus": "Failure",
    "Exception": "Wallet creation failed. Please check if you have existing wallet or try again later."
}

Success Response:
{
    "Wallet": {
        "id": 9,
        "balance": 0,
        "onHold": 0,
        "availableBalance": 0,
        "currency": "INR",
        "status": 1,
        "createdAt": 1568614855222,
        "updatedAt": 1568614855222,
        "studentId": 5
    },
    "createWalletSuccess": true
}


GET  localhost:8080/wallet/getBalance/2

{
    "Wallet": {
        "id": 1,
        "balance": 0.00,
        "onHold": 0.00,
        "availableBalance": 0.00,
        "currency": "INR",
        "status": 1,
        "createdAt": 1568206163000,
        "updatedAt": 1568206163000,
        "studentId": 2
    }
}


POST  localhost:8080/wallet/addMoney/1

Request:
{
	"value": 100.00,
	"currency": "INR"
}

Error Response:
{
    "addMoneySuccess": false,
    "Error": "Wallet is not available."
}

Success Response:
{
    "addMoneySuccess": true,
    "Wallet": {
        "id": 1,
        "balance": 100.00,
        "onHold": 0.00,
        "availableBalance": 100.00,
        "currency": "INR",
        "status": 1,
        "createdAt": 1568206163000,
        "updatedAt": 1568614988676,
        "studentId": 2
    }
}
