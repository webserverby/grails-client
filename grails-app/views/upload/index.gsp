<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="list.label" /></title>
</head>
<body>
    <div class="divButton">
        <h2>CSV Importer</h2>
        <form id="uploadFile" enctype="multipart/form-data">
            <input accept=".csv" class="upload" id="file" name="file" type="file" class="btn-new">
            <button type="submit" onclick="upload_file()" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span></button>
        </form>
    </div>

    <div class="divButton">
    <g:link controller="upload" action="create" class="btn btn-success">
        <span class="glyphicon glyphicon-plus"><span class="font-white"> Client</span></span>
    </g:link>
    </div>

    <div id="list-place" class="content scaffold-list" role="main">
        <h1><g:message code="clients.list"/></h1>
        <table>
            <thead>
            <tr>
                <th class="rounded-init"><g:message code="client.id.label"/></th>
                <th class="rounded-init"><g:message code="client.name.label"/></th>
                <th class="rounded-init"><g:message code="client.email.label"/></th>
                <th class="rounded-init"><g:message code="client.street.label"/></th>
                <th class="rounded-init"><g:message code="client.zip.label"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${clientList}" var="client">
                <tr>
                    <td>${fieldValue(bean: client, field: "id")}</td>
                    <td>${fieldValue(bean: client, field: "name")}</td>
                    <td>${fieldValue(bean: client, field: "email")}</td>
                    <td>${fieldValue(bean: client, field: "street")}</td>
                    <td>${fieldValue(bean: client, field: "zip")}</td>
                    <td>
                        <g:link controller="upload" action="edit" id="${client?.id}" class="btn btn-warning">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </g:link>
                    </td>
                    <td>
                        <g:link controller="upload" action="delete" id="${client?.id}" class="btn btn-danger">
                            <span class="glyphicon glyphicon-trash"></span>
                        </g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <g:paginate total="${clientCount ?: 0}"/>
        </div>
    </div>


    <div id="map" class="map"></div>

    <script>
        var map;
        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: -34.397, lng: 150.644},
                zoom: 10
            });

            var bounds = new google.maps.LatLngBounds();
            var centerLatLng;

            <g:each in="${clientList}" var="location">
            if(${location.lat}&&${location.lng}) {
                centerLatLng = new google.maps.LatLng(${location.lat}, ${location.lng});
                var marker${location.id} = new google.maps.Marker({
                    position: centerLatLng,
                    map: map
                });

                var contentString${location.id} = '<h3>${location.name}</h3><p>${location.email}</p><p>${location.street} ${location.zip}</p>';

                var infowindow${location.id} = new google.maps.InfoWindow({
                    content: contentString${location.id}
                });

                marker${location.id}.addListener('click', function() {
                    infowindow${location.id}.open(map, marker${location.id});
                });

                bounds.extend(centerLatLng);
            }
            </g:each>
            map.fitBounds(bounds);
        }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBSNjRlNcL4mtTsgNrJyLKf-JQDP8nKoo&callback=initMap"></script>

</body>

</html>
