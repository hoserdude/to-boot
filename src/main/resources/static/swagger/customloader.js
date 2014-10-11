$(function () {
    var url = window.location.search.match(/url=([^&]+)/);
    if (url && url.length > 1) {
        url = url[1];
    } else {
        url = "https://petstore.swagger.wordnik.com/v2/swagger.json";
    }
    window.swaggerUi = new SwaggerUi({
        url: window.location.origin + "/api-docs?group=core",
        dom_id: "swagger-ui-container",
        supportedSubmitMethods: ['get', 'post', 'put', 'delete'],
        onComplete: function(swaggerApi, swaggerUi){
            log("Loaded SwaggerUI");

            if(typeof initOAuth == "function") {
                /*
                 initOAuth({
                 clientId: "your-client-id",
                 realm: "your-realms",
                 appName: "your-app-name"
                 });
                 */
            }
            $('pre code').each(function(i, e) {
                hljs.highlightBlock(e)
            });
        },
        onFailure: function(data) {
            log("Unable to Load SwaggerUI");
        },
        docExpansion: "none",
        sorter : "alpha"
    });

    $('#input_apiKey').change(function() {
        var key = $('#input_apiKey')[0].value;
        log("key: " + key);
        if(key && key.trim() != "") {
            log("added key " + key);
            window.authorizations.add("key", new ApiKeyAuthorization("api_key", key, "query"));
        }
    })
    window.swaggerUi.load();
});
