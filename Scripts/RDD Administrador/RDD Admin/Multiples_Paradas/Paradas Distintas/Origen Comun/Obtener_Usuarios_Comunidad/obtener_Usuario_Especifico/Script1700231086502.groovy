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


// Obtener los usuarios de la comunidad
def response = WS.sendRequest(findTestObject('RDD Admin/Obtener_Usuarios_Comunidad'))

// Si la respuesta es exitosa (código 200)
if (response.getStatusCode() == 200) {
	// Convierte la respuesta JSON a un objeto Groovy
	def jsonResponse = new groovy.json.JsonSlurper().parseText(response.getResponseText())

	// Obtener la lista de usuarios del JSON
	def users = jsonResponse

	// Nombre del usuario que estamos buscando
	def userNameToFind = "Nico End"

	// Buscar el usuario por nombre
	def foundUser = users.find { it.name == userNameToFind }

	if (foundUser) {
		// Imprimir información del usuario encontrado
		println "Usuario encontrado: ${foundUser.name}, ID: ${foundUser._id}"

		// Guardar el ID del usuario encontrado en la variable global
		GlobalVariable.especificUserId1 = foundUser._id
		println "Se ha guardado el ID del usuario encontrado en GlobalVariable.especificUserId1"
	} else {
		// Si el usuario no se encuentra, imprimir un mensaje
		println "No se encontró un usuario con el nombre: $userNameToFind"
	}
} else {
	// Si la solicitud no fue exitosa, imprimir el código de respuesta
	println "La solicitud no fue exitosa. Código de respuesta: ${response.getStatusCode()}"
}
