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


// Obtenemos las paradas asociadas a la ruta
Response = WS.sendRequest(findTestObject('Scheduled/get_List_Order_Stops'))
assert Response.getStatusCode() == 200 : "Failed to send request. Status code: ${Response.getStatusCode()}"

responseBody = Response.getResponseText()

jsonSlurper = new groovy.json.JsonSlurper()

jsonResponse = jsonSlurper.parseText(responseBody)

// Definimos las paradas esperadas
def expectedStops = [
	["Id": "6564d9728956023c1b7f0a72", "sequence": 1],
	["Id": "6564da0529b0d03173e6b892", "sequence": 2],
	// Agrega más paradas según sea necesario
]

// Verificamos la presencia y el orden de las paradas esperadas
expectedStops.eachWithIndex { expectedStop, index ->
	def stopId = expectedStop.Id
	def sequence = expectedStop.sequence

	def foundStop = jsonResponse.find { it._id == stopId && it.sequence == sequence }

	if (!foundStop) {
		println("Error: La parada con ID $stopId y secuencia $sequence no se encontró en la lista de paradas asociadas.")
	} else {
		println("La parada con ID $stopId y secuencia $sequence se encontró en la posición $index.")
	}
}
