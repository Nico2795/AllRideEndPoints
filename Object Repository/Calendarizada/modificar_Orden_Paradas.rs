<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>modificar_Orden_Paradas</name>
   <tag></tag>
   <elementGuidId>bae3c747-7e9b-440f-b9fe-9da5c6e2e8e8</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <authorizationRequest>
      <authorizationInfo>
         <entry>
            <key>bearerToken</key>
            <value>${GlobalVariable.TokenAdmin}</value>
         </entry>
      </authorizationInfo>
      <authorizationType>Bearer</authorizationType>
   </authorizationRequest>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;_id\&quot;: \&quot;6564a1f88956023c1b7ee807\&quot;,\n  \&quot;communities\&quot;: [\&quot;653fd601f90509541a748683\&quot;],\n  \&quot;superCommunities\&quot;: [\&quot;653fd68233d83952fafcd4be\&quot;],\n  \&quot;visible\&quot;: true,\n  \&quot;stopId\&quot;: \&quot;653ffde9f90509541a749e18\&quot;,\n  \&quot;routeId\&quot;: {\n    \&quot;_id\&quot;: \&quot;6560f3dada7b3b61a1c044f8\&quot;,\n    \&quot;excludePassengers\&quot;: {\n      \&quot;active\&quot;: false,\n      \&quot;excludeType\&quot;: \&quot;dontHide\&quot;\n    },\n    \&quot;scheduling\&quot;: {\n      \&quot;enabled\&quot;: true,\n      \&quot;limitUnit\&quot;: \&quot;minutes\&quot;,\n      \&quot;limitAmount\&quot;: 30,\n      \&quot;lateNotification\&quot;: {\n        \&quot;enabled\&quot;: false,\n        \&quot;amount\&quot;: 0,\n        \&quot;unit\&quot;: \&quot;minutes\&quot;\n      },\n      \&quot;stopNotification\&quot;: {\n        \&quot;enabled\&quot;: false,\n        \&quot;amount\&quot;: 0,\n        \&quot;unit\&quot;: \&quot;minutes\&quot;\n      },\n      \&quot;startLimit\&quot;: {\n        \&quot;upperLimit\&quot;: {\n          \&quot;amount\&quot;: 60,\n          \&quot;unit\&quot;: \&quot;minutes\&quot;\n        },\n        \&quot;lowerLimit\&quot;: {\n          \&quot;amount\&quot;: 30,\n          \&quot;unit\&quot;: \&quot;minutes\&quot;\n        }\n      },\n      \&quot;schedule\&quot;: [\n        {\n          \&quot;capped\&quot;: {\n            \&quot;enabled\&quot;: false,\n            \&quot;capacity\&quot;: 0\n          },\n          \&quot;enabled\&quot;: true,\n          \&quot;_id\&quot;: \&quot;6560f3dada7b3b61a1c044fd\&quot;,\n          \&quot;day\&quot;: \&quot;mon\&quot;,\n          \&quot;time\&quot;: \&quot;12:00\&quot;,\n          \&quot;estimatedArrival\&quot;: null,\n          \&quot;stopSchedule\&quot;: [],\n          \&quot;vehicleCategoryId\&quot;: null,\n          \&quot;defaultResources\&quot;: []\n        },\n        {\n          \&quot;capped\&quot;: {\n            \&quot;enabled\&quot;: false,\n            \&quot;capacity\&quot;: 0\n          },\n          \&quot;enabled\&quot;: true,\n          \&quot;_id\&quot;: \&quot;6560f3dada7b3b61a1c044fe\&quot;,\n          \&quot;day\&quot;: \&quot;wed\&quot;,\n          \&quot;time\&quot;: \&quot;12:00\&quot;,\n          \&quot;estimatedArrival\&quot;: null,\n          \&quot;stopSchedule\&quot;: [],\n          \&quot;vehicleCategoryId\&quot;: null,\n          \&quot;defaultResources\&quot;: []\n        }\n      ],\n      \&quot;stopOnReservation\&quot;: false,\n      \&quot;restrictions\&quot;: {\n        \&quot;customParams\&quot;: {\n          \&quot;enabled\&quot;: false,\n          \&quot;params\&quot;: []\n        },\n        \&quot;_id\&quot;: \&quot;6560f3dada7b3b61a1c044ff\&quot;\n      }\n    },\n    \&quot;endDepartureNotice\&quot;: {\n      \&quot;enabled\&quot;: false,\n      \&quot;lastStop\&quot;: null\n    },\n    \&quot;restrictPassengers\&quot;: {\n      \&quot;enabled\&quot;: false,\n      \&quot;allowed\&quot;: []\n    },\n    \&quot;snapshots\&quot;: {\n      \&quot;enabled\&quot;: false\n    },\n    \&quot;validationParams\&quot;: {\n      \&quot;driverParams\&quot;: [],\n      \&quot;passengerParams\&quot;: []\n    },\n    \&quot;canResume\&quot;: {\n      \&quot;timeLimit\&quot;: {\n        \&quot;enabled\&quot;: false,\n        \&quot;amount\&quot;: 5,\n        \&quot;unit\&quot;: \&quot;minutes\&quot;\n      },\n      \&quot;enabled\&quot;: false\n    },\n    \&quot;departureHourFulfillment\&quot;: {\n      \&quot;enabled\&quot;: false,\n      \&quot;ranges\&quot;: []\n    },\n    \&quot;assistantIds\&quot;: [],\n    \&quot;superCommunities\&quot;: [\&quot;653fd68233d83952fafcd4be\&quot;],\n    \&quot;communities\&quot;: [\&quot;653fd601f90509541a748683\&quot;],\n    \&quot;active\&quot;: true,\n    \&quot;visible\&quot;: true,\n    \&quot;internal\&quot;: false,\n    \&quot;anchorStops\&quot;: [],\n    \&quot;isStatic\&quot;: false,\n    \&quot;labels\&quot;: [],\n    \&quot;hasExternalGPS\&quot;: false,\n    \&quot;hasCapacity\&quot;: false,\n    \&quot;hasBeacons\&quot;: false,\n    \&quot;hasRounds\&quot;: false,\n    \&quot;hasBoardingCount\&quot;: false,\n    \&quot;hasUnboardingCount\&quot;: false,\n    \&quot;usesBusCode\&quot;: false,\n    \&quot;usesVehicleList\&quot;: false,\n    \&quot;dynamicSeatAssignment\&quot;: false,\n    \&quot;usesDriverCode\&quot;: false,\n    \&quot;usesDriverPin\&quot;: false,\n    \&quot;usesTickets\&quot;: false,\n    \&quot;usesTextToSpeech\&quot;: false,\n    \&quot;allowsManualValidation\&quot;: false,\n    \&quot;allowsRating\&quot;: false,\n    \&quot;allowsOnlyExistingDrivers\&quot;: true,\n    \&quot;allowsMultipleDrivers\&quot;: false,\n    \&quot;startsOnStop\&quot;: false,\n    \&quot;notNearStop\&quot;: false,\n    \&quot;allowsNonServiceSnapshots\&quot;: false,\n    \&quot;allowsServiceSnapshots\&quot;: false,\n    \&quot;allowsDistance\&quot;: false,\n    \&quot;usesOfflineCount\&quot;: false,\n    \&quot;hasBoardings\&quot;: false,\n    \&quot;hasUnboardings\&quot;: false,\n    \&quot;usesManualSeat\&quot;: false,\n    \&quot;noPassengerInfo\&quot;: false,\n    \&quot;showParable\&quot;: false,\n    \&quot;allowGenericVehicles\&quot;: false,\n    \&quot;usesVehicleQRLink\&quot;: false,\n    \&quot;skipDeclaration\&quot;: false,\n    \&quot;skipQRValidation\&quot;: false,\n    \&quot;name\&quot;: \&quot;Prueba Katalon1\&quot;,\n    \&quot;shapeId\&quot;: \&quot;6560b6a8479c215a1b6e9247\&quot;,\n    \&quot;description\&quot;: \&quot;Prueba Katalon1\&quot;,\n    \&quot;extraInfo\&quot;: \&quot;\&quot;,\n    \&quot;color\&quot;: \&quot;892b2b\&quot;,\n    \&quot;ownerIds\&quot;: [\n      {\n        \&quot;_id\&quot;: \&quot;6560f3dada7b3b61a1c044fc\&quot;,\n        \&quot;id\&quot;: \&quot;653fd601f90509541a748683\&quot;,\n        \&quot;role\&quot;: \&quot;community\&quot;\n      }\n    ],\n    \&quot;segments\&quot;: [],\n    \&quot;roundOrder\&quot;: [],\n    \&quot;legOptions\&quot;: [],\n    \&quot;superCommunityId\&quot;: \&quot;653fd68233d83952fafcd4be\&quot;,\n    \&quot;communityId\&quot;: \&quot;653fd601f90509541a748683\&quot;,\n    \&quot;timeOnRoute\&quot;: 71,\n    \&quot;distance\&quot;: 39,\n    \&quot;distanceInMeters\&quot;: 39082,\n    \&quot;originStop\&quot;: \&quot;655d11d88a5a1a1ff0328464\&quot;,\n    \&quot;destinationStop\&quot;: \&quot;655d11d88a5a1a1ff0328466\&quot;,\n    \&quot;customParams\&quot;: {\n      \&quot;enabled\&quot;: false,\n      \&quot;params\&quot;: []\n    },\n    \&quot;customParamsAtTheEnd\&quot;: {\n      \&quot;enabled\&quot;: false,\n      \&quot;params\&quot;: []\n    },\n    \&quot;createdAt\&quot;: \&quot;2023-11-24T19:04:58.783Z\&quot;,\n    \&quot;updatedAt\&quot;: \&quot;2023-11-24T19:04:58.783Z\&quot;,\n    \&quot;__v\&quot;: 0\n  },\n  \&quot;sequence\&quot;: 2,\n  \&quot;cost\&quot;: 0,\n  \&quot;ownerIds\&quot;: [\n    {\n      \&quot;_id\&quot;: \&quot;6564a1f88956023c1b7ee808\&quot;,\n      \&quot;id\&quot;: \&quot;653fd601f90509541a748683\&quot;,\n      \&quot;role\&quot;: \&quot;community\&quot;\n    }\n  ],\n  \&quot;superCommunityId\&quot;: \&quot;653fd68233d83952fafcd4be\&quot;,\n  \&quot;communityId\&quot;: \&quot;653fd601f90509541a748683\&quot;,\n  \&quot;createdAt\&quot;: \&quot;2023-11-27T14:04:40.457Z\&quot;,\n  \&quot;updatedAt\&quot;: \&quot;2023-11-27T14:10:53.403Z\&quot;,\n  \&quot;__v\&quot;: 1\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>f1c69e1f-0601-48a6-bc67-51643d745838</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.TokenAdmin}</value>
      <webElementGuid>0e69745d-4166-4e3b-8544-0d3d8565c0c8</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.0.0</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>https://stage.allrideapp.com/api/v1/admin/pb/stop-time/6564a1f88956023c1b7ee807?community=653fd601f90509541a748683</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
