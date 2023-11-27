import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent //for text in body

// Obtener los lugares


//Obtenemos la lista de lugares
ResponseObject response = WS.sendRequest(findTestObject('RDD/Obtener Lugares'))

//Parseamos el JSON
String responseBody = response.getResponseText()
def jsonSlurper = new groovy.json.JsonSlurper()
def places = jsonSlurper.parseText(responseBody)


//Almacenamos los lugares que no cumplen con la config correcta para mostrarlos
def lugaresSinConfig = []


// Recorreremos los lugares
for (def place : places) {
    try {
		//Si es que el lugar no tiene opciones entra al if, donde se registra y se guarda.
        if (!place.stop.oDDConfig.options) {
            //Registramos los luagres que no cumplen con la estructura de config
			lugaresSinConfig.add(place)
			continue // Pasa al siguiente lugar
        }

        // Generar el JSON para el pedido de ODD con el ID del lugar actual
        GlobalVariable.oddRequest = """
        {
            "oddType": "Taxis Coni y Nico",
            "name": "Viaje No configurado",
            "direction": "out",
            "comments": "Conducir con precaución",
            "serviceDate": "${GlobalVariable.serviceDate}",
            "startLocation": {
                "placeId": "${place._id}",
                "lat": "${place.stop.lat}",
                "lon": "${place.stop.lon}",
                "loc": ["${place.stop.lon}", "${place.stop.lat}"],
                "address": "Dirección inicio"
            },
            "endLocation": {
                "lat": "-33.4549",
                "lon": "-70.5915",
                "loc": ["-70.5915", "-33.4549"],
                "address": "Dirección destino",
                "placeId": "6543d0600d3bbd73ffd6de2d"
            }
        }
        """
        // Imprimir el JSON para verificar si funciona
        println(GlobalVariable.oddRequest)
		
		//Se pide el servicio y se imprime para corroborar
		Response2= WS.sendRequest(findTestObject('RDD/Pedir ODD con Parada No configurada'))
		println(Response2.getResponseText())
		
		//Extraemos el id de la nueva reserva
		responseBody2 = Response2.getResponseText()
		
		jsonSlurper2 = new groovy.json.JsonSlurper()
		
		jsonResponse2 = jsonSlurper2.parseText(responseBody2)
		
		reservationId = jsonResponse2._id
		
		//Creamos una URL con el id de la reserva recien creada para poder eliminarla
		GlobalVariable.deleteUrl = ('https://stage.allrideapp.com/api/v1/pb/user/oddepartures/reservation/' + reservationId)
		
		//Imprimimos para corroborar
		println('El id de la reserva es: ' + reservationId)
		
		println('La URL que se va a eliminar es: ' + GlobalVariable.deleteUrl)
		
		//Obtenemos reservas y rutas favoritas
		Response3 = WS.sendRequest(findTestObject('RDD/Obtener Reservas y Rutas Favoritas'))
		
		//Verificamos que la reserva se haya hecho correctamente
		responseBody3 = Response3.getResponseText()
		
		jsonSlurper3 = new groovy.json.JsonSlurper()
		
		jsonResponse3 = jsonSlurper3.parseText(responseBody3)
		
		if (jsonResponse3.reservations.empty) {
			TestCase.fail('No se encontraron reservas')
		}
		
		//Añadimos un Delay para poder corroborar en la aplicacion que efectivamente se haya creado la reserva
		WebUI.delay(20)
		
		WS.sendRequest(findTestObject('RDD/EliminarReservaRecienCreada'))
		
        
       //Manejo de errores y mostrarlos por consola y reporte
    } catch (Exception e) {
       
        String errorMessage = "Error en el lugar ${place.longName} con ID ${place._id}: ${e.message}"
		KeywordLogger log = new KeywordLogger()
		log.logInfo(errorMessage)
	

    }
}
	
//Informamos los lugares que no tienen config
if(!lugaresSinConfig.empty) {
	KeywordLogger log = new KeywordLogger()
	log.logInfo("Lugares sin configuración:")
	for (def lugar : lugaresSinConfig) {
		log.logInfo("Nombre: ${lugar.longName}, ID: ${lugar._id}")
	}
}
