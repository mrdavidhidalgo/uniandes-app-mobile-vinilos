# uniandes-app-mobile-vinilos

## ¿Como configurar la aplicación en ambiente local?

### Descargar la aplicación
`` 
git clone https://github.com/mrdavidhidalgo/uniandes-app-mobile-vinilos.git
`` 

### Abrir el proyecto en Andoid Studio

Se debe Dar Clic en File, luego Open y seleccionar el proyecto descargado
![image](https://user-images.githubusercontent.com/3289138/200223069-8af42413-96cc-4c44-820a-2eec1f88eedb.png)



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
