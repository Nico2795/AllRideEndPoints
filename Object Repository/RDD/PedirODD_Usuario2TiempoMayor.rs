<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>PedirODD_Usuario2TiempoMayor</name>
   <tag></tag>
   <elementGuidId>f08c3fc0-4ea9-48d2-8ce6-c16bfe0518ce</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <authorizationRequest>
      <authorizationInfo>
         <entry>
            <key>bearerToken</key>
            <value>${GlobalVariable.TokenUsuario2}</value>
         </entry>
      </authorizationInfo>
      <authorizationType>Bearer</authorizationType>
   </authorizationRequest>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;oddType\&quot;: \&quot;Taxis Coni y Nico\&quot;,\n    \&quot;name\&quot;: \&quot;Prueba Union Usuario2\&quot;,\n    \&quot;direction\&quot;: \&quot;out\&quot;,\n    \&quot;comments\&quot;: \&quot;Conducir con precaución\&quot;,\n    \&quot;serviceDate\&quot;: \&quot;${GlobalVariable.serviceDate21min}\&quot;,\n    \&quot;startLocation\&quot;: {\n        \&quot;placeId\&quot;: \&quot;6543b3dc0d3bbd73ffd6cc3a\&quot;,\n        \&quot;lat\&quot;: \&quot;-33.5654\&quot;,\n        \&quot;lon\&quot;: \&quot;-70.7796\&quot;,\n        \&quot;loc\&quot;: [\&quot;-33.5654\&quot;, \&quot;-70.7796\&quot;],\n        \&quot;address\&quot;: \&quot;Dirección inicio\&quot;\n    },\n    \&quot;endLocation\&quot;: {\n        \&quot;lat\&quot;: \&quot;-33.5066\&quot;,\n        \&quot;lon\&quot;: \&quot;-70.6058\&quot;,\n        \&quot;loc\&quot;: [\&quot;-70.6058\&quot;, \&quot;-33.5066\&quot;],\n        \&quot;address\&quot;: \&quot;Dirección destino\&quot;,\n        \&quot;placeId\&quot;: \&quot;6548d72888041e5ab5db80b1\&quot;\n    }\n}&quot;,
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
      <webElementGuid>541029b3-0261-4854-a773-87d4c962b036</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.TokenUsuario2}</value>
      <webElementGuid>6e7ab3e5-0853-49f2-b810-6c9deb65cdcf</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.0.0</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.requestODD}</restUrl>
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
