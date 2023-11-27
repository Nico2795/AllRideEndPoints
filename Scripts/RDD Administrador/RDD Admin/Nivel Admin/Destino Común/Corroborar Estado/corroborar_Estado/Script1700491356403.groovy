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

// Obtén la lista de reservas
def obtenerListaReservasRequest = '{}'
def obtenerListaReservasResponse = WS.sendRequest(findTestObject('RDD Admin/obtener_ListaReservas'))
                                                  
// Verifica que la solicitud haya sido exitosa (código de estado 200)
assert obtenerListaReservasResponse.getStatusCode() == 200 : "Error al obtener la lista de reservas. Código de estado: ${obtenerListaReservasResponse.getStatusCode()}"

// Analiza la respuesta para obtener la lista de reservas
def listaReservas = new groovy.json.JsonSlurper().parseText(obtenerListaReservasResponse.getResponseText())

// Obtén el estado de la reserva recién creada
def estadoReservaRecienCreada = listaReservas.find { it._id == GlobalVariable.reservationId }?.state

// Verifica que el estado de la reserva recién creada sea "pendingAdminApproval"
assert estadoReservaRecienCreada == "pendingAdminApproval" : "La reserva recién creada no está en el estado esperado. Estado actual: ${estadoReservaRecienCreada}"

// Imprime un mensaje indicando el éxito
println("La reserva con ID ${GlobalVariable.reservationId} está en el estado esperado: ${estadoReservaRecienCreada}")

