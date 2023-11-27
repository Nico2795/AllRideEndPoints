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


// Realiza la solicitud web y obtén la respuesta
def response = WS.sendRequest(findTestObject('RDD Admin/obtener_ListaSuperAdmin'))

// Verifica si la solicitud fue exitosa (código de respuesta 200)
if (response.getStatusCode() == 200) {
	// Convierte la respuesta JSON a un objeto Groovy
	def jsonResponse = new groovy.json.JsonSlurper().parseText(response.getResponseText())

	// Nombre del Superadmin que estoy buscando
	def targetSuperAdminName = "Nicolás Bustamante"  // Reemplaza con el nombre específico

	// Buscamos el Superadmin por el nombre en la respuesta JSON
	def targetSuperAdmin = jsonResponse.find { it.name == targetSuperAdminName }

	// Verifica si se encontró el Superadmin
	if (targetSuperAdmin) {
		// Obtener el nivel de administrador del Superadmin
		def adminLevel = targetSuperAdmin.adminLevel
		
		// Función para asignar el estado según el nivel de administrador
		def asignarEstadoSegunAdminLevel = { nivel ->
		    if (["1", "2", "3", "13", "21"].contains(nivel.toString())) {
		        return "pendingDriverAssignment"
		    } else {
		        return "pendingAdminApproval"
		    }
		}

		// Llamar a la función y asignar el estado
		def estadoAsignado = asignarEstadoSegunAdminLevel(adminLevel)
		println "El estado asignado es: $estadoAsignado"
		println "El nivel de este admin es: $adminLevel"

		//Convertimos a variable Global para utilizarlo más adelante
		GlobalVariable.superAdminId = targetSuperAdmin._id
		GlobalVariable.assignState = estadoAsignado
		
		println("Ahora la variable global de superadmin es: " + GlobalVariable.superAdminId)
	} else {
		// Si no se encontró, imprime un mensaje indicando que no se encontró el Superadmin
		println "No se encontró el Superadmin con el nombre $targetSuperAdminName en la respuesta."
	}
} else {
	// Si la solicitud no fue exitosa, imprime el código de respuesta
	println "La solicitud no fue exitosa. Código de respuesta: ${response.getStatusCode()}"
}
