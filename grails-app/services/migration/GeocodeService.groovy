package migration

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class GeocodeService {

    def getGeocode(Client client) {

        def restUrl = "http://maps.googleapis.com/maps/api/geocode/json?address=" + client.street
        def url = restUrl.replace(" ", "-")
        println url
        def http = new HTTPBuilder(url);

        http.request( Method.GET, ContentType.JSON  ) {
            response.success = { resp, json ->
                client.lat = json.results.geometry.location.lat[0] as BigDecimal
                client.lng = json.results.geometry.location.lng[0] as BigDecimal
                println resp.statusLine
            }
            response.failure = { resp ->
                println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
            }
        }
        return client
    }

}
