# uniandes-app-mobile-vinilos

## ¿Como configurar la aplicación en ambiente local?

### Descargar la aplicación
`` 
git clone https://github.com/mrdavidhidalgo/uniandes-app-mobile-vinilos.git
`` 

### Abrir el proyecto en Andoid Studio

Se debe Dar Clic en File, luego Open y seleccionar el proyecto descargado
![image](https://user-images.githubusercontent.com/3289138/200223069-8af42413-96cc-4c44-820a-2eec1f88eedb.png)

### APK
The APK is esta aqui 
(https://github.com/mrdavidhidalgo/uniandes-app-mobile-vinilos/blob/1.0.0/app-debug.apk)

### Construir el proyecto
Para construir el proyecto dar Ctrl+F9
De esta forma se comenzara a contruir el proyecto

## Esquema de paquetes
![image](https://user-images.githubusercontent.com/3289138/200224255-2df61039-20fd-4714-a4ed-1e8adbf7c8d7.png)

Se esta usando el siguiente esquema de proyectos:

### models
  En este paquete se encuentran los DTO
### network
  En este paquete se encuentra los adaptadores de red
### repositories
  En este paquete se encuentra los repositorios que se utilizaran, estos repositorios son la entrada al modelo
### ui
  En este paquete se encuentra el código relacionado las vistas de la aplicación
### viewmodels
  En este paquete se encuentra el código de los mediadores, mas conocidos como ViewModesl de  MVVM


# Procedimientos de Pruebas automatizadas

## Para Espresso 
Desplegar el emulador o conectar el dispositivo con SO ANdroid (Activar depuracion por USB en mode desarrollador)


### De forma Grafica en Android Studio
En la carpeta com.team.vinylos (androidTest), dar click derechoc sobre cualquiera de estos 3 tests y dar Run:

* Test1Albums
* Test1Artists
* TestCollectors

![imagen](https://user-images.githubusercontent.com/98839764/200232283-7ec7b2d1-d8f2-4ab8-996a-1b6bcebe1403.png)

Se desplegará la siguiente ventana con el resumen

![imagen](https://user-images.githubusercontent.com/98839764/200232383-864b3cb1-f329-41c6-bf82-0f7671058d24.png)



### Por consola
Entrar al directorio app/src/androidTest/java/com/team/vinylos/
y ejecutar el comando

* Test1Album
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test1Artists' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``
* Test1Artists
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test1Albums' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``
* Test1Collectors
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test1Collectors' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``

