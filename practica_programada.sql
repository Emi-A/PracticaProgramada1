
drop database if exists practica_web;
drop user if exists usuario_web;
drop user if exists usuario_reportes;

CREATE database practica_web
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

-- usuarios
create user 'usuario_web'@'%' identified by 'la_Clave';
create user 'usuario_reportes'@'%' identified by 'la_Clave_Reportes';

-- permisos 
grant select, insert, update, delete on practica_web.* to 'usuario_web'@'%';
grant select on practica_web.* to 'usuario_reportes'@'%';
flush privileges;

use practica_web;

-- tabla de cursos
create table curso (
  id_curso INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(255),
  creditos INT NOT NULL,
  estado boolean,
  ruta_imagen varchar(1024),
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_curso),
  unique (nombre),
  index ndx_nombre (nombre)
) ENGINE = InnoDB;

-- tabla de estudiantes
create table estudiante (
  id_estudiante INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  correo VARCHAR(120) NOT NULL,
  edad INT NOT NULL,
  id_curso INT NOT NULL,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_estudiante),
  unique (correo),
  index ndx_nombre (nombre),
  index ndx_correo (correo),
  index ndx_id_curso (id_curso),
  foreign key fk_estudiante_curso (id_curso) references curso(id_curso)
) ENGINE = InnoDB;



insert into curso (nombre, descripcion, creditos, estado, ruta_imagen) values
('Programación Web', 'Curso de fundamentos web con MVC', 4, true, null),
('Bases de Datos', 'Modelo relacional y SQL básico', 3, true, null),
('Estructuras de Datos', 'Listas, pilas, colas y árboles', 4, true, null);

insert into estudiante (nombre, correo, edad, id_curso) values
('Ana Perez', 'ana.perez@email.com', 20, 1),
('Luis Mora', 'luis.mora@email.com', 21, 1),
('Maria Soto', 'maria.soto@email.com', 19, 2);