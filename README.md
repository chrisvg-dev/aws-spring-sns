# Implementación de AWS SNS con Spring Boot

Para que la aplicación funcione deberán agregar 4 datos fundamentales, los cuales son los siguientes:
1. ARN del servicio AWS SNS
2. Región
3. ACCESS_ID
4. SECRET_KEY

El arn y la región deberán agregarlas en el archivo properties en el apartado correspondiente. Ya viene indicado donde se debe agregar cada uno.

El access_id y secret_key deberán agregarlos dentro de la clase *SnsApplicationListener.java* que podrán encontrar dentro de la carpeta "config".

El proyecto está en proceso, más adelante se agregarán las funcionalidades faltantes.