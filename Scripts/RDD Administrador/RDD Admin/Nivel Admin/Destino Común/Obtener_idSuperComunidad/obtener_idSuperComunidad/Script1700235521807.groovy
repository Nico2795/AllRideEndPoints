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

// Obtenemos la lista de super comunidades
def response = WS.sendRequest(findTestObject('RDD Admin/obtener_ListaSuperComunidades'))

// Verificamos si la respuesta fue exitosa
if (response.getStatusCode() == 200) {
	// Convierte la respuesta JSON a un objeto Groovy
	def jsonResponse = new groovy.json.JsonSlurper().parseText(response.getResponseText())

	// Nombre de mi super comunidad
	def targetSuperCommunityName = "SC - Automatización"

	// Busca la supercomunidad por el nombre en la respuesta JSON
	def targetSuperCommunity = jsonResponse.find { it.name == targetSuperCommunityName }

	// Verificamos si se encontro la supercomunidad
	if (targetSuperCommunity) {
		// Si se encontró, imprime el ID de la supercomunidad
		def superCommunityId = targetSuperCommunity._id
		println "El ID de la supercomunidad $targetSuperCommunityName es: $superCommunityId"
		//Convertimos a variable global para utilizarlo posteriormente
		GlobalVariable.superCommunityId = superCommunityId
		println("Ahora la variable global tiene el valor de: " + GlobalVariable.superCommunityId)
	} else {
		// Si no se encontró, imprime un mensaje indicando que no se encontró la supercomunidad
		println "No se encontró la supercomunidad con el nombre $targetSuperCommunityName en la respuesta."
	}
} else {
	// Si la solicitud no fue exitosa, imprime el código de respuesta
	println "La solicitud no fue exitosa. Código de respuesta: ${response.getStatusCode()}"
}
