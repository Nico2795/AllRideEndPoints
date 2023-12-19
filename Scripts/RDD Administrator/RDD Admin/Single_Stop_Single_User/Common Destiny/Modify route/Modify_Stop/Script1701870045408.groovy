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
//Creamos el cuerpo de la solicitud POST para modificar parada con la nueva direccion
GlobalVariable.bodyModifyStops = 
"""
{
"name": "Parada CambioAutomatizacion",
"address": "Av. Alfredo Silva Carvallo, Maipú, Región Metropolitana, Chile",
"categories": ["odd"],
"communities": ["653fd601f90509541a748683"],
"communityId": "653fd601f90509541a748683",
"lat": -33.532782,
"lon": -70.7812735,
"ownerIds": [
{ "id": "653fd601f90509541a748683", "role": "community" }
],
"stopId": "6543d0600d3bbd73ffd6de2e",
"userQty": 1,
"users": []
}
"""

//Imprimimos la variable para corroborar

println "El cuerpo ahora es: ${GlobalVariable.bodyModifyStops}"

//Creamos la URL para poder modificar y enviar la solicitud de PUT

GlobalVariable.urlModifyStops = "${GlobalVariable.createStop}/${GlobalVariable.startLocationStopId}?community=${GlobalVariable.communityIdAdmin}"

//Finalmente modificamos la parada, ahora con la informacion de esta parada debo generar el inicio y el final junto con los waypoints y pasarselo 
//Al put para crear servicio, pero antes quizás deba obtener los datos de la reserva.

//Solicitud para modificar la parada
Response1 = WS.sendRequest(findTestObject('RDD Admin/modify_Stop'))
println(Response1.getResponseText())

// Verificar que la solicitud se haya enviado correctamente
assert Response1.getStatusCode() == 200 : "Failed to send request. Status code: ${Response1.getStatusCode()}"





