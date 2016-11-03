# PrototipoBusBRT
-->Añadido el servidor y cliente TCP locales para la lectura y conversion a cadenas de datos NMEA  

-->octubre 28, refactor para distribuir las clases existentes en paquetes de manera mas coherente y tener solo un main

-->noviembre 2, hay dos ejecutables, Launcher y MyTcpLocalClient el cual esta separado la funcionalidad para obtener los datos del sensor o simularlos y se conecta a la aplicacion principal atravez de un socket

-->noviembre 3, La actual aplicacion nesecita tener la web-aps CloudBRT en el servidor Tomcat, tener activo el servidor, y encender el servicio mongoDB, bajo esas condiciones la aplicacion funciona ejecutando el Launcher
