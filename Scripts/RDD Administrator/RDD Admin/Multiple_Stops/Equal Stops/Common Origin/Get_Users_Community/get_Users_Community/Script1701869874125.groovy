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

// Obetnemos los usuarios de la comunidad
def response = WS.sendRequest(findTestObject('RDD Admin/Get_Users_Community'))

// Si la respuesta es exitosa(200)
if (response.getStatusCode() == 200) {
    // Convierte la respuesta JSON a un objeto Groovy
    def jsonResponse = new groovy.json.JsonSlurper().parseText(response.getResponseText())

    // Obtener la lista de usuarios del JSON
    def users = jsonResponse

    // Obtener un ID de usuario aleatorio
    def randomUser = users[(int) (Math.random() * users.size())]
    def randomUserId = randomUser._id

    // Imprimir el ID de usuario aleatorio
    println "ID de usuario aleatorio: $randomUserId"

    // Guardamos el id en la variable global
    GlobalVariable.randomUserId = randomUserId
    println "Se ha guardado el ID de usuario aleatorio en GlobalVariable.randomUserId"
} else {
    // Si la solicitud no fue exitosa, imprime el código de respuesta
    println "La solicitud no fue exitosa. Código de respuesta: ${response.getStatusCode()}"
}


