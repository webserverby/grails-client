package migration

import grails.transaction.Transactional

@Transactional
class ClientService {

    def save(Client client) {
        client.save(flush: true)
    }

    def update(Client client) {
        client.save(flush: true)
    }

    def findAll() {
        Client.list()
    }

    def delete(Client client) {
        client.delete(flush: true)
    }
}
