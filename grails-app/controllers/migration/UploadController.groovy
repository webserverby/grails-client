package migration

import com.opencsv.CSVReader
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.HeaderColumnNameMappingStrategy
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UploadController {

    def geocodeService

    def index(Integer max) {
        params.max = Math.min(max ?: 5, 50)
        respond Client.list(params), model: [clientCount: Client.count()]
    }

    def uploadFile() {
        char separator = ';'
        def countNew = 0;
        def countUpdate = 0;
        def file = request.getFile('file')
        def reader = file.inputStream.newReader()
        def csv = new CSVReader(reader, separator)

        def strategy = new HeaderColumnNameMappingStrategy<Client>()
        strategy.setType(Client.class)
        CsvToBean<Client> csvToBean = new CsvToBean<>()
        List<Client> clientList = csvToBean.parse(strategy, csv)

        for(client in clientList){
            def clientFind = Client.findByEmail(client.email);
            if (!clientFind) {
                geocodeService.getGeocode(client)
                client.save(flush: true)
                countNew++
            } else {
                if (!clientFind.name.equals(client.name) || !clientFind.street.equals(client.street) || !clientFind.zip.equals(client.zip)) {
                    geocodeService.getGeocode(client)
                    def data = [name:client.name, street:client.street, zip:client.zip, lat:client.lat, lng:client.lng]
                    bindData(clientFind, data)

                    if (clientFind.isDirty()) {
                        println clientFind.toString()
                        println clientFind.dirtyPropertyNames
                        println clientFind.isDirty()
                        clientFind.save(flush: true)
                        countUpdate++
                    }
                }
            }
        }
        render "Импорт " + countNew + " новых клиентов и обновленно " + countUpdate + " клиентов"
    }

    def create() {
        def client = new Client()
        [client: client]
    }

    @Transactional
    def save(Client client) {
        geocodeService.getGeocode(client)
        client.save(flush: true)
        redirect(action: "index")
    }

    def edit() {
        def client = Client.get(params.id)
        [client: client]
    }

    @Transactional
    def update(Client client) {
        geocodeService.getGeocode(client)
        client.save(flush: true)
        redirect(action: "index")
    }

    def search(String arg) {
        respond Client.findAllByNameLikeOrEmailLikeOrStreetLike("%" + arg + "%", "%" + arg + "%", "%" + arg + "%")
    }

    @Transactional
    def delete() {
        def client = Client.get(params.id)
        client.delete(flush: true)
        redirect(action: "index")
    }

}
