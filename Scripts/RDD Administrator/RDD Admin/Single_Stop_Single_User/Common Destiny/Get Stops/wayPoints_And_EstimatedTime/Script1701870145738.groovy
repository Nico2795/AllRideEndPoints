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

def origin = "${GlobalVariable.startLocationLat},${GlobalVariable.startLocationLon}"
def destination = "${GlobalVariable.endLocationLat},${GlobalVariable.endLocationLon}"

// Construye la URL para la solicitud de Google Directions API
def apiUrl = "${GlobalVariable.googleDirectionsApiUrl}?origin=${origin}&destination=${destination}&key=${GlobalVariable.googleMapsApiKey}"

// Envía la solicitud a Google Directions API
def response = new JsonSlurper().parseText(new URL(apiUrl).text)

// Extrae la información necesaria(Waypoints, tiempo, distancia)
def route = response.routes[0].legs[0]
def waypoints = route.steps.collect { [it.end_location.lng, it.end_location.lat] }
def estimatedDistance = route.distance.value  // Distancia en metros
def estimatedDuration = route.duration.value  // Duración en segundos

// Convierte la duración a minutos
def estimatedDurationMinutes = (estimatedDuration / 60).toInteger()

//Calcular la hora estimada de llegada
def estimatedArrivalTime = dt.plusMinutes(estimatedDurationMinutes)

// Imprimir la hora estimada de llegada en el log
println("Estimated Arrival Time: ${estimatedArrivalTime}")

// Convertir la hora estimada de llegada a un formato adecuado para la solicitud POST
def formattedArrivalTime = estimatedArrivalTime.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
// Extraer la distancia estimada como número (sin unidades)
def roundedDistance = String.format("%.2f", estimatedDistance / 1000).replace(',', '.').toDouble()


// Imprime los resultados en el log de Katalon Studio
WebUI.comment("Waypoints: ${waypoints}")
WebUI.comment("Estimated Distance: ${estimatedDistance} meters")
WebUI.comment("Rounded Distance: ${roundedDistance} km")
WebUI.comment("Estimated Duration: ${estimatedDurationMinutes} mins")

//Se guardan en variables globales

GlobalVariable.waypoints = waypoints
GlobalVariable.estimatedDistance = estimatedDistance
GlobalVariable.travelTime = estimatedDurationMinutes
GlobalVariable.roundedDistance = roundedDistance
GlobalVariable.arrivalTime = formattedArrivalTime

