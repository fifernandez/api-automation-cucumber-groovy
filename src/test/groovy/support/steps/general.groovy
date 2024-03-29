package support.steps

import common.Endpoint
import common.HttpConnector
import io.cucumber.groovy.EN
this.metaClass.mixin(EN)

Given(~/^I do a get to the "(.*)" endpoint$/) { String endpointName ->
    new Endpoint(endpointName).get()
}

Then(~/^the returned status code is: "(\d{3})"$/) { String expectedCodeReturned ->
    String lastResponseStatusCode = HttpConnector.getResponse().responseCode
    assert expectedCodeReturned == lastResponseStatusCode
}

Then(~/^the response contains "(.*)" items$/) { int expectedAmount ->
    List<Map> returnedList = (List<Map>) HttpConnector.getResponse().list
    assert expectedAmount == returnedList.size()
}
