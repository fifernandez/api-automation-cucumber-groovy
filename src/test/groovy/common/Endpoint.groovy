package common


import groovy.json.JsonOutput

class Endpoint {
    protected String name
    protected String publicURI = null
    protected String privateURI = null
    protected String uriParameters = ''
    protected Map<String, String> headerParameters = [:]
    protected Map<String, String> bodyParameters = [:]

    Endpoint(String Name) {
        name = Name
    }

    String getURL() {
        String url
        url = BasePath.getBasePath() + Uri.getUri(name) + uriParameters
        return url
    }

    Map<String, String> getHeaderParameters() {
        addHeaderParameter('Cache-Control', 'no-cache')
        addHeaderParameter('x-cache-control', 'no-cache')
        //addHeaderParameter('Accept', 'application/json')
        addHeaderParameter('content-type', 'application/json')
        return headerParameters
    }

    Endpoint addHeaderParameter(String parameterName, String value) {
        headerParameters[parameterName] = value
        this
    }

    Endpoint addBodyParameter(String parameterName, def value) {
        bodyParameters[parameterName] = value
        this
    }

    Endpoint setURIParameters(String newURIParameters) {
        uriParameters = newURIParameters
        this
    }

    String getBody() {
        JsonOutput.toJson(bodyParameters)
    }

    String getName() {
        return name
    }

    Endpoint setName(String newName) {
        name = newName
        this
    }

    String getURIParameters() {
        return uriParameters
    }

    void get() {
        HttpConnector.get(this)
    }

    void post() {
        HttpConnector.post(this)
    }

    void put() {
        HttpConnector.put(this)
    }

    void delete() {
        HttpConnector.delete(this)
    }

}
