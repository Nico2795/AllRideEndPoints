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
// Supongamos que tu respuesta es un String JSON almacenado en la variable 'response'
def response = WS.sendRequest(findTestObject('Calendarizada/obtener_Lista_Orden_Paradas')).getResponseText()

// Parsear el JSON
def jsonSlurper = new groovy.json.JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response)

// Supongamos que 'routeId' es la clave que contiene el ID de la ruta que obtuviste anteriormente
def routeId = GlobalVariable.routeId

// Encontrar todos los 'stopId' asociados al 'routeId'
def stopIds = jsonResponse.findAll { it.routeId._id == routeId }*.stopId?._id

// Verificar si hay al menos un 'stopId' disponible
if (stopIds) {
    // Elegir el primer 'stopId' de la lista
    def selectedStopId = stopIds[0]

    // Imprimir el 'stopId' seleccionado
    println "El stopId seleccionado asociado al routeId $routeId es: $selectedStopId"
	//Almacenamos en una variable global el stopId asociado a esa ruta
	GlobalVariable.selectedStopId = selectedStopId
	
} else {
    println "No hay stopIds disponibles para el routeId $routeId"
}


 


