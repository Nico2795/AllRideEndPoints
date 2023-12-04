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

import java.text.SimpleDateFormat
import java.net.URLEncoder


// Armamos la URL para obtener los servicios por día con el ID de la ruta
GlobalVariable.urlGetServicesList = "https://stage.allrideapp.com/api/v1/admin/pb/services/day?community=653fd601f90509541a748683&startDate=${GlobalVariable.startDate}&endDate=${GlobalVariable.endDate4weeks}&routeId=${GlobalVariable.routeId}"

// Imprimimos el URL final
println("URL final: " + GlobalVariable.urlGetServicesList)

// Enviamos la solicitud para obtener servicios por día
def request = findTestObject('Object Repository/Calendarizada/Obtener_Servicios_Por_Dia')
def Response = WS.sendRequest(request)

// Verificamos que la solicitud se haya enviado correctamente
assert Response.getStatusCode() == 200 : "Failed to send request. Status code: ${Response.getStatusCode()}"

// Extraemos el ID del servicio de la respuesta JSON
def responseBody = Response.getResponseText()
def jsonResponse = new groovy.json.JsonSlurper().parseText(responseBody)

println(jsonResponse)

// Aseguramos que la respuesta contenga la propiedad "routeId"
assert jsonResponse.routeId : "La respuesta no contiene la propiedad 'routeId'"

// ID de la ruta que tienes
def tuRutaId = "6569e8c4d80bd53101e73a8d"

// Verificamos que el routeId se encuentre exactamente 4 veces en la respuesta
def countRouteId = jsonResponse.count { it.routeId?._id == tuRutaId }
assert countRouteId == 4 : "El routeId no se encuentra 4 veces en la respuesta."

// Imprimir para corroborar
println("El routeId se encontró exactamente 4 veces en la respuesta.")


