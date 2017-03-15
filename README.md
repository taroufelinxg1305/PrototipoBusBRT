# Creacion de un Sensor Driver


1. Implementar la interface uis.brt.sensor.api.Sensor que posee 4 metodos
   1. start inicia la ejecuión del driver
   2. stop detiene la ejecuión del driver
   3. setBus injecta la instacia del EventBus a utilizar para la comunicación con la plataforma
   4. configure es llamado por la plataforma para configurar el sensor driver. 
   
2. Incluir un archivo de configuracion llamamdo config.properties. Las propiedades se definen de tipo valor según esta [definición](https://en.wikipedia.org/wiki/.properties)
3. Incluir un archivo llamado uis.brt.sensor.api.Sensor dentro de este archivo hay una unica linea con el nombre de la clase implemnentando el driver
4. Empaquetar el código y los dos archivos descritos anteriormente en un archivo tipo jar con la siguiente estructura
