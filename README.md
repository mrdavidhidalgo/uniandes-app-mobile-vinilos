# uniandes-app-mobile-vinilos


## Plan de pruebas y calidad Semana 6
Wiki plan pruebas Sprint 2 https://github.com/mrdavidhidalgo/uniandes-app-mobile-vinilos/wiki/plan_pruebas_sprint2

## Apk sprint 2 - semana 5 y 6
https://github.com/mrdavidhidalgo/uniandes-app-mobile-vinilos/releases/download/1.0.2/app-debug.apk

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
En la carpeta com.team.vinylos (androidTest), dar click derechoc sobre cualquiera de estos tests y dar Run:


![imagen](https://user-images.githubusercontent.com/98839764/205480414-7c5d31d1-53a9-48b3-93d5-02ac2cab7cd4.png)



Se desplegará la siguiente ventana con el resumen

![imagen](https://user-images.githubusercontent.com/98839764/200232383-864b3cb1-f329-41c6-bf82-0f7671058d24.png)



### Por consola
Entrar al directorio app/src/androidTest/java/com/team/vinylos/
y ejecutar el comando

#### Test1Albums
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test1Artists' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``
#### Test1Artists
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test1Albums' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``
#### Test1Collectors
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test1Collectors' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``
#### Test2AlbumsCreateE2E
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test2AlbumsCreateE2E' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``
#### Test2AlbumsCreateValidacion (Escenarios de Validación de Datos)
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test2AlbumsCreateValidacion' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``
#### Test2ArtistsDetailE2E
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test2ArtistsDetail' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``

#### Test3PrizesCreateE2E
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test3PrizesCreateE2E' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``

#### Test2AlbumDetailE2E
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test2AlbumDetailE2E' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner

``
#### Test3CommentsAlbumCreateE2E
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test3CommentsAlbumCreateE2E' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner`` 
`
#### Test2AlbumsCreateValidacion (Escenarios de Validación de Datos)
``
adb shell am instrument -w -m -e debug false -e class 'com.team.vinylos.Test2AlbumsCreateValidacion' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``

#### Test3PrizesCreateValidation
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test3PrizesCreateValidation' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner
``
##`## Test3CommentsAlbumCreateValidation
``
adb shell am instrument -w -m    -e debug false -e class 'com.team.vinylos.Test3CommentsAlbumCreateValidation' com.team.vinylos.test/androidx.test.runner.AndroidJUnitRunner`` 


