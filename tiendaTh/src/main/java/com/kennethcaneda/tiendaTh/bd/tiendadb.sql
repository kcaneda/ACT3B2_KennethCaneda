drop database if exists tiendadb_in5bm;
create database tiendadb_in5bm;
use tiendadb_in5bm;

create table clientes(
dpi_cliente BIGINT primary key not null,
nombre_cliente varchar(60) not null,
apellido_cliente varchar(60) not null,
direccion varchar(100) not null,
estado boolean not null
);


create table usuarios(
codigo_usuario int primary key not null auto_increment,
username varchar(45) not null,
password varchar(60) not null,
email varchar(60) not null,
rol varchar(45) not null,
estado boolean not null
);

create table ventas(
codigo_venta int primary key not null auto_increment,
fecha_venta date not null,
total decimal(10,2) not null,
estado boolean not null,
clientes_dpi_cliente bigint not null,
constraint fk_ventas_clientes
foreign key(clientes_dpi_cliente)
references clientes(dpi_cliente)
on update cascade
on delete cascade,
usuarios_codigo_usuario int not null,
constraint fk_usuarios_ventas
foreign key(usuarios_codigo_usuario)
references usuarios(codigo_usuario)
on update cascade
on delete cascade
);

create table productos(
codigo_producto int primary key auto_increment not null,
nombre_producto varchar(60) not null,
precio decimal(10,2) not null,
stock int not null,
estado boolean not null
);


create table detalleventa(
codigo_detalle_venta int primary key not null auto_increment,
cantidad int not null,
precio_unitario decimal(10,2) not null,
subtotal decimal(10,2) not null,
productos_codigo_producto int not null,
constraint fk_productos_dventas
foreign key(productos_codigo_producto)
references productos(codigo_producto)
on update cascade
on delete cascade,
ventas_codigo_venta int not null,
constraint fk_ventas_dventas
foreign key(ventas_codigo_venta)
references ventas(codigo_venta)
on update cascade
on delete cascade
);
