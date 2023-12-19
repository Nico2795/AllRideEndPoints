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

//Armamos el cuerpo de la solicitud para reservar asientos

GlobalVariable.seatReservationBody = """
{
    "serviceId": "${GlobalVariable.serviceId}",
    "departureId": "${GlobalVariable.departureId}",
    "stopId": "${GlobalVariable.selectedStopId}",
    "seat": "2"
}
"""


//Enviamos la solicitud para poder realizar la reserva como usuario
Response = WS.sendRequest(findTestObject('Object Repository/Scheduled/Seat_Reservation'))

// Imprimir la respuesta en la consola
println("Response Body: " + Response.getResponseText())

// Verificar que la solicitud se haya enviado correctamente
assert Response.getStatusCode() == 200 : "Failed to send request. Status code: ${Response.getStatusCode()}"
//Obtener la ruta, con las paradas, y asociar la parada de la ruta al cuerpo de la reserva de asientos
//Luego, modificar la fecha de la ruta y verificar que el asiento reservado siga ahi
//Y verificar que cada 4 semanas estan las reservas