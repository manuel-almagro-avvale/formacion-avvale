import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper

def Message processData(Message message) {

    def body = message.getBody(String)
    def json = new JsonSlurper().parseText(body)

    json.each { key, value ->
        message.setProperty(key.toString(), value)
    }

    return message
}