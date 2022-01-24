var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        XPathQuery(xhttp.responseXML);
    }
};
xhttp.open("GET", "shop.xml", true);
xhttp.send(); 


function XPathQuery(xml) {
    var txt = "";
    path = "/shop/article[@prix_vent<1000]"
    if (xml.evaluate) {
        var nodes = xml.evaluate(path, xml, null, XPathResult.ANY_TYPE, null);
        var result = nodes.iterateNext();
        while (result) {
            txt += result.childNodes[0].nodeValue + "<br>";
            result = nodes.iterateNext();
        }  
    console.log(txt)
}}