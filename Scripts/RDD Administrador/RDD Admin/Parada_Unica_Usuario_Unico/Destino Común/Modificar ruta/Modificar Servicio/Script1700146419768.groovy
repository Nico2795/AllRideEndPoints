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

GlobalVariable.bodyModifyAndCreateRDD =

"""
	{
  "superCommunityId": "$GlobalVariable.superCommunityId",
  "adminId": "$GlobalVariable.superAdminId",
  "adjustmentFactor": 1,
  "adminName": "Soporte AllRide",
  "apportionByParams": [],
  "arrivalDate": "${GlobalVariable.modifiedArrivalDate}",
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
  "estimatedArrival": "${GlobalVariable.modifiedArrivalDate}",
  "estimatedDistance": $GlobalVariable.estimatedDistance,
  "extraMinutes": 0,
  "hourIsDepartureOrArrival": "arrival",
  "linkedDeparture": null,
  "name": "Prueba modificacion y Waypoints",
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
      "userId": "$GlobalVariable.especificUserId1",
      "stopId": "$GlobalVariable.startLocationStopId",
      "placeId": null,
      "order": 0,
      "estimatedArrival": "${GlobalVariable.r_modifiedEstimatedArrival}"
    }
  ],
  "roundedDistance": $GlobalVariable.roundedDistance,
  "serviceCost": null,
  "serviceDate": "${GlobalVariable.modifiedServiceDate}",
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
println "El cuerpo modificado  es: ${GlobalVariable.bodyModifyAndCreateRDD}"

//Creamos la URL para poder modificar y crear el servicio
GlobalVariable.urlModifyAndCreateRDD = "https://stage.allrideapp.com/api/v1/admin/pb/odd/${GlobalVariable.reservationId}?community=${GlobalVariable.communityIdAdmin}"

//Enviamos la solicitud PUT para poder crear el servicio modificado
Response1 = WS.sendRequest(findTestObject('RDD Admin/modificar_Servicio'))
println(Response1.getResponseText())