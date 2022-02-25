
-- 757 GB	16:32 PM	12-20-2021
--	genero, persona, usuario, vendedor, cliente, gerente, factura, cabecera_factura, detalle_factura, 
--	producto, tipo_producto, estado_cuenta, cabecera_estado_cuenta, detalle_estado_cuenta


-- 1	usuario administrador/gerente 	2	vendedor	3	cliente 
CREATE TABLE tipo_usuario(
id_tipo_usuario SERIAL 	NOT NULL,
	tipus_nombre VARCHAR(25) NOT NULL,
	CONSTRAINT pk_tip_us PRIMARY KEY(id_tipo_usuario)
);




/*

drop table detalle_abono;
drop table estado_pedido;
drop table detalle_factura;
drop table detalle_ingreso;
drop table factura_ingreso;
drop table cabecera_ingreso;
drop table detalle_compra;
drop table compra_producto;
drop table cabecera_compra;
drop table producto;
drop table talla_producto;
drop table tipo_producto;
drop table factura;
drop table tipo_factura;
drop table forma_pago;
drop table cabecera_factura;
drop table empresa;
drop table gerente;
drop table vendedor;
drop table usuario;
drop table cliente;
drop table tipo_usuario;
*/

CREATE TABLE usuario(
id_usuario VARCHAR(25) NOT NULL,
	us_email VARCHAR(60) NOT NULL UNIQUE,
	us_password VARCHAR(100) NOT NULL,
	us_activo BOOLEAN NOT NULL,
	us_nombres VARCHAR(60) NOT NULL,
	us_apellidos VARCHAR(60) NOT NULL,
	us_fecha_registro DATE NOT NULL,
	us_tipousuario INTEGER NOT NULL,
	CONSTRAINT pk_usuario PRIMARY KEY(id_usuario),
	CONSTRAINT fk_tus_us FOREIGN KEY(us_tipousuario) REFERENCES tipo_usuario(id_tipo_usuario)
);

