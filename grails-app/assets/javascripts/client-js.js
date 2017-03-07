function upload_file(){
    $('#uploadFile').submit(function (e) {
        var formdata = new FormData(this);
        $.ajax({
            url: "upload/uploadFile",
            type: "POST",
            data: formdata,
            mimeType: "multipart/form-data",
            contentType: false,
            cache: false,
            processData: false,
            success: function(client) {
                alert(client)
            },
            error:  function(){
                alert('Ошибка!');
            }
        });
        e.preventDefault();
    });
}

