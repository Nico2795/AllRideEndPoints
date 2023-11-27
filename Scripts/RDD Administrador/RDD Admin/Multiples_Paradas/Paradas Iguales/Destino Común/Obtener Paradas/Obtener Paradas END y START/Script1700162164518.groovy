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
def response = WS.sendRequest(findTestObject('RDD Admin/obtener_ListaParadas'))

// Parseamos la respuesta JSON
def jsonSlurper = new groovy.json.JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseText())

// Obtenemos la lista de paradas desde la respuesta
def listaDeParadas = jsonResponse

// Función para obtener una parada aleatoria
def obtenerParadaAleatoria(listaParadas) {
	return listaParadas[(int) (Math.random() * listaParadas.size())]
}

// Elige una parada aleatoria para startlocation
def startLocation = obtenerParadaAleatoria(listaDeParadas)

// Elige una parada aleatoria para endlocation asegurándote de que no sea igual a startlocation
def endLocation = obtenerParadaAleatoria(listaDeParadas)
while (endLocation.lat == startLocation.lat && endLocation.lon == startLocation.lon) {
	endLocation = obtenerParadaAleatoria(listaDeParadas)
}


// Imprime las coordenadas de startlocation
println("Start Location:")
println("Nombre: ${startLocation.name}")
println("Latitud: ${startLocation.lat}")
println("Longitud: ${startLocation.lon}")
println("Stop ID: ${startLocation._id}")
println("Place ID: ${startLocation.placeId}")
println("Loc: ${startLocation.loc}")

// Imprime las coordenadas de endlocation
println("\nEnd Location:")
println("Nombre: ${endLocation.name}")
println("Latitud: ${endLocation.lat}")
println("Longitud: ${endLocation.lon}")
println("Stop ID: ${endLocation._id}")
println("Place ID: ${endLocation.placeId}")
println("Loc: ${endLocation.loc}")

// Definir las variables globales para startlocation
GlobalVariable.startLocationName = startLocation.name
GlobalVariable.startLocationLat = startLocation.lat
GlobalVariable.startLocationLon = startLocation.lon
GlobalVariable.startLocationStopId = startLocation._id
GlobalVariable.startLocationPlaceId = startLocation.placeId
GlobalVariable.startLocationLoc = startLocation.loc

// Definir las variables globales para endlocation
GlobalVariable.endLocationName = endLocation.name
GlobalVariable.endLocationLat = endLocation.lat
GlobalVariable.endLocationLon = endLocation.lon
GlobalVariable.endLocationStopId = endLocation._id
GlobalVariable.endLocationPlaceId = endLocation.placeId
GlobalVariable.endLocationLoc = endLocation.loc

// Imprimir variables globales para corroborar
println("\nGlobal Variables:")
println("startLocationName: " + GlobalVariable.startLocationName)
println("startLocationLat: " + GlobalVariable.startLocationLat)
println("startLocationLon: " + GlobalVariable.startLocationLon)
println("startLocationStopId: " + GlobalVariable.startLocationStopId)
println("startLocationPlaceId: " + GlobalVariable.startLocationPlaceId)
println("startLocationLoc: " + GlobalVariable.startLocationLoc)
println("endLocationName: " + GlobalVariable.endLocationName)
println("endLocationLat: " + GlobalVariable.endLocationLat)
println("endLocationLon: " + GlobalVariable.endLocationLon)
println("endLocationStopId: " + GlobalVariable.endLocationStopId)
println("endLocationPlaceId: " + GlobalVariable.endLocationPlaceId)
println("endLocationLoc: " + GlobalVariable.endLocationLoc)
