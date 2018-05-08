var admin = require('firebase-admin'),
    firebase = require("firebase"),
    request = require("request"),
    serviceAccount = require('./taipei-parking-lot-firebase-adminsdk-0qals-4c4402c7d9.json'),
    config = {
        apiKey: "AIzaSyC3lYRy-f1aRlmnxS-PWOcAPy363jIIA6c",
        authDomain: "taipei-parking-lot.firebaseapp.com",
        databaseURL: "https://taipei-parking-lot.firebaseio.com",
        projectId: "taipei-parking-lot",
        storageBucket: "taipei-parking-lot.appspot.com",
        messagingSenderId: "253981260051"
    };
firebase.initializeApp(config);
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: 'https://taipei-parking-lot.firebaseio.com'
});
var baseRequest = request.defaults({
    baseUrl: 'http://data.ntpc.gov.tw/od/data/api/'
});
var parkConfig = {
    data: [],
    parkInfoUrl: 'B1464EF0-9C7C-4A6F-ABF7-6BDF32847E68?$format=json',
    parkInfoCntUrl: 'E09B35A5-A738-48CC-B0F5-570B67AD9C78?$format=json',
    Api: function (uri) {
        return new Promise(function (resolve, reject) {
            var option = {
                uri
            };
            baseRequest(option, function (err, response, body) {
                if (err) reject(err);
                if (response.statusCode !== 200) reject(response);
                resolve(body);
            });
        }).catch(function (error) {
            console.log('caught', error);
        });
    }
};

Promise.all([parkConfig.Api(parkConfig.parkInfoUrl), parkConfig.Api(parkConfig.parkInfoCntUrl)]).then(function (res) {
    var parkInfo = JSON.parse(res[0]),
        parkCnt = JSON.parse(res[1]);
    var conact = function (data) {
        for (var i = 0, len = parkCnt.length; i < len; i++) {
            if (parkCnt[i].ID === data.ID) {
                data.AVAILABLECAR = parkCnt[i].AVAILABLECAR;
            }
        }
    };
    parkInfo.map(function (list) {
        conact(list);
    });
});