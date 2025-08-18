-- ==============================================
-- Migración inicial ForoHub
-- Tablas: usuarios, cursos, topicos
-- ==============================================

-- Tabla: usuarios
CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          email VARCHAR(150) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL
);

-- Tabla: cursos
CREATE TABLE cursos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(150) NOT NULL,
                        categoria VARCHAR(100) NOT NULL
);

-- Tabla: topicos
CREATE TABLE topicos (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         titulo VARCHAR(255) NOT NULL,
                         mensaje TEXT NOT NULL,
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         estado BIT,

    -- Claves foráneas
                         usuario_id BIGINT NOT NULL,
                         curso_id BIGINT NOT NULL,

                         CONSTRAINT fk_topico_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                         CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
