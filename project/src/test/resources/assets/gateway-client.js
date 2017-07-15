function invoke(e) {
    // No param
    var req = {
        path : "/",
        method : "GET"
    };

    var params = {};
    $(".req-query").each(function (i) {
        var val = $(this).val();
        var id = $(this).attr("id");
        params[id] = val;
    });
    
    if (Object.size(params) > 0) {
        req.query = params;
    }
    
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "https://" + window.location.host + "/gateway",
        cache: false,
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(req)
    })
        .done(function(res) {
            var content = "";
            if (res.success) {
                content = "<strong>Status:</strong><br/>"
                    + "&nbsp;&nbsp;" + res["status-code"] + " " + res["status-message"]

                    + "<br/><strong>Headers:</strong><br/>";

                $.each( res.headers, function( key, value ) {
                    content += "&nbsp;&nbsp;" + key + ": " + value + "<br/>";
                });

                content += "<strong>Content:</strong><br/>&nbsp;&nbsp;" + res.body.replace(/\n/g, "<br/>");
                if (res["status-code"] < 400) {
                    $("#result").html("<p class='bg-success result'>" + content + "</p>");
                } else {
                    $("#result").html("<p class='bg-warning result'>" + content + "</p>");
                }
            } else {
                content = res["error"] + ": " + res["reason"];
                $("#result").html("<p class='bg-danger result'>" + content + "</p>");
            }
        })
        .fail(function(jqXHR, textStatus) {
            $("#result").html("<p class='bg-danger result'>Gateway invocation failed with status: " + textStatus +
                "</p>");
        });

    e.preventDefault();
}

Object.size = function(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) size++;
    }
    return size;
};


$( document ).ready(function() {
    $("#invoke").click(invoke);
});