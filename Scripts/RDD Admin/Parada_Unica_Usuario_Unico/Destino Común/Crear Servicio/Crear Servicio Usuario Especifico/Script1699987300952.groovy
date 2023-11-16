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
import java.time.LocalDateTime

//Numero de prueba

//def numeroPrueba = 1

//Obtener fecha y hora actual
def dt = LocalDateTime.now()
println dt

//Creamos el body para la solicitud POST y crear servicio.
GlobalVariable.oddAdminRequest = """
	{
  "superCommunityId": "$GlobalVariable.superCommunityId",
  "adminId": "$GlobalVariable.superAdminId",
  "adjustmentFactor": 1,
  "adminName": "Soporte AllRide",
  "apportionByParams": [],
  "arrivalDate": "2023-11-17T01:00:00.000Z",
  "comments": "",
  "communityId": "",
  "direction": "in",
  "endLocation": {
    "lat": $GlobalVariable.endLocationLat,
    "lon": $GlobalVariable.endLocationLon,
    "loc": [$GlobalVariable.endLocationLon, $GlobalVariable.endLocationLat],
    "placeId": $GlobalVariable.endLocationPlaceId,
    "referencePoint": true,
    "stopId": "$GlobalVariable.endLocationStopId"
  },
  "lat": $GlobalVariable.endLocationLat,
  "loc": [$GlobalVariable.endLocationLon, $GlobalVariable.endLocationLat],
  "lon": $GlobalVariable.endLocationLon,
  "placeId": "$GlobalVariable.endLocationPlaceId",
  "estimatedArrival": "2023-11-17T01:00:00.000Z",
  "estimatedDistance": $GlobalVariable.estimatedDistance,
  "extraMinutes": 0,
  "hourIsDepartureOrArrival": "arrival",
  "linkedDeparture": null,
  "name": "Prueba Asignacion Conductor2.2",
  "oddType": "$GlobalVariable.serviceName",
  "originalEstimatedArrival": "",
  "originalServiceDate": "",
  "originalTravelTime": $GlobalVariable.travelTime,
  "placeLat": $GlobalVariable.endLocationLat,
  "placeLon": $GlobalVariable.endLocationLon,
  "placeLongName": "$GlobalVariable.endLocationName",
  "placeName": "$GlobalVariable.endLocationName",
  "placeWaitTime": 0,
  "reason": "",
  "reservations": [
    {
      "userId": "$GlobalVariable.especificUserId",
      "stopId": "$GlobalVariable.startLocationStopId",
      "placeId": null,
      "order": 0,
      "estimatedArrival": "2023-11-17T14:45:57.000Z"
    }
  ],
  "roundedDistance": $GlobalVariable.roundedDistance,
  "serviceCost": null,
  "serviceDate": "2023-11-17T00:25:29.000Z",
  "serviceHour": "",
  "startLocation": {
    "lat": $GlobalVariable.startLocationLat,
    "lon": $GlobalVariable.startLocationLon,
    "loc": [$GlobalVariable.startLocationLon, $GlobalVariable.startLocationLat],
    "placeId": null,
    "stopId": "$GlobalVariable.startLocationStopId"
  },
  "lat": $GlobalVariable.startLocationLat,
  "loc": [$GlobalVariable.startLocationLon, $GlobalVariable.startLocationLat],
  "lon": $GlobalVariable.startLocationLon,
  "placeId": null,
  "stopId": "$GlobalVariable.startLocationStopId",
  "state": "$GlobalVariable.assignState", 
  "stopId": "$GlobalVariable.endLocationStopId",
  "totalReservations": 1,
  "travelTime": $GlobalVariable.travelTime,
  "waypoints": $GlobalVariable.waypoints
}

"""
//Imprimimos para corroborar
println(GlobalVariable.oddAdminRequest)

//Pedimos el servicio
Response1 = WS.sendRequest(findTestObject('RDD Admin/Pedir_ODD_Admin'))
println(Response1.getResponseText())

// Verificar que la solicitud se haya enviado correctamente
assert Response1.getStatusCode() == 200 : "Failed to send request. Status code: ${Response1.getStatusCode()}"


//Obtener id de esta respuesta para poder generar la URL de cancelación.

//Extraemos el id de la nueva reserva
responseBody = Response1.getResponseText()

jsonSlurper = new groovy.json.JsonSlurper()

jsonResponse = jsonSlurper.parseText(responseBody)

reservationId = jsonResponse._id

//Imprimimos para corroborar
println("El id de la reserva es: " + reservationId)


//Creamos una URL con el id de la reserva para asignar conductor
GlobalVariable.assignDriverUrl = "https://stage.allrideapp.com/api/v1/admin/pb/odd/assignDriver/${reservationId}?community=${GlobalVariable.communityIdAdmin}"

//Creamos una URL con el id de la reserva para asignar el vehiculo
GlobalVariable.assignVehicleUrl = "https://stage.allrideapp.com/api/v1/admin/pb/odd/assignVehicle/${reservationId}?community=${GlobalVariable.communityIdAdmin}"

//Creamos una URL con el id de la reserva recien creada para poder cancelarla desde el administrador
GlobalVariable.cancelUrlAdmin = "https://stage.allrideapp.com/api/v1/admin/pb/odd/cancel/${reservationId}?community=${GlobalVariable.communityIdAdmin}"

//Imprimimos la url para corroborar
println("La Url para cancelar es: " + GlobalVariable.cancelUrlAdmin)
println("La Url para asignar conductor es: " + GlobalVariable.assignDriverUrl)
println("La Url para asignar vehiculo es: " + GlobalVariable.assignVehicleUrl)

//Se le suma 1 al numero prueba para que vaya cambiando automaticamente

//numeroPrueba = numeroPrueba +1

//Añadimos un delay para poder confirmar la reservación desde la página del administrador
WebUI.delay(20)
//Se continua con la cancelación del servicio en otro test case -->


//Crear un estimated arrival con la api de google para que sea exacto