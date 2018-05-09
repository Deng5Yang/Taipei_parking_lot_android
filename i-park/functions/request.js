var request = require("request");
var baseRequest = request.defaults({
  baseUrl: "http://data.ntpc.gov.tw/od/data/api/"
});

exports.get = function(uri) {
  return new Promise(function(resolve, reject) {
    var option = {
      uri
    };
    baseRequest(option, function(err, response, body) {
      if (err) reject(err);
      if (response.statusCode !== 200) reject(response);
      resolve(body);
    });
  }).catch(function(error) {
    console.log("caught", error);
  });
};
