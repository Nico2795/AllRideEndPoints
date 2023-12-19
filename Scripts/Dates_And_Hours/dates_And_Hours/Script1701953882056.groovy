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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

// Se escriben las variables globales para poder generar un codigo donde la fecha se obtenga de manera automatica para el dia de hoy

// Obtén la fecha actual
def fechaHoy = LocalDate.now()

// Obtén la fecha de mañana
def fechaManana = fechaHoy.plusDays(1)

// Obtén la fecha de pasado mañana
def fechaPasadoManana = fechaHoy.plusDays(2)

// Obtén la fecha dos dias despues, oara generar el la busqueda correcta por dia
def fechaPasadoPasadoManana = fechaHoy.plusDays(3)


// Formatea las fechas al formato deseado (por ejemplo, "yyyy-MM-dd")
def formato = DateTimeFormatter.ofPattern("yyyy-MM-dd")
def fechaHoyFormateada = fechaHoy.format(formato)
def fechaMananaFormateada = fechaManana.format(formato)
def fechaPasadoMananaFormateada = fechaPasadoManana.format(formato)
def fechaPasadoPasadoMananaFormateada = fechaPasadoPasadoManana.format(formato)

//Obtenemos el nombre del dia actual en formato de 3 letras
def formatoDia = DateTimeFormatter.ofPattern("E", Locale.ENGLISH)
def diaActual = fechaHoy.format(formatoDia)
//Corroboramos
println (fechaHoyFormateada)
println (fechaMananaFormateada)
println (fechaPasadoMananaFormateada)
println (fechaPasadoPasadoMananaFormateada)
println (diaActual.toLowerCase())

GlobalVariable.arrivalDate = "${fechaMananaFormateada}T01:00:00.000Z"
GlobalVariable.r_estimatedArrival1 = "${fechaMananaFormateada}T14:45:57.000Z"
GlobalVariable.serviceDate  = "${fechaMananaFormateada}T00:25:29.000Z"
GlobalVariable.modifiedArrivalDate  = "${fechaMananaFormateada}T01:00:00.000Z"
GlobalVariable.r_modifiedEstimatedArrival = "${fechaPasadoMananaFormateada}T14:45:57.000Z"
GlobalVariable.modifiedServiceDate = "${fechaMananaFormateada}T00:25:29.000Z"
GlobalVariable.serviceDate20min = "${fechaMananaFormateada}T00:20:00.000Z"
GlobalVariable.serviceDate22min = "${fechaMananaFormateada}T00:47:00.000Z"
GlobalVariable.startDate = "${fechaMananaFormateada}T03:00:00.000Z"
GlobalVariable.endDate4weeks = "2023-12-30T02:59:59.999Z"
GlobalVariable.endDate = "${fechaPasadoPasadoMananaFormateada}T03:00:00.000Z"
GlobalVariable.scheduleDay = "${diaActual.toLowerCase()}"
GlobalVariable.startDateToday =  "${fechaHoyFormateada}T03:00:00.000Z"
GlobalVariable.todayDate = "${fechaHoyFormateada}"

// Muestra los prints
println("arrivalDate: " + GlobalVariable.arrivalDate)
println("r_estimatedArrival1: " + GlobalVariable.r_estimatedArrival1)
println("serviceDate: " + GlobalVariable.serviceDate)
println("modifiedArrivalDate: " + GlobalVariable.modifiedArrivalDate)
println("r_modifiedEstimatedArrival: " + GlobalVariable.r_modifiedEstimatedArrival)
println("modifiedServiceDate: " + GlobalVariable.modifiedServiceDate)
println("serviceDate20min: " + GlobalVariable.serviceDate20min)
println("serviceDate21min: " + GlobalVariable.serviceDate22min)
println("startDate: " + GlobalVariable.startDate)
println("endDate4weeks: " + GlobalVariable.endDate4weeks)
println("endDate: " + GlobalVariable.endDate)
println("scheduleDay: " + GlobalVariable.scheduleDay)

