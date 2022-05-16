
## 1. Introducción
El objetivo de este trabajo es la transformación de un conjunto de datos de origen CSV a datos enlazados de acuerdo a las guías metodológicas descritas en el curso. Para ello, he seleccionado unos datos de personas empadronadas en los municipios de la Comunidad de Madrid por rango de edad y sexo. 

## 2. Proceso de transformación
### a) Selección de la fuente de datos
La fuente de datos seleccionada proviene del portal de datos abiertos del Gobierno de España (https://datos.gob.es/es/catalogo/a13002908-residentes-en-la-comunidad-de-madrid-por-rango-de-edad1). En concreto, he seleccionado el dataset “Residentes en la Comunidad de Madrid por Rango de Edad” el cuál contiene datos de las personas empadronadas en los diferentes municipios de la Comunidad de Madrid distribuidos por sexo y rango de edad. He escogido este dataset porque se trata de un escenario real de datos fácilmente accesibles que pueden ser actualizados en cualquier momento. También me parece interesante conocer cómo se distribuyen las personas en la Comunidad de Madrid según su edad.

### b) Análisis de los datos
Antes de comenzar con el análisis de los datos, debemos conocer la licencia del conjunto de datos. La licencia del dataset que hemos utilizado pertenece a “Licencias Públicas de Creative Commons”. Esta licencia permite usar los datos a cualquier persona y con cualquier fin, ya sea comercial o no, siempre que se indique la atribución de éstos. Por lo tanto, utilizaremos la misma licencia para los datos transformados obtenidos en este proyecto.

Una vez definida la procedencia de los datos, se procede a la realización del análisis para entender el conjunto de datos. Para ello he usado la herramienta OpenRefine con la extensión RDF. El dataset contiene 7518 registros agrupados en los siguientes campos:

| Columna | Tipo | Rango de valores | Comentarios |
| ------------- | ------------- | -------------| ------------- | 
| municipio_codigo | String | [1, ..., 903] | Contiene el código del municipio dentro de la Comunidad de Madrid. Hay 179 códigos de municipios. Lo más recomendable es cambiar la variable a valor numérico. |
| municipio_nombre | String | [Acebeda (La), ..., Zarzalejo] | Contiene el nombre del municipio dentro de la Comunidad de Madrid. Hay un total de 179 municipios. Están directamente relacionados con la variable *municipio_codigo*.|
| sexo | String | [Mujer, Hombre] | Contiene la identificación del sexo de la persona. Hay 3759 registros de hombres y 3759 registros de mujeres. |
| rango_edad | String | [De 0 a 4 años, …, De 100 y más años] | Contiene el rango de edad de la persona. Hay un total de 21 rangos posibles de edad. |
| poblacion_empadronada | Numérica | [0, .., 137406] | Contiene la cantidad de personas que están empadronadas en un municipio. |

Como se puede observar, el conjunto de datos no tiene ningún tipo de error ni valor nulo. 

### c) Estrategia de nombrado
Para elegir la estrategia de nombrado de los recursos, primero hay que elegir la forma de las URIs, es decir, saber si vamos a usar “/” o “#”. En este ejemplo, he decidido utilizar URIs con almohadilla (#) para los términos ontológicos y URIs con barra inclinada (/) para los individuos. 
A continuación, debemos elegir el dominio que utilizaremos para las URIs, la ruta de las URIs y los patrones que seguiremos para nombrar clases, propiedades e individuos.
-	Dominio: http://ejemplo-empadronados.es
-	Ruta para términos ontológicos: http://ejemplo-empadronados.es/CM/municipio#
-	Ruta para individuos: http://ejemplo-empadronados.es/CM/municipio/
-	Patrón para términos ontológicos: http://ejemplo-empadronados.es/CM/municipio#
-	Patrón para individuos: http://ejemplo-empadronados.es/CM/municipio/edad

### d) Desarrollo del vocabulario

### e) Proceso de transformación de los datos

### f) Enlazado

### g) Publicación

## 3. Aplicación y explotación

## 4. Conclusiones

## 5. Bibliografía
