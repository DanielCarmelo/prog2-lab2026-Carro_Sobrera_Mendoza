# [Control de Stock de artículos de tienda]

Trabajo de Programación 2 del CeRP del Suroeste, modalidad semipresencial.
Docente: Domingo Pérez. Año: 2026.

## Integrantes

-  Daniel Mendoza.
- Cristian Carro.
- Gerardo Sobrera.

## Estado del proyecto

La aplicación permite gestionar artículos de una tienda mediante una interfaz gráfica desarrollada en Java Swing. El sistema registra, modifica, elimina, busca, y almacena artículos en formato JSON manteniendola separación de responsabilidades mediante el patrón MVC y una capa de fachada. Los datos se conservan entre ejecuciones gracias a la persistencia local.
Partimos del prototipo docente de un gestor de registros en Java y Swing,
organizado con MVC, fachada y persistencia en JSON.

**Actualmente solo está disponible el prototipo.** La ventana muestra tres
registros de ejemplo del archivo JSON. Los botones Registrar, Modificar,
Eliminar, Buscar, Limpiar y Guardar muestran un aviso de funcionalidad pendiente.
Seleccionar una fila todavía no carga sus datos en el formulario.

Los apartados marcados como **Pendiente** deben completarse con nuestro
desarrollo real antes de entregar. La [consigna del laboratorio](docs/letra.md)
detalla los requisitos.

## Problema y destinatarios

Este proyecto está orientado al control de stock de comercios pequeños y medianos que necesitan  administrar mercadería de manera simple y eficiente. 
El sistema permite registrar productos utilizando códigos de barras EAN-13, controlar existencias disponibles y gestionar información relevante para la comercialización de artículos
Los destinatarios son: 
                      Almacenes
                      Minimercados
                      Autoservicios
                      Kioscos
                      Comercios de barrios
## Utilidad de la solución

La aplicación facilita: Controlar el inventariodisponible. Registrar ingresos de mercadería. Mantener información organizada mediante categorías. Detectar atículos con bajo stock. Identificar productos en oferta. Evitar errores en el registro de artículos mediante validaciones automáticas. Conservar los datos mediante almacenamiento JSON.

## Adaptación del modelo y reglas

La entidad principal del sistema es Artículo, cuyos atributos son:

| Atributo | Descripción |
| --- | --- |
| codigoBarras | Código EAN-13 de 13 dígitos|
| idSeccion | Categoría derivada de los primeros dos dígitos del código|
| descripcion | Descripción del artículo |
| precioCosto | Precio de compra al proveedor |
| oferta | Indica si el artículo está en oferta |
| stock | Cantidad disponible |



**Pendiente de implementar y documentar:** qué representará un registro en
nuestro proyecto, qué atributos agregaremos o cambiaremos y cuáles serán las
categorías y operaciones propias del problema.

| Validación requerida | Regla de nuestro proyecto | Estado |
| --- | --- | --- |
| Primera validación | Pendiente de definir. | Sin implementar. |
| Segunda validación | Pendiente de definir. | Sin implementar. |
| Tercera validación, propia del problema | Pendiente de definir. | Sin implementar. |

También falta implementar el identificador único, la selección y modificación,
la eliminación con confirmación, la búsqueda, la limpieza del formulario y la
actualización del listado. La lectura y escritura JSON están disponibles como
infraestructura; falta integrar el guardado con las operaciones.
La entidad principal del sistema es Artículo, cuyos atributos son: Atributo DscripcióncodigoBarras Código EAN-13 de 13 dígitos id SeccionCategoría derivada de los primeros dos dígitos del código descripción Descripción del artículo 
precioCosto Precio de compra al proveedor
oferta indica si el artículo esta en oferta 
stock Cantidad disponible
Validaciones implementadas
Validación requerida	Regla del proyecto	EstadoCódigo EAN-13 válido	Debe contener exactamente 13 dígitos numéricos	Implementada
Descripción obligatoria	No puede estar vacía ni contener solo espacios	Implementada
Precio y stock válidos	Precio mayor a 0 y stock mayor o igual a 0	Implementada

