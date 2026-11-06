import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    message.setProperty("SAP_MessageProcessingLogCustomStatus","FAILED")
    return message
}
