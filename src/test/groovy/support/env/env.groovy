package support.env

import common.Environment
import config.Configuration
import groovy.json.JsonOutput
import common.HttpConnector
import io.cucumber.groovy.Hooks
import io.cucumber.groovy.Scenario

this.metaClass.mixin(Hooks)
Configuration.loadAllConfigs()
Environment.getEnvironment()
Environment.getMode()

Before() {
    HttpConnector.clearSession()
}

After(1000) { Scenario scenario ->
    if (scenario.failed) {
        scenario.log("Request: \n" + HttpConnector.lastCurl)
        scenario.log("Response: \n" + JsonOutput.prettyPrint(JsonOutput.toJson(HttpConnector.getResponse())))
    }
}
