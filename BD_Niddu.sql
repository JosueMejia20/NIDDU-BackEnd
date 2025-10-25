DROP DATABASE IF EXISTS Niddu;

CREATE DATABASE Niddu;

USE Niddu;

-- Tabla: Personas
CREATE TABLE Personas (
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE
) DEFAULT CHARSET=utf8;

-- Tabla: Tipo_Usuario
CREATE TABLE Tipo_Usuario (
    id_tipo_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT
) DEFAULT CHARSET=utf8;

-- Tabla: Usuarios
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    id_persona INT NOT NULL,
    id_tipo_usuario INT NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    foto_perfil VARCHAR(255),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('activo', 'inactivo', 'suspendido') DEFAULT 'activo',
    FOREIGN KEY (id_persona) REFERENCES Personas(id_persona) ON DELETE CASCADE,
    FOREIGN KEY (id_tipo_usuario) REFERENCES Tipo_Usuario(id_tipo_usuario)
) DEFAULT CHARSET=utf8;

-- Tabla: Clientes
CREATE TABLE Clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
) DEFAULT CHARSET=utf8;

-- Tabla: Cuidadores
CREATE TABLE Cuidadores (
    id_cuidador INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
) DEFAULT CHARSET=utf8;

-- Tabla: Tipo_Especie
CREATE TABLE Tipo_Especie (
    id_especie INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
) DEFAULT CHARSET=utf8;

-- Tabla: Mascota
CREATE TABLE Mascota (
    id_mascota INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    id_especie INT NOT NULL,
    raza VARCHAR(100),
    edad INT,
    peso DECIMAL(5,2),
    caracteristicas_especiales TEXT,
    alergias TEXT,
    medicamentos TEXT,
    notas_comportamiento TEXT,
    foto VARCHAR(255),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente) ON DELETE CASCADE,
    FOREIGN KEY (id_especie) REFERENCES Tipo_Especie(id_especie)
) DEFAULT CHARSET=utf8;

-- Tabla: Entrenadores
CREATE TABLE Entrenadores (
    id_entrenador INT AUTO_INCREMENT PRIMARY KEY,
    id_cuidador INT NOT NULL,
    certificaciones TEXT,
    especialidades TEXT,
    experiencia_detallada TEXT,
    FOREIGN KEY (id_cuidador) REFERENCES Cuidadores(id_cuidador) ON DELETE CASCADE
) DEFAULT CHARSET=utf8;

-- Tabla: Tipo_Servicio
CREATE TABLE Tipo_Servicio (
    id_tipo_servicio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT
) DEFAULT CHARSET=utf8;

-- Tabla: Servicios
CREATE TABLE Servicios (
    id_servicio INT AUTO_INCREMENT PRIMARY KEY,
    id_cuidador INT NOT NULL,
    descripcion TEXT,
    precio_base DECIMAL(10,2) NOT NULL,
    id_tipo_servicio INT NOT NULL,
    disponibilidad JSON,
    capacidad_maxima INT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,
    promedio_calificacion DECIMAL(3,2) DEFAULT 0.00,
    FOREIGN KEY (id_cuidador) REFERENCES Cuidadores(id_cuidador) ON DELETE CASCADE,
    FOREIGN KEY (id_tipo_servicio) REFERENCES Tipo_Servicio(id_tipo_servicio)
) DEFAULT CHARSET=utf8;

-- Tabla: Solicitudes
CREATE TABLE Solicitudes (
    id_solicitud INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_servicio INT NOT NULL,
    id_mascota INT NOT NULL,
    fecha_solicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_servicio_solicitada DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    estado ENUM('pendiente', 'aceptado', 'rechazado', 'completado', 'cancelado') DEFAULT 'pendiente',
    notas_cliente TEXT,
    notas_cuidador TEXT,
    valor_final DECIMAL(10,2),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente) ON DELETE CASCADE,
    FOREIGN KEY (id_servicio) REFERENCES Servicios(id_servicio),
    FOREIGN KEY (id_mascota) REFERENCES Mascota(id_mascota)
) DEFAULT CHARSET=utf8;

-- Tabla: Reseñas_Servicios
CREATE TABLE Reseñas_Servicios (
    id_resena_servicio INT AUTO_INCREMENT PRIMARY KEY,
    id_solicitud INT NOT NULL,
    id_cliente INT NOT NULL,
    id_servicio INT NOT NULL,
    calificacion INT NOT NULL CHECK (calificacion BETWEEN 1 AND 5),
    comentario TEXT,
    fecha_resena TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_solicitud) REFERENCES Solicitudes(id_solicitud) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_servicio) REFERENCES Servicios(id_servicio)
) DEFAULT CHARSET=utf8;

-- Tabla: Reseñas_Cuidador
CREATE TABLE Reseñas_Cuidador (
    id_resena_cuidador INT AUTO_INCREMENT PRIMARY KEY,
    id_solicitud INT NOT NULL,
    id_cliente INT NOT NULL,
    id_cuidador INT NOT NULL,
    calificacion INT NOT NULL CHECK (calificacion BETWEEN 1 AND 5),
    comentario TEXT,
    fecha_resena TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_solicitud) REFERENCES Solicitudes(id_solicitud) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_cuidador) REFERENCES Cuidadores(id_cuidador)
) DEFAULT CHARSET=utf8;

-- Tabla: Tipo_Pago
CREATE TABLE Tipo_Pago (
    id_tipo_pago INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
) DEFAULT CHARSET=utf8;

-- Tabla: Pagos
CREATE TABLE Pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_solicitud INT NOT NULL,
    id_cliente INT NOT NULL,
    id_tipo_pago INT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    estado_pago ENUM('pendiente', 'completado', 'fallido', 'reembolsado') DEFAULT 'pendiente',
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_solicitud) REFERENCES Solicitudes(id_solicitud) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_tipo_pago) REFERENCES Tipo_Pago(id_tipo_pago)
) DEFAULT CHARSET=utf8;

-- Tabla: Direcciones
CREATE TABLE Direcciones (
    id_direccion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    departamento VARCHAR(100),
    ciudad VARCHAR(100),
    colonia VARCHAR(100),
    pais VARCHAR(100),
    activa BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
) DEFAULT CHARSET=utf8;

-- Tabla: Documentos_Verificacion
CREATE TABLE Documentos_Verificacion (
    id_documento INT AUTO_INCREMENT PRIMARY KEY,
    id_cuidador INT NOT NULL,
    tipo_documento VARCHAR(100) NOT NULL,
    ruta_archivo VARCHAR(255) NOT NULL,
    fecha_subida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado_verificacion ENUM('pendiente', 'aprobado', 'rechazado') DEFAULT 'pendiente',
    comentarios_moderador TEXT,
    fecha_revision TIMESTAMP NULL,
    id_moderador INT,
    FOREIGN KEY (id_cuidador) REFERENCES Cuidadores(id_cuidador) ON DELETE CASCADE,
    FOREIGN KEY (id_moderador) REFERENCES Usuarios(id_usuario)
) DEFAULT CHARSET=utf8;