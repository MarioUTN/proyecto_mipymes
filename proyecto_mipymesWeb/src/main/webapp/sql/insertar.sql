

insert into tipo_usuario (id_tipo_usuario, tipus_nombre) values (1, 'Gerente Empresarial');
insert into tipo_usuario (id_tipo_usuario, tipus_nombre) values (2, 'Vendedor de Productos');
insert into tipo_usuario (id_tipo_usuario, tipus_nombre) values (3, 'Cientes de la Empresa');
insert into tipo_usuario (id_tipo_usuario, tipus_nombre) values (4, 'Cajero de la Empresa');
insert into tipo_usuario (id_tipo_usuario, tipus_nombre) values (5, 'Proveedor de Productos');


--insert into usuario (id_usuario, us_email, us_password, us_activo, us_nombres, us_apellidos, us_fecha_registro, us_tipousuario) values ('CLI-1003938477', 'mario10salazar@gmail.com', 'xikF42WbD9QhYz8Vy0td8w==', true, 'Mario Andres', 'Salazar Anrango', '2022/05/27', 3);
insert into usuario (id_usuario, us_email, us_password, us_activo, us_nombres, us_apellidos, us_fecha_registro, us_tipousuario) values ('ADM-1003938477', 'elenita_admin@gmail.com', 'xikF42WbD9QhYz8Vy0td8w==', true, 'Elena Anabel', 'Rueda Carlosama', '2022/05/27', 1);
insert into usuario (id_usuario, us_email, us_password, us_activo, us_nombres, us_apellidos, us_fecha_registro, us_tipousuario) values ('VEN-1003938477', 'elenita_vend@gmail.com', 'xikF42WbD9QhYz8Vy0td8w==', true, 'Elena Anabel', 'Rueda Carlosama', '2022/05/27',2);

insert into usuario (id_usuario, us_email, us_password, us_activo, us_nombres, us_apellidos, us_fecha_registro, us_tipousuario) values ('ADM-1001590650', 'clarity@utn.edu.ec', 'xikF42WbD9QhYz8Vy0td8w==', true, 'Clarity June', 'July Esthers', '2022/05/27', 1);


insert into vendedor (id_vendedor, ven_usuario, ven_telefono, ven_email, ven_direccion, ven_cedula, ven_sueldo, ven_fecha_inicio) values (2, 'VEN-1003938477', '4921322018', 'elenita_vend@gravatar.com', '863 Evergreen Park', '1003938477', 674.31, '2021/05/13');

--insert into cliente (id_cliente, cli_usuario, cli_telefono, cli_email, cli_direccion, cli_ruc_cedula, cli_fecha_registro, cli_codigo) values (1, 'CLI-1003938477', '5583301739', 'rmeddings0@imgur.com', '1 Havey Trail', '1003938477', '2021/03/24', 'C-7');

insert into gerente (id_gerente, ger_usuario, ger_telefono, ger_email, ger_direccion, ger_cedula) values (1, 'ADM-1003938477', '7023125225', 'elenita_gerente@elen.com.ec', 'Ibarra - El Tejar','1003938477');

insert into gerente (id_gerente, ger_usuario, ger_telefono, ger_email, ger_direccion, ger_cedula) values (2, 'ADM-1001590650', '0989028282', 'masalazara@utn.edu.ec', 'Av. Simon Bolivar Quito','1001590650');


insert into empresa (id_empresa, emp_ruc, emp_nombre_empresa, emp_gerente, emp_matriz, emp_sucursal, emp_pais, emp_provincia, emp_ciudad, emp_telefono, emp_email, emp_fecha_inicio) values (1, '1003938477001', 'ELENITA S.A.', 1, '163 Crownhardt Parkway', '29569 Myrtle Hill', 'Ecuador', 'Pichincha', 'Quito', '7675017505', 'ephelip0@illinois.edu', '2019/11/26');
insert into empresa (id_empresa, emp_ruc, emp_nombre_empresa, emp_gerente, emp_matriz, emp_sucursal, emp_pais, emp_provincia, emp_ciudad, emp_telefono, emp_email, emp_fecha_inicio) values (2, '1003938478001', 'Pasamanería S.A.', 1, '48097 South Avenue', '3274 Pearson Crossing', 'Ecuador', 'Pichincha', 'Calderon', '1038705661', 'amcgettrick1@illinois.edu', '2015/10/02');
insert into empresa (id_empresa, emp_ruc, emp_nombre_empresa, emp_gerente, emp_matriz, emp_sucursal, emp_pais, emp_provincia, emp_ciudad, emp_telefono, emp_email, emp_fecha_inicio) values (3, '1003938479001', 'Terracotton Cía. Ltda. ( Ziro)', 1, '94 Macpherson Drive', '66352 Katie Avenue', 'Ecuador', 'Pichincha', 'Quito', '6821165749', 'edouch2@netvibes.com', '2011/04/16');
insert into empresa (id_empresa, emp_ruc, emp_nombre_empresa, emp_gerente, emp_matriz, emp_sucursal, emp_pais, emp_provincia, emp_ciudad, emp_telefono, emp_email, emp_fecha_inicio) values (4, '1003938470001', 'La Esperanza Comercializadora Wholesaleinn S.A.', 1, '0 Dexter Avenue', '85 Spenser Junction', 'Ecuador', 'Pichincha', 'Cayambe', '5147888360', 'sdarby3@va.gov', '2014/11/20');
insert into empresa (id_empresa, emp_ruc, emp_nombre_empresa, emp_gerente, emp_matriz, emp_sucursal, emp_pais, emp_provincia, emp_ciudad, emp_telefono, emp_email, emp_fecha_inicio) values (5, '1003938471001', 'Printextil.', 1, '31 Sullivan Junction', '22 Rusk Street', 'Ecuador', 'Pichincha', 'Calderon', '5217814423', 'ngoodluck4@fastcompany.com', '2017/01/03');


