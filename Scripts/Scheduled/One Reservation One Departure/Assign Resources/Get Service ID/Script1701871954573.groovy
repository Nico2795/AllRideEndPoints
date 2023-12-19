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

// Pedimos los servicios por día
Response = WS.sendRequest(findTestObject('Object Repository/Scheduled/Get_Services_Per_Day'))

// Verificamos que la solicitud se haya enviado correctamente
assert Response.getStatusCode() == 200 : "Failed to send request. Status code: ${Response.getStatusCode()}"

// Extraemos el ID del servicio de la respuesta JSON
responseBody = Response.getResponseText()
jsonSlurper = new groovy.json.JsonSlurper()
jsonResponse = jsonSlurper.parseText(responseBody)

// ...

// Aseguramos que la respuesta contenga la propiedad "routeId"
assert jsonResponse.routeId : "La respuesta no contiene la propiedad 'routeId'"

// ID de la ruta que tienes
def tuRutaId = GlobalVariable.routeId

// Buscamos el servicio que tiene el mismo ID de ruta que el que ya tienes
def servicioEncontrado = jsonResponse.find { it.routeId?._id == tuRutaId }

// Verificamos que hayamos encontrado el servicio
assert servicioEncontrado : "No se encontró el servicio con el ID de ruta: ${tuRutaId}"

// Extraemos el ID del servicio encontrado
def serviceId = servicioEncontrado._id

// Imprimimos para corroborar
println("El ID del servicio asociado a la ruta es: ${serviceId}")

// Convertimos a variable global para utilizarlo más adelante
GlobalVariable.serviceId = serviceId

//Tengo entonces la obtencion de trzado, la creacion del servicio, la obtencion del id del servicio, ahora viene la asignacion de recursos,
//Ahora viene la reserva de asientos y de buses como usuario. 
//luego la verificacion del tiempo
// que se mantenga por 4 semanas, y que la asignacion de los recursos y reservas se mantengan al momento de hacer algun cambio en el servicio como fecha u hora
// Me faltaria lo de arriba y aparte poder asignar de manera automatica la fecha de las solicitudes para no tener falsos negativos