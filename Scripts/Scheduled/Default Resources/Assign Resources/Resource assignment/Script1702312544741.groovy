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
//Armamos el cuerpo de la solicitud
GlobalVariable.bodyAssignResources = """
[
  {
    "multipleDrivers": false,
    "driver": {
      "driverId": "${GlobalVariable.driverId}"
    },
    "drivers": [],
    "vehicle": {
      "vehicleId": "${GlobalVariable.vehicleId}",
      "capacity": ${GlobalVariable.vehicleCapacity}
    },
    "passengers": [],
    "departure": null
  }
]
"""
//Armamos la url de la solicitud

GlobalVariable.assignResources = "https://stage.allrideapp.com/api/v1/admin/pb/assignServiceResources/${GlobalVariable.serviceId}?community=${GlobalVariable.communityIdAdmin}"

//Enviamos la solicitud HTTP
Response = WS.sendRequest(findTestObject('Object Repository/Scheduled/Assign Vehicle and driver'))

assert Response.getStatusCode() == 200 : "Failed to send request. Status code: ${Response.getStatusCode()}"

def responseBody = Response.getResponseText()

println(responseBody)
