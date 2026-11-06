import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    //Leemos la property con el número del DNI
    def numDNI = message.getProperty("NIF_SinLetra")

    //Definimos la lista de letras en el orden adecuado
    def letras = "TRWAGMYFPDXBNJZSQVHLCKE"

    //Validación de longitud
    if (!numDNI || numDNI.length() != 8) {
        message.setProperty("EjecucionValida", "false")
        message.setProperty("CausaError", "El NIF debe tener exactamente 8 caracteres.")
        message.setHeader("CamelHttpResponseCode", "400") //Error HTTPP 400: Contenido improcesable

        return message
    }

    //Validación de que sea un número entero
    try {
        def numero = numDNI.toInteger()
    } catch (Exception e) {
        message.setProperty("EjecucionValida", "false")
        message.setProperty("CausaError", "El NIF debe ser un número entero.")
        message.setHeader("CamelHttpResponseCode", "400") //Error HTTPP 400: Contenido improcesable
        return message
    }

    //Si pasó ambas validaciones, calculamos la letra
    def posicion = numDNI.toInteger() % 23
    def completoDNI = numDNI + letras[posicion]

    //Establecemos propiedades de salida
    message.setProperty("NIF_ConLetra", completoDNI)
    message.setProperty("EjecucionValida", "true")

    return message
}