insert into forma_pago (id_forma_pago, fp_nombre) values (1, 'Pago mediante Cheque');
insert into forma_pago (id_forma_pago, fp_nombre) values (2, 'Pago mediante Tarjeta de Crédito');
insert into forma_pago (id_forma_pago, fp_nombre) values (3, 'Pago mediante Tarjeta de Débito');
insert into forma_pago (id_forma_pago, fp_nombre) values (4, 'Pago mediante Transferencia Electrónica');
insert into forma_pago (id_forma_pago, fp_nombre) values (5, 'Pago mediante Efectivo');

insert into tipo_factura (id_tipo_factura, tf_nombre) values (1, 'Factura de pago a Crédito');
insert into tipo_factura (id_tipo_factura, tf_nombre) values (2, 'Factura Anulada');
insert into tipo_factura (id_tipo_factura, tf_nombre) values (3, 'Factura de pago a Contado');
insert into tipo_factura (id_tipo_factura, tf_nombre) values (4, 'Factura de venta por Anticipación');


insert into tipo_producto (id_tipo_producto, tp_nombre) values (1, 'Ropa de embarazada');
insert into tipo_producto (id_tipo_producto, tp_nombre) values (2, 'Ropa de género');
insert into tipo_producto (id_tipo_producto, tp_nombre) values (3, 'Ropa de talles especiales');
insert into tipo_producto (id_tipo_producto, tp_nombre) values (4, 'Ropa interior');
insert into tipo_producto (id_tipo_producto, tp_nombre) values (5, 'Ropa de trabajo');
insert into tipo_producto (id_tipo_producto, tp_nombre) values (6, 'Ropa de abrigo');
insert into tipo_producto (id_tipo_producto, tp_nombre) values (7, 'Ropa deportiva');
insert into tipo_producto (id_tipo_producto, tp_nombre) values (8, 'Ropa informal');
insert into tipo_producto (id_tipo_producto, tp_nombre) values (9, 'Ropa de etiqueta');


insert into talla_producto (id_talla_producto, talla_nombre) values (1, 'XS (40-42)');
insert into talla_producto (id_talla_producto, talla_nombre) values (2, 'S (44-46)');
insert into talla_producto (id_talla_producto, talla_nombre) values (3, 'M (48-50)');
insert into talla_producto (id_talla_producto, talla_nombre) values (4, 'L (52-54)');
insert into talla_producto (id_talla_producto, talla_nombre) values (5, 'XL (56-58)');
insert into talla_producto (id_talla_producto, talla_nombre) values (6, '2XL (60-62)');
insert into talla_producto (id_talla_producto, talla_nombre) values (7, '3XL (64-66)');


insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (1, 'RI-2245', 11 , 'Bata - Robe', 'Bata - Robe', 3, 4, 7.46, 12, 12.309, '', 4);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (2, 'RI-2331', 5 , 'Calcetines - medias	Socks', 'Calcetines - medias	Socks', 1, 4, 9.86, 12, 16.269, '', 1);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (3, 'RI-2092', 4 , 'Calzoncillos ceñidosBr - iefs', 'Calzoncillos ceñidosBr - iefs', 3, 4, 6.48, 12, 10.692, '', 6);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (4, 'RI-2149', 7 , 'Calzoncillos sueltos - Boxers', 'Calzoncillos sueltos - Boxers', 2, 4, 12.14, 12, 20.031, '', 7);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (5, 'RI-2676', 6 , 'Camisón	Nightgown - camisole', 'Camisón	Nightgown - camisole', 2, 4, 11.66, 12, 19.239, '', 3);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (6, 'RI-2226', 5 , 'Pantimedias	Pantyhose - stocking', 'Pantimedias	Pantyhose - stocking', 3, 4, 10.98, 12, 18.117, '', 2);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (7, 'RI-2818', 9 , 'Pijama	Pajamas - Pyjamas', 'Pijama	Pajamas - Pyjamas', 4, 4, 6.41, 12, 10.5765, '', 2);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (8, 'RI-2175', 3 , 'Slippers - Pantuflas', 'Slippers - Pantuflas', 4, 4, 9.01, 12, 14.8665, '', 3);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (9, 'RI-2089', 10 , 'Sostén - sujetador	Brassier', 'Sostén - sujetador	Brassier', 3, 4, 12.47, 12, 20.5755, '', 5);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (10, 'RI-2556', 7 , 'Sostén - sujetador deportivo', 'Sostén - sujetador deportivo', 2, 4, 11.95, 12, 19.7175, '', 4);


insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (11, 'RIn-2481', 2 , 'Abrigo	Coat', 'Abrigo	Coat', 1, 5, 13.27, 12, 21.8955, '', 3);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (12, 'RIn-2012', 4 , 'Botas	Boots', 'Botas	Boots', 2, 6, 13.76, 12, 22.704, '', 3);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (13, 'RIn-2887', 7 , 'Camiseta de manga larga	Long-sleeve', 'Camiseta de manga larga	Long-sleeve', 3, 5, 13.48, 12, 22.242, '', 4);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (14, 'RIn-2837', 8 , 'Cárdigan	Cardigan', 'Cárdigan	Cardigan', 4, 6, 13.27, 12, 21.8955, '', 5);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (15, 'RIn-2834', 6 , 'Casaca / chaqueta	Jacket', 'Casaca / chaqueta	Jacket', 4, 5, 5.13, 12, 8.4645, '', 2);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (16, 'RIn-2432', 6 , 'Casaca / chaqueta de cuero	Leather jacket', 'Casaca / chaqueta de cuero	Leather jacket', 4, 5, 13.95, 12, 23.0175, '', 2);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (17, 'RIn-2128', 5 , 'Chaleco	Vest', 'Chaleco	Vest', 2, 5, 6.66, 12, 10.989, '', 4);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (18, 'RIn-2523', 9 , 'Gorro	Beanie', 'Gorro	Beanie', 3, 6, 10.11, 12, 16.6815, '', 3);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (19, 'RIn-2288', 11 , 'Impermeable	Raincoat', 'Impermeable	Raincoat', 2, 6, 14.82, 12, 24.453, '', 3);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_proveedor, prod_tipo_producto, prod_pvproveedor, prod_iva, prod_pvpublico, prod_imagen, prod_talla_producto) values (20, 'RIn-2941', 10 , 'Polera / sudadera	Hoodie / sweatshirt', 'Polera / sudadera	Hoodie / sweatshirt', 1, 4, 7.67, 12, 12.6555, '', 3);


--INSERT INTO cabecera_compra(id_cabecera_compra,cabcomp_vendedor,cabcomp_proveedor) VALUES(1,2,3);

/*
INSERT INTO compra_producto(id_compra_producto,comprod_cabecera_compra,comprod_aprobado,comprod_fecha,comprod_subtotal,comprod_iva,comprod_total)
VALUES(1,1,'false','2022/01/11',0,0,0);


INSERT INTO detalle_compra(id_detalle_compra,detcomp_compra_producto,detcomp_codigo_producto,detcomp_nombre_producto,detcomp_descripcion,detcomp_cantidad,detcomp_precio_unit,detcomp_precio_total)
VALUES(6,1,'P-06','Producto 06','Description P06',1,10,10);


INSERT INTO detalle_compra(id_detalle_compra,detcomp_compra_producto,detcomp_codigo_producto,detcomp_nombre_producto,detcomp_descripcion,detcomp_cantidad,detcomp_precio_unit,detcomp_precio_total)
VALUES(7,1,'P-07','Producto 07','Description P07',2,7,14);

INSERT INTO detalle_compra(id_detalle_compra,detcomp_compra_producto,detcomp_codigo_producto,detcomp_nombre_producto,detcomp_descripcion,detcomp_cantidad,detcomp_precio_unit,detcomp_precio_total)
VALUES(8,1,'P-08','Producto 08','Description P08',3,10,30);

INSERT INTO detalle_compra(id_detalle_compra,detcomp_compra_producto,detcomp_codigo_producto,detcomp_nombre_producto,detcomp_descripcion,detcomp_cantidad,detcomp_precio_unit,detcomp_precio_total)
VALUES(9,1,'P-09','Producto 09','Description P09',4,8,32);

*/
