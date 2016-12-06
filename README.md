Configuración inicial

Para configurar la aplicación para su primer uso se deben realizar los siguientes pasos: 

1) Creación de conexión de base de datos: en el motor de base de datos de mySql, si no existe, se debe crear el usuario 'root', el mismo debe crearse con la siguiente contraseña: 'ddsistemas'. Si el usuario root ya existe se puede cambiar su contraseña ejecutando el siguiente script: SET PASSWORD =  PASSWORD('ddsistemas')

2) El siguiente paso es crear la base de datos: Se debe crear una base de datos llamada de la siguiente forma: 'DDS'.

3) Antes de correr la aplicación web, es necesario realizar una carga inicial de datos, para esto existe un test que realiza la carga inicial; El test que se debe correr por única vez es el siguiente: 'cargaInicialDatosTest' y se encuentra en la clase 'testEntregaFinal', en el paquete 'utn.dds.g10.test.entrega_final'. Este test crea datos de prueba y ademas crea un usuario para poder loguearse al sistemas, el mismo es: 

  usuario: admin
  password: admin

4) Por último, se debe correr la aplicación en el servidor de Tomcat local e ingresar en el navegador la siguiente ruta: 

  http://localhost:{ingresar_puerto}/PuntosInteres/faces/login.xhtml

  donde {ingresar_puerto} debe ser reemplazado por el puerto local en donde se esté corriendo la aplicación.
