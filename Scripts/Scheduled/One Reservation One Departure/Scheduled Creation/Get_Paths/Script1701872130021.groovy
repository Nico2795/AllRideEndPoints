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

def Response = WS.sendRequest(findTestObject('Scheduled/Get_Shapes'))

//Extraer el shape id, distance, distanceInMeteres y time
//Verificamos si la respuesta fue exitosa
if(Response.getStatusCode() == 200) {
	//Convertimos la respuesta JSON a Groovy
	def jsonResponse = new groovy.json.JsonSlurper().parseText(Response.getResponseText())
	
	//Nombre del trazado que necesitamos
	def targetShapeName = "Trazado Prueba Automatizacion"
	
	// Buscamos el trazado por el nombre en la respuesta JSON
	def targetShape = jsonResponse.find { it.name == targetShapeName }

	// Verificamos si se encontró el trazado
	if (targetShape) {
		// Si se encontró, imprime el ID de la supercomunidad
		def shapeId = targetShape._id
		println "El ID del trazado $targetShapeName es: $shapeId"
		// Convertimos a variable global para utilizarlo posteriormente
		GlobalVariable.shapeId = shapeId
		println("Ahora la variable global tiene el valor de: " + GlobalVariable.shapeId)
	
		// Extraemos la información adicional (distance, distanceInMeters, timeOnRoute)
		def distance = targetShape.distance
		def distanceInMeters = targetShape.distanceInMeters
		def timeOnRoute = targetShape.timeOnRoute
	
		// Imprimimos la información adicional
		println "Distancia: $distance"
		println "Distancia en metros: $distanceInMeters"
		println "Tiempo en ruta: $timeOnRoute"
	
		// Almacenamos en variables globales si es necesario
		GlobalVariable.distance = distance
		GlobalVariable.distanceInMeters = distanceInMeters
		GlobalVariable.timeOnRoute = timeOnRoute
	} else {
		// Si no se encontró, imprime un mensaje indicando que no se encontró la supercomunidad
		println "No se encontró el trazado con el nombre $targetShapeName en la respuesta."
	}
}

