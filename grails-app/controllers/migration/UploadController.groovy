package migration

import com.opencsv.CSVReader
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.HeaderColumnNameMappingStrategy

class UploadController {

    def clientService

    def geocodingCoordinate = new GeocodingCoordinate()

    def location = new Location()

    def index(Integer max) {
        //redirect(action: "list", params: params)
        //redirect view:"index"
        params.max = Math.min(max ?: 5, 50)
        respond Client.list(params), model: [clientCount: Client.count()]
        //def clientList = clientService.findAll()
        //[clientList: clientList]
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
                location.setStreet(client.street)
                location = geocodingCoordinate.geocoding(location)
                client.lat = location.getLat()
                client.lng = location.getLng()

                clientService.save(client)
                countNew++
            } else {
                if (!clientFind.name.equals(client.name) || !clientFind.street.equals(client.street) || !clientFind.zip.equals(client.zip)) {
                    clientFind.name = client.name;
                    clientFind.street = client.street;
                    clientFind.zip = client.zip;

                    location.setStreet(clientFind.street)
                    location = geocodingCoordinate.geocoding(location)
                    clientFind.lat = location.getLat()
                    clientFind.lng = location.getLng()

                    clientService.save(clientFind)
                    countUpdate++
                }
            }

        }

        render "Импорт " + countNew + " новых клиентов и обновленно " + countUpdate + " клиентов"
    }

    def create() {
        def client = new Client()
        client.properties = params
        [client: client]
    }

    def save(Client client) {
        location.setStreet(client.street)
        location = geocodingCoordinate.geocoding(location)
        client.lat = location.getLat()
        client.lng = location.getLng()

        clientService.save(client)
        redirect(action: "index")
    }

    def edit() {
        def client = Client.get(params.id)
        [client: client]
    }

    def update(Client client) {
        location.setStreet(client.street)
        location = geocodingCoordinate.geocoding(location)
        client.lat = location.getLat()
        client.lng = location.getLng()

        clientService.update(client)
        redirect(action: "index")
    }

    def search(String arg) {
        respond Client.findAllByNameLikeOrEmailLikeOrStreetLike("%" + arg + "%", "%" + arg + "%", "%" + arg + "%")
    }

    def delete() {
        def client = Client.get(params.id)
        clientService.delete(client)
        redirect(action: "index")
    }

}
