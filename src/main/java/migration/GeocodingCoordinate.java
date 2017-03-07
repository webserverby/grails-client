package migration;

import com.google.common.collect.Maps;
import org.json.JSONObject;

import java.util.Map;

public class GeocodingCoordinate extends AbstracCoordinate {

    public Location geocoding(Location location){
        final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";// путь к Geocoding API по HTTP
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");// исходит ли запрос на геокодирование от устройства с датчиком местоположения
        params.put("address", location.getStreet());// адрес, который нужно геокодировать
        final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами
        System.out.println(url); // Путь, что бы можно было посмотреть в браузере ответ службы

        JSONObject response = JsonReader.read(url); // делаем запрос к вебсервису и получаем от него ответ
        // как правило наиболее подходящий ответ первый и данные о кординатах можно получить по пути
        // //results[0]/geometry/location/lng и //results[0]/geometry/location/lat
        JSONObject results = response.getJSONArray("results").getJSONObject(0);
        results = results.getJSONObject("geometry");
        results = results.getJSONObject("location");
        double lat = results.getDouble("lat");// широта
        double lng = results.getDouble("lng");// долгота
        location.setLat(lat);
        location.setLng(lng);

        return location;
    }

}
