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


// Solicitar servicio para Usuario1
Response = WS.sendRequest(findTestObject('RDD/Order ODD'))

// Esperar un momento para permitir que el primer viaje se registre
// WebUI.delay(10)

// Extraer el id de la nueva reserva para Usuario1
responseBody = Response.getResponseText()
jsonSlurper = new groovy.json.JsonSlurper()
jsonResponse = jsonSlurper.parseText(responseBody)
reservationIdUser1 = jsonResponse._id

GlobalVariable.reservationIdUser1 = reservationIdUser1

// Imprimir el id de la reserva para Usuario1 para referencia
println('El id de la reserva del Usuario 1 es: ' + reservationIdUser1)

WebUI.delay(20)


//Se aprueba la solicitud del usuario 1

def Response1 = WS.sendRequest(findTestObject('RDD Admin/Accept approval'))

println(Response1.getResponseText())

// Verificar que la solicitud se haya enviado correctamente
assert Response1.getStatusCode() == 200 : "Failed to send request. Status code: ${Response1.getStatusCode()}"

// Solicitar servicio para Usuario2
ResponseUser2 = WS.sendRequest(findTestObject('RDD/RequestODD_User2'))

// Extraer el id de la nueva reserva para Usuario2
responseBodyUser2 = ResponseUser2.getResponseText()
jsonResponseUser2 = jsonSlurper.parseText(responseBodyUser2)
reservationIdUser2 = jsonResponseUser2._id

// Imprimir el id de la reserva para Usuario2 para referencia
println('El id de la reserva del Usuario 2 es: ' + reservationIdUser2)

// Verificar que el Usuario1 cumple con "exclusiveDepartures": false
def user1Response = WS.sendRequest(findTestObject('RDD Admin/Get_Users_Community'))
def user1Json = new groovy.json.JsonSlurper().parseText(user1Response.getResponseText())

// Encontrar el usuario Nico End en la lista de usuarios obtenidos
def user1 = user1Json.find { it._id == "6540083633d83952fafcfe46" }

// Verificar que el usuario existe y cumple con "exclusiveDepartures": false
if (user1 && user1.communities[0].privateBus.odd.exclusiveDepartures != false) {
	TestCase.fail("Usuario Nico End no cumple con la condición 'exclusiveDepartures': false.")
}

// Verificar que el Usuario2 cumple con "exclusiveDepartures": false
def user2Response = WS.sendRequest(findTestObject('RDD Admin/Get_Users_Community'))
def user2Json = new groovy.json.JsonSlurper().parseText(user2Response.getResponseText())

// Encontrar el usuario Usuario2 en la lista de usuarios obtenidos
def user2 = user2Json.find { it._id == "655dee4e479c215a1b6db7a4" }

// Verificar que el usuario existe y cumple con "exclusiveDepartures": false
if (user2 && user2.communities[0].privateBus.odd.exclusiveDepartures != false) {
	TestCase.fail("Usuario Usuario2 no cumple con la condición 'exclusiveDepartures': false.")
}

// Imprimir el id de la reserva del Usuario1 y Usuario2 y la URL que se va a eliminar para referencia
println('El id de la reserva del Usuario 1 es: ' + reservationIdUser1)
println('El id de la reserva del Usuario 2 es: ' + reservationIdUser2)
println('La URL que se va a eliminar es: ' + GlobalVariable.deleteUrl)

// Verificamos que la reserva se haya hecho correctamente
Response1 = WS.sendRequest(findTestObject('RDD/Get Reservations and Favorite Routes'))

responseBody1 = Response1.getResponseText()
jsonSlurper1 = new groovy.json.JsonSlurper()
jsonResponse1 = jsonSlurper1.parseText(responseBody1)

if (jsonResponse1.reservations.empty) {
	TestCase.fail("No se encontraron reservas")
}

// Dejamos como variable global el reservationId del Usuario1 para corroborar la reserva
GlobalVariable.reservationId = reservationIdUser1
// Añadimos un Delay para poder corroborar en la aplicación que efectivamente se haya creado la reserva
// WebUI.delay(20)
//Comprobamos que ambas ID son iguales y si es asi significa que se unieron los servicios
if(reservationIdUser1 != reservationIdUser2) {
	throw new AssertionError("Las Id de las reservas no son iguales, lo que indica que los servicios son separados")
}else println "Los servicios se han unido"


GlobalVariable.reservationIdUser2 = reservationIdUser2



