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


//Obtenemos la lista de paradas
def response = WS.sendRequest(findTestObject('RDD Admin/get_StopList'))

// Parseamos la respuesta JSON
def jsonSlurper = new groovy.json.JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseText())

// Obtenemos la lista de paradas desde la respuesta
def listaDeParadas = jsonResponse

// Función para obtener una parada aleatoria
def obtenerParadaAleatoria(listaParadas) {
    return listaParadas[(int) (Math.random() * listaParadas.size())]
}

// Elige una parada aleatoria
def paradaAleatoria = obtenerParadaAleatoria(listaDeParadas)

// Imprime las coordenadas de la parada aleatoria
println("Parada Aleatoria:")
println("Nombre: ${paradaAleatoria.name}")
println("Latitud: ${paradaAleatoria.lat}")
println("Longitud: ${paradaAleatoria.lon}")
println("Stop ID: ${paradaAleatoria._id}")
println("Place ID: ${paradaAleatoria.placeId}")

//Definir las variables globales de lat,lon,loc (START LOCATION)

GlobalVariable.startLocationName = paradaAleatoria.name
GlobalVariable.startLocationLat = paradaAleatoria.lat
GlobalVariable.startLocationLon = paradaAleatoria.lon
GlobalVariable.startLocationStopId = paradaAleatoria._id
GlobalVariable.startLocationPlaceId = paradaAleatoria.placeId
GlobalVariable.startLocationLoc = paradaAleatoria.loc

//Imprimir variables globales para corroborar

println("startLocationName: " + GlobalVariable.startLocationName)
println("startLocationLat: " + GlobalVariable.startLocationLat)
println("startLocationLon: " + GlobalVariable.startLocationLon)
println("startLocationStopId: " + GlobalVariable.startLocationStopId)
println("startLocationPlaceId: " + GlobalVariable.startLocationPlaceId)


