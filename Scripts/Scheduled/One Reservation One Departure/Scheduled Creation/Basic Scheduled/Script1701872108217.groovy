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

GlobalVariable.bodyRoute = """
{
   "name":"Prueba Flujo Conductor ${GlobalVariable.todayDate}", 
   "description":"Prueba aceptacion chofer y mantencion de RyR",
   "communities":[${GlobalVariable.communityId}],
   "superCommunities":["653fd68233d83952fafcd4be"],
   "ownerIds":[
      {
         "id":"653fd601f90509541a748683",
         "role":"community"
      }
   ],
   "shapeId":"${GlobalVariable.shapeId}",
   "usesBusCode":false,
   "usesVehicleList":true,
   "usesDriverCode":false,
   "allowsOnlyExistingDrivers":false,
   "allowsMultipleDrivers":false,
   "dynamicSeatAssignment":false,
   "usesTickets":false,
   "startsOnStop":false,
   "notNearStop":false,
   "routeCost":0,
   "ticketCost":0,
   "excludePassengers":{
      "active":false,
      "excludeType":"dontHide"
   },
   "restrictPassengers":{
      "enabled":false,
      "allowed":[]
   },
   "endDepartureNotice":{
      "enabled":false,
      "lastStop":null
   },
   "scheduling":{
      "enabled":true,
      "limitUnit":"minutes",
      "limitAmount":30,
      "lateNotification":{
         "enabled":false,
         "amount":0,
         "unit":"minutes"
      },
      "stopNotification":{
         "enabled":false,
         "amount":0,
         "unit":"minutes"
      },
      "startLimit":{
         "upperLimit":{
            "amount":60,
            "unit":"minutes"
         },
         "lowerLimit":{
            "amount":30,
            "unit":"minutes"
         }
      },
      "schedule":[
         {
            "enabled":true,
            "day":"${GlobalVariable.scheduleDay}",
            "time":"21:00",
            "estimatedArrival":null,
            "stopSchedule":[],
            "capped":{
               "enabled":false,
               "capacity":0
            },
            "vehicleCategoryId":null,
            "defaultResources":[]
         }
      ],
      "stopOnReservation":false,
      "restrictions":{
         "customParams":{
            "enabled":false,
            "params":[]
         }
      }
   },
   "customParams":{
      "enabled":false,
      "params":[]
   },
   "customParamsAtTheEnd":{
      "enabled":false,
      "params":[]
   },
   "validationParams":{
      "enabled":false,
      "driverParams":[],
      "passengerParams":[]
   },
   "allowsServiceSnapshots":false,
   "allowsNonServiceSnapshots":false,
   "labels":[],
   "roundOrder":[],
   "anchorStops":[],
   "originStop":"655d11d88a5a1a1ff0328466",
   "destinationStop":"655d11d88a5a1a1ff0328464",
   "hasBeacons":false,
   "hasCapacity":false,
   "isStatic":false,
   "showParable":false,
   "extraInfo":"",
   "color":"b24646",
   "usesManualSeat":true,
   "allowsManualValidation":false,
   "usesDriverPin":false,
   "hasBoardings":false,
   "hasUnboardings":false,
   "allowsDistance":false,
   "allowGenericVehicles":false,
   "hasExternalGPS":false,
   "departureHourFulfillment":{
      "enabled":false,
      "ranges":[]
   },
   "canReserve":true,
   "visible":true,
   "active":true,
   "usesOfflineCount":false,
   "usesTextToSpeech":false,
   "hasBoardingCount":false,
   "hasRounds":false,
   "hasUnboardingCount":false,
   "timeOnRoute":${GlobalVariable.timeOnRoute},
   "distance":${GlobalVariable.distance},
   "distanceInMeters":${GlobalVariable.distanceInMeters},
   "route_type":3
}


 """
//Imprimimos para corroborar
 println(GlobalVariable.bodyRoute)
 
 
 //Pedimos el servicio
 Response = WS.sendRequest(findTestObject('Object Repository/Scheduled/Create Scheduled'))
 println(Response.getResponseText())
 // Imprimir toda la respuesta
 println(Response.getResponseBodyContent())
 
 // Verificar que la solicitud se haya enviado correctamente
 assert Response.getStatusCode() == 200 : "Failed to send request. Status code: ${Response.getStatusCode()}"
 
 
 //Obtener id de esta respuesta para poder generar la URL de cancelación.
 
 //Extraemos el id de la nueva ruta calendarizada
 responseBody = Response.getResponseText()
 
 jsonSlurper = new groovy.json.JsonSlurper()
 
 jsonResponse = jsonSlurper.parseText(responseBody)
 
 routeId = jsonResponse._id
 
 //Imprimimos para corroborar
 println("El id de la reserva es: " + routeId)
 
 //Convertimos a variable global para utilizarlo más adelante
 
 GlobalVariable.routeId = routeId
 
 WebUI.delay(10)
 