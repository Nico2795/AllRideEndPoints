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

// Se genera un script para que se pida un servicio como un administrador que no cuente con el permiso necesario 
//Es decir que aparezca como pendingAdminApproval
//Se aprueba como administrador
//Luego se genera el mismo viaje a la misma hora, pero con un viaje que tenga el permiso necesario para que quede como pendingDriverAssignment
//Se verifica que los dos viajes se hayan unido

//Se realiza la solicitud para poder aceptar el servicio

def Response1 = WS.sendRequest(findTestObject('RDD Admin/Accept approval'))

println(Response1.getResponseText())

// Verificar que la solicitud se haya enviado correctamente
assert Response1.getStatusCode() == 200 : "Failed to send request. Status code: ${Response1.getStatusCode()}"



//Obtengo el admin que no posee el permiso necesario, se genera el servicio y queda pendiente de aprovacion
//Una vez que queda pendiente de aprobacion, se acepta como superAdmin, y se guarda el id de ese servicio
//Una vez guardado el id de ese servicio, se genera la siguiente reserva con un admin que si cuente con el permiso necesario y quede como pendiente de asignacion
// Se comparan ambos ids y debiesen ser los mismos, lo que significa que los viajes se han juntado
