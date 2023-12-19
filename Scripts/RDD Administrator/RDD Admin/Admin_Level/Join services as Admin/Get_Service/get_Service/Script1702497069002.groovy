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

//Obtenemos la comunidad
Response = WS.sendRequest(findTestObject('RDD Admin/get_Community_ID'))

//Obtenemos el contenido del Json en formato String
jsonString = Response.getResponseText()

//Parseamos el JSON
jsonSlurper = new groovy.json.JsonSlurper()
jsonData = jsonSlurper.parseText(jsonString)

//Obtener nombre del servicio correspondiente a la comunidad
serviceName = jsonData.custom.realTimeTransportSystem.buses.oDDServices[0].name

//Imprimimos el nombre del servicio para corroborar
println("El nombre del servicio es: " + serviceName)

//Almacenamos en una variable Global
GlobalVariable.serviceName = serviceName

//Ver más tipos de servicio