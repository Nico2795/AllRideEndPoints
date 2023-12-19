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

//Creamos el cuerpo con una dirección fija con una sola ruta por el momento Alfredo Silva Carvallo
GlobalVariable.bodyOrderStops = """
{
  "routeId": "${GlobalVariable.routeId}",
  "stopId": "655768457c8bfa7bb15efb6e",
  "stop_sequence": 1,
  "cost": 0,
  "sequence": "1",
  "ownerIds": [
    {
      "id": "653fd601f90509541a748683",
      "role": "community"
    }
  ],
  "communities": ["653fd601f90509541a748683"],
  "superCommunities": ["653fd68233d83952fafcd4be"]
}

"""
//LLamamos a la solicutud para crear el orden de las paradas
Response = WS.sendRequest(findTestObject('Scheduled/create_Stops_Order'))

// Verificar que la solicitud se haya enviado correctamente
assert Response.getStatusCode() == 200 : "Failed to send request. Status code: ${Response.getStatusCode()}"

//Extraemos el id de 
responseBody = Response.getResponseText()

jsonSlurper = new groovy.json.JsonSlurper()

jsonResponse = jsonSlurper.parseText(responseBody)

orderRouteId = jsonResponse._id

//Imprimimos para corroborar
println("El id de la reserva es: " + orderRouteId)

//Convertimos a variable global
GlobalVariable.orderRouteId = orderRouteId

//El mismo stopId que se utiliza aqui debe utilizarse almomento de poder realizar la reserv

