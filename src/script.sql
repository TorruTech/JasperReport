CREATE DATABASE flotaespacial;
USE flotaespacial;

CREATE TABLE naves_espaciales (
    id_nave INT AUTO_INCREMENT PRIMARY KEY,
    nombre_nave VARCHAR(100) NOT NULL,
    clase ENUM('Exploración', 'Combate', 'Transporte') NOT NULL,
    capacidad_tripulacion INT NOT NULL
);

CREATE TABLE tripulantes (
    id_tripulante INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tripulante VARCHAR(100) NOT NULL,
    rango ENUM('Capitán', 'Ingeniero', 'Piloto') NOT NULL,
    id_nave INT,
    FOREIGN KEY (id_nave) REFERENCES Naves_espaciales(id_nave) ON DELETE SET NULL
);

CREATE TABLE misiones (
    id_mision INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT NOT NULL,
    id_nave INT,
    estado ENUM('Completada', 'Fallida', 'En progreso') NOT NULL,
    FOREIGN KEY (id_nave) REFERENCES Naves_espaciales(id_nave) ON DELETE SET NULL
);