Además:

No se permiten códigos EAN duplicados.
Los primeros dos dígitos deben corresponder a una sección válida.
Las modificaciones mantienen el código EAN original.


## Componentes gráficos

La base incluye JTextField, JComboBox, JCheckBox, JButton y JTable.
JLabel y los contenedores de apoyo no cuentan para alcanzar el mínimo solicitado.

Los tres tipos nuevos todavía no fueron elegidos ni implementados:

| Componente nuevo | Dato o acción real que permitirá gestionar | Estado |
| --- | --- | --- |
| JSpinner |Gestión precisa del stock | Sin implementar. |
| JFormattedTextField |Ingreso y validación del precio | Sin implementar. |
| Ingreso y validación del precio | Filtros rápidos de búsqueda| Sin implementar. |

## Organización de paquetes y recorrido de una operación

| Paquete o carpeta | Responsabilidad |
| --- | --- |
| [app](src/main/java/app) | Iniciar la aplicación y conectar los objetos. |
| [modelo](src/main/java/modelo) | Representar los registros y, al completar el proyecto, sus reglas. |
| [vista](src/main/java/vista) | Mostrar la interfaz Swing y los mensajes. |
| [controlador](src/main/java/controlador) | Recibir eventos y coordinar las acciones mediante la fachada. |
| [fachada](src/main/java/fachada) | Ofrecer operaciones y coordinar el modelo con la persistencia. |
| [persistencia](src/main/java/persistencia) | Leer y escribir JSON, sin depender de Swing. |
| [datos](datos) | Contener el archivo JSON; no es un paquete Java. |

El recorrido que ya funciona es la carga inicial:
[Aplicacion](src/main/java/app/Aplicacion.java) conecta los objetos;
el controlador solicita la carga a la fachada; la fachada utiliza la
persistencia para leer el archivo; y el controlador entrega los registros a la
vista para mostrarlos en la tabla. La vista no lee ni escribe archivos.

**Pendiente de documentar:** el recorrido completo de una operación de gestión
implementada por nuestro equipo.
Organización de paquetes y recorrido de una operación
Paquete	Responsabilidadapp	Inicio de la aplicación
modelo	Entidades y reglas de negocio
vista	Interfaz gráfica
controlador	Gestión de eventos
fachada	Operaciones del sistema
persistencia	Lectura y escritura JSON
datos	Archivo de almacenamiento

##Recorrido de una operación

Cuando el usuario registra un artículo:
1.La vista captura los datos.
2.El controlador recibe el evento.
3.La fachada ejecuta las validaciones.
4.El modelo representa el artículo.
5.La persistencia guarda la información en JSON.
6.La vista actualiza la tabla de resultados.

## Instrucciones de ejecución

### Requisitos

- JDK 25 LTS o superior, con `JAVA_HOME` apuntando al JDK y su carpeta `bin`
  en el `PATH`, para disponer de los comandos `java` y `javac`.
- Windows, macOS o Linux con un entorno gráfico para mostrar Swing.
- Git, si se descarga el proyecto mediante clonación.
- Conexión a Internet en la primera ejecución para descargar Maven y las
  dependencias. La aplicación utiliza un archivo local, sin base de datos ni
  conexión a Internet durante su uso.

No es necesario instalar Maven ni agregarlo al `PATH`: el repositorio incluye
Maven Wrapper, que descarga y utiliza Maven 3.9.11 automáticamente.
La compilación genera clases para Java 25; no se pueden ejecutar con Java 17
ni Java 21. Maven Wrapper utiliza el JDK indicado por `JAVA_HOME`.

Después de instalar o cambiar el JDK, reiniciar la terminal y VS Code.
Comprobar que `java -version`, `javac -version` y `.\mvnw.cmd -version`
(Windows) o `sh mvnw -version` (macOS y Linux) indiquen Java 25 o superior.

### Descargar el proyecto

