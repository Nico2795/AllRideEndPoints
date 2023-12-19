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


// Obtener los usuarios de la comunidad
def response = WS.sendRequest(findTestObject('RDD Admin/Get_Users_Community'))

// Si la respuesta es exitosa (código 200)
if (response.getStatusCode() == 200) {
    // Convierte la respuesta JSON a un objeto Groovy
    def jsonResponse = new groovy.json.JsonSlurper().parseText(response.getResponseText())

    // Lista de nombres de usuarios que estamos buscando
    def userNamesToFind = ["Nico End", "Naru To", "Pedro Pascal"]

    // Buscar cada usuario por nombre
    userNamesToFind.each { userName ->
        def foundUser = jsonResponse.find { it.name == userName }

        if (foundUser) {
            // Imprimir información del usuario encontrado
            println "Usuario encontrado: ${foundUser.name}, ID: ${foundUser._id}"

            // Guardar el ID del usuario encontrado en la variable global específica
            switch (userName) {
                case "Nico End":
                    GlobalVariable.especificUserId1 = foundUser._id
                    println "Se ha guardado el ID del usuario encontrado en GlobalVariable.especificUserId11"
                    break
                case "Naru To":
                    GlobalVariable.especificUserId2 = foundUser._id
                    println "Se ha guardado el ID del usuario encontrado en GlobalVariable.especificUserId12"
                    break
                case "Pedro Pascal":
                    GlobalVariable.especificUserId3 = foundUser._id
                    println "Se ha guardado el ID del usuario encontrado en GlobalVariable.especificUserId13"
                    break
                // Puedes agregar más casos según sea necesario
            }
        } else {
            // Si el usuario no se encuentra, imprimir un mensaje
            println "No se encontró un usuario con el nombre: $userName"
        }
    }
} else {
    // Si la solicitud no fue exitosa, imprimir el código de respuesta
    println "La solicitud no fue exitosa. Código de respuesta: ${response.getStatusCode()}"
}
