package common

import groovy.json.JsonSlurper
import org.apache.groovy.json.internal.LazyMap

class Response extends LazyMap {

    Response(HttpURLConnection connection) {
        String responseCode = connection.responseCode
        String info = ''
        super
        if (responseCode == '200' || responseCode == '201' || responseCode == '202') {
            info = connection.inputStream.text
            def jsonSlurper = new JsonSlurper()
            if (info) {
                def aux = jsonSlurper.parseText(info)
                if (aux instanceof ArrayList) {
                    this << ['list': aux]
                } else {
                    if (aux != null) {
                        this.putAll((LazyMap) jsonSlurper.parseText(info))
                    }
                    else {
                        this.put("responseBody", "null")
                    }
                }
            }
        } else {
            if (responseCode == '404') {
                [error: '404 - Not found, empty response']
            }
            def jsonSlurper = new JsonSlurper()
            try {
                info = connection.errorStream.text
                this.putAll((LazyMap) jsonSlurper.parseText(info))
            }
            catch (Exception e) {
                this << [error: 'Could not parse error info']
            }
        }
        this << [responseCode: responseCode]
    }

}