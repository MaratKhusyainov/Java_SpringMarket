angular.module('app').controller('productsSoapController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showProductsSoap = function () {
        let xmlhttp = new XMLHttpRequest();
        xmlhttp.open('POST', 'http://localhost:8189/market/ws', true);
        let sr;
        let body;
        console.log($scope.number);
        if ($scope.number == null) {
            body = '  <f:getAllProductsRequest/>';
        } else {
            body = '<f:getProductByIdRequest>' +
                '<f:id>' + $scope.number + '</f:id>' +
                '</f:getProductByIdRequest>';
        }
        sr = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" ' +
            'xmlns:f="http://www.evgeniiboznyakov.com/spring/ws/products"> ' +
            '<soapenv:Header/> ' +
            '<soapenv:Body> ' +
            body +
            '</soapenv:Body> ' +
            '</soapenv:Envelope> ';
        xmlhttp.setRequestHeader('Content-Type', 'text/xml');
        xmlhttp.send(sr);
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                let xmlDoc = xmlhttp.responseXML;
                console.log(xmlDoc);
                let obj = xmlToJson(xmlDoc);
                if ($scope.number == null) {
                    $scope.ProductsPageSoap = obj.Envelope.Body.getAllProductsResponse.products;
                    console.log($scope.ProductsPageSoap);
                    console.log(obj);
                    $scope.ProductSoap = null;
                } else {
                    $scope.ProductSoap = obj.Envelope.Body.getProductByIdResponse.products;

                    $scope.ProductsPageSoap = null;
                }

            }
        }
    }

    function xmlToJson(xml) {
        // Create the return object
        var obj = {};

        if (xml.nodeType == 1) {// element
            // do attributes
            if (xml.attributes.length > 0) {
                obj["@attributes"] = {};
                for (var j = 0; j < xml.attributes.length; j++) {
                    var attribute = xml.attributes.item(j);
                    obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
                }
            }
        } else if (xml.nodeType == 3) {// text
            obj = xml.nodeValue;
        }

        // do children
        if (xml.hasChildNodes()) {
            for (var i = 0; i < xml.childNodes.length; i++) {
                var item = xml.childNodes.item(i);
                var nodeName = item.nodeName.substring(item.nodeName.indexOf(":") + 1).replace('#', '');
                if (typeof (obj[nodeName]) == "undefined") {
                    obj[nodeName] = xmlToJson(item);
                } else {
                    if (typeof (obj[nodeName].push) == "undefined") {
                        var old = obj[nodeName];
                        obj[nodeName] = [];
                        obj[nodeName].push(old);
                    }
                    obj[nodeName].push(xmlToJson(item));
                }
            }
        }
        return obj;
    }

});
