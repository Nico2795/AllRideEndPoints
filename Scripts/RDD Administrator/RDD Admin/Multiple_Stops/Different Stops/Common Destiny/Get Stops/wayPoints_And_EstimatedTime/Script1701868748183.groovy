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

import groovy.json.JsonSlurper
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestDataFactory
import java.time.LocalDateTime

def dt = LocalDateTime.now()
println dt

// Obtener la lista de paradas
def response = WS.sendRequest(findTestObject('RDD Admin/get_StopList'))

// Parsear la respuesta JSON
def jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseText())

// Obtener la lista de paradas desde la respuesta
def listaDeParadas = jsonResponse

// Función para obtener una parada aleatoria que no contiene "Dirección destino"
def obtenerParadaAleatoria(listaParadas, paradasSeleccionadas) {
	def paradaAleatoria
	while (true) {
		paradaAleatoria = listaParadas[(int) (Math.random() * listaParadas.size())]
		if (!paradasSeleccionadas.contains(paradaAleatoria) && !paradaAleatoria.name.contains("Dirección destino")) {
			break
		}
	}

	return paradaAleatoria
}

// Elegir varias paradas aleatorias para location1, location2 y location3
def numberOfLocations = 3
def locations = []
def selectedLocations = []

for (int i = 0; i < numberOfLocations; i++) {
	def randomLocation = obtenerParadaAleatoria(listaDeParadas, selectedLocations)
	locations.add(randomLocation)
	selectedLocations.add(randomLocation)
}

// Imprimir las coordenadas de todas las ubicaciones
println("Locations:")
locations.eachWithIndex { location, index ->
	println("Location ${index + 1}:")
	println("Nombre: ${location.name}")
	println("Latitud: ${location.lat}")
	println("Longitud: ${location.lon}")
	println("Stop ID: ${location._id}")
	println("Place ID: ${location.placeId}")
	println("Loc: ${location.loc}")
	println("---")
}

// Construir la URL para la solicitud de Google Directions API
def origin = "${locations[0].lat},${locations[0].lon}"
def destination = "${locations[2].lat},${locations[2].lon}"
def apiUrl = "${GlobalVariable.googleDirectionsApiUrl}?origin=${origin}&destination=${destination}&key=${GlobalVariable.googleMapsApiKey}"

// Enviar la solicitud a Google Directions API
def directionsResponse = new JsonSlurper().parseText(new URL(apiUrl).text)

// Extraer la información necesaria (Waypoints, tiempo, distancia)
def route = directionsResponse.routes[0].legs[0]
def waypoints = route.steps.collect { [it.end_location.lng, it.end_location.lat] }
def estimatedDistance = route.distance.value  // Distancia en metros
def estimatedDuration = route.duration.value  // Duración en segundos

// Convertir la duración a minutos
def estimatedDurationMinutes = (estimatedDuration / 60).toInteger()

// Calcular la hora estimada de llegada
def estimatedArrivalTime = LocalDateTime.now().plusMinutes(estimatedDurationMinutes)

// Convertir la hora estimada de llegada a un formato adecuado para la solicitud POST
def formattedArrivalTime = estimatedArrivalTime.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
// Extraer la distancia estimada como número (sin unidades)
def roundedDistance = String.format("%.2f", estimatedDistance / 1000).replace(',', '.').toDouble()

// Imprimir los resultados en el log de Katalon Studio
WebUI.comment("Waypoints: ${waypoints}")
WebUI.comment("Estimated Distance: ${estimatedDistance} meters")
WebUI.comment("Rounded Distance: ${roundedDistance} km")
WebUI.comment("Estimated Duration: ${estimatedDurationMinutes} mins")
WebUI.comment("Estimated Arrival Time: ${estimatedArrivalTime}")

// Se guardan en variables globales
locations.eachWithIndex { location, index ->
	GlobalVariable."locationName_${index + 1}" = location.name
	GlobalVariable."locationLat_${index + 1}" = location.lat
	GlobalVariable."locationLon_${index + 1}" = location.lon
	GlobalVariable."locationStopId_${index + 1}" = location._id
	GlobalVariable."locationPlaceId_${index + 1}" = location.placeId
	GlobalVariable."locationLoc_${index + 1}" = location.loc
}

GlobalVariable.waypoints = waypoints
GlobalVariable.estimatedDistance = estimatedDistance
GlobalVariable.travelTime = estimatedDurationMinutes
GlobalVariable.roundedDistance = roundedDistance
GlobalVariable.arrivalTime = formattedArrivalTime

// Obtener las coordenadas de startLocation y endLocation
def startLocationCoords = [GlobalVariable.startLocationLon, GlobalVariable.startLocationLat]
def endLocationCoords = [GlobalVariable.endLocationLon, GlobalVariable.endLocationLat]

// Agregar las coordenadas de startLocation y endLocation a la lista de waypoints
waypoints.add(0, startLocationCoords)
waypoints.add(endLocationCoords)

// Imprimir las coordenadas de startLocation y endLocation
println("\nStart Location Coordinates:")
println("Lon: ${startLocationCoords[0]}, Lat: ${startLocationCoords[1]}")
println("\nEnd Location Coordinates:")
println("Lon: ${endLocationCoords[0]}, Lat: ${endLocationCoords[1]}")

