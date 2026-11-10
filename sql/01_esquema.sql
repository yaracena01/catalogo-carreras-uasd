CREATE TABLE facultad (
    id INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(100) NOT NULL,
    CONSTRAINT PK_facultad PRIMARY KEY (id),
    CONSTRAINT UQ_facultad_nombre UNIQUE (nombre)
);
GO

CREATE TABLE escuela (
    id INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(150) NOT NULL,
    facultad_id INT NOT NULL,
    CONSTRAINT PK_escuela PRIMARY KEY (id),
    CONSTRAINT UQ_escuela_nombre UNIQUE (nombre),
    CONSTRAINT FK_escuela_facultad FOREIGN KEY (facultad_id) REFERENCES facultad (id)
);
GO

CREATE TABLE carrera (
    id INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(200) NOT NULL,
    nivel NVARCHAR(10) NOT NULL,
    proyectada BIT NOT NULL DEFAULT 0,
    escuela_id INT NOT NULL,
    CONSTRAINT PK_carrera PRIMARY KEY (id),
    CONSTRAINT CK_carrera_nivel CHECK (nivel IN (N'Técnico', N'Grado')),
    CONSTRAINT FK_carrera_escuela FOREIGN KEY (escuela_id) REFERENCES escuela (id)
);
GO