**Pendiente:** reemplazar `USUARIO` y `REPOSITORIO` por los datos del repositorio
del equipo antes de entregar.

```text
git clone https://github.com/USUARIO/REPOSITORIO.git
cd REPOSITORIO
```

El nombre del repositorio debe seguir el formato indicado en la consigna:
`prog2-lab2026-apellido1_apellido2`.

Ejecutar los siguientes comandos desde la raíz del proyecto, donde se
encuentra [pom.xml](pom.xml).

### Windows: PowerShell

```powershell
.\mvnw.cmd compile exec:java
```

Para compilar sin abrir la ventana:

```powershell
.\mvnw.cmd compile
```

Si aparece «mvn no se reconoce», utilizar `.\mvnw.cmd` como en los comandos
anteriores. El prefijo `.\` indica a PowerShell que ejecute el lanzador de la
carpeta actual.

### macOS y Linux: terminal

```sh
sh mvnw compile exec:java
```

Para compilar sin abrir la ventana:

```sh
sh mvnw compile
```

Al utilizar `sh mvnw` no es necesario cambiar los permisos del archivo.
En Linux se requiere una sesión de escritorio; una terminal de servidor sin
entorno gráfico no puede mostrar la ventana.

### VS Code y dependencias

Abrir la carpeta raíz con Extension Pack for Java instalado e importar el
proyecto Maven. También se puede ejecutar el método `main` de
[Aplicacion](src/main/java/app/Aplicacion.java), con la raíz del proyecto como
directorio de trabajo.
En `Java: Configure Java Runtime`, seleccionar un JDK 25 para este proyecto.
La configuración local de VS Code selecciona el JDK 25 instalado en
`%USERPROFILE%\.jdks\jdk-25.0.4.1+1` para las terminales de Windows y el
proyecto Java, sin modificar el `PATH` del sistema. Si se utiliza otra
instalación, ajustar las rutas en `.vscode/settings.json`.

La biblioteca de persistencia es Gson 2.13.2. Está declarada, junto con los
complementos de compilación y ejecución, en [pom.xml](pom.xml).

### Archivo de datos

El archivo de ejemplo es [datos/registros.json](datos/registros.json).
La ruta es relativa al directorio desde el cual se inicia la aplicación.

Si el archivo no existe, la aplicación inicia con una lista vacía. Si es
inválido o ilegible, muestra un error y mantiene la ventana abierta sin
reemplazarlo. La fachada bloquea el guardado cuando la carga no fue correcta.
Los errores de escritura se propagan al controlador; falta integrar el guardado
y sus mensajes con las operaciones del equipo.

**En el estado actual, ningún botón guarda datos y cerrar la ventana tampoco
guarda.**

## Pruebas realizadas y limitaciones conocidas

### Comprobaciones heredadas del prototipo

La base fue verificada en Windows con OpenJDK 17.0.17 y Maven 3.9.11.
Es compatible por diseño con macOS y Linux, pero su ejecución en esos sistemas
todavía no fue comprobada.

Tras actualizar a Java 25 LTS, se verificó en Windows con Eclipse Temurin
25.0.4.1 y Maven 3.9.11 la compilación limpia mediante `.\mvnw.cmd clean verify`.
También se comprobaron la carga de los tres registros de ejemplo, la lectura
sin archivo, la escritura y lectura JSON con tildes, la copia del listado,
el rechazo de JSON inválido y el bloqueo del guardado tras una carga fallida.
No hay pruebas automatizadas en el repositorio; estas comprobaciones se
realizaron con un programa temporal. La interfaz gráfica no se volvió a
verificar como parte de esta actualización.

| Acción | Resultado esperado | Resultado obtenido en la base |
| --- | --- | --- |
| Compilar mediante Maven Wrapper en Windows. | Compilación sin errores. | Correcto. |
| Abrir la aplicación desde la raíz. | Mostrar los tres registros del JSON. | Correcto. |
| Pulsar los seis botones. | Mostrar avisos de funcionalidad pendiente sin modificar datos. | Correcto. |
| Iniciar sin JSON, con una ruta de prueba. | Mostrar la tabla vacía sin crear el archivo. | Correcto. |
| Iniciar con JSON inválido, usando una copia de prueba. | Informar el error sin cerrar la ventana ni modificar el archivo. | Correcto. |
| Leer y escribir registros en un archivo temporal. | Recuperar todos los atributos, incluidos caracteres con tildes. | Correcto. |
| Guardar desde la fachada tras una carga fallida. | Rechazar el guardado sin reemplazar el archivo. | Correcto. |

### Pruebas de nuestro desarrollo

**Pendiente:** realizar estas pruebas y reemplazar los resultados por lo
realmente observado. No se consideran aprobadas por estar incluidas en la tabla.

| Acción | Resultado esperado | Resultado obtenido |
| --- | --- | --- |
| Crear un registro válido. | Aparece en el listado con identificador único. | Pendiente: alta sin implementar. |
| Incumplir la primera validación. | Informar el motivo y permitir corregir. | Pendiente: validación sin implementar. |
| Incumplir la segunda validación. | Informar el motivo y permitir corregir. | Pendiente: validación sin implementar. |
| Incumplir la regla propia del problema. | Rechazar la operación y explicar el motivo. | Pendiente: regla sin implementar. |
| Modificar un registro seleccionado. | Actualizarlo conservando su identificador, sin duplicarlo. | Pendiente: modificación sin implementar. |
| Cancelar una eliminación y confirmar otra con un registro de prueba. | Conservar el primero y eliminar solo el confirmado. | Pendiente: eliminación sin implementar. |
| Aplicar una búsqueda o filtro. | Mostrar únicamente los registros que cumplen el criterio. | Pendiente: búsqueda sin implementar. |
| Limpiar el formulario. | Dejarlo preparado para una nueva operación. | Pendiente: limpieza sin implementar. |
| Crear y modificar datos, guardar, cerrar y volver a abrir. | Recuperar los cambios desde el JSON. | Pendiente: guardado sin integrar. |
| Iniciar sin JSON y con JSON inválido, usando copias de prueba. | Mantener el manejo de errores de la base. | Pendiente: volver a comprobar tras nuestros cambios. |

Las limitaciones actuales son las funciones pendientes descritas en este
documento. **Pendiente:** actualizar esta sección con las limitaciones reales
de nuestra entrega.

## Procedencia del código y recursos utilizados

- **Base docente:** estructura MVC con fachada, modelo genérico, ventana inicial,
  avisos de funcionalidad pendiente, carga de ejemplo, infraestructura JSON y
  configuración Maven con Wrapper.
- **Trabajo de nuestro equipo:** pendiente de implementar y detallar,
  identificando qué agregamos y qué modificamos.
- **Bibliotecas:** Gson 2.13.2 para leer y escribir JSON; Swing, incluido en Java,
  para la interfaz.
- **Tutoriales, documentación y herramientas de IA utilizados por el equipo:**
  pendiente de declarar. Indicar los recursos, su uso concreto y las partes
  correspondientes, o aclarar si no se utilizaron.

## Aportes de cada integrante

| Integrante | Aportes realizados |
| --- | --- |
| Pendiente: primer integrante. | Pendiente de registrar aportes concretos. |
| Pendiente: segundo integrante. | Pendiente de registrar aportes concretos. |

Agregar una tercera fila solamente si corresponde. Todos los integrantes deben
conocer la solución completa y participar en el video.

## Video explicativo

**Pendiente de crear y publicar. No hay un video disponible todavía.**

Enlace público: **pendiente de agregar**.

El video debe durar como máximo cinco minutos, incluir la participación de todos
los integrantes y mostrar el problema, la aplicación, una validación, los tres
componentes nuevos, el recorrido de una operación y la conservación de datos.

Lo alojaremos en una plataforma externa, sin subir el archivo al repositorio.
Antes de entregar, comprobaremos que el enlace permita verlo desde una ventana
privada, sin iniciar sesión ni solicitar permisos.
