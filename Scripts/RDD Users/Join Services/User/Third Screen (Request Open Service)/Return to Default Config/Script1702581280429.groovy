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

// Envía una solicitud GET para obtener la información del usuario
def response = WS.sendRequest(findTestObject('Object Repository/RDD/Get Community User List'))

// Parsea el JSON de la respuesta
def jsonSlurper = new groovy.json.JsonSlurper()
def detallesUsuario = jsonSlurper.parseText(response.getResponseText())

// Busca la entrada de la comunidad para "Nico End"
def comunidadNicoEnd = detallesUsuario.find { it.name == "Nico End" }

// Verifica si "needAdminApproval" es false
if (comunidadNicoEnd.communities.privateBus.odd.needsAdminApproval) {
	// Activa el paso del Object Repository para enviar la solicitud PUT y actualizar a true
	WebUI.comment('Activar el paso del Object Repository para enviar la solicitud PUT y actualizar needAdminApproval a false para volver a la configuracion inicial')
	WS.sendRequest(findTestObject('Object Repository/RDD/Return to Default Config User'))
	
	// Puedes agregar un delay o esperar a que se complete la actualización antes de continuar
	WebUI.delay(5) // 5 segundos de espera (ajusta según sea necesario)
} else {
	// Imprime un mensaje si needAdminApproval ya es true
	println("El usuario 'Nico End' ya tiene needAdminApproval en false.")
}



