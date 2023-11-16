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

//Pedimos un servicio

Response = WS.sendRequest(findTestObject('RDD/Pedir ODD'))

//Extraemos el id de la nueva reserva
responseBody = Response.getResponseText()

jsonSlurper = new groovy.json.JsonSlurper()

jsonResponse = jsonSlurper.parseText(responseBody)

reservationId = jsonResponse._id

//Creamos una URL con el id de la reserva recien creada para poder eliminarla
GlobalVariable.deleteUrl = ('https://stage.allrideapp.com/api/v1/pb/user/oddepartures/reservation/' + reservationId)

//Imprimimos para corroborar
println('El id de la reserva es: ' + reservationId)

println('La URL que se va a eliminar es: ' + GlobalVariable.deleteUrl)

//Obtenemos reservas y rutas favoritas
Response1 = WS.sendRequest(findTestObject('RDD/Obtener Reservas y Rutas Favoritas'))

//Verificamos que la reserva se haya hecho correctamente

responseBody1 = Response1.getResponseText()
jsonSlurper1 = new groovy.json.JsonSlurper()
jsonResponse1 = jsonSlurper1.parseText(responseBody1)

if (jsonResponse1.reservations.empty) {
	TestCase.fail("No se encontraron reservas")
}

//Añadimos un Delay para poder corroborar en la aplicacion que efectivamente se haya creado la reserva
WebUI.delay(20)

//Eliminamos la reserva

WS.sendRequest(findTestObject('RDD/EliminarReservaRecienCreada'))


