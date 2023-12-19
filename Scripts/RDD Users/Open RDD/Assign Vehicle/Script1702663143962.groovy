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

GlobalVariable.bodyAssignVehicle = """
{
  "vehicleId": "6553b81315e27f4db2b45935"
}

"""


//Se arman las url para poder asignar los recursos al servicio

// Se envían las solicitudes para asignar recursos
Response = WS.sendRequest(findTestObject('Object Repository/RDD/assignVehicle'))

// Verificar el código de estado HTTP para la primera solicitud
def statusCodeAssignVehicle = Response.getStatusCode()
if (statusCodeAssignVehicle == 200) {
	println("La asignación del vehiculo tuvo éxito. Código de estado: ${statusCodeAssignVehicle}")
} else {
	TestCase.fail("La asignación del vehiculo falló. Código de estado: ${statusCodeAssignVehicle}")
}
