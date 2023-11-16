<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>crear_Parada</name>
   <tag></tag>
   <elementGuidId>16c2ab4e-e982-45cc-862e-483ab10fb766</elementGuidId>
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
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;name\&quot;: \&quot;PruebaParada2\&quot;,\n  \&quot;categories\&quot;: \&quot;odd\&quot;,\n  \&quot;address\&quot;: \&quot;Río Eufrates, Maipú, Región Metropolitana, Chile\&quot;,\n  \&quot;communities\&quot;: [\&quot;653fd601f90509541a748683\&quot;],\n  \&quot;communityId\&quot;: \&quot;653fd601f90509541a748683\&quot;,\n  \&quot;lat\&quot;: null,\n  \&quot;lon\&quot;: null,\n  \&quot;ownerIds\&quot;: [{\&quot;id\&quot;: \&quot;653fd601f90509541a748683\&quot;, \&quot;role\&quot;: \&quot;community\&quot;}],\n  \&quot;stopId\&quot;: \&quot;\&quot;,\n  \&quot;userQty\&quot;: 0,\n  \&quot;users\&quot;: []\n}\n\n&quot;,
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
      <webElementGuid>0bdfde36-1adc-4fbf-861a-9cc6586581ac</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.TokenAdmin}</value>
      <webElementGuid>36303de2-91e0-4d5a-9c90-7a092cbed36c</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.0.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://stage.allrideapp.com/api/v1/admin/pb/stops?community=653fd601f90509541a748683</restUrl>
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
