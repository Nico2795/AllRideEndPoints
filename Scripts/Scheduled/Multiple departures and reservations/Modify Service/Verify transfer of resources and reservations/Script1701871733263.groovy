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

//Para poder hacer esto, voy a necesitar comparar los id de la reserva con los datos

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

// Establecer la URL para obtener servicios
GlobalVariable.urlGetServicesList = "https://stage.allrideapp.com/api/v1/admin/pb/services/day?community=653fd601f90509541a748683&startDate=${GlobalVariable.startDate}&endDate=${GlobalVariable.endDate}"

// Enviar la solicitud para obtener servicios
def response = WS.sendRequest(findTestObject('Object Repository/Scheduled/Get_Services_Per_Day'))

// Parsear el JSON de la respuesta
def responseBody = response.getResponseText()
def jsonSlurper = new groovy.json.JsonSlurper()
def jsonResponse = jsonSlurper.parseText(responseBody)

println(jsonResponse)

// ID del servicio que estás buscando
def targetId = GlobalVariable.modifyServiceId

// Función para buscar el departureId dado el _id
def getReservationsIdById(targetId, data) {
	if (!data) {
		return null
	}

	// Buscar en la estructura utilizando findAll
	def matches = data.findAll { it._id == targetId || it.id == targetId }

	// Si se encontraron coincidencias, devolver el departureId de la primera
	if (matches) {
		return matches[0]?.reservations._id
	}

	// Si no se encuentra, devolver null
	return null
}

// Uso de la función
def reservationsID = getReservationsIdById(targetId, jsonResponse)

// Función para buscar el userId dado el _id
def getReservationsUserIdById(targetId, data) {
	if (!data) {
		return null
	}

	// Buscar en la estructura utilizando findAll
	def matches = data.findAll { it._id == targetId || it.id == targetId }

	// Si se encontraron coincidencias, devolver el departureId de la primera
	if (matches) {
		return matches[0]?.reservations.userId
	}

	// Si no se encuentra, devolver null
	return null
}

// Uso de la función
def reservationsUserID = getReservationsUserIdById(targetId, jsonResponse)

// Función para buscar el seat dado el _id
def getReservationsSeatIdById(targetId, data) {
	if (!data) {
		return null
	}

	// Buscar en la estructura utilizando findAll
	def matches = data.findAll { it._id == targetId || it.id == targetId }

	// Si se encontraron coincidencias, devolver el departureId de la primera
	if (matches) {
		return matches[0]?.reservations.seat
	}

	// Si no se encuentra, devolver null
	return null
}

// Uso de la función
def reservationsSeat = getReservationsSeatIdById(targetId, jsonResponse)

// Función para buscar el departureId dado el _id
def getReservationsDepartureIdById(targetId, data) {
	if (!data) {
		return null
	}

	// Buscar en la estructura utilizando findAll
	def matches = data.findAll { it._id == targetId || it.id == targetId }

	// Si se encontraron coincidencias, devolver el departureId de la primera
	if (matches) {
		return matches[0]?.reservations.departureId
	}

	// Si no se encuentra, devolver null
	return null
}

// Uso de la función
def reservationsDepartureId = getReservationsDepartureIdById(targetId, jsonResponse)

// Función para buscar el stopId dado el _id
def getReservationsStopIdById(targetId, data) {
	if (!data) {
		return null
	}

	// Buscar en la estructura utilizando findAll
	def matches = data.findAll { it._id == targetId || it.id == targetId }

	// Si se encontraron coincidencias, devolver el departureId de la primera
	if (matches) {
		return matches[0]?.reservations.stopId
	}

	// Si no se encuentra, devolver null
	return null
}

// Uso de la función
def reservationsStopId = getReservationsStopIdById(targetId, jsonResponse)

// Función para buscar el stopId dado el _id
def getResources(targetId, data) {
	if (!data) {
		return null
	}

	// Buscar en la estructura utilizando findAll
	def matches = data.findAll { it._id == targetId || it.id == targetId }

	// Si se encontraron coincidencias, devolver el departureId de la primera
	if (matches) {
		return matches[0]?.resources._id
	}

	// Si no se encuentra, devolver null
	return null
}

// Uso de la función
def serviceResources = getResources(targetId, jsonResponse)

// Imprimir el resultado
println("ReservationId: $reservationsID")
println("UserId: $reservationsUserID")
println("Seat: $reservationsSeat")
println("DepartureId: $reservationsDepartureId")
println("StopId: $reservationsStopId")
println("Resources: $serviceResources")
// ...

// Guardar los datos antes de la modificación en variables globales
GlobalVariable.reservationsAfterModification = [
	userId: reservationsUserID,
	seat: reservationsSeat,
	stopId: reservationsStopId
]

GlobalVariable.resourcesAfterModification = [
	id: serviceResources
]

// ...

//este si funciona, si la fecha es 05 y 07, busca la fecha del 06 por alguna razon
