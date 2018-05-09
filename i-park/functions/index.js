var admin = require("firebase-admin"),
  serviceAccount = require("./taipei-parking-lot-firebase-adminsdk-0qals-4c4402c7d9.json"),
  request = require("./request.js"),
  parkInfo = require("./park.js");

// init db
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://taipei-parking-lot.firebaseio.com"
});

// Use the shorthand notation to retrieve the default app's services
var defaultAuth = admin.auth();
var defaultDatabase = admin.database();

Promise.all([
  request.get(parkInfo.parkInfoUrl),
  request.get(parkInfo.parkInfoCntUrl)
]).then(function(res) {
  var parkInfoList = JSON.parse(res[0]),
    parkCntList = JSON.parse(res[1]);

  function concat(data) {
    for (var i = 0; i < parkCntList.length; i++) {
      if (data.ID === parkCntList[i].ID) {
        data.AVAILABLECAR = parkCntList[i].AVAILABLECAR;
      }
    }
  }

  for (var i = 0, len = parkInfoList.length; i < len; i++) {
    concat(parkInfoList[i]);
  }
  

  defaultDatabase.ref('park').set(parkInfoList);
});
