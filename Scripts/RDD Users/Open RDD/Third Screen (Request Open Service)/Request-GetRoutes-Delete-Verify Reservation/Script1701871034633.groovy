import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
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


//Armarmos el cuerpo para solicitar el servicio

GlobalVariable.requestBodyODD = """
{
    "oddType": "Taxis Coni y Nico",
    "name": "Solicitud y comprobación RDD Abierto ${GlobalVariable.todayDate}",
    "direction": "out",
    "comments": "Conducir con precaución",
    "serviceDate": "${GlobalVariable.serviceDate}",
    "startLocation": {
        "placeId": "655d11d88a5a1a1ff0328466",
        "lat": "-33.3908833",
        "lon": "-70.54620129999999",
        "loc": ["-70.54620129999999", "-33.3908833"],
        "address": "Alto Las Condes Avenida Presidente Kennedy Lateral, Las Condes, Chile"
    },
    "endLocation": {
        "lat": "-33.409873",
        "lon": "-70.5673477",
        "loc": ["-70.5673477", "-33.409873"],
        "address": "Mall Apumanque Avenida Manquehue Sur, Las Condes, Chile",
        "placeId": "655d11f68a5a1a1ff03284b1"
    }
}

"""

//Pedimos un servicio

Response = WS.sendRequest(findTestObject('RDD/Order Open ODD'))

//Extraemos el id de la nueva reserva
responseBody = Response.getResponseText()

jsonSlurper = new groovy.json.JsonSlurper()

jsonResponse = jsonSlurper.parseText(responseBody)

reservationId = jsonResponse._id


//Creamos una URL con el id de la reserva recien creada para poder eliminarla
GlobalVariable.deleteUrl = ('https://stage.allrideapp.com/api/v1/pb/user/oddepartures/reservation/' + reservationId)

//Creamos la URL con el ide de la reserva para poder asignar vehiculo y conductor
GlobalVariable.assignDriverUrl = "https://stage.allrideapp.com/api/v1/admin/pb/odd/assignDriver/$reservationId?community=653fd601f90509541a748683"
GlobalVariable.assignVehicleUrl = "https://stage.allrideapp.com/api/v1/admin/pb/odd/assignVehicle/$reservationId?community=653fd601f90509541a748683"

//Se arma la URL para que el conductor pueda aceptar el servicio

GlobalVariable.confirmDepartureUrl = "https://stage.allrideapp.com/api/v2/pb/driver/oddepartures/confirm/$reservationId" 
//Imprimimos para corroborar
println('El id de la reserva es: ' + reservationId)
println('La Url para poder asignar conductor es : ' + GlobalVariable.assignDriverUrl)
println('La URL que se va a eliminar es: ' + GlobalVariable.deleteUrl)

// Obtenemos reservas y rutas favoritas
Response1 = WS.sendRequest(findTestObject('RDD/Get Reservations and Favorite Routes'))

// Verificamos que la respuesta sea exitosa
if (Response1.getStatusCode() == 200) {
    // Convertimos la respuesta a JSON
    responseBody1 = Response1.getResponseText()
    jsonSlurper1 = new groovy.json.JsonSlurper()
    jsonResponse1 = jsonSlurper1.parseText(responseBody1)

    // Buscamos la reserva recién creada en la lista de reservas
    def newlyCreatedReservation = jsonResponse1.reservations.find { it._id == reservationId }

    if (newlyCreatedReservation) {
        println("La reserva recién creada se encuentra en la lista de reservas.")
    } else {
        TestCase.fail("La reserva recién creada no se encontró en la lista de reservas.")
    }
} else {
    TestCase.fail("La solicitud para obtener reservas no fue exitosa. Código de estado: " + Response1.getStatusCode())
}

// Añadimos un Delay para poder corroborar en la aplicación que efectivamente se haya creado la reserva
WebUI.delay(5)

// Eliminamos la reserva
//WS.sendRequest(findTestObject('RDD/DeleteReservationNewlyCreated'))

// Comprobamos que efectivamente la reserva recién creada es la que está presente,
// ya que este script solo reconoce el hecho de que hay reservas