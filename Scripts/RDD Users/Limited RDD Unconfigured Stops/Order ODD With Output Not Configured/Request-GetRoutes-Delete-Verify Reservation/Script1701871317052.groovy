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

Response = WS.sendRequest(findTestObject('RDD/Request ODD with Stop Not Configured'))

// Verificar que las paradas tengan oDDConfig Options, en el caso de que no tengan, debe fallar, no se puede hacer el pedido de ODD



//Extraemos el id de la nueva reserva
responseBody = Response.getResponseText()

jsonSlurper = new JsonSlurper()

jsonResponse = jsonSlurper.parseText(responseBody)

reservationId = jsonResponse._id

//Creamos una URL con el id de la reserva recien creada para poder eliminarla
GlobalVariable.deleteUrl = ('https://stage.allrideapp.com/api/v1/pb/user/oddepartures/reservation/' + reservationId)

//Imprimimos para corroborar
println('El id de la reserva es: ' + reservationId)

println('La URL que se va a eliminar es: ' + GlobalVariable.deleteUrl)

//Obtenemos reservas y rutas favoritas
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
WebUI.delay(20)

// Eliminamos la reserva
WS.sendRequest(findTestObject('RDD/DeleteReservationNewlyCreated'))

