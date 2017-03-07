package migration

import com.opencsv.bean.CsvBindByName

class Client {

    @CsvBindByName(column = "name", required = true)
    String name
    @CsvBindByName(column = "email", required = true)
    String email
    @CsvBindByName(column = "street")
    String street
    @CsvBindByName(column = "zip")
    Integer zip
    BigDecimal lat
    BigDecimal lng

    static constraints = {
        name (maxSize: 64)
        email (email: true, unique: true, nullable: false)
        street (blank: false)
        zip (blank: false)
        lat(scale: 6, blank: true, nullable: true)
        lng(scale: 6, blank: true, nullable: true)
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", zip=" + zip +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
