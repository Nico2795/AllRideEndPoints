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

// Armamos la URL para obtener los servicios por día con el ID de la ruta
GlobalVariable.urlGetServicesList = "https://stage.allrideapp.com/api/v1/admin/pb/services/day?community=653fd601f90509541a748683&startDate=${GlobalVariable.startDate}&endDate=${GlobalVariable.endDate}"

response = WS.sendRequest(findTestObject('Object Repository/Scheduled/Get_Services_Per_Day'))

// Parsear el JSON
def responseBody = response.getResponseText()
def jsonSlurper = new groovy.json.JsonSlurper()
def jsonResponse = jsonSlurper.parseText(responseBody)

println(jsonResponse)

// ID del servicio que estás buscando
def targetId = "${GlobalVariable.serviceId}"

// Función para buscar el departureId dado el _id
def getDepartureIdById(targetId, data) {
    if (!data) {
        return null
    }

    // Buscar en la estructura utilizando findAll
    def matches = data.findAll { it._id == targetId || it.id == targetId }

    // Si se encontraron coincidencias, devolver el departureId de la primera
    if (matches) {
        return matches[0]?.resources.departure.departureId
    }

    // Si no se encuentra, devolver null
    return null
}

// Uso de la función
def departureId = getDepartureIdById(targetId, jsonResponse)

// Imprimir el resultado
println("DepartureId: $departureId")

// Convertimos a variable global para utilizarlo en la reserva de asientos
GlobalVariable.departureId = departureId ? departureId[0] : null

//este si funciona, si la fecha es 05 y 07, busca la fecha del 06 por alguna razon
