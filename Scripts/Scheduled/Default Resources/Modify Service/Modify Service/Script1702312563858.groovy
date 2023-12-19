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

//23 son las 20:00
//Cuerpo para modificar el servicio
GlobalVariable.bodyModifyService = """
{
  "serviceDate": "${GlobalVariable.modifiedServiceDate}",
  "notificationOptions": {
    "notifyByEmail": true,
    "notifyByPush": true
  }
}
"""
//Armamos la url con el id del servicio que queremos modificar
GlobalVariable.urlModifyService = "https://stage.allrideapp.com/api/v1/admin/pb/service/${GlobalVariable.serviceId}?community=653fd601f90509541a748683"

//Pedimos el servicio
Response = WS.sendRequest(findTestObject('Object Repository/Scheduled/Modify_Service'))
println(Response.getResponseText())
// Imprimir toda la respuesta
println(Response.getResponseBodyContent())

// Verificar que la solicitud se haya enviado correctamente
assert Response.getStatusCode() == 200 : "Failed to send request. Status code: ${Response.getStatusCode()}"

//Extraemos el id del nuevo servicio
responseBody = Response.getResponseText()

jsonSlurper = new groovy.json.JsonSlurper()

jsonResponse = jsonSlurper.parseText(responseBody)

modifyServiceId = jsonResponse._id

//Imprimimos para corroborar
println("El id del nuevo servicio es: " + modifyServiceId)

//Lo paso a variable global para poder corroborar que los cambios se realizaron correctamente y aparte para verificar las 4 semanas
GlobalVariable.modifyServiceId = modifyServiceId