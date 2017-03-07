package migration

import grails.transaction.Transactional
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

@Transactional
class GeocodeService {

    def getGeocode(String street) {
        def lat = 0
        def lng = 0
        def result = [lat: 0, lng: 0]

        def restUrl = "http://maps.googleapis.com/maps/api/geocode/json?address=" + street
        def url = restUrl.replace(" ", "-")
        println url
        def http = new HTTPBuilder(url);

        http.request( Method.GET, ContentType.JSON  ) {
            response.success = { resp, json ->
                lat = json.results.geometry.location.lat[0]
                lng = json.results.geometry.location.lng[0]
                result = [lat: Double.valueOf(lat as String), lng: Double.valueOf(lng as String)]
                println resp.statusLine
            }

            response.failure = { resp ->
                println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
            }
        }
        return result

    }

}
