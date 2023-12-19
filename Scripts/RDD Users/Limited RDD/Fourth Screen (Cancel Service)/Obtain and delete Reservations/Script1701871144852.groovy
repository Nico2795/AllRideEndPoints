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
import groovy.json.JsonSlurper as JsonSlurper

Response = WS.sendRequest(findTestObject('RDD/Get Reservations and Favorite Routes'))

//Extraemos el id de la reserva para poder eliminarla
responseBody = Response.getResponseText()

jsonSlurper = new JsonSlurper()

jsonResponse = jsonSlurper.parseText(responseBody)

reservationId = jsonResponse.reservations[0]._id

//Contruimos la Url para la eliminacion de la reserva
GlobalVariable.deleteReservationUrl = ('https://stage.allrideapp.com/api/v1/pb/user/oddepartures/reservation/' + reservationId)

//Verificamos URL
println('URL delete: ' + GlobalVariable.deleteReservationUrl)

WS.sendRequest(findTestObject('RDD/Delete Reservation'))

