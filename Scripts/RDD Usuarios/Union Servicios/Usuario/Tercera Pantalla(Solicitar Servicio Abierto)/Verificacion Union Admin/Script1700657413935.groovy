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

// Verificamos que la reserva se haya hecho correctamente
Response1 = WS.sendRequest(findTestObject('RDD Admin/Obtener_ListaReservas'))

responseBody1 = Response1.getResponseText()
jsonSlurper1 = new groovy.json.JsonSlurper()
jsonResponse1 = jsonSlurper1.parseText(responseBody1)

if (jsonResponse1.reservations.empty) {
    TestCase.fail("No se encontraron reservas")
}

// Extraer la reserva con el ID obtenido anteriormente
def createdReservation = jsonResponse1.reservations.find { it._id == GlobalVariable.reservationId }

// Verificar que la reserva contiene ambos usuarios
if (!createdReservation) {
    throw new AssertionError("No se encontró la reserva con el ID proporcionado.")
}

def createdUserIds = createdReservation.reservations.collect { it.userId._id }
def expectedUserIds = ["6540083633d83952fafcfe46", "655dee4e479c215a1b6db7a4"]

if (!(createdUserIds.containsAll(expectedUserIds)) || createdUserIds.size() != expectedUserIds.size()) {
    throw new AssertionError("La reserva creada no contiene a ambos usuarios.")
}
