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

Response = WS.sendRequest(findTestObject('RDD Admin/get_Vehicles'))


// Obtener el contenido de la respuesta como una cadena de texto
def responseBody = Response.getResponseText()

// Parsear la respuesta JSON
def jsonSlurper = new groovy.json.JsonSlurper()
def jsonResponse = jsonSlurper.parseText(responseBody)

// Obtener el _id del vehiculo
def vehicleId = jsonResponse[0]._id

// Almacenar el _id en la variable global
GlobalVariable.vehicleId = vehicleId

// Imprimir el _id para verificar
println("El _id del vehiculo es: ${vehicleId}")

// Verificar si hay al menos un elemento en la lista
if (jsonResponse.size() > 0) {
	// Obtener la capacidad del vehiculo desde el objeto "category"
	def capacity = jsonResponse[0].category?.capacity

	// Verificar si la capacidad existe antes de almacenarla
	if (capacity != null) {
		// Almacenar la capacidad en la variable global
		GlobalVariable.vehicleCapacity = capacity

		// Imprimir la capacidad para verificar
		println("La capacidad del vehiculo es: ${capacity}")
	} else {
		println("No se pudo encontrar la capacidad del vehiculo en la respuesta JSON.")
	}
} else {
	println("La respuesta JSON no contiene elementos.")
}