CREATE TABLE vendedor(
id_vendedor SERIAL NOT NULL,
	ven_usuario VARCHAR(25) NOT NULL UNIQUE,
	ven_telefono VARCHAR(15) NOT NULL,
	ven_email VARCHAR(60) NOT NULL UNIQUE,
	ven_direccion VARCHAR(100) NOT NULL,
	ven_cedula VARCHAR(10) NOT NULL UNIQUE,
	ven_sueldo NUMERIC(7,2) NOT NULL,
	ven_fecha_inicio DATE NOT NULL,
	CONSTRAINT pk_vendedor PRIMARY KEY(id_vendedor),
	CONSTRAINT fk_us_vend FOREIGN KEY(ven_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE cliente(
id_cliente SERIAL NOT NULL,
	cli_usuario VARCHAR(25) NOT NULL UNIQUE,
	cli_telefono VARCHAR(15) NOT NULL,
	cli_email VARCHAR(60) NOT NULL UNIQUE,
	cli_direccion VARCHAR(100) NOT NULL,
	cli_ruc_cedula VARCHAR(13) NOT NULL UNIQUE,
	cli_fecha_registro DATE NOT NULL,
	cli_codigo VARCHAR(10) NOT NULL UNIQUE,
	CONSTRAINT pk_cliente PRIMARY KEY(id_cliente),
	CONSTRAINT fk_us_client FOREIGN KEY(cli_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE gerente(
id_gerente SERIAL NOT NULL,
	ger_usuario VARCHAR(25) NOT NULL UNIQUE,
	ger_telefono VARCHAR(15) NOT NULL,
	ger_email VARCHAR(60) NOT NULL UNIQUE,
	ger_direccion VARCHAR(100) NOT NULL,
	ger_cedula VARCHAR(10) NOT NULL UNIQUE,
	CONSTRAINT pk_gerente PRIMARY KEY(id_gerente),
	CONSTRAINT fk_us_vend FOREIGN KEY(ger_usuario) REFERENCES usuario(id_usuario)
);


CREATE TABLE empresa(
id_empresa SERIAL NOT NULL,
	emp_ruc VARCHAR(13) NOT NULL,
	emp_nombre_empresa VARCHAR(100) NOT NULL,
	emp_gerente INTEGER NOT NULL,
	emp_matriz VARCHAR(100) NOT NULL,
	emp_sucursal VARCHAR(100),
	emp_pais VARCHAR(50) NOT NULL,
	emp_provincia VARCHAR(50) NOT NULL,
	emp_ciudad VARCHAR(50) NOT NULL,
	emp_telefono VARCHAR(15) NOT NULL,
	emp_email VARCHAR(60) NOT NULL UNIQUE,
	emp_fecha_inicio DATE NOT NULL,
	CONSTRAINT pk_empresa PRIMARY KEY(id_empresa),
	CONSTRAINT fk_ger_emp FOREIGN KEY(emp_gerente) REFERENCES gerente(id_gerente)
);

CREATE TABLE cabecera_factura(
id_cabecera_factura SERIAL NOT NULL,
	cab_cliente INTEGER NOT NULL,
	cab_empresa INTEGER NOT NULL,
	cab_vendedor INTEGER NOT NULL,
	CONSTRAINT pk_cab_fact PRIMARY KEY(id_cabecera_factura),
	CONSTRAINT fk_cli_cabfact FOREIGN KEY(cab_cliente) REFERENCES cliente(id_cliente),
	CONSTRAINT fk_vend_cabfact FOREIGN KEY(cab_vendedor) REFERENCES vendedor(id_vendedor),
	CONSTRAINT fk_emp_cabfact FOREIGN KEY(cab_empresa) REFERENCES empresa(id_empresa)
);
-- Forma Pago: E
CREATE TABLE forma_pago(
id_forma_pago SERIAL NOT NULL,
	fp_nombre VARCHAR(50) NOT NULL,
	CONSTRAINT pk_forma_pago PRIMARY KEY(id_forma_pago)
);

-- factura a credito o contado, anulada
CREATE TABLE tipo_factura(
id_tipo_factura SERIAL NOT NULL,
	tf_nombre VARCHAR(50) NOT NULL,
	CONSTRAINT pk_tip_fact PRIMARY KEY(id_tipo_factura)
);

CREATE TABLE factura(
id_factura SERIAL NOT NULL,
	fact_numero_factura VARCHAR(20) NOT NULL UNIQUE,
	fact_fecha_emision DATE NOT NULL,
	fact_fecha_remision DATE NOT NULL,
	fact_fecha_autorizacion DATE NOT NULL,
	fact_forma_pago INTEGER NOT NULL,
	fact_tipo_factura INTEGER NOT NULL,
	fact_cabecera_factura INTEGER NOT NULL,
	fact_estado BOOLEAN NOT NULL,
	fact_entregado BOOLEAN NOT NULL,
	fact_descuento NUMERIC(4,2),
	fact_subtotal NUMERIC(8,2),
	fact_iva NUMERIC(8,2),
	fact_total NUMERIC(8,2),
	CONSTRAINT pk_factura PRIMARY KEY(id_factura),
	CONSTRAINT fk_fp_fact FOREIGN KEY(fact_forma_pago) REFERENCES forma_pago(id_forma_pago),
	CONSTRAINT fk_tf_fact FOREIGN KEY(fact_tipo_factura) REFERENCES tipo_factura(id_tipo_factura),
	CONSTRAINT fk_cab_fact_fact FOREIGN KEY(fact_cabecera_factura) REFERENCES cabecera_factura(id_cabecera_factura)
);

-- Deportiva, Casual, Calzado, Formal
CREATE TABLE tipo_producto(
id_tipo_producto SERIAL NOT NULL,
	tp_nombre VARCHAR(50) NOT NULL,
	CONSTRAINT pk_tip_prod PRIMARY KEY(id_tipo_producto)
);

CREATE TABLE talla_producto(
id_talla_producto SERIAL NOT NULL,
	talla_nombre VARCHAR(15) NOT NULL,
	CONSTRAINT pk_talla PRIMARY KEY(id_talla_producto)
);

CREATE TABLE producto(
id_producto SERIAL NOT NULL,
	prod_codigo VARCHAR(15) NOT NULL UNIQUE,
	prod_cantidad INTEGER NOT NULL,
	prod_nombre VARCHAR(60) NOT NULL,
	prod_descripcion VARCHAR(100) NOT NULL,
	prod_proveedor INTEGER NOT NULL,
	prod_tipo_producto INTEGER NOT NULL,
	prod_pvproveedor NUMERIC(7,2) NOT NULL,
	prod_iva NUMERIC(4,2) NOT NULL,
	prod_pvpublico NUMERIC(7,2) NOT NULL,
	prod_imagen VARCHAR(100),
	prod_talla_producto INTEGER NOT NULL,
	CONSTRAINT pk_producto PRIMARY KEY(id_producto),
	CONSTRAINT fk_emp_prod FOREIGN KEY(prod_proveedor) REFERENCES empresa(id_empresa),
	CONSTRAINT fk_tp_prod FOREIGN KEY(prod_tipo_producto) REFERENCES tipo_producto(id_tipo_producto),
	CONSTRAINT fk_talla_prod FOREIGN KEY(prod_talla_producto) REFERENCES talla_producto(id_talla_producto)
);



-- ************* Comprar producto al proveedor ****************************

CREATE TABLE cabecera_compra(
id_cabecera_compra SERIAL NOT NULL,
	cabcomp_vendedor INTEGER NOT NULL,
	cabcomp_proveedor INTEGER NOT NULL,
	CONSTRAINT pk_cab_comp PRIMARY KEY(id_cabecera_compra),
	CONSTRAINT fk_vend_cc FOREIGN KEY(cabcomp_vendedor) REFERENCES vendedor(id_vendedor),
	CONSTRAINT fk_emp_cc FOREIGN KEY(cabcomp_proveedor) REFERENCES empresa(id_empresa)
);


CREATE TABLE compra_producto(
id_compra_producto SERIAL NOT NULL,
	comprod_cabecera_compra INTEGER NOT NULL,
	comprod_aprobado BOOLEAN,
	comprod_fecha DATE NOT NULL,
	comprod_subtotal NUMERIC(8,2),
	comprod_iva NUMERIC(8,2),
	comprod_total NUMERIC(8,2),
	CONSTRAINT pk_cp PRIMARY KEY(id_compra_producto),
	CONSTRAINT fk_cc_cp FOREIGN KEY(comprod_cabecera_compra) REFERENCES cabecera_compra(id_cabecera_compra)
);


CREATE TABLE detalle_compra(
id_detalle_compra SERIAL NOT NULL,
	detcomp_compra_producto INTEGER NOT NULL,
	detcomp_codigo_producto VARCHAR(15),
	detcomp_nombre_producto VARCHAR(60) NOT NULL,
	detcomp_descripcion VARCHAR(100),
	detcomp_cantidad INTEGER NOT NULL,
	detcomp_precio_unit NUMERIC(7,2) NOT NULL,
	detcomp_precio_total NUMERIC(7,2) NOT NULL,
	CONSTRAINT pk_det_comp PRIMARY KEY(id_detalle_compra),
	CONSTRAINT fk_cp_dcomp FOREIGN KEY(detcomp_compra_producto) REFERENCES compra_producto(id_compra_producto)
);

-- *************** Ingresar un nuevo producto de la compra realizada ****************************

CREATE TABLE cabecera_ingreso(
id_cabecera_ingreso SERIAL NOT NULL,
	cabing_proveedor INTEGER NOT NULL,
	cabing_vendedor INTEGER NOT NULL,
	cabing_fecha_ingreso DATE NOT NULL,
	cabing_autorizacion VARCHAR(15) NOT NULL,
	cabing_numero_factura VARCHAR(15) NOT NULL,
	cabing_fecha_emision DATE NOT NULL,
	cabing_fecha_caducacion DATE NOT NULL,
	cabing_empresa integer not null,
	CONSTRAINT pk_cabing PRIMARY KEY(id_cabecera_ingreso),
	CONSTRAINT fk_vend_cabing FOREIGN KEY(cabing_vendedor) REFERENCES vendedor(id_vendedor),
	CONSTRAINT fk_prov_cabing FOREIGN KEY(cabing_proveedor) REFERENCES empresa(id_empresa),
	CONSTRAINT fk_emp_cabing FOREIGN KEY(cabing_empresa) REFERENCES empresa(id_empresa)
);



CREATE TABLE factura_ingreso(
id_factura_ingreso SERIAL NOT NULL,
	facting_cabecera_ingreso INTEGER NOT NULL,
	facting_subtotal NUMERIC(8,2),
	facting_iva NUMERIC(8,2),
	facting_total NUMERIC(8,2),
	CONSTRAINT pk_facing PRIMARY KEY(id_factura_ingreso),
	CONSTRAINT fk_cabing_facting FOREIGN KEY(facting_cabecera_ingreso) REFERENCES cabecera_ingreso(id_cabecera_ingreso)
);



CREATE TABLE detalle_ingreso(
id_detalle_ingreso SERIAL NOT NULL,
	deting_factura_ingreso INTEGER NOT NULL,
	deting_producto INTEGER NOT NULL,
	deting_cantidad INTEGER NOT NULL,
	deting_precio_unitario NUMERIC(8,2) NOT NULL,
	deting_iva NUMERIC(8,2) NOT NULL,
	deting_subtotal NUMERIC(8,2),
	deting_precio_total NUMERIC(8,2) NOT NULL,
	CONSTRAINT pk_deting PRIMARY KEY(id_detalle_ingreso),
	CONSTRAINT fk_facting_deting FOREIGN KEY(deting_factura_ingreso) REFERENCES factura_ingreso(id_factura_ingreso),
	CONSTRAINT fk_prod_deting FOREIGN KEY(deting_producto) REFERENCES producto(id_producto)
);

-- ************************************** FIN ******************************* ---------


CREATE TABLE detalle_factura(
id_detalle_factura SERIAL NOT NULL,
	det_cantidad INTEGER NOT NULL,
	det_producto INTEGER NOT NULL,
	det_factura INTEGER NOT NULL,
	det_precio_unitario NUMERIC(8,2) NOT NULL,
	det_iva NUMERIC(8,2) NOT NULL,
	det_subtotal NUMERIC(8,2),
	det_precio_total NUMERIC(8,2) NOT NULL,
	CONSTRAINT pk_det_fact PRIMARY KEY(id_detalle_factura),
	CONSTRAINT fk_prod_detf FOREIGN KEY(det_producto) REFERENCES producto(id_producto),
	CONSTRAINT fk_fact_detf FOREIGN KEY(det_factura) REFERENCES factura(id_factura)
);


--	genero, persona, usuario, vendedor, cliente, gerente, factura, cabecera_factura, detalle_factura, 
--	producto, tipo_producto, estado_pedido, detalle_abono
CREATE TABLE estado_pedido(
id_estado_pedido SERIAL NOT NULL,
	est_factura INTEGER NOT NULL,
	est_valor_total NUMERIC(8,2) NOT NULL,
	est_saldo NUMERIC(8,2) NOT NULL,
	est_fecha_emision DATE NOT NULL,
	CONSTRAINT pk_est_ped PRIMARY KEY(id_estado_pedido),
	CONSTRAINT fk_fact_ep FOREIGN KEY(est_factura) REFERENCES factura(id_factura)
);


CREATE TABLE detalle_abono(
id_detalle_abono SERIAL NOT NULL,
	detab_estado_pedido INTEGER NOT NULL,
	detab_abono NUMERIC(8,2) NOT NULL,
	detab_saldo_anterior NUMERIC(8,2) NOT NULL,
	detab_saldo_actual NUMERIC(8,2) NOT NULL,
	detab_fecha_abono DATE NOT NULL,
	detab_cliente INTEGER NOT NULL,
	detab_vendedor INTEGER NOT NULL,
	CONSTRAINT pk_det_ab PRIMARY KEY(id_detalle_abono),
	CONSTRAINT fk_ep_da FOREIGN KEY(detab_estado_pedido) REFERENCES estado_pedido(id_estado_pedido),
	CONSTRAINT fk_cli_da FOREIGN KEY(detab_cliente) REFERENCES cliente(id_cliente),
	CONSTRAINT fk_vend_da FOREIGN KEY(detab_vendedor) REFERENCES vendedor(id_vendedor)
);


-- https://utneduec-my.sharepoint.com/:p:/g/personal/cdquinterop_utn_edu_ec/EdhenApwzbtKnRLqjw05F8EB-oNbB5PBfDhsbf8blBoxsw?rtime=9Rpn-xLU2Ug
-- mail -s "Hola Mario" -a mipymes.sql salazar10mario.1995@gmail.com < mipymes.sql


-- Ver Facturas por tipo, forma pago, cliente, vendedor, 

-- pg_dump -U usuario -W -h host basename > basename.sql
-- psql -U username -W -h host basename < basename.sql

select ue.us_nombres||' '||ue.us_apellidos as nombres_gerempresa, e.id_empresa as id_empresa , e.emp_ruc as ruc_empresa, 
e.emp_nombre_empresa as nombre_empresa, e.emp_pais || ' - '|| e.emp_provincia ||' - '|| e.emp_ciudad as ppc_empresa, e.emp_matriz as direccion_empresa,
 e.emp_telefono as telefono_empresa, e.emp_email as email_empresa,
cp.id_compra_producto, v.ven_cedula, uv.us_nombres ||' '|| uv.us_apellidos as nombres_vendedor,
p.emp_ruc, p.emp_nombre_empresa, p.emp_pais, p.emp_provincia, p.emp_ciudad, p.emp_matriz, p.emp_telefono, p.emp_email,
cp.comprod_fecha, cp.comprod_subtotal, cp.comprod_iva, cp.comprod_total,
dc.detcomp_codigo_producto, dc.detcomp_nombre_producto ||' '|| dc.detcomp_descripcion as nombre_producto, 
dc.detcomp_cantidad, dc.detcomp_precio_unit, dc.detcomp_precio_total,
g.ger_cedula, ug.us_nombres ||' '|| ug.us_apellidos as nombres_gerente, ug.us_email
from vendedor v, empresa e, empresa p, cabecera_compra cc, usuario uv, usuario ug, usuario ue, compra_producto cp, detalle_compra dc, gerente g, gerente ge
where v.ven_usuario=uv.id_usuario
and g.ger_usuario=ug.id_usuario and p.emp_gerente=g.id_gerente
and p.id_empresa=cc.cabcomp_proveedor
and dc.detcomp_compra_producto=cp.id_compra_producto
and cc.id_cabecera_compra=cp.comprod_cabecera_compra
and ge.ger_usuario=ue.id_usuario and e.emp_gerente=ge.id_gerente
and e.id_empresa=1
and cp.id_compra_producto=7;


select fi.id_factura_ingreso, fi.facting_subtotal, fi.facting_iva, fi.facting_total, di.deting_cantidad,
ci.cabing_fecha_ingreso, ci.cabing_fecha_emision, ci.cabing_fecha_caducacion, ci.cabing_autorizacion, ci.cabing_numero_factura,
pr.prod_codigo, pr.prod_nombre, pr.prod_descripcion, di.deting_precio_unitario, di.deting_subtotal, di.deting_iva, di.deting_precio_total,
e.id_empresa as id_empresa, e.emp_ruc as ruc_empresa, e.emp_telefono as telf_empresa, 
e.emp_email as email_empresa, e.emp_nombre_empresa as nombre_empresa, e.emp_matriz as matriz_empresa, 
e.emp_pais||' - '|| e.emp_provincia||' - '|| e.emp_ciudad as ubicacion_empresa,
p.id_empresa as id_proveedor, p.emp_ruc as ruc_proveedor, p.emp_telefono as telf_proveedor, p.emp_email as email_proveedor, 
p.emp_nombre_empresa as nombre_proveedor, p.emp_matriz as matriz_proveedor, 
p.emp_pais||' - '||p.emp_provincia||' - '||p.emp_ciudad as ubicacion_proveedor,
v.ven_cedula as cedula_vendedor, v.ven_email as email_vendedor, 
uv.us_nombres ||' '|| uv.us_apellidos as nombres_vendedor, uv.us_email as correo_vendedor,
ge.ger_cedula as ge_cedula, ge.ger_email as ge_email, ue.us_nombres||' '||ue.us_apellidos as ge_nombres, ue.us_email as ge_correo,
gp.ger_cedula as gp_cedula, gp.ger_email as gp_email, up.us_nombres||' '||up.us_apellidos as gp_nombres, up.us_email as gp_correo
from factura_ingreso fi, detalle_ingreso di, producto pr, cabecera_ingreso ci, empresa e, 
empresa p, vendedor v, usuario uv, gerente ge, usuario ue, gerente gp, usuario up
where fi.id_factura_ingreso=di.deting_factura_ingreso
and ci.id_cabecera_ingreso = fi.facting_cabecera_ingreso and pr.id_producto = di.deting_producto 
and di.deting_factura_ingreso = fi.id_factura_ingreso and ge.id_gerente = e.emp_gerente and gp.id_gerente = p.emp_gerente 
and ci.cabing_proveedor = p.id_empresa and uv.id_usuario = v.ven_usuario and ue.id_usuario = ge.ger_usuario 
and up.id_usuario = gp.ger_usuario and e.id_empresa = 1
and ci.cabing_vendedor = v.id_vendedor and fi.id_factura_ingreso = 1
group by fi.id_factura_ingreso , di.deting_cantidad ,ci.cabing_fecha_ingreso ,ci.cabing_fecha_emision ,
ci.cabing_fecha_caducacion , ci.cabing_autorizacion ,ci.cabing_numero_factura ,pr.prod_codigo ,pr.prod_nombre ,pr.prod_descripcion ,di.deting_precio_unitario ,
di.deting_subtotal , di.deting_iva , di.deting_precio_total ,e.id_empresa ,p.id_empresa ,v.ven_cedula ,v.ven_email ,uv.us_nombres , uv.us_apellidos ,uv.us_email ,
ge.ger_cedula, ge.ger_email, ue.us_nombres, ue.us_apellidos, ue.us_email,
gp.ger_cedula, gp.ger_email, up.us_nombres, up.us_apellidos, up.us_email;


/**

<p:commandButton icon="fa fa-trash"
									styleClass="rounded-button ui-button-danger">
								</p:commandButton>
								<p:commandButton icon="fa fa-file-pdf-o"
									styleClass="rounded-button ui-button-help" actionListener="#{beanCompras.actionListenerInsertarPedido(cp.idCompraProducto)}">
								</p:commandButton>
								<p:commandButton icon="fa fa-send"
									styleClass="rounded-button ui-button-warning">
								</p:commandButton>

**/



select fi.id_factura_ingreso, fi.facting_subtotal, fi.facting_iva, fi.facting_total, di.deting_cantidad,
ci.cabing_fecha_ingreso, ci.cabing_fecha_emision, ci.cabing_fecha_caducacion, ci.cabing_autorizacion, ci.cabing_numero_factura,
pr.prod_codigo, pr.prod_nombre, pr.prod_descripcion, di.deting_precio_unitario, di.deting_subtotal, di.deting_iva, di.deting_precio_total,
e.id_empresa as id_empresa, e.emp_ruc as ruc_empresa, e.emp_telefono as telf_empresa, 
e.emp_email as email_empresa, e.emp_nombre_empresa as nombre_empresa, e.emp_matriz as matriz_empresa, 
e.emp_pais||' - '|| e.emp_provincia||' - '|| e.emp_ciudad as ubicacion_empresa,
p.id_empresa as id_proveedor, p.emp_ruc as ruc_proveedor, p.emp_telefono as telf_proveedor, p.emp_email as email_proveedor, 
p.emp_nombre_empresa as nombre_proveedor, p.emp_matriz as matriz_proveedor, 
p.emp_pais||' - '||p.emp_provincia||' - '||p.emp_ciudad as ubicacion_proveedor,
v.ven_cedula as cedula_vendedor, v.ven_email as email_vendedor, 
uv.us_nombres ||' '|| uv.us_apellidos as nombres_vendedor, uv.us_email as correo_vendedor,
ge.ger_cedula as ge_cedula, ge.ger_email as ge_email, ue.us_nombres||' '||ue.us_apellidos as ge_nombres, ue.us_email as ge_correo,
gp.ger_cedula as gp_cedula, gp.ger_email as gp_email, up.us_nombres||' '||up.us_apellidos as gp_nombres, up.us_email as gp_correo
from factura_ingreso fi, detalle_ingreso di, producto pr, cabecera_ingreso ci, empresa e, 
empresa p, vendedor v, usuario uv, gerente ge, usuario ue, gerente gp, usuario up
where fi.id_factura_ingreso=di.deting_factura_ingreso
and ci.id_cabecera_ingreso = fi.facting_cabecera_ingreso and pr.id_producto = di.deting_producto 
and di.deting_factura_ingreso = fi.id_factura_ingreso and ge.id_gerente = e.emp_gerente and gp.id_gerente = p.emp_gerente 
and ci.cabing_proveedor = p.id_empresa and uv.id_usuario = v.ven_usuario and ue.id_usuario = ge.ger_usuario 
and up.id_usuario = gp.ger_usuario
and ci.cabing_vendedor = v.id_vendedor and fi.id_factura_ingreso = 1
group by fi.id_factura_ingreso , di.deting_cantidad ,ci.cabing_fecha_ingreso ,ci.cabing_fecha_emision ,
ci.cabing_fecha_caducacion , ci.cabing_autorizacion ,ci.cabing_numero_factura ,pr.prod_codigo ,pr.prod_nombre ,pr.prod_descripcion ,di.deting_precio_unitario ,
di.deting_subtotal , di.deting_iva , di.deting_precio_total ,e.id_empresa ,p.id_empresa ,v.ven_cedula ,v.ven_email ,uv.us_nombres , uv.us_apellidos ,uv.us_email ,
ge.ger_cedula, ge.ger_email, ue.us_nombres, ue.us_apellidos, ue.us_email,
gp.ger_cedula, gp.ger_email, up.us_nombres, up.us_apellidos, up.us_email;




create table cliente(
id_cliente VARCHAR(10) NOT NULL,
	nombres VARCHAR(60) NOT NULL,
	apellidos VARCHAR(60) NOT NULL,
	telefono VARCHAR(15) NOT NULL,
	email VARCHAR(60) NOT NULL UNIQUE,
	direccion VARCHAR(100) NOT NULL,
	CONSTRAINT pk_cliente PRIMARY KEY(id_cliente)
);

CREATE TABLE producto(
id_producto SERIAL NOT NULL,
	prod_codigo VARCHAR(15) NOT NULL UNIQUE,
	prod_cantidad INTEGER NOT NULL,
	prod_nombre VARCHAR(60) NOT NULL,
	prod_descripcion VARCHAR(100) NOT NULL,
	prod_iva NUMERIC(4,2) NOT NULL,
	prod_pvpublico NUMERIC(7,2) NOT NULL,
	CONSTRAINT pk_producto PRIMARY KEY(id_producto)
);

CREATE TABLE cabecera_proforma(
id_cabecera_proforma SERIAL NOT NULL,
	fecha_emision DATE NOT NULL,
	cliente VARCHAR(10) NOT NULL,
	CONSTRAINT pk_cab_prof PRIMARY KEY(id_cabecera_proforma),
	CONSTRAINT fk_cp_cliente FOREIGN KEY(cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE proforma(
id_proforma SERIAL NOT NULL,
	numero_proforma VARCHAR(10) NOT NULL UNIQUE,
	vendedor VARCHAR(60) NOT NULL,
	estado BOOLEAN NOT NULL,
	concepto VARCHAR(100) NOT NULL,
	cabecera_proforma INTEGER NOT NULL,
	valor_subtotal NUMERIC(8,2) NOT NULL,
	valor_iva NUMERIC(8,2) NOT NULL,
	valor_total NUMERIC(8,2) NOT NULL,
	CONSTRAINT pk_proforma PRIMARY KEY(id_proforma),
	CONSTRAINT fk_cp_proforma FOREIGN KEY(cabecera_proforma) REFERENCES cabecera_proforma(id_cabecera_proforma)
);

CREATE TABLE detalle_proforma(
id_detalle_proforma SERIAL NOT NULL,
	producto INTEGER NOT NULL,
	proforma INTEGER NOT NULL,
	cantidad INTEGER NOT NULL,
	precio_unitario NUMERIC(8,2) NOT NULL,
	valor_subtotal NUMERIC(8,2) NOT NULL,
	valor_iva NUMERIC(8,2) NOT NULL,
	valor_total NUMERIC(8,2) NOT NULL,
	CONSTRAINT pk_dp PRIMARY KEY(id_detalle_proforma),
	CONSTRAINT fk_prod_dp FOREIGN KEY(producto) REFERENCES producto(id_producto),
	CONSTRAINT fk_prof_dp FOREIGN KEY(proforma) REFERENCES proforma(id_proforma)
);









insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (1, '57955-5063', 20, 'Volkswagen', 'GTI', 12, 2901.15);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (2, '0019-9094', 19, 'Infiniti', 'G', 0, 3096.27);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (3, '49738-096', 14, 'Nissan', 'Titan', 12, 3600.7);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (4, '59762-5091', 19, 'Nissan', 'Xterra', 0, 3093.57);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (5, '24236-199', 18, 'Mitsubishi', 'Challenger', 12, 2306.12);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (6, '14222-425', 10, 'Volkswagen', 'Routan', 12, 3594.51);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (7, '10096-0295', 15, 'Nissan', 'Altima', 0, 3277.11);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (8, '49281-225', 14, 'Dodge', 'Durango', 0, 4703.49);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (9, '49436-292', 15, 'Kia', 'Soul', 0, 2517.34);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (10, '0069-0176', 18, 'Rolls-Royce', 'Phantom', 12, 2330.79);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (11, '0093-0309', 17, 'Infiniti', 'QX56', 12, 4534.68);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (12, '75862-005', 18, 'Jaguar', 'XF', 0, 3730.59);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (13, '68462-392', 18, 'Mercedes-Benz', 'SLK-Class', 12, 3717.04);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (14, '55111-137', 20, 'Chrysler', 'New Yorker', 0, 4414.81);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (15, '49288-0370', 13, 'Volkswagen', 'Passat', 12, 2199.23);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (16, '41616-636', 13, 'Ford', 'Ranger', 12, 4088.15);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (17, '0268-6233', 13, 'BMW', 'Z8', 12, 2737.9);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (18, '0703-3015', 16, 'Chevrolet', 'Impala', 12, 3231.89);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (19, '47335-788', 12, 'Mercury', 'Sable', 12, 3612.52);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (20, '0603-9418', 19, 'Chevrolet', 'Monte Carlo', 0, 4067.03);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (21, '49281-752', 20, 'Toyota', 'Venza', 12, 3205.23);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (22, '0781-3408', 11, 'Jeep', 'Commander', 12, 2545.25);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (23, '15127-833', 20, 'Mercury', 'Mountaineer', 0, 3030.24);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (24, '60575-212', 18, 'Honda', 'FCX Clarity', 12, 3146.36);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (25, '29500-9085', 17, 'Volkswagen', 'rio', 12, 2183.96);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (26, '43419-312', 16, 'Mazda', 'B-Series Plus', 12, 2882.24);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (27, '53808-0947', 19, 'Mercury', 'Cougar', 0, 4153.97);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (28, '61957-1821', 19, 'Porsche', '911', 0, 2683.6);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (29, '68428-054', 17, 'Ford', 'Galaxie', 0, 3031.37);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (30, '42291-662', 18, 'Honda', 'Insight', 12, 3723.27);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (31, '0173-0758', 17, 'Mercedes-Benz', 'SLS AMG', 12, 2162.45);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (32, '49288-0407', 17, 'Porsche', '944', 12, 4969.45);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (33, '57520-0303', 20, 'Volkswagen', 'Routan', 0, 2253.07);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (34, '37000-070', 18, 'Saab', '9-5', 12, 2241.02);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (35, '51808-201', 17, 'Toyota', 'Paseo', 12, 2328.69);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (36, '42406-007', 17, 'Acura', 'TL', 12, 3812.56);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (37, '16110-260', 11, 'Mercedes-Benz', 'G-Class', 12, 4775.38);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (38, '63941-250', 19, 'Mazda', 'B-Series Plus', 12, 2874.9);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (39, '50730-8608', 19, 'Dodge', 'Ram 1500 Club', 0, 3721.63);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (40, '68113-107', 17, 'Ford', 'Probe', 12, 2813.06);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (41, '48951-8117', 19, 'Chevrolet', 'S10 Blazer', 12, 3430.7);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (42, '63868-960', 19, 'Subaru', 'Impreza', 0, 4047.99);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (43, '44924-132', 18, 'Chevrolet', 'Tahoe', 12, 3869.17);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (44, '0944-2822', 13, 'Volkswagen', 'New Beetle', 12, 4993.06);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (45, '36987-2050', 19, 'Pontiac', 'Sunbird', 12, 3127.54);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (46, '64578-0090', 18, 'Dodge', 'Avenger', 12, 4734.87);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (47, '65862-081', 18, 'Aston Martin', 'V12 Vantage', 0, 2637.84);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (48, '57237-104', 13, 'Jaguar', 'S-Type', 12, 4032.14);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (49, '36800-495', 12, 'Mercury', 'Grand Marquis', 0, 4511.24);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (50, '49288-0818', 20, 'Mitsubishi', 'Precis', 12, 4967.87);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (51, '57915-002', 17, 'Lexus', 'RX', 0, 4901.51);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (52, '58463-002', 17, 'Lexus', 'ES', 12, 4284.12);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (53, '0517-0032', 14, 'Mitsubishi', 'Eclipse', 0, 3803.86);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (54, '57237-058', 14, 'Audi', '80', 12, 3223.74);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (55, '55154-2675', 16, 'Volkswagen', 'Cabriolet', 0, 3245.15);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (56, '43857-0049', 18, 'Plymouth', 'Sundance', 0, 3233.6);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (57, '59011-752', 10, 'Chevrolet', 'Cavalier', 12, 4618.72);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (58, '42248-124', 10, 'Nissan', 'Frontier', 0, 2859.43);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (59, '54569-5800', 17, 'Ford', 'Tempo', 12, 4197.05);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (60, '42254-055', 13, 'Mercury', 'Lynx', 0, 3306.84);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (61, '0179-0060', 15, 'Infiniti', 'G', 0, 4231.16);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (62, '75862-003', 14, 'Ford', 'Aerostar', 12, 3728.94);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (63, '0363-9060', 18, 'Subaru', 'Justy', 0, 4860.41);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (64, '54473-188', 13, 'Mitsubishi', 'Galant', 12, 2446.59);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (65, '50102-130', 11, 'GMC', 'Savana 3500', 12, 4137.04);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (66, '62935-452', 10, 'Toyota', 'Land Cruiser', 12, 4365.62);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (67, '65923-104', 13, 'Mercedes-Benz', 'SL-Class', 12, 3404.58);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (68, '48951-6030', 15, 'Hyundai', 'Elantra', 12, 3197.6);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (69, '64376-630', 10, 'Saab', '9-3', 12, 4120.93);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (70, '41190-054', 19, 'Suzuki', 'Sidekick', 12, 3262.76);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (71, '47682-112', 15, 'Land Rover', 'Discovery', 0, 4279.29);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (72, '52244-040', 20, 'Mitsubishi', 'Eclipse', 0, 3307.67);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (73, '52125-209', 14, 'Nissan', 'Stanza', 12, 4833.75);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (74, '63187-101', 16, 'Saab', '9000', 12, 3421.02);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (75, '51079-896', 18, 'Mercedes-Benz', '500SEL', 0, 2711.7);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (76, '50844-157', 10, 'Jeep', 'Compass', 12, 4918.77);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (77, '49349-367', 19, 'Lincoln', 'Continental', 12, 3365.88);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (78, '66521-112', 14, 'Pontiac', 'Grand Prix', 12, 4153.76);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (79, '0187-5525', 13, 'Ford', 'Taurus', 12, 2840.13);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (80, '0093-1177', 10, 'Oldsmobile', 'Silhouette', 12, 3231.54);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (81, '54838-501', 16, 'Ford', 'Tempo', 12, 4048.61);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (82, '53603-2002', 18, 'Nissan', 'Altima', 12, 4463.83);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (83, '43419-362', 11, 'Jeep', 'Liberty', 12, 3178.54);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (84, '0472-0163', 14, 'Chevrolet', 'G-Series G20', 12, 2430.15);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (85, '55154-2353', 18, 'Dodge', 'Ram 1500', 0, 2734.5);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (86, '65003-007', 12, 'Aston Martin', 'DB9', 0, 2139.88);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (87, '43406-0002', 14, 'GMC', 'Savana 3500', 12, 3631.81);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (88, '68196-102', 20, 'Suzuki', 'X-90', 12, 3035.31);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (89, '35356-413', 12, 'Pontiac', 'Trans Sport', 12, 3179.63);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (90, '0603-2957', 14, 'Volkswagen', 'Cabriolet', 12, 2506.84);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (91, '42507-236', 16, 'Jeep', 'Comanche', 12, 2311.83);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (92, '69197-002', 16, 'Ford', 'E150', 0, 4093.63);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (93, '54879-002', 11, 'Toyota', 'Matrix', 12, 2433.92);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (94, '0378-0183', 15, 'Ford', 'Fiesta', 0, 2038.99);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (95, '16590-144', 14, 'Volkswagen', 'Type 2', 12, 4307.03);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (96, '0338-0687', 18, 'Dodge', 'Ram', 0, 3310.33);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (97, '61941-0042', 14, 'GMC', 'Canyon', 12, 4391.62);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (98, '67544-511', 13, 'Lotus', 'Elise', 12, 3352.37);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (99, '43742-0485', 10, 'Infiniti', 'G', 0, 2024.02);
insert into producto (id_producto, prod_codigo, prod_cantidad, prod_nombre, prod_descripcion, prod_iva, prod_pvpublico) values (100, '76472-1132', 12, 'Nissan', 'NV2500', 12, 3092.6);








<context-param>
		<param-name>primefaces.THEME</param-name>
		<param-value>nova-colored</param-value>
	</context-param>
	<context-param>
		<param-name>primefaces.FONT_AWESOME</param-name>
		<param-value>true</param-value>
</context-param>


CREATE TABLE proveedor (
 cod_proveedor VARCHAR(5) NOT NULL, 
 nombre VARCHAR(30) NOT NULL,
 contacto VARCHAR(20) NULL,
 ciudad VARCHAR(15) NULL,
 pais VARCHAR(15) NULL,
 direccion VARCHAR(30) NOT NULL,
 telefono VARCHAR(20) NULL,
 fax VARCHAR(20) NULL,
 e_mail VARCHAR(30) NULL, 
 celular VARCHAR(20) NULL
);

ALTER TABLE proveedor ADD PRIMARY KEY (cod_proveedor) ;
 
--rem *** TABLA PRODUCTOS O ITEMS
CREATE TABLE items (
 cod_item VARCHAR(5) NOT NULL, 
 descripcion VARCHAR(30) NOT NULL,
 marca VARCHAR(20) NOT NULL,
 precio_unitario NUMERIC(10,2) NOT NULL, 
 precio_distribuidor NUMERIC(10,2) NOT NULL,
 iva_sn CHAR(1) DEFAULT 'S' NOT NULL
 CHECK (iva_sn IN ('S', 'N')),
 stock NUMERIC(10,2) DEFAULT 0 NOT NULL
 CHECK (stock >= 0),
existencia_minima NUMERIC(10,2) DEFAULT 0 NOT NULL 
);

ALTER TABLE items
 ADD PRIMARY KEY (cod_item) ;
 
-- rem *** TABLA COMPRAS

CREATE TABLE compras (
 cod_compra VARCHAR(5) NOT NULL, 
 no_factura VARCHAR(5) NOT NULL, 
 fecha DATE NOT NULL,
 cod_proveedor VARCHAR(5) NULL, 
 comentario VARCHAR(20) NULL,
 descuento NUMERIC(4,2) DEFAULT 0 NOT NULL,
 iva NUMERIC(4,2) NOT NULL,
 saldo_pendiente NUMERIC(10,2) DEFAULT 0 NULL
);

ALTER TABLE compras
 ADD PRIMARY KEY (cod_compra)  ;
 
-- rem *** TABLA DETALLE_COMPRAS

CREATE TABLE detalle_compras (
 cod_compra VARCHAR(5) NOT NULL, 
 cod_item VARCHAR(5) NOT NULL, 
 cantidad NUMERIC(10,2) NOT NULL,
 CHECK (cantidad > 0),
 valor_unitario NUMERIC(10,2) NOT NULL
 CHECK (valor_unitario > 0)
);

ALTER TABLE detalle_compras ADD PRIMARY KEY (cod_compra, cod_item)  ;
ALTER TABLE detalle_compras ADD FOREIGN KEY (cod_compra) REFERENCES compras  ;
ALTER TABLE detalle_compras ADD FOREIGN KEY (cod_item) REFERENCES items ;
 
insert into proveedor values('00025','MegaSystems','Juan Perez','Ibarra','Ecuador','Sucre 3-26','9713-877','9993333','juan@gmail.com', '09995006001'); 
-- rem * Ingreso de un nuevo item a la BDD
insert into items values('00101','Mainboard M6VLQ','Biostar', 85.00, 70.00,'S', 4, 1);
insert into items values('00059','Teclado','MSI', 15.00, 10.00,'S', 14, 5);
insert into items values('00055','Mouse','MSI', 25.00, 20.00,'S', 20, 5); 
 
-- rem * Ingreso en la tabla compras
insert into compras values('00102','00006',TO_DATE('24/02/02','DD/MM/YY'),'00003','Cretido 2 meses',10,12,90);
 
-- rem * Ingreso en la tabla detalle_compras (Cuerpo de la compra)
insert into detalle_compras values('00102','00101',0005.00,60.00);
insert into detalle_compras values('00102','00059',00010.00,8.00);
insert into detalle_compras values('00102','00055',00021.00,15.00);


CREATE TABLE bienes_inmuebles(
codigo VARCHAR(10) NOT NULL,
	nombres_propietario VARCHAR(100) NOT NULL,
	correo_propietario VARCHAR(60) NOT NULL UNIQUE,
	descripcion VARCHAR(100) NOT NULL,
	area_dimension NUMERIC(8,2),
	fecha_compra DATE NOT NULL,
	CONSTRAINT pk_bieninmueble PRIMARY KEY(codigo)
);


recurso: mario-salazar-utn

database: bdmarioutn
server: server-marioutn

user: server-marioutn
pass: password-10utn
group: group-marioutn


hsalazar@utn.edu.ec
Contraseña: 








VBoxManage.exe setextradata "Windows 10" CustomVideoModel 1920x1080x32


















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








CREATE TABLE tipo_producto(
id_tipo_producto INTEGER NOT NULL,
	tp_nombre VARCHAR(50) NOT NULL,
	CONSTRAINT pk_tip_prod PRIMARY KEY(id_tipo_producto)
);

CREATE TABLE talla_producto(
id_talla_producto INTEGER NOT NULL,
	talla_nombre VARCHAR(15) NOT NULL,
	CONSTRAINT pk_talla PRIMARY KEY(id_talla_producto)
);

CREATE TABLE producto(
id_producto INTEGER NOT NULL,
	prod_codigo VARCHAR(15) NOT NULL UNIQUE,
	prod_cantidad INTEGER NOT NULL,
	prod_nombre VARCHAR(60) NOT NULL,
	prod_descripcion VARCHAR(100) NOT NULL,
	prod_proveedor INTEGER NOT NULL,
	prod_tipo_producto INTEGER NOT NULL,
	prod_pvproveedor NUMERIC(7,2) NOT NULL,
	prod_iva NUMERIC(4,2) NOT NULL,
	prod_pvpublico NUMERIC(7,2) NOT NULL,
	prod_imagen VARCHAR(100),
	prod_talla_producto INTEGER NOT NULL,
	CONSTRAINT pk_producto PRIMARY KEY(id_producto),
	CONSTRAINT fk_tp_prod FOREIGN KEY(prod_tipo_producto) REFERENCES tipo_producto(id_tipo_producto),
	CONSTRAINT fk_talla_prod FOREIGN KEY(prod_talla_producto) REFERENCES talla_producto(id_talla_producto)
);















CREATE TABLE producto(
id_producto VARCHAR(25) NOT NULL,
	prod_cantidad INTEGER NOT NULL,
	prod_marca VARCHAR(60) NOT NULL,
	prod_modelo VARCHAR(100) NOT NULL,
	prod_anio INTEGER NOT NULL,
	prod_pvproveedor NUMERIC(8,2) NOT NULL,
	prod_iva NUMERIC(8,2) NOT NULL,
	prod_pvpublico NUMERIC(8,2) NOT NULL,
	CONSTRAINT pk_producto PRIMARY KEY(id_producto)
);
CREATE TABLE cliente(
id_cliente VARCHAR(15) NOT NULL,
	cli_nombres VARCHAR(60) NOT NULL,
	cli_apellidos VARCHAR(60) NOT NULL,
	cli_telefono VARCHAR(15) NOT NULL,
	cli_email VARCHAR(60) NOT NULL UNIQUE,
	cli_direccion VARCHAR(100) NOT NULL,
	CONSTRAINT pk_cliente PRIMARY KEY(id_cliente)
);

CREATE TABLE factura(
id_factura INTEGER NOT NULL IDENTITY,
	fact_fecha_emision DATE NOT NULL,
	fact_fecha_remision DATE NOT NULL,
	fact_descuento NUMERIC(4,2),
	fact_subtotal NUMERIC(8,2),
	fact_iva NUMERIC(8,2),
	fact_total NUMERIC(8,2),
	fact_cliente VARCHAR(15) NOT NULL,
	CONSTRAINT pk_factura PRIMARY KEY(id_factura),
	CONSTRAINT fk_cli_fact FOREIGN KEY(fact_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE detalle_factura(
id_detalle_factura INTEGER NOT NULL IDENTITY,
	det_cantidad INTEGER NOT NULL,
	det_producto VARCHAR(25) NOT NULL,
	det_factura INTEGER NOT NULL,
	det_precio_unitario NUMERIC(8,2) NOT NULL,
	det_iva NUMERIC(8,2) NOT NULL,
	det_subtotal NUMERIC(8,2),
	det_total NUMERIC(8,2) NOT NULL,
	CONSTRAINT pk_det_fact PRIMARY KEY(id_detalle_factura),
	CONSTRAINT fk_prod_detf FOREIGN KEY(det_producto) REFERENCES producto(id_producto),
	CONSTRAINT fk_fact_detf FOREIGN KEY(det_factura) REFERENCES factura(id_factura)
);

CREATE TABLE pedido(
id_pedido INTEGER NOT NULL IDENTITY,
	ped_cantidad INTEGER NOT NULL,
	ped_producto VARCHAR(25) NOT NULL,
	ped_cliente VARCHAR(15) NOT NULL,
	ped_fecha DATE NOT NULL,
	ped_estado INTEGER,
	CONSTRAINT pk_ped PRIMARY KEY(id_pedido),
	CONSTRAINT fk_prod_ped FOREIGN KEY(ped_producto) REFERENCES producto(id_producto),
	CONSTRAINT fk_cli_ped FOREIGN KEY(ped_cliente) REFERENCES cliente(id_cliente)
);


insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('WAUDF98E87A367718', 23, 'Honda', 'S2000', 2021, 12832.04, 12.0, 20146.3028);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('5TFBW5F12CX871269', 16, 'GMC', 'Sonoma', 2020, 22867.41, 12.0, 35901.8337);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('1G4HP54K914292969', 21, 'Subaru', 'Baja', 2006, 15831.53, 12.0, 24855.5021);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('JN8AF5MR7DT692288', 23, 'Chevrolet', 'Monte Carlo', 2018, 20652.89, 12.0, 32425.0373);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('1J4PN2GK0AW368948', 16, 'Toyota', 'Corolla', 2020, 8017.85, 12.0, 12588.0245);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('3GYFK66N35G576138', 17, 'Ford', 'F150', 2014, 7280.57, 12.0, 11430.4949);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('JN1BJ0HP8DM187383', 20, 'Subaru', 'Impreza', 2015, 15819.66, 12.0, 24836.8662);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('WBAKA4C53BC098427', 18, 'Jaguar', 'XF', 2021, 11020.26, 12.0, 17301.8082);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('3VWJX7AJ1AM023153', 22, 'Toyota', 'Land Cruiser', 2005, 18189.85, 12.0, 28558.0645);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('WAUGFAFR3DA036903', 18, 'Ford', 'Econoline E150', 2009, 10592.05, 12.0, 16629.5185);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('WA1LMBFE2FD631005', 18, 'Mercedes-Benz', 'CL-Class', 2020, 18162.32, 12.0, 28514.8424);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('5BZBF0AA3FN091081', 21, 'Toyota', 'T100 Xtra', 2006, 20981.29, 12.0, 32940.6253);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('1G6KF57943U148337', 24, 'Hyundai', 'Elantra', 2006, 8969.91, 12.0, 14082.7587);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('WAUHGAFCXEN851798', 16, 'Infiniti', 'G37', 2012, 10832.93, 12.0, 17007.7001);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('1FBNE3BL3AD598239', 19, 'Lincoln', 'Navigator', 2013, 24095.64, 12.0, 37830.1548);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('1FTEX1C82AF255334', 15, 'Chrysler', '300', 2014, 22675.05, 12.0, 35599.8285);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('WAUK2AFDXFN553962', 23, 'Volvo', 'S80', 2006, 10798.86, 12.0, 16954.2102);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('WBA3A5G54FN651049', 16, 'Volkswagen', 'Jetta', 2010, 22739.22, 12.0, 35700.5754);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('NM0KS9BN3BT109194', 19, 'GMC', '3500', 2016, 17228.38, 12.0, 27048.5566);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('JTDZN3EU1FJ699034', 24, 'Audi', 'A4', 2014, 12252.72, 12.0, 19236.7704);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('1G6DJ5EV1A0230926', 22, 'GMC', 'Savana 2500', 2013, 19561.27, 12.0, 30711.1939);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('1N6AA0ECXDN725857', 18, 'Acura', 'TSX', 2008, 16975.29, 12.0, 26651.2053);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('2C4RDGCG6CR531842', 21, 'BMW', '6 Series', 2012, 19699.13, 12.0, 30927.6341);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('4T1BF1FK5EU081448', 19, 'Dodge', 'Ram 2500', 2010, 22381.89, 12.0, 35139.5673);
insert into producto (id_producto, prod_cantidad, prod_marca, prod_modelo, prod_anio, prod_pvproveedor, prod_iva, prod_pvpublico) values ('1GTN1TEH8FZ073768', 18, 'Chevrolet', 'Express 2500', 2009, 13091.56, 12.0, 20553.7492);


insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('5647809823', 'Karlene', 'Jochanany', '(171) 8452129', 'kjochanany0@prweb.com', '3 Laurel Lane');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('7299551463', 'Phip', 'Saban', '(574) 9868629', 'psaban1@spotify.com', '41001 Bellgrove Avenue');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('4482638502', 'Fonsie', 'Dislee', '(990) 2014667', 'fdislee2@ibm.com', '450 Stoughton Way');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('4726372946', 'Martguerita', 'Winship', '(326) 3081844', 'mwinship3@time.com', '7781 Veith Lane');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('4693749401', 'Faber', 'Corwin', '(575) 3191925', 'fcorwin4@t-online.de', '39945 Anzinger Junction');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('9064162977', 'Shandra', 'Guiel', '(915) 1235031', 'sguiel5@cdbaby.com', '9 Prentice Park');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('2440599824', 'Laureen', 'Clinning', '(416) 6507348', 'lclinning6@dyndns.org', '5 Pawling Junction');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('9574905431', 'Carlina', 'Antecki', '(442) 7933640', 'cantecki7@census.gov', '0 Knutson Plaza');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('6357946843', 'Avivah', 'Caple', '(563) 5900860', 'acaple8@adobe.com', '3957 Warner Center');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('6939678309', 'Hetti', 'Adrienne', '(602) 9664820', 'hadrienne9@google.de', '5327 Raven Park');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('9715122831', 'Floria', 'Danilewicz', '(579) 7272135', 'fdanilewicza@latimes.com', '28004 Declaration Pass');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('7141634964', 'Sibby', 'Spratt', '(495) 8358060', 'ssprattb@vkontakte.ru', '8 Randy Junction');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('8571070498', 'Nickie', 'Stonhouse', '(586) 5549166', 'nstonhousec@gizmodo.com', '406 Schmedeman Alley');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('8338284761', 'Anthia', 'Tallach', '(565) 4423085', 'atallachd@blogs.com', '65007 Prentice Avenue');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('3429341128', 'Odette', 'Gabits', '(527) 5398827', 'ogabitse@paypal.com', '9 Arkansas Terrace');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('6805527861', 'Hastie', 'Sorey', '(317) 3294898', 'hsoreyf@google.com.au', '04667 Debs Court');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('1676048567', 'Karoly', 'Mundford', '(783) 9780635', 'kmundfordg@facebook.com', '316 Charing Cross Circle');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('8124641265', 'Norrie', 'Leys', '(171) 2727859', 'nleysh@netvibes.com', '32 Del Sol Junction');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('2618661489', 'Estrellita', 'Lundberg', '(619) 1142036', 'elundbergi@yandex.ru', '865 Stang Trail');
insert into cliente (id_cliente, cli_nombres, cli_apellidos, cli_telefono, cli_email, cli_direccion) values ('2110928514', 'Bertha', 'Allsupp', '(853) 5319613', 'ballsuppj@jigsy.com', '709 Mallory Circle');



select f.id_factura, f.fact_numero_factura ,f.fact_fecha_emision ,f.fact_tipo_factura ,
tf.id_tipo_factura ,tf.tf_nombre ,p.id_producto ,p.prod_codigo ,p.prod_nombre ,p.prod_talla_producto ,
tp.id_talla_producto ,tp.talla_nombre ,tip.id_tipo_producto ,tip.tp_nombre ,
df.id_detalle_factura ,df.det_cantidad ,df.det_precio_unitario ,df.det_iva ,df.det_subtotal ,df.det_precio_total ,
cf.id_cabecera_factura ,
c.id_cliente ,c.cli_telefono ,c.cli_email ,c.cli_ruc_cedula , uc.id_usuario ,uc.us_nombres ||' ' ||uc.us_apellidos as nombre_cliente,
v.id_vendedor ,v.ven_telefono ,v.ven_email ,v.ven_cedula ,uv.id_usuario ,uv.us_nombres ||' ' ||uv.us_apellidos as nombre_vendedor
from factura f ,tipo_factura tf , producto p ,detalle_factura df ,cliente c , vendedor v , 
usuario uc, usuario uv, cabecera_factura cf , talla_producto tp , tipo_producto tip
where f.id_factura = df.det_factura and tf.id_tipo_factura = f.fact_tipo_factura and p.id_producto = df.det_producto 
and c.id_cliente = cf.cab_cliente and c.cli_usuario = uc.id_usuario and v.id_vendedor = cf.cab_vendedor 
and v.ven_usuario = uv.id_usuario and f.fact_cabecera_factura = cf.id_cabecera_factura 
and p.prod_tipo_producto = tip.id_tipo_producto and p.prod_talla_producto = tp.id_talla_producto 
and p.prod_codigo = 'RIn-2288';


select f.id_factura, f.fact_numero_factura ,f.fact_fecha_emision ,f.fact_tipo_factura ,
tf.id_tipo_factura ,tf.tf_nombre ,p.id_producto ,p.prod_codigo ,p.prod_nombre ,p.prod_talla_producto ,
tp.id_talla_producto ,tp.talla_nombre ,tip.id_tipo_producto ,tip.tp_nombre ,
df.id_detalle_factura ,df.det_cantidad ,df.det_precio_unitario ,df.det_iva ,df.det_subtotal ,df.det_precio_total ,
cf.id_cabecera_factura ,
c.id_cliente ,c.cli_telefono ,c.cli_email ,c.cli_ruc_cedula , uc.id_usuario ,uc.us_nombres ||' ' ||uc.us_apellidos as nombre_cliente,
v.id_vendedor ,v.ven_telefono ,v.ven_email ,v.ven_cedula ,uv.id_usuario ,uv.us_nombres ||' ' ||uv.us_apellidos as nombre_vendedor
from factura f ,tipo_factura tf , producto p ,detalle_factura df ,cliente c , vendedor v , 
usuario uc, usuario uv, cabecera_factura cf , talla_producto tp , tipo_producto tip
where f.id_factura = df.det_factura and tf.id_tipo_factura = f.fact_tipo_factura and p.id_producto = df.det_producto 
and c.id_cliente = cf.cab_cliente and c.cli_usuario = uc.id_usuario and v.id_vendedor = cf.cab_vendedor 
and v.ven_usuario = uv.id_usuario and f.fact_cabecera_factura = cf.id_cabecera_factura 
and p.prod_tipo_producto = tip.id_tipo_producto and p.prod_talla_producto = tp.id_talla_producto 
and c.cli_ruc_cedula = '1003938477';

