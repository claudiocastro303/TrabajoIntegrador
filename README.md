# Sistema de Gestión Vehicular

## Descripción del Dominio
Sistema CRUD completo para gestión de vehículos y sus seguros asociados. Desarrollado en Java con arquitectura en capas (Models, DAO, Service, Config) y MySQL como base de datos relacional.

## Funcionalidades principales:
- Gestión de vehículos (alta, baja, modificación, consulta)
- Administración de pólizas de seguro
- Relación vehículo-seguro
- Eliminación lógica de registros
- Búsqueda por dominio y marca

## Requisitos Técnicos
**Requisitos mínimos:**
- Java JDK 8 o superior
- MySQL 5.7 o superior
- MySQL Connector/J 8.0.33

## Configuración de Base de Datos

### Crear base de datos y tablas
Ejecutar en MySQL Workbench las líneas de comandos:
```sql
-- Crear estructura
SOURCE database/database_schema.sql

-- Insertar datos de prueba
SOURCE database/sample_data.sql

```

### Configuración y Flujo de Uso

### Credenciales de Prueba
Editar en `src/Config/DatabaseConnection.java`:
```java
URL: jdbc:mysql://localhost:3306/vehiculo_seguro
USER: root
PASSWORD: [tu_password_mysql]

````
## Compilación y Ejecución

###Compilar:
```bash
javac -cp .:mysql-connector-java-8.0.33.jar src/Main/Main.java
````
### Ejecutar: 
````bash
java -cp .:mysql-connector-java-8.0.33.jar Main.Main
````
### Flujo de Trabajo
1. **Menú Principal** - 5 opciones disponibles
2. **Crear Vehículo** - Con o sin seguro, opcional  
3. **Listar Vehículos** - Todos o filtrados por dominio/marca
4. **Actualizar** - Modificar datos de vehículo y seguro
5. **Eliminar** - Eliminación lógica (no física)
6. **Listar Seguros** - Ver todas las pólizas

## Video
[Ver video en Google Drive](https://drive.google.com/drive/folders/1DW397jAwhqHtrsQOCSIYJrDk1XcsZJA4)
