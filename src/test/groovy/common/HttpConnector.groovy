package common

class HttpConnector {
    private static Response response
    static String lastCurl = ''
    private static int waitTimeout = 55000


    static Response get(Endpoint endpoint) {
        String url = endpoint.getURL()
        println "${endpoint.getName()} - GET - ${url}"
        def connection = new URL(url).openConnection() as HttpURLConnection
        prepareHeaderParameters(connection, endpoint)
        saveCurlRequest(connection, endpoint)
        connection.setConnectTimeout(waitTimeout)
        connection.setReadTimeout(waitTimeout)
        response = new Response(connection)
        return response
    }

    static Response post(Endpoint endpoint) {
        String url = endpoint.getURL()
        println "${endpoint.getName()} - POST - ${url}"
        def connection = new URL(url).openConnection() as HttpURLConnection
        connection.setDoOutput(true)
        connection.setDoInput(true)
        connection.setRequestMethod('POST')
        prepareHeaderParameters(connection, endpoint)
        saveCurlRequest(connection, endpoint)
        OutputStream os = connection.getOutputStream()
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")
        osw.write(endpoint.getBody())
        osw.flush()
        response = new Response(connection)
        return response
    }

    static Response put(Endpoint endpoint) {
        String url = endpoint.getURL()
        println "${endpoint.getName()} - PUT - ${url}"
        def connection = new URL(url).openConnection() as HttpURLConnection
        connection.setDoOutput(true)
        connection.setDoInput(true)
        connection.setRequestMethod('PUT')
        prepareHeaderParameters(connection, endpoint)
        saveCurlRequest(connection, endpoint)
        OutputStream os = connection.getOutputStream()
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")
        osw.write(endpoint.getBody())
        osw.flush()
        response = new Response(connection)
        return response
    }

    static Response delete(Endpoint endpoint) {
        String url = endpoint.getURL()
        println "${endpoint.getName()} - DELETE - ${url}"
        def connection = new URL(url).openConnection() as HttpURLConnection
        connection.setDoOutput(true)
        connection.setDoInput(true)
        connection.setRequestMethod('DELETE')
        prepareHeaderParameters(connection, endpoint)
        saveCurlRequest(connection, endpoint)
        OutputStream os = connection.getOutputStream()
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")
        osw.write(endpoint.getBody())
        osw.flush()
        response = new Response(connection)
        return response
    }

    static Response getResponse() {
        return response
    }

    static void prepareHeaderParameters(HttpURLConnection connection, Endpoint endpoint) {
        endpoint.getHeaderParameters().each { String key, String value ->
            connection.setRequestProperty(key, value)
        }
    }

    static void saveCurlRequest(HttpURLConnection connection, Endpoint endpoint) {
        StringBuilder builder = new StringBuilder("curl --location ")
        builder.append("--request ${connection.getRequestMethod()} '${connection.getURL()}' \\\n")
        connection.getRequestProperties().each { String key, List<String> values ->
            builder.append("--header '${key}: ")
            for (int i in 0..(values.size() - 1)) {
                builder.append("${values[i]}")
                if (i < (values.size() - 1)) {
                    builder.append(", ")
                }
            }
            builder.append("' \\\n")
        }
        if (endpoint.getHeaderParameters().containsKey('authorization')) {
            builder.append("--header 'authorization:${endpoint.getHeaderParameters()['authorization']}' \\\n ")
        }
        String body = endpoint.getBody()
        if ((body != '{}') && (body != 'null') && (body != null)) {
            builder.append("--data-raw '${body}'")
        }
        lastCurl = builder.toString()
    }

    static void clearSession() {
        response = null
    }
}