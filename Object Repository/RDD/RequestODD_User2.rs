<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>RequestODD_User2</name>
   <tag></tag>
   <elementGuidId>b82f80bc-07aa-4c07-ba53-1d4e800c2dcc</elementGuidId>
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
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;oddType\&quot;: \&quot;Taxis Coni y Nico\&quot;,\n    \&quot;name\&quot;: \&quot;Prueba Union Usuario2\&quot;,\n    \&quot;direction\&quot;: \&quot;out\&quot;,\n    \&quot;comments\&quot;: \&quot;Conducir con precaución\&quot;,\n    \&quot;serviceDate\&quot;: \&quot;${GlobalVariable.serviceDate}\&quot;,\n    \&quot;startLocation\&quot;: {\n        \&quot;placeId\&quot;: \&quot;6543b3dc0d3bbd73ffd6cc3a\&quot;,\n        \&quot;lat\&quot;: \&quot;-33.5654\&quot;,\n        \&quot;lon\&quot;: \&quot;-70.7796\&quot;,\n        \&quot;loc\&quot;: [\&quot;-33.5654\&quot;, \&quot;-70.7796\&quot;],\n        \&quot;address\&quot;: \&quot;Dirección inicio\&quot;\n    },\n    \&quot;endLocation\&quot;: {\n        \&quot;lat\&quot;: \&quot;-33.5066\&quot;,\n        \&quot;lon\&quot;: \&quot;-70.6058\&quot;,\n        \&quot;loc\&quot;: [\&quot;-70.6058\&quot;, \&quot;-33.5066\&quot;],\n        \&quot;address\&quot;: \&quot;Dirección destino\&quot;,\n        \&quot;placeId\&quot;: \&quot;6548d72888041e5ab5db80b1\&quot;\n    }\n}&quot;,
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
      <webElementGuid>a05145ae-c3ef-4a49-9d81-c7843ee2910c</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.TokenUsuario2}</value>
      <webElementGuid>bd12f5b6-4e94-490c-91d2-f38e1784a9e4</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.0.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.requestODD}</restUrl>
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
