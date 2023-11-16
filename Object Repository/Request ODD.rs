<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Request ODD</name>
   <tag></tag>
   <elementGuidId>097cdb70-b8fe-40ef-9cb7-43798e3bf92e</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <authorizationRequest>
      <authorizationInfo>
         <entry>
            <key>bearerToken</key>
            <value>${GlobalVariable.TokenNico}</value>
         </entry>
      </authorizationInfo>
      <authorizationType>Bearer</authorizationType>
   </authorizationRequest>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;comments\&quot;: \&quot;Conducir con precaución\&quot;,\n    \&quot;serviceDate\&quot;: \&quot;2024-09-12T13:20:00.000Z\&quot;,\n    \&quot;startLocation\&quot;: {\n        \&quot;lat\&quot;: -33.4192657,\n        \&quot;lon\&quot;: -70.5888519,\n        \&quot;loc\&quot;: [\n            -70.5888519,\n            -33.4192657\n        ],\n        \&quot;address\&quot;: \&quot;Dirección inicio\&quot;,\n        \&quot;placeId\&quot;: \&quot;651c55d84f983b1002c6ef06\&quot;\n    },\n    \&quot;endLocation\&quot;: {\n        \&quot;lat\&quot;: -33.5066,\n        \&quot;lon\&quot;: -70.6058,\n        \&quot;loc\&quot;: [\n            -70.6058,\n            -33.5066\n        ],\n        \&quot;address\&quot;: \&quot;Dirección destino\&quot;,\n        \&quot;placeId\&quot;: \&quot;\&quot;\n    },\n    \&quot;oddType\&quot;: \&quot;Autobus\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.TokenNico}</value>
      <webElementGuid>2638158f-26f3-4746-9236-5c1e23dbb554</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.0.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://stage.allrideapp.com/api/v1/pb/user/oddepartures/5a906201d0c7684ad80e2ec6</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
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
